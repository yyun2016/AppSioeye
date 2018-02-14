package one.hardware.Testcase;

import java.util.HashSet;
import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Page.Camera;
import one.hardware.Util.Base;

public class LiveNotSaveCase extends Base {
  private void LiveNotSave(String quality, String angle) throws Exception {
	try{
		initUIAutomator(this.getName());
		
		common.startLog("*****Start to run " + runcase + " *****");
		common.initDevice();
		common.pmclear();
		common.startCamera();
		 int liveNotSaveCaseRunTime=1;
		if (liveNotSaveCaseRunTime==1) {
			CameraAction.login();
			common.infoLog("liveCaseRunTime:"+liveNotSaveCaseRunTime);
			}
		liveNotSaveCaseRunTime=liveNotSaveCaseRunTime+1;

		CameraAction.configLiveQualityAndAngle(quality, angle);
        String video_path = CameraAction.getVideoPath();
        HashSet<String> beforeTakeVideoList = common.FileList(video_path);
        
        CameraAction.makeLive();
		sleep(10000);
		if (CameraAction.stopLive()) {
			sleep(500);
	        HashSet<String> afterTakeVideoList = common.FileList(video_path);
	        HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
	        if (resultHashSet.size() == 0) {
	        	common.passcase();
	        	} else {
	           common.failcase(runcase);
	        	}
			}else {
				common.failcase(runcase);
				}
		}catch(Exception e){
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
    }
	/**
	 * 默认设置480@25(SD)普通视角直播不保存
	 * @throws Exception
	 */
//	public void testLiveNotSave48025SDMedium() throws Exception{
//		LiveNotSave(Camera.live_quality[0],Camera.video_Angle[2]);
//	}
//	/**
//	 * 默认设置480@25(SD)宽视角直播不保存
//	 * @throws Exception
//	 */
//	public void testLiveNotSave48025SDWide() throws Exception{
//		LiveNotSave(Camera.live_quality[0],Camera.video_Angle[1]);
//	}
//	/**
//	 * 默认设置480@25(SD)超宽视角直播不保存
//	 * @throws Exception
//	 */
//	public void testLiveNotSave48025SDSuperWide() throws Exception{
//		LiveNotSave(Camera.live_quality[0],Camera.video_Angle[0]);
//	}
	/**
	 * 默认设置480@25(HD)普通视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025HDMedium() throws Exception{
		LiveNotSave(Camera.live_quality[0],Camera.video_Angle[2]);
	}
	/**
	 * 默认设置480@25(HD)宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025HDWide() throws Exception{
		LiveNotSave(Camera.live_quality[0],Camera.video_Angle[1]);
	}
	/**
	 * 默认设置480@25(HD)超宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025HDSuperWide() throws Exception{
		LiveNotSave(Camera.live_quality[0],Camera.video_Angle[0]);
	}
	/**
	 * 默认设置720@25(HD)普通视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave72025HDMedium() throws Exception{
		LiveNotSave(Camera.live_quality[1],Camera.video_Angle[2]);
	}
	/**
	 * 默认设置720@25(HD)宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave72025HDWide() throws Exception{
		LiveNotSave(Camera.live_quality[1],Camera.video_Angle[1]);
	}
	/**
	 * 默认设置720@25(HD)超宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave72025HDSuperWide() throws Exception{
		LiveNotSave(Camera.live_quality[1],Camera.video_Angle[0]);
	}
	/**
	 * 默认设置1080@25(HD)普通视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave1080HDMedium() throws Exception{
		LiveNotSave(Camera.live_quality[2],Camera.video_Angle[2]);
	}
	/**
	 * 默认设置1080@25(HD)宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave1080HDWide() throws Exception{
		LiveNotSave(Camera.live_quality[2],Camera.video_Angle[1]);
	}
	/**
	 * 默认设置1080@25(HD)超宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave1080HDSuperWide() throws Exception{
		LiveNotSave(Camera.live_quality[2],Camera.video_Angle[0]);
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.LiveNotSaveCase", "", "1");
	}
}
