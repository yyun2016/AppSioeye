package one.hardware.Testcase;

import java.io.File;
import java.util.HashSet;

import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Action.FileManagerAction;
import one.hardware.Action.VideoNode;
import one.hardware.Page.Camera;
import one.hardware.Page.Picture;
import one.hardware.Util.Base;

public class LapseCase extends Base{
	public static void main(String[] args) {
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.LapseCase", "testLapse2s48030AllAngle", "2");
		
	}
	
	public void lapseAllAngle(int lapse_quality,int lapse_time) throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			boolean isExistSDCard=false;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
				isExistSDCard=true;
			}else {
				videoFilePath="/storage/sdcard0/Video";
				isExistSDCard=false;
			}
			boolean result = true;
			common.startCamera();
			String quality =Camera.lapse_quality[lapse_quality];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			CameraAction.configLapseTimeAndVideoQuality(Camera.lapse_time[lapse_time], quality);
			for (int t = 0; t <anglesize; t++) {
				common.deleteVideo();
				String angle =Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				CameraAction.configCurrentModeAndAngle(t);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(7000);
				boolean lapstatus = true;
				for (int i = 0; i < 3; i++) {
					if (!CameraAction.checklapsevalue(Camera.lapse_times[lapse_time])) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					common.waitTime(lapse_time+2);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(920);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName,isExistSDCard);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2(Picture.button1_id).clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse2s48030AllAngle
	 * Description:延时录像延时为2秒，480P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:39:16
	 */
	public void testLapse2s48030AllAngle() throws Exception{
		lapseAllAngle(0, 0);
	}
	
	public void testLapse2s72030AllAngle() throws Exception{
		lapseAllAngle(1, 0);
	}
	
	public void testLapse2s108030AllAngle() throws Exception{
		lapseAllAngle(2, 0);
	}
	
	public void testLapse2s2K30AllAngle() throws Exception{
		lapseAllAngle(3, 0);
	}
	
	public void testLapse2s4k30AllAngle() throws Exception{
		lapseAllAngle(4, 0);
	}
	
	public void testLapse3s48030AllAngle() throws Exception{
		lapseAllAngle(0, 1);
	}
	
	public void testLapse3s72030AllAngle() throws Exception{
		lapseAllAngle(1, 1);
	}
	
	public void testLapse3s108030AllAngle() throws Exception{
		lapseAllAngle(2, 1);
	}
	
	public void testLapse3s2k30AllAngle() throws Exception{
		lapseAllAngle(3, 1);
	}
	
	public void testLapse3s4k30AllAngle() throws Exception{
		lapseAllAngle(4, 1);
	}
	
	public void testLapse5s48030AllAngle() throws Exception{
		lapseAllAngle(0, 2);
	}
	
	public void testLapse5s72030AllAngle() throws Exception{
		lapseAllAngle(1, 2);
	}
	
	public void testLapse5s108030AllAngle() throws Exception{
		lapseAllAngle(2, 2);
	}
	
	public void testLapse5s2k30AllAngle() throws Exception{
		lapseAllAngle(3, 2);
	}
	
	public void testLapse5s4k30AllAngle() throws Exception{
		lapseAllAngle(4, 2);
	}
	
	public void testLapse10s48030AllAngle() throws Exception{
		lapseAllAngle(0, 3);
	}
	
	public void testLapse10s72030AllAngle() throws Exception{
		lapseAllAngle(1, 3);
	}
	
	public void testLapse10s108030AllAngle() throws Exception{
		lapseAllAngle(2, 3);
	}
	
	public void testLapse10s2k30AllAngle() throws Exception{
		lapseAllAngle(3, 3);
	}
	
	public void testLapse10s4k30AllAngle() throws Exception{
		lapseAllAngle(4, 3);
	}
}
