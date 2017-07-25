package one.hardware.Testcase;
/*
 * 慢速录像过程中，连续亮灭屏
 */

import com.ckt.demo.UiAutomatorHelper;
import android.view.KeyEvent;
import one.hardware.Action.CameraAction;
import one.hardware.Util.Base;

public class Video_Slo_Mo_Screen_onoff extends Base {
	/*
	 * 超宽视角，慢速录像连续亮灭屏
	 */
	
	public void testAlo_Mo_SuperWide_Screen_onoff() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run  + runcase +  *****");

			common.device.wakeUp();
			common.initDevice();
			common.startCamera();
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[4]);
			CameraAction.configSlomoAngle(one.hardware.Page.Camera.video_Angle[0]);
			common.startLog("*****慢速录像选择");

			common.cameraKey();
			sleep(3000);
			for(int i= 1;i<11;i++){
				device.pressKeyCode(KeyEvent.KEYCODE_POWER);
				sleep(3000);
			}

			common.cameraKey();

		}catch (Exception e) {
			common.handleException(e.getMessage());
		}
	}
	
	/*
	 * 宽视角，慢速录像连续亮灭屏
	 */
	
	public void testAlo_Mo_Wide_Screen_onoff() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run  + runcase +  *****");

			common.device.wakeUp();
			common.initDevice();
			common.startCamera();
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[4]);
			CameraAction.configSlomoAngle(one.hardware.Page.Camera.video_Angle[1]);
			common.startLog("*****慢速录像选择");

			common.cameraKey();
			sleep(3000);
			for(int i= 1;i<11;i++){
				device.pressKeyCode(KeyEvent.KEYCODE_POWER);
				sleep(3000);
			}

			common.cameraKey();

		}catch (Exception e) {
			common.handleException(e.getMessage());
		}
	}
	
	/*
	 * 普通视角，慢速录像连续亮灭屏
	 */
	
	public void testAlo_Mo_Medium_Screen_onoff() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run  + runcase +  *****");

			common.device.wakeUp();
			common.initDevice();
			common.startCamera();
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[4]);
			CameraAction.configSlomoAngle(one.hardware.Page.Camera.video_Angle[2]);
			common.startLog("*****慢速录像选择");

			common.cameraKey();
			sleep(3000);
			for(int i= 1;i<11;i++){
				device.pressKeyCode(KeyEvent.KEYCODE_POWER);
				sleep(3000);
			}

			common.cameraKey();

		}catch (Exception e) {
			common.handleException(e.getMessage());
		}
	}
	
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.Video_Slo_Mo_Screen_onoff", "testAlo_Mo_Screen_onoff", "4");
	}

}













