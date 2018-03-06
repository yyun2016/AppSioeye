package one.hardware.Testcase;
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
/*
 * a.普通录像模式
 * 1.修改视频质量为720@60FPS
 * 2.修改视角为Super Wide
 * 3.修改上下颠倒为auto
 * 4.切换到延时录像模式后再切换为普通录像
 * b.所有设置项都修改成功，更改的设置项都没有改变
 */
import com.ckt.demo.UiAutomatorHelper;

import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import one.hardware.Action.CameraAction;
import one.hardware.Util.Base;

/*
 * 普通录像设置
 * 1、普通录像设置为720@60、超宽视场角，打开video
 * 2、切换到延时录像，通过3s来判断
 * 3、再切换到普通录像，通过720@60来判断
 * 
 */
public class VideoSettings extends Base{
	String quality = one.hardware.Page.Camera.video_quality[4];  //视频质量720@60
	String angle =one.hardware.Page.Camera.video_Angle[0];	 //超宽视场角
	int flag1 = 0;
	int flag2 = 0;
	public void testVideoSettings() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			if(!device.isScreenOn()){
				device.pressKeyCode(KeyEvent.KEYCODE_POWER);
				common.infoLog("点击POWER使屏幕点亮");
			}
			common.initDevice();
			common.pmclear();
			common.startCamera();
			CameraAction.configVideoQuality(quality);		//切换到video模式，并设置视频质量	
			CameraAction.configVideoAngle(angle);			//切换到Video模式，并设置视频视场角	
			
			//打开Auto
			sleep(500);
			common.infoLog("即将进入setting");
			CameraAction.cameraSetting();
			sleep(500);
			common.infoLog("即将找auto");
		    CameraAction.openCompoundButton("Auto");   //打开auto开关
			device.pressBack();
			
			
			
			//切换到延时录像再切换到普通录像
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);  //切换到延时录像
			common.infoLog("切换到延时录像");
			if(common.findViewByText2("10s").exists()){
				//通过检查当前页面是否有10s存在，若存在，则正确，并记录flag1=1即true
				flag1 = 1;
			}else{
				flag1 = 0;
				common.failcase("切换到延时录像失败");
			}
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[1]);
			common.infoLog("切换到普通录像");
			if(common.findViewByText2("720@60").exists()){
				flag2 = 1;
			}else{
				flag2 = 0;
				common.failcase("切换到普通录像失败");
			}
			common.infoLog("flag2="+flag2);			
			if(flag1==1 && flag2 ==1){
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO: handle exception
			common.handleException(e.getMessage());
		}
	}
	public static void main(String[] args){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.VideoSettings", "", "1");
	}
}
