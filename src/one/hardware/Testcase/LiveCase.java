package one.hardware.Testcase;

import java.util.HashSet;
import com.ckt.demo.UiAutomatorHelper;
import one.hardware.Action.CameraAction;
import one.hardware.Action.LiveAction;
import one.hardware.Page.Camera;
import one.hardware.Util.Base;

public class LiveCase extends Base{
	public static int liveCaseRunTime=1;//登陆和开启直播保存操作仅一次

	private void LiveQualityAngle(int quality,String angle) throws Exception{	
		try{
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			if (liveCaseRunTime==1) {
				CameraAction.login();
				CameraAction.openLiveAndSave();
				common.infoLog("liveCaseRunTime:"+liveCaseRunTime);
				}
			liveCaseRunTime=liveCaseRunTime+1;
			CameraAction.configLiveQualityAndAngle(Camera.live_quality[quality], angle);

			String video_path = CameraAction.getVideoPath();
			HashSet<String> beforeTakeVideoList = common.FileList(video_path);
			CameraAction.makeLive();
			sleep(10000);
			if (CameraAction.stopLive()) {
				sleep(600+(quality)*300);
				HashSet<String> afterTakeVideoList = common.FileList(video_path);
				HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
				if (LiveAction.checkLiveAndSaveVideo(resultHashSet)) {
					common.passcase();
						}
				else {
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
	 * 默认设置480@25(SD)普通视角直播并保存
	 * @throws Exception
	 */
//	public void testLiveAndSave48025SDMedium() throws Exception{
//		LiveQualityAngle(Camera.live_quality[0],Camera.video_Angle[2]);
//	}
//	/**
//	 * 默认设置480@25(SD)宽视角直播并保存
//	 * @throws Exception
//	 */
//	public void testLiveAndSave48025SDWide() throws Exception{
//		LiveQualityAngle(Camera.live_quality[0],Camera.video_Angle[1]);
//	}
//	/**
//	 * 默认设置480@25(SD)超宽视角直播并保存
//	 * @throws Exception
//	 */
//	public void testLiveAndSave48025SDSuperWide() throws Exception{
//		LiveQualityAngle(Camera.live_quality[0],Camera.video_Angle[0]);
//	}
	/**
	 * 默认设置480@25(HD)普通视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025HDMedium() throws Exception{
		LiveQualityAngle(0,Camera.video_Angle[2]);
	}
	/**
	 * 默认设置480@25(HD)宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025HDWide() throws Exception{
		LiveQualityAngle(0,Camera.video_Angle[1]);
	}
	/**
	 * 默认设置480@25(HD)超宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025HDSuperWide() throws Exception{
		LiveQualityAngle(0,Camera.video_Angle[0]);
	}
	/**
	 * 默认设置720@25(HD)普通视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave72025HDMedium() throws Exception{
		LiveQualityAngle(1,Camera.video_Angle[2]);
	}
	/**
	 * 默认设置720@25(HD)宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave72025HDWide() throws Exception{
		LiveQualityAngle(1,Camera.video_Angle[1]);
	}
	/**
	 * 默认设置720@25(HD)超宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave72025HDSuperWide() throws Exception{
		LiveQualityAngle(1,Camera.video_Angle[0]);
	}
	/**
	 * 默认设置1080@25(HD)普通视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave108025HDMedium() throws Exception{
		LiveQualityAngle(2,Camera.video_Angle[2]);
	}
	/**
	 * 默认设置1080@25(HD)宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave108025HDWide() throws Exception{
		LiveQualityAngle(2,Camera.video_Angle[1]);
	}
	/**
	 * 默认设置1080@25(HD)超宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave108025HDSuperWide() throws Exception{
		LiveQualityAngle(2,Camera.video_Angle[0]);
	}
	
	
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.LiveCase", "testLiveAndSave48025SDMedium", "2");
	}
}
