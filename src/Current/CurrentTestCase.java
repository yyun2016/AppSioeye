package Current;

import android.os.RemoteException;

import android.view.KeyEvent;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.ckt.demo.UiAutomatorHelper;

import java.io.IOException;
import java.util.logging.Logger;

import one.hardware.Action.AccountAction;
import one.hardware.Action.CameraAction;
import one.hardware.Action.SettingAction;
import one.hardware.Page.Camera;
import one.hardware.Page.GalleryPage;
import one.hardware.Page.SettingPage;

import one.hardware.Util.Base;

/**
 * Created by yun.yang on 2017/4/6.
 * 执行前手动设置
 * 3.插上SIM卡；
 * 4.插上SD卡；
 * 5.连接1个信号网速良好的WiFi；
 * 6.清除相册、三方推流 新手指引
 */
public class CurrentTestCase extends Base {
    Logger logger = Logger.getLogger(CurrentTestCase.class.getName());
    
    public static void main(String[] args){
		new UiAutomatorHelper("AppSioeye", "Current.CurrentTestCase", "testForCurrent", "2");
	}
    private int testTime=122;//测试时间，单位秒
    private int coolingTime=85;//冷却相机时间，单位秒

    public void testForCurrent() throws Exception {
    	initUIAutomator(this.getName());
    	UiDevice.getInstance().pressHome();
    	common.startLog("*****Start to run " + runcase + " *****");
        String liveQuality480="480@25FPS(Bitrate0.3-2 Mbps)",
                liveQuality720HD="720@25FPS(Bitrate1.3-6 Mbps)",
                liveQuality1080HD="1080@25FPS(Bitrate10 Mbps)";
        String videoQuality4KP25="4K@25FPS",
        		videoQuality2KP25="2K@25FPS",
        		videoQuality1080P120="1080@120FPS",
        		videoQuality1080P60="1080@60FPS",
        		videoQuality1080P25="1080@25FPS",
        		videoQuality720P120="720@120FPS",
                videoQuality720P60="720@60FPS",
                videoQuality720P25="720@25FPS",
                videoQuality480P120="480@120FPS",
                videoQuality480P60="480@60FPS",
                videoQuality480P25="480@25FPS";
        String switchName[]={
                "Altimeter",//高度计0
                "Speedometer",//速度计1
                "Video&Live(beta)",//录播2  V3暂时无该功能；有的
                "Anti-shake",//防抖3
                "Voice interaction",//语音交互4
                "Auto",//自动--V3
        };
        String videoAngle[]={
                "Medium",
                "Wide",
                "Super Wide"
        };
        common.waitTime(2);
        common.initDevice();
        common.pmclear();
        Runtime.getRuntime().exec("dumpsys battery set level 100");//设置电流为100%
        makeScreenOn();
        changeSystemLanguageToEnglih();
        common.startCamera();
        CameraAction.cameraSetting();
        common.ScrollViewByText("Account");
		common.clickViewByText("Account");
		AccountAction.loginAccount("sioeye", "y123456");
		boolean login = one.hardware.Action.AccountAction.isLoginSuccess();
		UiDevice.getInstance().pressBack();
		common.waitTime(1);
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
		
        for (int i = 1;i<=1;i++){
            makeScreenOn();
            changeSleepTime("Never");
            switchTo4G();
//            storageFormat();//格式化储存空间
            closeWifi();
            //开启相机
            launchCamera();
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
            
            //不同分辨率录像
            makeToasts("start",5);
            configVideoQuality(videoQuality4KP25);//4K亮
            makeVideo("ON");
            makeVideo("OFF");//4K灭
            configVideoQuality(videoQuality2KP25);//2K灭
            makeVideo("OFF");
            configVideoQuality(videoQuality1080P120);//1080P120FPS
            makeVideo("OFF");
            configVideoQuality(videoQuality1080P60);//1080P60FPS
            makeVideo("OFF");
            configVideoQuality(videoQuality1080P25);//1080P25FPS
            makeVideo("ON");
            makeVideo("OFF");
            configVideoQuality(videoQuality720P120);//720P120FPS
            makeVideo("ON");
            makeVideo("OFF");
            configVideoQuality(videoQuality720P60);//720P60FPS
            makeVideo("OFF");
            configVideoQuality(videoQuality720P25);//720P25FPS
            makeVideo("ON");
            makeVideo("OFF");
            
            //4G 720P相册直播
            common.stopCamera();
            common.waitTime(2);
            common.startGallery();
            galleryLive("ON");
            galleryLive("OFF");
            common.stopGallery();
            //WIFI 720P相册直播
            openWifi();
            common.startGallery();
            galleryLive("OFF");//WIFI相册灭屏直播720P
            common.stopGallery();
            closeWifi();
            
            //录像
            launchCamera();
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
            configVideoQuality(videoQuality480P120);//480P120FPS
            makeVideo("OFF");
            configVideoQuality(videoQuality480P60);//480P25FPS
            makeVideo("OFF");
            configVideoQuality(videoQuality480P25);//480P25FPS
            makeVideo("OFF");
            
            //4G 480P相册直播
            common.stopCamera();
            common.waitTime(2);
            common.startGallery();
            galleryLive("OFF");
            common.stopGallery();
            
            //WIFI 480P相册直播
            openWifi();
            common.startGallery();
            galleryLive("OFF");
            common.stopGallery();
            closeWifi();
            
            //延时 慢速录像
            launchCamera();
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
            CameraAction.navconfig(Camera.nav_menu[4]);//"Slo_Mo" Modem
            common.waitTime(2);
            makeVideo("OFF");
            CameraAction.navconfig(Camera.nav_menu[5]);//"Lapse" Modem
            common.waitTime(2);
            makeVideo("OFF");
            
            //预览亮屏
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
            configVideoQuality(videoQuality1080P25);
            common.waitTime(2);
            common.waitTime(testTime*3);
            //主屏幕亮屏待机
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            common.waitTime(testTime*3);
            storageFormat();
            
            //4G直播
            launchCamera();
            CameraAction.cameraLive();;//Live Modem
            configVideoQuality(liveQuality480);
            makeLive("ON");
            makeLive("OFF");
            configVideoQuality(liveQuality720HD);
            makeLive("ON");
            makeLive("OFF");
            configVideoQuality(liveQuality1080HD);
            makeLive("ON");
            makeLive("OFF");
            clickLiveAndSave();//开启直播保存
            common.waitTime(2);
            configVideoQuality(liveQuality480);
            makeLive("ON");
            makeLive("OFF");
            configVideoQuality(liveQuality720HD);
            makeLive("ON");
            makeLive("OFF");
            configVideoQuality(liveQuality1080HD);
            makeLive("ON");
            makeLive("OFF");
            clickLiveAndSave();//关闭直播保存
            
            //自定义码率
            
            
            //4G+其他设置
            common.waitTime(1);
            configVideoQuality(liveQuality480);
            clickSwitch(switchName[0]);//开启高度计
            makeLive("OFF");
            clickSwitch(switchName[0]);//关闭高度计
            common.waitTime(2);
            clickSwitch(switchName[1]);//开启速度计
            makeLive("OFF");
            clickSwitch(switchName[1]);//关闭速度计
            common.waitTime(2);
            clickLiveMute();  //开启静音开关
            makeLive("OFF");
            clickLiveMute();//关闭静音开关
            clickSwitch(switchName[3]); //开启防抖开关
            makeLive("OFF");
            clickSwitch(switchName[3]);//关闭防抖开关
            clickSwitch(switchName[4]);//开启语音交互
            makeLive("OFF");
            clickSwitch(switchName[4]);//关闭语音交互
            clickSwitch(switchName[5]);//开启为倒置Auto
            makeLive("OFF");
            clickSwitch(switchName[5]);//关闭为倒置Auto
            configVideoAngle(videoAngle[2]);//视场角为超宽
            makeLive("OFF");
            configVideoAngle(videoAngle[1]);//视场角为宽
            makeLive("OFF");
            configVideoAngle(videoAngle[0]);//视场角为普通
            makeLive("OFF");
            liveForMaxZoom("OFF");//最大焦距直播
            
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            common.waitTime(testTime*3);
            
            //3G直播
            switchTo3G();
            common.waitTime(1);
            launchCamera();
            CameraAction.cameraLive();//Live Modem
            common.waitTime(2);
            configVideoQuality(liveQuality480);
            makeLive("ON");
            makeLive("OFF");
            clickLiveAndSave();//开启直播保存
            makeLive("ON");
            makeLive("OFF");
            clickLiveAndSave();//关闭直播保存
            common.waitTime(2);
//            UiDevice.getInstance().pressBack();
//            UiDevice.getInstance().pressBack();
//            UiDevice.getInstance().pressBack();
//            
//            //WIFI直播
//            openWifi();
//            launchCamera();
//            CameraAction.cameraLive();//Live Modem
//            common.waitTime(2);
//            clickLiveAndSave();//开启直播保存
//            configVideoQuality(liveQuality480);
//            makeLive("ON");
//            makeLive("OFF");
//            clickLiveAndSave();//关闭直播保存
//            common.waitTime(1);
//            makeLive("ON");
//            makeLive("OFF");
//            configVideoQuality(liveQuality720HD);
//            makeLive("ON");
//            makeLive("OFF");
//            configVideoQuality(liveQuality1080HD);
//            makeLive("ON");
//            makeLive("OFF");
            
            //4G 三方推流
        }
        makeToasts("10秒后关机......",5);
        common.waitTime(10);
        Runtime.getRuntime().exec("reboot -p ");
    }

    
/*
 * 切换当前系统语言为英语
 */
private void changeSystemLanguageToEnglih() throws Exception {
	UiDevice.getInstance().pressBack();
	UiDevice.getInstance().pressBack();
	UiDevice.getInstance().pressBack();
	UiDevice.getInstance().pressBack();
	String iconTitle=common.findViewById("com.android.launcher3:id/application_icon_title").getText();
	
	if ("相机".equals(iconTitle)||"相册".equals(iconTitle)||"设置".equals(iconTitle)||"第三方推流".equals(iconTitle)
			||"文件管理器".equals(iconTitle)||"速度计".equals(iconTitle)||"高度计".equals(iconTitle)) {
		common.startSettings();
		common.clickViewByText("系统");
		common.waitTime(1);
		common.clickViewByText("语言和输入法");
		common.waitTime(1);
		common.clickViewByText("语言");
		common.waitTime(1);
		common.clickViewByText("English");
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}
}
    
/**
 * 格式化存储空间
 */
private void storageFormat() throws Exception {
	common.startSettings();
    SettingAction.navToStorage();
    common.waitTime(1);
    common.clickViewById(SettingPage.storage_delete_img);
    common.clickViewByText("FORMAT");
    common.clickViewByText("FORMAT");
    common.waitTime(10);
    logger.info("格式化完成！");
    common.startSettings();
    common.waitTime(1);
}

/**
 *关闭WiFi
 */
private void closeWifi() throws Exception {
    SettingAction.navToWifi();
    if (common.findViewByText2("Add new network...").exists()){
        logger.info("wifi状态为关闭");
    }else {
        common.clickViewById(SettingPage.wifi_boottom);
        logger.info("关闭wifi...");
        common.waitTime(5);
    }
    CameraAction.stopSettings();
    common.waitTime(2);
}


/**
 *开启wifi
 */
private void openWifi() throws Exception {
    SettingAction.navToWifi();
    if (common.findViewByText2("Add new network...").exists()){
    	common.clickViewById(SettingPage.wifi_boottom);
        logger.info("开启wifi中..");
        common.waitTime(10);
        logger.info("wifi自动连接中..");
    }else {
        logger.info("wifi为开启状态");
    }
    CameraAction.stopSettings();
    common.waitTime(2);
}
/**
 *进入相机
 */
private void launchCamera() throws Exception {
	common.waitTime(2);
	String name=device.getCurrentPackageName();
	while (!name.equalsIgnoreCase("com.hicam")) {
	Runtime.getRuntime().exec("am start -n com.hicam/.application.HiCam");
	common.waitTime(6);
	name = device.getCurrentPackageName();
	}	
}
/**
 *灭屏
 */
private void makeScreenOff() throws RemoteException {
    if (UiDevice.getInstance().isScreenOn()) {
    	UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
        logger.info("make screen off");
    }
    common.waitTime(2);
}
/**
 *亮屏
 */
private void makeScreenOn() throws RemoteException {
	common.waitTime(1);
    if (!UiDevice.getInstance().isScreenOn()) {
       UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
        logger.info("make screen on");
    }
    common.waitTime(2);
}

/**
 *结束直播
 */
private void makeLiveStop() throws Exception {
	common.waitTime(1);
    makeScreenOn();
    if (!common.findViewById2(Camera.recording_time_id).exists()) {
        common.clickViewByText("OK");
        common.waitTime(1);
        for (int i=0;i<4;i++){
        	UiDevice.getInstance().pressBack();
        	UiDevice.getInstance().pressBack();
            common.waitTime(15);
            launchCamera();
            common.waitTime(15);
        }
    }else {
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(3);
        UiDevice.getInstance().pressBack();
    	UiDevice.getInstance().pressBack();
    	common.waitTime(coolingTime);
    	launchCamera();
    }
    common.waitTime(2);
}

/**
 *录像
 */
private void makeVideo(String screenStatus) throws Exception {
    makeScreenOn();
    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
    logger.info("LaunchCameraKeyForStart");
    common.waitTime(2);
    if (screenStatus.equals("OFF")) {makeScreenOff();}
    common.waitTime(3);
    common.waitTime(testTime);
    makeScreenOn();
    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
    common.waitTime(3);
    //录像后冷却相机
    UiDevice.getInstance().pressBack();
	UiDevice.getInstance().pressBack();
	common.waitTime(coolingTime);
	launchCamera();
	CameraAction.cameraVideo();
    common.waitTime(3);
}
/**
 *直播
 */
private void makeLive(String ScreenStatus) throws Exception {
    makeScreenOn();
    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
    common.waitTime(1);
    checkLiveStatusAndTry2s(ScreenStatus);
    common.waitTime(1);
}

private void liveForMaxZoom(String ScreenStatus) throws Exception {
    makeScreenOn();
    changZoom();
    makeLive(ScreenStatus);
}
/**
 *检查发起直播是否成功，并会重试一次
 * 两次失败会产生一个两分钟的电流锯齿波
 */
private void checkLiveStatusAndTry2s(String ScreenStatus) throws Exception {
	common.findViewById2(one.hardware.Page.Camera.recording_time_id).waitForExists(50000);
    common.waitTime(3);
    UiObject recordingTimeId = common.findViewById2(one.hardware.Page.Camera.recording_time_id);
    if (!recordingTimeId.exists()) {
    	UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);//消除对话框
        common.waitTime(2);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("Try again");
        common.findViewById2(one.hardware.Page.Camera.recording_time_id).waitForExists(50000);
        common.waitTime(3);
        UiObject recordingTimeIdAgain = common.findViewById2(one.hardware.Page.Camera.recording_time_id);
        if (!recordingTimeIdAgain.exists()) {
        	UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);//消除对话框
            logger.info("Live Failed");
            logger.info("SawtoothWaveNoteMakeLiveFailed");
            for (int i=0;i<4;i++) {
            	common.waitTime(15);
                UiDevice.getInstance().pressBack();
                UiDevice.getInstance().pressBack();
                common.waitTime(15);
                common.startCamera();
            }
            CameraAction.cameraLive();//Live Modem
        }else {
            logger.info("Live succeed");
            if (ScreenStatus.equals("OFF")){makeScreenOff();}
            common.waitTime(testTime);
            makeLiveStop();
        }
    } else {
        logger.info("Live succeed");
        if (ScreenStatus.equals("OFF")){makeScreenOff();}
        common.waitTime(testTime);
        makeLiveStop();
    }
}
private void makeLiveOfMaxZoom(int screen) throws Exception {
	common.findViewById2(one.hardware.Page.Camera.recording_time_id).waitForExists(50000);
    common.waitTime(3);
    UiObject recordingTimeId = common.findViewById2(one.hardware.Page.Camera.recording_time_id);
    if (!recordingTimeId.exists()) {
    	UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);//消除对话框
        common.waitTime(2);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("Try again");
        common.findViewById2(one.hardware.Page.Camera.recording_time_id).waitForExists(50000);
        common.waitTime(3);
        UiObject recordingTimeIdAgain = common.findViewById2(one.hardware.Page.Camera.recording_time_id);
        if (!recordingTimeIdAgain.exists()) {
        	UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);//消除对话框
            for (int i=0;i<4;i++) {
            	common.waitTime(15);
                UiDevice.getInstance().pressBack();
                UiDevice.getInstance().pressBack();
                common.waitTime(15);
                common.startCamera();
            }
            CameraAction.cameraLive();//Live Modem
        }else {
            logger.info("Live succeed");
            changZoom();
            if (screen==0){makeScreenOff();}
            common.waitTime(1);
            makeLiveStop();
        }
    } else {
        logger.info("Live succeed");
        changZoom();
        if (screen==0){makeScreenOff();}
        common.waitTime(1);
        makeLiveStop();
    }
}
private void changZoom() throws Exception {
	device.click(60, 60);
	device.click(60, 60);
	common.findViewById2("com.hicam:id/preview").swipeRight(60);
	common.findViewById2("com.hicam:id/preview").swipeRight(60);
	
}

/**
 * 相册亮屏直播，相册第一个视频可以直播
 */
private void galleryLive(String screenStatus) throws Exception {
    device.click(60, 60);//点击屏幕
    common.waitTime(1);
    if (!common.findViewById2(GalleryPage.gallery_delete_bottom).exists()) {
    	common.findViewById2("android:id/content").swipeRight(60);
    	common.findViewById2("android:id/content").swipeRight(60);
    	common.findViewById2("android:id/content").swipeRight(60);
    	common.waitTime(5);
	}
    common.clickViewById(SettingPage.gallery_live_bottom);//点击live
    common.waitTime(1);
    common.clickViewById(SettingPage.gallery_live);
    common.waitTime(1);
    common.clickViewById(SettingPage.gallery_live_bitrate_skip);
    checkGalleryToLiveStatusAndTryAgain(screenStatus);
    stopGalleryLive();
    common.waitTime(2);
}

/**
 * 检查相册直播是否发起成功，并会重试一次
 * 若两次均失败会产生一个两分钟的电流锯齿波
 * 参数a=0表示灭屏直播；a!=0表示亮屏直播
 * @param a
 * @throws Exception
 */
private void checkGalleryToLiveStatusAndTryAgain(String screenStatus) throws Exception {
	common.findViewByText2("broadcasting").waitForExists(15000);
	common.waitTime(3);;
	UiObject galleryLiveTip=common.findViewByText2("broadcasting");
    if (!galleryLiveTip.exists()){
        common.findViewByText2("Retry").waitForExists(3000);
        common.clickViewByText("Retry");
        common.findViewByText2("broadcasting").waitForExists(10000);
        if (!common.findViewByText2("broadcasting").exists()){
        	common.findViewByText2("Retry").waitForExists(3000);
        	common.clickViewByText("No");
            common.stopGallery();
            common.waitTime(1);
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            common.waitTime(1);
            //直播失败后会出现锯齿状电流波形
            logger.info("SawtoothWaveNoteMakeGalleryLiveFailed");
            launchCamera();
            for (int i=0;i<4;i++) {
                common.waitTime(15);
                UiDevice.getInstance().pressBack();
                UiDevice.getInstance().pressBack();
                common.waitTime(15);
                common.startCamera();
            }
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            common.waitTime(2);
            common.startGallery();
        }else {
            logger.info("retryLiveSuccess");
            if (screenStatus.equals("OFF")){makeScreenOff();}//判断用例该亮灭屏
            common.waitTime(testTime-5);
        }
    }else {
        logger.info("LiveSuccess");
        if (screenStatus.equals("OFF")){makeScreenOff();}
        common.waitTime(testTime-5);
    }
}
/**
 * 停止相册直播
 * 如果发现已经异常中断，会产生一个3段锯齿波
 */
private void stopGalleryLive()throws Exception{
	makeScreenOn();
    if (!common.findViewByText2("broadcasting").exists()) {
    	UiDevice.getInstance().pressBack();//消除异常
        common.waitTime(2);
        UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressBack();
        common.waitTime(1);
        logger.info("SawtoothWaveNoteGalleryLiveBreakOff");
        //出现锯齿状电流波形
        launchCamera();
        for (int i=0;i<4;i++) {
            common.waitTime(15);
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            common.waitTime(15);
            launchCamera();
        }
        UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressBack();
        common.waitTime(2);
        common.startGallery();
    }
    else{
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(1);
        common.clickViewByText("Yes");
        common.waitTime(coolingTime);
    }
}
/**
 * 配置视频质量
 */
private void configVideoQuality(String videoQuality) throws Exception {
    makeScreenOn();
    CameraAction.cameraSetting();
    common.waitTime(1);
    common.ScrollViewByText("Video Quality");
    common.clickViewByText("Video Quality");
    common.waitTime(2);
    common.ScrollViewByText(videoQuality);
    common.clickViewByText(videoQuality);
    common.waitTime(2);
    UiDevice.getInstance().pressBack();
    logger.info(" -configLiveVideoQuality - "+videoQuality);
    common.waitTime(2);
}
private void configUserDefinedLive(String resolution,String framerate,int bitrate) throws Exception {
    makeScreenOn();
    CameraAction.cameraSetting();
    common.waitTime(1);
    common.ScrollViewByText("Video Quality");
    common.clickViewByText("Video Quality");
    common.waitTime(2);
    common.findScrollViewById(Camera.user_defined_btn);
    common.clickViewById(Camera.user_defined_btn);
    common.waitTime(2);
    common.ScrollViewByText("Resolution");
    common.clickViewById(Camera.user_defined_resolution_options);
    common.waitTime(1);
    common.clickViewByText(resolution);
    common.waitTime(1);
    common.ScrollViewByText("Frame Rate");
    common.clickViewByText(framerate);
    common.waitTime(1);
    common.ScrollViewByText("Bitrate");
    
    UiDevice.getInstance().pressBack();
    common.waitTime(2);
}
private void configVideoAngle(String VideoAngle) throws Exception {
    makeScreenOn();
    CameraAction.cameraSetting();
    common.waitTime(1);
    common.ScrollViewByText("More settings");
    common.clickViewByText("More settings");
    common.waitTime(2);
    common.ScrollViewByText("Video Angle");
    common.clickViewByText("Video Angle");
    common.waitTime(2);
    common.clickViewByText(VideoAngle);
    common.waitTime(2);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
}
private void clickLiveMute() throws Exception {
    makeScreenOn();
    CameraAction.cameraSetting();
    common.waitTime(1);
    common.ScrollViewByText("More settings");
    common.clickViewByText("More settings");
    common.waitTime(2);
    common.ScrollViewByText("Live Mute");
    CameraAction.openCompoundButton("Live Mute");
    common.waitTime(2);
    if (common.findViewByText2("OK").exists()) {
        common.clickViewByText("OK");
    }
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
}
private void clickLiveAndSave() throws Exception {
    makeScreenOn();
    CameraAction.cameraSetting();
    common.waitTime(1);
    common.ScrollViewByText("More settings");
    common.clickViewByText("More settings");
    common.waitTime(2);
    common.ScrollViewByText("Live&Save");
    CameraAction.openCompoundButton("Live&Save");
    common.waitTime(2);
    if (common.findViewByText2("OK").exists()) {
        common.clickViewByText("OK");
    }
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
}
private void changeUpDownTo(String UpDown) throws Exception {
    makeScreenOn();
    CameraAction.cameraSetting();
    common.waitTime(1);
    common.ScrollViewByText("More settings");
    common.clickViewByText("More settings");
    common.waitTime(2);
    common.ScrollViewByText("Up/Down");
    common.clickViewByText("Up/Down");
    common.waitTime(3);
    common.clickViewByText(UpDown);
    common.waitTime(2);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
}
private void switchTo3G() throws Exception {
    makeScreenOn();
    SettingAction.navToPreferredNetworkType();
    common.clickViewByText("3G");
    common.waitTime(3);
    logger.info("已设置网络模式为3G");
    common.startSettings();
    common.waitTime(15);
}
private void navToPreferredNetworkType() throws Exception {
    common.startSettings();
    common.clickViewByText("Connection");
//    common.waitTime(2);
    common.ScrollViewByText2("SimCard Info");
    common.clickViewByText("SimCard Info");
    common.clickViewByText("Preferred network type");
}
private void switchTo4G() throws Exception {
    makeScreenOn();
    navToPreferredNetworkType();
    common.clickViewByText("4G (recommended)");
    common.waitTime(3);
    logger.info("已设置网络模式为4G");
    common.startSettings();
    common.waitTime(2);
}
private void changeSleepTime(String sleepTime) throws Exception {
    makeScreenOn();
    SettingAction.navToSleepTime();
    common.waitTime(1);
    makeScreenOn();
    common.ScrollViewByText2(sleepTime);
    makeScreenOn();
    common.clickViewByText(sleepTime);
    common.waitTime(1);
    logger.info("设置灭屏时间为："+sleepTime);
    common.startSettings();
    common.waitTime(2);
}
private void makeToasts(String message,int time) throws IOException {
//    initDevice();
    String command = String.format("am broadcast -a com.sioeye.alert.action -e message %s -e time %d",message,time);
    logger.info(command);
    Runtime.getRuntime().exec(command);
}
private void clickSwitch(String switchName) throws Exception {
    makeScreenOn();
    CameraAction.cameraSetting();
    common.waitTime(1);
    common.ScrollViewByText("More settings");
    common.clickViewByText("More settings");
    common.waitTime(2);
    common.ScrollViewByTextFotCurrenttest(switchName);
    CameraAction.openCompoundButton(switchName);
    logger.info("已点击" + switchName);
    common.waitTime(2);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
    UiDevice.getInstance().pressBack();
    common.waitTime(1);
}
}
