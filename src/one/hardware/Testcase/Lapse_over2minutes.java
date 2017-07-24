package one.hardware.Testcase;

/**
 * 延时录像超过2分钟无异常
 * */

import java.util.HashSet;
import com.ckt.demo.UiAutomatorHelper;
import one.hardware.Action.CameraAction;
import one.hardware.Action.VideoNode;
import one.hardware.Util.Base;

public class Lapse_over2minutes extends Base{
	public void  testLapse_Delay_Recording2Minutes() throws Exception{
		try{
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();          			
			common.startCamera();		  
			CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);
			sleep(5000);
			String videoPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoPath="/storage/sdcard1/Video";
			}else {
				videoPath="/storage/sdcard0/Video";
			}
						
			HashSet<String> beforeTakeVideoList = common.FileList(videoPath);   	
			common.cameraKey();
			common.infoLog("录像2分钟，等待2分钟");
			sleep(128000);			
			common.cameraKey();
			sleep(2000);
			HashSet<String> afterTakeVideoList = common.FileList(videoPath);   				 
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);  
			
			if (resultHashSet.size()==1) {  						 
				String videopath = resultHashSet.iterator().next();  
				common.infoLog("new file:"+videopath);       		 				
				VideoNode videoNode = common.VideoInfo(videopath);   				
				if(videoNode.getDuration()<120){                    
					fail("max duration is 120 seconds");
				}
			}else {
				common.failcase(runcase);
			}
		
			common.backToHome();
			
			} catch (Exception e) {
				common.handleException(e.getMessage());
				}
	}
	public static void main(String[] args) {
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.Lapse_over2minutes", "testLapse_Delay_Recording2Minutes", "7");		
	}
}
