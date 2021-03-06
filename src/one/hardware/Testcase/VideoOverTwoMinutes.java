package one.hardware.Testcase;
/*
 * case：普通录像超过两分钟，录制结果无异常
 * 打开camera，按menu键，找到video，并点击；
 * 录制125秒，判断结果
 */
import java.io.File;
import java.util.HashSet;

import android.view.KeyEvent;

import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Action.FileManagerAction;
import one.hardware.Action.VideoNode;
import one.hardware.Page.Picture;
import one.hardware.Util.Base;
import one.hardware.Util.Common;

public class VideoOverTwoMinutes extends Base{
	public static int WaitTime = 125;
	public void testVideoOverTwoMinutes() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			if(!device.isScreenOn()){
				device.pressKeyCode(KeyEvent.KEYCODE_POWER);
				common.infoLog("点击POWER使屏幕点亮");
			}
			common.initDevice();			
			common.startCamera();
			common.device.pressMenu();
			sleep(500);
			common.ScrollViewByText(one.hardware.Page.Camera.nav_menu[1]); //找到video对象
			common.clickViewByText(one.hardware.Page.Camera.nav_menu[1]); //点击video
			
			
			HashSet<String> beforeTakeVideoList = common.FileList(CameraAction.getVideoPath());			
			common.cameraKey();
			CameraAction.cameraRecordTime();
			Common.waitTime(WaitTime);
			CameraAction.cameraRecordTime();
			common.cameraKey();
			Common.waitTime(2);
			HashSet<String> afterTakeVideoList = common.FileList(CameraAction.getVideoPath());
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			if (resultHashSet.size()==1 ) {
				String videopath = resultHashSet.iterator().next();
				common.infoLog("new file:"+videopath);
				String videoName = new File(videopath).getName();
				common.VideoInfo(videopath);
				VideoNode videoNode = common.VideoInfo(videopath);				
				FileManagerAction.playVideoByFileManager(videoName);

				if (common.findViewByText2("^Can't play this video.*").exists()) {
					common.infoLog(videoName+" 播放失败" + "-Can't play this video");
					common.findViewById2(Picture.button1_id).clickAndWaitForNewWindow();
					common.failcase(runcase);
					throw new Exception("FindObject" + "Can't play this video");
				}
				else if(videoNode.getDuration()<(WaitTime-2)){
					common.infoLog("期望录制的视频时长"+WaitTime);
					common.infoLog("实际录成的视频时长"+videoNode.getDuration());					
					common.failcase(runcase);
				}else {
					common.infoLog(videoName+" 播放成功");
					common.passcase();
				}
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
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.VideoOverTwoMinutes", "testVideoOverTwoMinutes", "1");
	}
}
