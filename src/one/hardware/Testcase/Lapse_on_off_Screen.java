package one.hardware.Testcase;

/**
 * 延时录像过程中，多次灭亮屏对屏幕无影响（试验6次）
 * */
import java.util.HashSet;

import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Action.VideoNode;
import one.hardware.Util.Base;
import one.hardware.Action.FileManagerAction;

import java.io.File;


public class Lapse_on_off_Screen extends Base {
	public void testLapse_on_off_Screen() throws Exception {
		try{
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();         			
			common.startCamera();
			String lapsePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				lapsePath="/storage/sdcard1/Video";
			}else {
				lapsePath="/storage/sdcard0/Video";
			}
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);  

			HashSet<String> beforeTakeVideoList = common.FileList(lapsePath);   	
			common.cameraKey();               																									
			common.waitTime(3);
			for(int n=0;n<=5;n++){
				UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);//按电源键6次
				common.infoLog("launchPowerKey"+(n+1)+"次");
				common.waitTime(3);
			}					
			common.cameraKey();
			sleep(1500);
			HashSet<String> afterTakeVideoList = common.FileList(lapsePath);   				 
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList); 
			
			if (resultHashSet.size()==1) {  						 
				String videopath = resultHashSet.iterator().next();  
				common.infoLog("new file:"+videopath);       		 
				String videoName = new File(videopath).getName();   
				VideoNode videoNode = common.VideoInfo(videopath);   
				FileManagerAction.playVideoByFileManager(videoName); 
				if(videoNode.getDuration()<120){                    
					fail("max duration is 120 seconds");
				}
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			
			common.backToHome();
					
		} catch (Exception e) {
		common.handleException(e.getMessage());
		}
	}
	public static void main(String[] args) {
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.Lapse_on_off_Screen", "testLapse_on_off_Screen", "1");		
	}
}
