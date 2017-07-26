package one.hardware.Action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.ckt.demo.UiAutomatorHelper;

import android.R.string;
import one.hardware.Util.Base;

public class CameraAction extends Base {
	/**
	 * Click android.widget.CompoundButton按钮
	 */
	public static void openCompoundButton(String cbtnNname) throws UiObjectNotFoundException{
		UiCollection videos = new UiCollection(
				new UiSelector().className("android.widget.ScrollView"));
		int count = videos.getChildCount(new UiSelector()
				.className("android.widget.RelativeLayout"));
		for (int instance = 0; instance < count; instance++) {
			UiObject uiObject = videos.getChildByInstance(
					new UiSelector().className("android.widget.RelativeLayout"),
					instance);
			UiObject sObject = uiObject.getChild(new UiSelector()
					.className("android.widget.TextView"));
			if (uiObject.exists() && uiObject.isEnabled() && sObject.exists()) {
				if (sObject.getText().equals(cbtnNname)) {
					UiObject switchbtn = uiObject.getChild(new UiSelector().className("android.widget.CompoundButton"));
					switchbtn.click();
				}
			}
		}
	}
	public void checkLiveStatus(int retryTimes){
		for (int i = 0; i < retryTimes; i++) {
			UiObject initiazlingLiveStream= common.findViewByText2("^Initializing Live Stream*");
			if (initiazlingLiveStream.waitUntilGone(60000)) {
				UiObject connecFail = common.findViewByText2("^Connection fail, please try again*");
				UiObject connecTooSlow = common.findViewByText2("^connection too slow*");
				if (connecFail.exists()) {
					common.infoLog("Connection fail, please try again");
				}
			}
		}
	}
	/**
	 * Click launcher_application_id按钮
	 */
	public static  void LaunchCamera() throws Exception {
		common.clickViewById(one.hardware.Page.Camera.launcher_application_id);
	}
	/**
	 * Click camera_live_shortcut_id按钮
	 */
	public static  void cameraLive() throws Exception {
		common.clickViewById(one.hardware.Page.Camera.camera_live_shortcut_id);
	}
	/**
	 * Click camera_video_shortcut_id按钮
	 */
	public static  void cameraVideo() throws Exception {
		common.clickViewById(one.hardware.Page.Camera.camera_video_shortcut_id);
	}
	/**
	 * Click camera_cap_shortcut_id按钮
	 */
	public static  void cameraCap() throws Exception {
		common.clickViewById(one.hardware.Page.Camera.camera_cap_shortcut_id);
	}
	/**
	 * Click camera_setting_shortcut_id按钮
	 */
	public static  void cameraSetting() throws Exception {
		common.clickViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
	}
	/**
	 * 等待开始录制-60秒
	 */
	public static  String  cameraRecordTime() throws Exception {
		common.findViewById2(one.hardware.Page.Camera.recording_time_id).waitForExists(60000);
		UiObject recordTime = common.findViewById(one.hardware.Page.Camera.recording_time_id);
		String time = recordTime.getText();
		common.infoLog("recording_time:"+time);
		return time;
	}
	/**
	 * 获取当前视频质量
	 */
	public static  UiObject  currentQuality() throws Exception {
		UiObject qualityObject = common.findViewById(one.hardware.Page.Camera.currentvideoquliaty);
		common.infoLog("Current Video Quality :"+qualityObject.getText());
		return qualityObject;
	}
	/**
	 * Uiobject video-paly 按钮
	 */
	public static  UiObject  playVideoBtn() throws Exception {
		UiObject videoObject = common.findViewById(one.hardware.Page.Camera.playvideobtn);
		common.infoLog("video play object :"+videoObject.getText());
		return videoObject;
	}
	public static int DateInseconds(String recordTime){
		int seconds = 0;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = format.parse(recordTime);
			cal.setTime(date);
			int hour=cal.get(Calendar.HOUR);//小时
	        int minute=cal.get(Calendar.MINUTE);//分           
	        int second=cal.get(Calendar.SECOND);//秒
	        seconds = hour*60*60+minute*60+second;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return seconds;
	}
	public static  boolean  checklapsevalue(int lap) throws Exception {
		String lapse1= common.findViewById(one.hardware.Page.Camera.lapsetime1).getText();
		String lapse2= common.findViewById(one.hardware.Page.Camera.lapsetime2).getText();
		int lapse1secs = DateInseconds(lapse1);
		int lapse2secs = DateInseconds(lapse2);
		common.infoLog(lapse1secs+"-"+lapse2secs);
		if (lapse1secs/lapse2secs==lap) {
			common.infoLog("Time Lapse验证结果-PASS  "+lapse1secs +"-"+lapse2secs);
			return true;
		}else {
			common.infoLog("Time Lapse验证结果-Failed  "+lapse1secs +"-"+lapse2secs);
			return false;
		}
	}
	public static  boolean  checkmovalue(int mo) throws Exception {
		String mo1= common.findViewById(one.hardware.Page.Camera.lapsetime1).getText();
		String mo2= common.findViewById(one.hardware.Page.Camera.lapsetime2).getText();
		int mo1secs = DateInseconds(mo1);
		int mo22secs = DateInseconds(mo2);
		common.infoLog(mo1secs+"-"+mo22secs);
		if (mo22secs/mo1secs==mo) {
			common.infoLog("Slo-Mo验证结果-PASS  "+mo1secs +"-"+mo22secs);
			return true;
		}else {
			common.infoLog("Slo-Mo验证结果-Failed  "+mo1secs +"-"+mo22secs);
			return false;
		}
	}
	/**
	 * TimeLapse设置
	 *   "2 3 5 10";
	 */
	public static void configTimeLapse(String timelapse) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);
		CameraAction.cameraSetting();
		common.clickViewByText("Time Lapse");
		common.ScrollViewByText(timelapse);
		common.clickViewByText(timelapse);
		common.infoLog("Time Lapse设置为 :"+common.findViewByText2(timelapse).getText());
		common.device.pressBack();
	}
	public static void configTimeLapse(int index,String timelapse) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[index]);
		CameraAction.cameraSetting();
		common.clickViewByText("Time Lapse");
		common.ScrollViewByText(timelapse);
		common.clickViewByText(timelapse);
		common.infoLog("Time Lapse设置为 :"+common.findViewByText2(timelapse).getText());
		common.device.pressBack();
	}
	/**
	 * 视频质量设置
	 *   "480@30FPS",
		  "480@60FPS",
		  "480@120FPS",
		  "720@30FPS",
		  "720@60FPS",
		  "1080@30FPS"};
	 */
	public static void configLiveQuality(String quality) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[0]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Video Quality");
		common.clickViewByText("Video Quality");
		common.ScrollViewByText(quality);
		common.clickViewByText(quality);
		common.infoLog("Video Quality设置为 :"+common.findViewByText(quality).getText());
		common.device.pressBack();
	}
	public static void configVideoQuality(String quality) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[1]);
		CameraAction.cameraSetting();
		common.clickViewByText("Video Quality");
		common.ScrollViewByText(quality);
		common.clickViewByText(quality);
		common.infoLog("Video Quality设置为 :"+common.findViewByText2(quality).getText());
		common.device.pressBack();
	}
	public static boolean isExistVideoQuality(String quality) throws Exception{
		boolean isexist = false;
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Video Quality");
		common.clickViewByText("Video Quality");
		isexist = common.ScrollViewByTextNotFind(quality);
		if (!isexist) {
			common.infoLog("Video Quality不支持 :"+quality);
		}
		common.device.pressBack();
		return isexist;
	}
	public static void configVideoQuality(int index,String quality) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[index]);
		CameraAction.cameraSetting();
		common.clickViewByText("Video Quality");
		common.ScrollViewByText(quality);
		common.clickViewByText(quality);
		common.infoLog("Video Quality设置为 :"+common.findViewByText2(quality).getText());
		common.device.pressBack();
	}
	/**
	 * 连拍数设置
	 *    "10P",
		  "20P",
		  "30P};
	 */
	public static void configBurstRate(String burst) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[3]);
		CameraAction.cameraSetting();
		common.clickViewByText("Burst Rate");
		common.clickViewByText(burst);
		common.infoLog("Burst Rate设置为 :"+burst);
		common.device.pressBack();
	}
	/**
	 * Angle设置
	 *  {"Super Wide","Wide","Medium"};
	 */
	public static void configLiveAngle(String angle) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[0]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Video Angle");
		common.clickViewByText("Video Angle");
		common.clickViewByText(angle);
		common.infoLog("Video Angle设置为 :"+angle);
		common.device.pressBack();
	}
	public static void configVideoAngle(String angle) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[1]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Video Angle");
		common.clickViewByText("Video Angle");
		common.clickViewByText(angle);
		common.infoLog("Video Angle设置为 :"+angle);
		common.device.pressBack();
	}
	public static void configSlomoAngle(String angle) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[4]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Video Angle");
		common.clickViewByText("Video Angle");
		common.clickViewByText(angle);
		common.infoLog("Video Angle设置为 :"+angle);
		common.device.pressBack();	
	}
	/**
	 * 通用配置视场角方法
	 * @param index
	 * index参数配置模式 ；即Camera.nav_menu[index]
	 * @param angle
	 * angle为视场角，不区分首字母大小写
	 * @throws Exception
	 */
	public static void configVideoModeAndAngle(int index,String angle) throws Exception{
		  CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[index]);
		  CameraAction.cameraSetting();
		  common.ScrollViewByText("Video Angle");
		  common.clickViewByText("Video Angle");
		  if (angle=="Super Wide"||angle=="super wide") {
		   common.clickViewByText("Super Wide");
		  }
		  if (angle=="Medium"||angle=="medium") {
		   common.clickViewByText("Medium");
		  }
		  int ang=3;
		  if (angle=="Wide"||angle=="wide") {
		   ang=1;
		   UiObject tObject = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(ang));
		   tObject.click();
		   }
		  common.infoLog("ang:"+ang);
		  common.infoLog("Video Angle设置为 :"+angle);
		  common.device.pressBack();
		 }
	public static void configVideoModeAndAngle(int index,int angle) throws Exception{
		  CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[index]);
		  CameraAction.cameraSetting();
		  common.ScrollViewByText("Video Angle");
		  common.clickViewByText("Video Angle");
		  if (angle==0) {
		   common.clickViewByText("Super Wide");
		  }
		  if (angle==2) {
		   common.clickViewByText("Medium");
		  }
		  if (angle==1) {
		   UiObject tObject = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(angle));
		   tObject.click();
		   }
		  common.infoLog("Video Angle设置为 :"+angle);
		  common.device.pressBack();
		 }
	public static void configVideoAngle(int angle) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[1]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Video Angle");
		common.clickViewByText("Video Angle");
		//common.clickViewByText(angle);
		UiObject tObject = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(angle));
		tObject.click();
		common.infoLog("Video Angle设置为 :"+angle);
		common.device.pressBack();
	}
	/**
	 * 图片设置
	 *   "18M(4:3)",
		  "13M(16:9)",
		  "8M(4:3)"};
	 */
	public static void configImageSize(String size) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[2]);
		CameraAction.cameraSetting();
		common.clickViewByText("Image Size");
		common.ScrollViewByText(size);
		common.clickViewByText(size);
		common.infoLog("Image Size设置为 :"+size);
		common.device.pressBack();
	}
	public static void configBurstImageSize(String size) throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[3]);
		CameraAction.cameraSetting();
		common.clickViewByText("Image Size");
		common.ScrollViewByText(size);
		common.clickViewByText(size);
		common.infoLog("Image Size设置为 :"+size);
		common.device.pressBack();
	}
	/**
	 * 进入对应的模块
	 * 0： "Live Stream",
		1:  "Video",
		2: "Capture",
		3:"Burst",
		4:"Slo_Mo",
		5:"Lapse"
	 */
	public static void navconfig(String text) throws Exception{
		common.device.pressMenu();
		common.ScrollViewByText(text);
		common.clickViewByText(text);
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", " one.hardware.Action.CameraAction", "one.hardware.Testcase", "2");
		//new UiAutomatorHelper("AppSioeye", " one.test.ImageTestCase", "", "3");
	}
	
	public static void stopSettings() throws Exception{
		try {
			Runtime.getRuntime().exec(
					"adb shell am force-stop com.android.settings");
			common.infoLog("stopSetting");
		} catch (IOException e) {
		common.infoLog("stopSettingSuccessed");
		}
	}
	/**
	 * Name:getPhotoPath
	 * Description:判断是否存在SD卡，并返回photo文件目录
	 * author yun.yang
	 * date 2017年7月26日上午9:15:04
		 */
	public static String getVideoPath() {
		String videoFilePath=null;
		if (common.isExistSDCard()) {//判断是否存在SD卡
			videoFilePath="/storage/sdcard1/Video";
			return videoFilePath;
		}else {
			videoFilePath="/storage/sdcard0/Video";
			return videoFilePath;
		}
	}
	/**
	 * Name:getPhotoPath
	 * Description:判断是否存在SD卡，并返回video文件目录
	 * author yun.yang
	 * date 2017年7月26日上午9:18:57
		 */
	public static String getPhotoPath() {
		String photoFilePath=null;
		if (common.isExistSDCard()) {//判断是否存在SD卡
			photoFilePath="/storage/sdcard1/Video";
			return photoFilePath;
		}else {
			photoFilePath="/storage/sdcard0/Video";
			return photoFilePath;
		}
	}
}
