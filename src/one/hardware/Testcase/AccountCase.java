package one.hardware.Testcase;

import com.android.uiautomator.core.UiDevice;
import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.AccountAction;
import one.hardware.Action.CameraAction;
import one.hardware.Util.Base;

public class AccountCase extends Base{
	public void testLogin() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.pmclear();
			common.startCamera();
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[0]);
			CameraAction.cameraSetting();
			common.ScrollViewByText("Live&Save");
			CameraAction.openCompoundButton("Live&Save");
			common.waitTime(2);
	        if (common.findViewByText2("OK").exists()) {
	            common.clickViewByText("OK");
	        }
	        UiDevice.getInstance().pressBack();
	        common.waitTime(1);
			common.ScrollViewByText("Account");
			common.clickViewByText("Account");
			AccountAction.loginAccount("eyes", "eye1321");
			boolean login = one.hardware.Action.AccountAction.isLoginSuccess();
			if (login) {
				common.infoLog(" 账号登陆成功");
				common.passcase();
			}else {
				common.infoLog(" 账号登陆失败");
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public static void main(String args[]){
//		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.AccountCase", "", "2");
		//new UiAutomatorHelper("AppSioeye", " one.test.ImageTestCase", "", "2");
//		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.BurstDownToUp", "testBurstDownToUp", "2");
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.BurstCase", "", "2");
	}
}
