package cn.edu.csu.dbhospital;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.util.PrefUtil;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(900, 700));
		// 设置菜单栏
		configurer.setShowMenuBar(true);
		// 设置工具栏
		configurer.setShowCoolBar(true);
		// 设置状态线
		configurer.setShowStatusLine(true);
		// 设置标题
		configurer.setTitle("妙手医院挂号系统");

		// 定制应用程序的外观
		IPreferenceStore preferenceStore = PrefUtil.getAPIPreferenceStore();
		// 设置选项卡的样式，不是矩形的边框，而是弧形的
		preferenceStore.setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
		// 设置透视图按钮的位置，默认是左上角，改为放置在右上角
		preferenceStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR,
				IWorkbenchPreferenceConstants.TOP_RIGHT);
	}

}
