package one.hardware.Testcase;

import java.io.File;
import java.util.HashSet;

import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Action.FileManagerAction;
import one.hardware.Action.VideoNode;
import one.hardware.Util.Base;

public class SlomoCase extends Base{
	
	/*
	 * 配置慢动作模式
	 * 切换不同的视场角：超宽、宽、普通
	 * 录制并判断
	 * 
	 * index  4 对应慢动作录像模式
	 * 
	 * angle  Super Wide    Medium  Wide
	 * 不区分大小写
	 * 
	 */
	
	private void configSlomoAngleAndRecord(int index,String angle) throws Exception {
		
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			
			
			CameraAction.configVideoModeAndAngle(index, angle); //切换拍摄模式和视场角
			
			HashSet<String> beforeTakeVideoList = common.FileList("/sdcard/video");
			common.cameraKey();
			sleep(10000);
			CameraAction.cameraRecordTime();
			sleep(5000);
			boolean mostatus = true;
			for (int i = 0; i < 20; i++) {
				if (!CameraAction.checkmovalue(4)) {
					mostatus=false;
				}else {
					mostatus=true;
				}
				sleep(2000);
			}
			CameraAction.cameraRecordTime();
			common.cameraKey();
			sleep(3000);

			
			 Boolean result=true;
			if (mostatus) {
				HashSet<String> afterTakeVideoList = common.FileList("/sdcard/Video");
				HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
				if (resultHashSet.size()==1) {
					String videopath = resultHashSet.iterator().next();
					common.infoLog("new file:"+videopath);
					String videoName = new File(videopath).getName();
					VideoNode activeNode = common.VideoInfo(videopath);
					if (common.checkVideoInfo(1080, activeNode)) {
						common.infoLog("video info check success-"+videopath);
						FileManagerAction.playVideoByFileManager(videoName);
						
						if (common.findViewByText2("^Can't play this video.*").exists()) {
							common.infoLog(videoName+" 播放失败" + "-Can't play this video");
							common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
							common.failcase(runcase);
							throw new Exception("FindObject" + "Can't play this video");
						}else {
							common.infoLog(videoName+" 播放成功");
							result= true;
						}
					}else {
						common.infoLog("video info check failed"+videopath);
						result= false;
					}
				}else {
					result= false;
				}
			}else {
				result= false;
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
			
			
		}catch (Exception e) {
			// TODO: handle exception
			
			common.handleException(e.getMessage());
		}
		
	}
	

	/*
	 * 慢动作超宽视场角
	 */
	public void testSloMoSuperWide() throws Exception{
		int  index=4;
		String angle="Super Wide"; 
		configSlomoAngleAndRecord(index, angle);
	}
	
       
	/*
	 * 慢动作宽视场角
	 */
	public void testSloMoWide() throws Exception{
    	int  index=4;
		String angle="Wide"; 
		configSlomoAngleAndRecord(index, angle);
	}
	
	
	/*
	 * 慢动作普通视场角
	 */
	public void testSloMoMedium() throws Exception{
		int  index=4;
		String angle="Medium"; 
		configSlomoAngleAndRecord(index, angle);

	}

	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.SlomoCase", "", "1");
	}
	
	
	
	
}




