package com.yj.smarthome.composite;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.ImageKeyManager;
import org.eclipse.wb.swt.ResourceManager;

import com.yj.smarthome.Activator;
import com.yj.smarthome.beans.Appliance;
import com.yj.smarthome.beans.ApplianceCommand;
import com.yj.smarthome.dialog.RenameDialog;
import com.yj.smarthome.interfaces.IResponse;
import com.yj.smarthome.util.ProtocolUtil;
import com.yj.smarthome.util.SystemUtil;
import com.yj.smarthome.util.XmlUtil;
import com.yj.smarthome.xmlImpls.ApplianceControlXmlImpl;

/**
 * 家电控制组件
 * 
 * @author yinger
 * 
 */
public class ApplianceControlComposite extends Composite implements IResponse {

	private Appliance appliance;
	private Canvas canvas_state;
	private Image image;
	private Composite composite_control;

	public ApplianceControlComposite(Composite parent, int style, Appliance appliance) {
		super(parent, SWT.NONE);
		setAppliance(appliance);
		//设置初始状态
		appliance.setState(SystemUtil.ALL_DEVICE_STATE[appliance.DeviceType_ID][appliance.getId()]);

		//首先设置一下当前灯的状态，即图片
		if (this.appliance.getState() == Appliance.STATE_ON) {
			image = ResourceManager.getPluginImage(Activator.PLUGIN_ID, ImageKeyManager.APPLIANCE_ON);
		} else {
			image = ResourceManager.getPluginImage(Activator.PLUGIN_ID, ImageKeyManager.APPLIANCE_OFF);
		}
		initUI();
		makeButtons();
		//修改按钮的状态
//		changeButtonState(appliance.getState());
	}

	//根据家电内容生成按钮
	private void makeButtons() {
		List<ApplianceCommand> commands = appliance.getCommands();
		for (int i = 0, size = commands.size(); i < size; i++) {
			System.out.println(commands.get(i).getName());
			final Button button = new Button(composite_control, SWT.NONE);
			button.setText(commands.get(i).getName());
			button.setSize(70, 24);
//			button.setData("id", commands.get(i).getId());
			button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			button.setData("command", commands.get(i).getCommand());
//			button.setBounds(10, 20 + 40 * i, 80, 30);//设置按钮的位置
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {//发送命令
					sendCommand(Integer.parseInt((String) button.getData("command")));
				}
			});
		}
		composite_control.layout();//这一句一定要有，否则button是显示不出来的 
	}

	//初始化界面
	private void initUI() {
		setLayout(null);
		final Group group_control = new Group(this, SWT.NONE);
		group_control.setBounds(20, 10, 365, 126);
//		group_control.setText("\u5BB6\u7535\u63A7\u5236");
		group_control.setText(appliance.getClientName());

		canvas_state = new Canvas(group_control, SWT.DOUBLE_BUFFERED);
		canvas_state.setBounds(22, 34, 86, 63);
		canvas_state.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, canvas_state.getBounds().width,
						canvas_state.getBounds().height);
			}
		});

		Button button_rename = new Button(group_control, SWT.NONE);
		button_rename.setImage(ResourceManager.getPluginImage(Activator.PLUGIN_ID, ImageKeyManager.RENAME_ICON));
		button_rename.setBounds(130, 24, 84, 24);
		button_rename.setText("\u4FEE\u6539\u540D\u79F0");
		button_rename.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {//修改名称
				String oldName = appliance.getClientName();
				RenameDialog renameDialog = new RenameDialog(getShell(), appliance);
				renameDialog.open();
				String newName = appliance.getClientName();
				if (!newName.equals(oldName)) {
					//第一步要把这个名称的修改保存到xml文件中
					try {
//						XmlUtil.renameappliance(appliance, ProtocolUtil.DeviceType_Door);
						XmlUtil.changeDeviceName(ApplianceControlXmlImpl.getInstance(), appliance);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					//第二步才是修改UI显示，因为文件中修改成功了才算成功
					group_control.setText(appliance.getClientName());
				}
			}
		});

		composite_control = new Composite(group_control, SWT.NONE);
		composite_control.setBounds(125, 54, 230, 63);
		composite_control.setLayout(new GridLayout(3, true));
	}

	public Appliance getAppliance() {
		return appliance;
	}

	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}

	@Override
	//这里很重要，用于处理反馈信息的方法
	public void dealWithResponse(byte[] response) {
		//首先检测是否是网关故障
		byte state = response[8];
//		if (state == ProtocolUtil.Gateway_Error) {
//			label_state.setText("网关故障！");
//			return;
//		} else if (state == ProtocolUtil.Instruction_Ok) {
//			label_state.setText("发送成功！");
//		} else if (state == ProtocolUtil.Instruction_Fail) {
//			label_state.setText("发送失败！");
//			return;
//		}
		//只有发送成功了才进行修改！
		//接着得到是什么命令
		boolean lightState = false;
		byte[] bytes = { 0, 0, 0, response[7] };
		int command = ProtocolUtil.convertByteArrayToInt(bytes);
		if (command == Appliance.COMMAND_ON) {//如果是开命令
			lightState = true;
		}
		//最后更改显示状态
		if (lightState) {
			image = ResourceManager.getPluginImage(Activator.PLUGIN_ID, ImageKeyManager.APPLIANCE_ON);
			appliance.setState(Appliance.STATE_ON);
		} else {
			image = ResourceManager.getPluginImage(Activator.PLUGIN_ID, ImageKeyManager.APPLIANCE_OFF);
			appliance.setState(Appliance.STATE_OFF);
		}
		canvas_state.redraw();
//		changeButtonState(appliance.getState());
		//保存到文件中！
		try {
//			XmlUtil.saveapplianceState(appliance);
			XmlUtil.changeDeviceState(ApplianceControlXmlImpl.getInstance(), appliance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendCommand(int command) {
		if (command == ProtocolUtil.NO_COMMAND) {
			return;
		}
		//首先得到要传送的数据
		byte[] data = ProtocolUtil.packInstructionRequest(command);
		//然后将数据传给通信类
		try {
			SystemUtil.communication.sendData(data);
		} catch (Exception e1) {
			e1.printStackTrace();
			//提示错误
		}
	}
}
