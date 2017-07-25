package one.hardware.Testcase;
import java.io.File;
import java.util.HashSet;
import java.util.logging.Logger;

import com.android.uiautomator.core.UiObject;
import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Action.FileManagerAction;
import one.hardware.Util.Base;

public class SettingCase extends Base{
	private static Logger logger=Logger.getLogger(LiveCase.class.getName());
	public void testSetDisplayTimeNever() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");						
			common.initDevice();
			
			device.pressBack();
			device.pressBack();			
			device.pressMenu();			
			device.waitForWindowUpdate("com.android.settings", 5000);
			common.clickViewByText("Device");
			common.ScrollViewByText("Display");
			common.clickViewByText("Display");
			common.clickViewByText("Sleep");
			common.clickViewByText("Never");		
			device.pressBack();
			device.pressBack();
			device.pressBack();
		
			common.clickViewByText("Connection");
			common.clickViewByText("Wi-Fi");
			sleep(3000);
			UiObject addNewNetWork = common.findViewByText2("Add new network...");
			if (addNewNetWork.exists()) {
			    common.clickViewById("com.mediatek:id/imageswitch");
				addNewNetWork.waitUntilGone(10000);
			}
			common.ScrollViewByText("CKT");
			common.clickViewByText("CKT");
			
			if(common.findViewByText2("Forget").exists()){
				common.clickViewByText("Forget");
				common.waitTime(10);
				common.ScrollViewByText2("CKT");
				common.clickViewByText("CKT");
			}
				UiObject passwd = common.findViewById2("com.android.settings:id/password");
			if (passwd!=null&&passwd.exists()) {
					passwd.setText("ck88888!");
					common.clickViewByText("Connect");
			    }else if (common.findViewByText2("Connect").exists()) {
						common.clickViewByText("Connect");
						common.waitTime(10);			
					}
			//验证wifi是否连接成功
				common.ScrollViewByText2("CKT");
				common.clickViewByText("CKT");
			if(common.findViewByText("CKT").exists()&&common.findViewByText("Forget").exists()){
					logger.info("CKT已经连接成功！");
					common.passcase();
				}else{
					logger.info("CKT未成功连接");
					common.takeScreen("wifi连接错误！");
					common.failcase(runcase);
			}
			device.pressBack();
			device.pressBack();
			common.clickViewByText("Wi-Fi");
			common.clickViewById("com.mediatek:id/imageswitch");//最终关闭wifi		
			common.startLog( "*****End to run " + runcase + " *****");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", " one.hardware.Testcase.SettingCase", "", "2");
	}
}
