package one.hardware.Testcase;

import java.io.File;
import java.util.HashSet;
import java.util.logging.Logger;

import com.android.uiautomator.core.UiDevice;
import com.ckt.demo.UiAutomatorHelper;

import android.R.menu;
import one.hardware.Action.AccountAction;
import one.hardware.Action.CameraAction;
import one.hardware.Action.FileManagerAction;
import one.hardware.Action.LiveAction;
import one.hardware.Action.VideoNode;
import one.hardware.Util.Base;

public class LiveCase extends Base{
	private static Logger logger=Logger.getLogger(LiveCase.class.getName());
	/*
	 * 		  "480@30FPS",
		  "480@60FPS",
		  "480@120FPS",
		  "720@30FPS",
		  "720@60FPS",
		  "1080@30FPS"};
	 * */
	private void LiveQualityAngle(String quality,String angle) throws Exception{	
		try{
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.pmclear();
			common.startCamera();
			CameraAction.configLiveQuality(quality);
			CameraAction.configLiveAngle(angle);
			CameraAction.cameraLive();
			
			String video_path = CameraAction.getVideoPath();
			HashSet<String> beforeTakeVideoList = common.FileList(video_path);
			//判断账号是否登录
			CameraAction.cameraSetting();
			common.ScrollViewByText("Live&Save");
			CameraAction.openCompoundButton("Live&Save");
			common.waitTime(2);
	        if (common.findViewByText2("OK").exists()) {
	            common.clickViewByText("OK");
	        }
	        common.waitTime(1);
			common.ScrollViewByText("Account");
			common.clickViewByText("Account");
			String userName=AccountAction.getUserName();
			String passWord=AccountAction.getPassword();
			AccountAction.loginAccount(userName, passWord);
			boolean login = one.hardware.Action.AccountAction.isLoginSuccess();
			if (login) {
				logger.info(" 账号登陆成功");
				common.passcase();
			}else {
				logger.info(" 账号登陆失败");
				common.failcase(runcase);
			}
			common.cameraKey();
			
			CameraAction.cameraRecordTime();
			sleep(15000);
			CameraAction.cameraRecordTime();
			common.cameraKey();
			sleep(1000);
			
			HashSet<String> afterTakeVideoList = common.FileList(video_path);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			LiveAction.checkLiveAndSaveVideo(resultHashSet);
		}catch(Exception e){
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	
	/**
	 * 默认设置480@25(SD)普通视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025SDMedium() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[11],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置480@25(SD)宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025SDWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[11],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置480@25(SD)超宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025SDSuperWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[11],one.hardware.Page.Camera.video_Angle[0]);
	}
	/**
	 * 默认设置480@25(HD)普通视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025HDMedium() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[12],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置480@25(HD)宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025HDWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[12],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置480@25(HD)超宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave48025HDSuperWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[12],one.hardware.Page.Camera.video_Angle[0]);
	}
	/**
	 * 默认设置720@25(HD)普通视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave72025HDMedium() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[13],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置720@25(HD)宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave72025HDWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[13],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置720@25(HD)超宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave72025HDSuperWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[13],one.hardware.Page.Camera.video_Angle[0]);
	}
	/**
	 * 默认设置1080@25(HD)普通视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave108025HDMedium() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[14],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置1080@25(HD)宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave108025HDWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[14],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置1080@25(HD)超宽视角直播并保存
	 * @throws Exception
	 */
	public void testLiveAndSave108025HDSuperWide() throws Exception{
		LiveQualityAngle(one.hardware.Page.Camera.video_quality[14],one.hardware.Page.Camera.video_Angle[0]);
	}
	
	
	public static void main(String args[]){
//		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.AccountCase", "", "2");
		//new UiAutomatorHelper("AppSioeye", " one.test.ImageTestCase", "", "2");
//		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.BurstDownToUp", "testBurstDownToUp", "2");
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.LiveCase", "", "2");
	}
}
