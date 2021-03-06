package cn.edu.csu.dbhospital.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cn.edu.csu.dbhospital.db.TWorkerManager;
import cn.edu.csu.dbhospital.pojo.TWorker;
import cn.edu.csu.dbhospital.util.SystemUtil;

public class TWorkerAddDialog extends TitleAreaDialog {
	private Text txt_realname;
	private Text txt_name;
	private Text txt_phone;
	private Combo cb_gander;
	private Text txt_pwd;
	private Button btn_modify;
	private Button btn_statics;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public TWorkerAddDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("\u6DFB\u52A0\u5DE5\u4F5C\u4EBA\u5458\u4FE1\u606F");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FormLayout());
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.widthHint = 341;
		container.setLayoutData(gd_container);

		Label label = new Label(container, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 26);
		label.setLayoutData(fd_label);
		label.setText("\u5458\u5DE5\u59D3\u540D");

		txt_realname = new Text(container, SWT.BORDER);
		fd_label.right = new FormAttachment(txt_realname, -18);
		FormData fd_txt_realname = new FormData();
		fd_txt_realname.top = new FormAttachment(label, -3, SWT.TOP);
		fd_txt_realname.left = new FormAttachment(0, 98);
		fd_txt_realname.right = new FormAttachment(100, -36);
		txt_realname.setLayoutData(fd_txt_realname);

		Label label_2 = new Label(container, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(label, 25);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("\u5458\u5DE5\u6027\u522B");

		cb_gander = new Combo(container, SWT.NONE);
		fd_label_2.right = new FormAttachment(cb_gander, -18);
		FormData fd_cb_gander = new FormData();
		fd_cb_gander.right = new FormAttachment(0, 161);
		fd_cb_gander.top = new FormAttachment(txt_realname, 18);
		fd_cb_gander.left = new FormAttachment(0, 98);
		cb_gander.setLayoutData(fd_cb_gander);
		cb_gander.add(SystemUtil.GANSER_MALE);
		cb_gander.add(SystemUtil.GANSER_FEMALE);
		cb_gander.setData(SystemUtil.GANSER_FEMALE, SystemUtil.GANSER_FEMALE_VALUE);
		cb_gander.setData(SystemUtil.GANSER_MALE, SystemUtil.GANSER_MALE_VALUE);
		cb_gander.setText(SystemUtil.GANSER_MALE);

		Label label_4 = new Label(container, SWT.NONE);
		FormData fd_label_4 = new FormData();
		fd_label_4.right = new FormAttachment(txt_realname, -223, SWT.RIGHT);
		fd_label_4.top = new FormAttachment(cb_gander, 25);
		fd_label_4.left = new FormAttachment(label, 0, SWT.LEFT);
		label_4.setLayoutData(fd_label_4);
		label_4.setText("\u6CE8\u518C\u8D26\u53F7");

		txt_name = new Text(container, SWT.BORDER);
		FormData fd_txt_name = new FormData();
		fd_txt_name.top = new FormAttachment(cb_gander, 22);
		fd_txt_name.right = new FormAttachment(txt_realname, 0, SWT.RIGHT);
		fd_txt_name.left = new FormAttachment(0, 98);
		txt_name.setLayoutData(fd_txt_name);

		Label label_1 = new Label(container, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(label_4, 24);
		fd_label_1.left = new FormAttachment(label, 0, SWT.LEFT);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("\u6CE8\u518C\u5BC6\u7801");

		txt_pwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
		FormData fd_txt_pwd = new FormData();
		fd_txt_pwd.top = new FormAttachment(cb_gander, 63);
		fd_txt_pwd.left = new FormAttachment(txt_realname, 0, SWT.LEFT);
		fd_txt_pwd.right = new FormAttachment(txt_realname, 0, SWT.RIGHT);
		txt_pwd.setLayoutData(fd_txt_pwd);

		Label label_3 = new Label(container, SWT.NONE);
		FormData fd_label_3 = new FormData();
		fd_label_3.left = new FormAttachment(label, 0, SWT.LEFT);
		label_3.setLayoutData(fd_label_3);
		label_3.setText("\u8054\u7CFB\u65B9\u5F0F");
		fd_label_3.top = new FormAttachment(label_1, 25);

		txt_phone = new Text(container, SWT.BORDER);
		FormData fd_txt_phone = new FormData();
		fd_txt_phone.top = new FormAttachment(label_3, -3, SWT.TOP);
		fd_txt_phone.left = new FormAttachment(txt_realname, 0, SWT.LEFT);
		fd_txt_phone.right = new FormAttachment(txt_realname, 0, SWT.RIGHT);
		txt_phone.setLayoutData(fd_txt_phone);

		btn_modify = new Button(container, SWT.CHECK);
		FormData fd_btn_modify = new FormData();
		fd_btn_modify.bottom = new FormAttachment(100, -10);
		fd_btn_modify.left = new FormAttachment(label, 0, SWT.LEFT);
		btn_modify.setLayoutData(fd_btn_modify);
		btn_modify.setText("\u53EF\u4EE5\u4FEE\u6539\u7528\u6237\u4FE1\u606F");

		btn_statics = new Button(container, SWT.CHECK);
		FormData fd_btn_statics = new FormData();
		fd_btn_statics.top = new FormAttachment(btn_modify, 0, SWT.TOP);
		fd_btn_statics.right = new FormAttachment(txt_realname, 0, SWT.RIGHT);
		btn_statics.setLayoutData(fd_btn_statics);
		btn_statics.setText("\u53EF\u4EE5\u8FDB\u884C\u8D39\u7528\u7684\u6570\u636E\u7EDF\u8BA1");

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, "\u4FEE\u6539", true);
		button.setText("\u6DFB\u52A0");
		createButton(parent, IDialogConstants.CANCEL_ID, "\u53D6\u6D88", false);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			if ("".equalsIgnoreCase(txt_realname.getText()) || "".equalsIgnoreCase(txt_pwd.getText())
					|| "".equalsIgnoreCase(txt_name.getText()) || "".equalsIgnoreCase(txt_phone.getText())
					|| "".equalsIgnoreCase(cb_gander.getText())) {
				setMessage("所有信息都不能为空");
				return;
			}
			TWorkerManager manager = new TWorkerManager();
			TWorker worker = new TWorker();
			worker.setRealname(txt_realname.getText());
			worker.setName(txt_name.getText());
			worker.setGander((int) (cb_gander.getData(cb_gander.getText())));
			worker.setPassword(txt_pwd.getText());
			worker.setPhone(txt_phone.getText());
			if (btn_modify.getSelection()) {
				worker.setCanModify(SystemUtil.RIGHT_YES);
			} else {
				worker.setCanModify(SystemUtil.RIGHT_NO);
			}
			if (btn_statics.getSelection()) {
				worker.setCanStatics(SystemUtil.RIGHT_YES);
			} else {
				worker.setCanStatics(SystemUtil.RIGHT_NO);
			}
			try {
				manager.add(worker);
			} catch (Exception e) {
				setReturnCode(SystemUtil.RESULT_FAIL);
				close();
			}
			okPressed();
		} else {
			cancelPressed();
		}
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(350, 430);
	}
}
