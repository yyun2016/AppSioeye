package one.hardware.Testcase;

import java.util.HashSet;
import java.util.logging.Logger;

import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.AccountAction;
import one.hardware.Action.CameraAction;
import one.hardware.Action.SettingAction;
import one.hardware.Page.Camera;
import one.hardware.Util.Base;

public class LiveNotSaveCase extends Base {
	private static Logger logger=Logger.getLogger(LiveNotSaveCase.class.getName());
	private void LiveNotSave(String quality, String angle) throws Exception {
	try{
		initUIAutomator(this.getName());
		
		common.startLog("*****Start to run " + runcase + " *****");
		common.initDevice();
		common.pmclear();
		common.startCamera();
		common.waitTime(2);
        
        //验证设置的显示,当前处于直播模式，左顶部显示直播图标
		CameraAction.cameraSetting();
        common.ScrollViewByText("Account");
		common.clickViewByText("Account");
		String userName=AccountAction.getUserName();
		String passWord=AccountAction.getPassword();
		AccountAction.loginAccount(userName, passWord);
		boolean login = one.hardware.Action.AccountAction.isLoginSuccess();
		if (login) {
			logger.info(" 账号登陆成功");
		}else {
			logger.info(" 账号登陆失败");
			common.failcase(runcase);
		}
		common.waitTime(2);
		CameraAction.configLiveQuality(quality);
        CameraAction.configLiveAngle(angle);
        HashSet<String> beforeTakeVideoList = common.FileList("/sdcard/video");
        common.cameraKey();
        CameraAction.cameraRecordTime();
        common.waitTime(20);
        CameraAction.cameraRecordTime();
        common.cameraKey();
        common.waitTime(5);
        HashSet<String> afterTakeVideoList = common.FileList("/sdcard/Video");
        HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
        if (resultHashSet.size() == 0) {
        	logger.info("LiveNotSave:true--------"+quality+"&"+angle+"直播:无保存视频");
        	} else {
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
	public void testLiveNotSave48025SDMedium() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[11],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置480@25(SD)宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025SDWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[11],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置480@25(SD)超宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025SDSuperWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[11],one.hardware.Page.Camera.video_Angle[0]);
	}
	/**
	 * 默认设置480@25(HD)普通视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025HDMedium() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[12],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置480@25(HD)宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025HDWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[12],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置480@25(HD)超宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave48025HDSuperWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[12],one.hardware.Page.Camera.video_Angle[0]);
	}
	/**
	 * 默认设置720@25(HD)普通视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave72025HDMedium() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[13],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置720@25(HD)宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave72025HDWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[13],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置720@25(HD)超宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave72025HDSuperWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[13],one.hardware.Page.Camera.video_Angle[0]);
	}
	/**
	 * 默认设置1080@25(HD)普通视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave1080HDMedium() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[14],one.hardware.Page.Camera.video_Angle[2]);
	}
	/**
	 * 默认设置1080@25(HD)宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave1080HDWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[14],one.hardware.Page.Camera.video_Angle[1]);
	}
	/**
	 * 默认设置1080@25(HD)超宽视角直播不保存
	 * @throws Exception
	 */
	public void testLiveNotSave1080HDSuperWide() throws Exception{
		LiveNotSave(one.hardware.Page.Camera.video_quality[14],one.hardware.Page.Camera.video_Angle[0]);
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.LiveNotSaveCase", "", "2");
	}
}
