package Current;

import android.os.RemoteException;

import android.view.KeyEvent;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.ckt.demo.UiAutomatorHelper;

import java.io.IOException;
import java.util.logging.Logger;

import one.hardware.Action.AccountAction;
import one.hardware.Action.CameraAction;
import one.hardware.Action.SettingAction;
import one.hardware.Page.SettingPage;

import one.hardware.Util.Base;

/**
 * Created by yun.yang on 2017/4/6.
 * 执行前手动设置
 * 1.系统语言为英语；
 * 2.灭屏时间为永不；
 * 3.插上SIM卡；
 * 4.插上SD卡；
 * 5.连接1个信号网速良好的WiFi；
 * 6.将config.properties账号信息配置文件放置在相机中
 * 7.单条测试时间配置testTime
 */
public class CurrentTestCase extends Base {
    Logger logger = Logger.getLogger(CurrentTestCase.class.getName());
    private int testTime=120;
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
        common.startCamera();
        common.waitTime(10);
        logger.info("launchCamera");
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
        if (common.findViewById2("OK").exists()) {
            logger.info("Bad network, livestream has ended");
            common.clickViewByText("OK");
            logger.info("SawtoothWaveNoteLiveBreakOff");
            for (int i=0;i<3;i++){
            	UiDevice.getInstance().pressBack();
            	UiDevice.getInstance().pressBack();
                common.waitTime(15);
                launchCamera();
                common.waitTime(15);
            }
            
        }else {
            UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        }
        logger.info("LiveHasStop");
        common.waitTime(2);
    }

    /**
     *灭屏录像
     */
    private void p2pScreenOff() throws RemoteException {
        makeScreenOn();
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("LaunchCameraKeyForStart");
        common.waitTime(2);
        makeScreenOff();
        logger.info("已灭屏");
        common.waitTime(3);
        common.waitTime(testTime);
        makeScreenOn();
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("LaunchCameraKeyForEnd");
        common.waitTime(5);
    }
    /**
     *亮屏录像
     */
    private void p2pScreenOn() throws RemoteException {
        makeScreenOn();
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("LaunchCameraKeyForStart");
        common.waitTime(3);
        common.waitTime(testTime);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("LaunchCameraKeyForEnd");
        common.waitTime(5);
    }
    /**
     *亮屏直播
     */
    private void live2ScreenOn() throws Exception {
        makeScreenOn();
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("LaunchCameraKeyForStart");
        checkLiveStatusAndTry2s(1);//0---灭屏；1---亮屏
        common.waitTime(1);
    }
    /**
     *灭屏屏直播
     */
    private void live2ScreenOff() throws Exception {
        makeScreenOn();
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        logger.info("LaunchCameraKeyForStart");
        common.waitTime(2);
        checkLiveStatusAndTry2s(0);
        common.waitTime(1);
    }
    /**
     *检查发起直播是否成功，并会重试一次
     * 两次失败会产生一个两分钟的电流锯齿波
     */
    private void checkLiveStatusAndTry2s(int a) throws Exception {
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
                if (a==0){makeScreenOff();}
                common.waitTime(testTime);
                makeLiveStop();
            }
        } else {
            logger.info("Live succeed");
            if (a==0){makeScreenOff();}
            common.waitTime(testTime);
            makeLiveStop();
        }
    }

    /**
     * 相册亮屏直播，相册第一个视频可以直播
     */
    private void galleryLiveScreenOn() throws Exception {
        common.clickViewById(SettingPage.video_timeText);//点击屏幕
        common.clickViewById(SettingPage.gallery_live_bottom);//点击live
        common.waitTime(2);
        common.clickViewById(SettingPage.gallery_live);
        checkGalleryToLiveStatusAndTryAgain(1);
        stopGalleryLive();
        common.waitTime(2);
    }
    /**
     * 相册灭屏直播，相册第一个视频可以直播
     */
    private void galleryLiveScreenOff() throws Exception {
        common.clickViewById(SettingPage.video_timeText);//点击屏幕
        common.clickViewById(SettingPage.gallery_live_bottom);//点击live
        common.waitTime(2);
        common.clickViewById(SettingPage.gallery_live);
        checkGalleryToLiveStatusAndTryAgain(0);
        makeScreenOn();
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
    private void checkGalleryToLiveStatusAndTryAgain(int a) throws Exception {
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
                if (a==0){makeScreenOff();}//判断用例该亮灭屏
                common.waitTime(testTime-5);
            }
        }else {
            logger.info("LiveSuccess");
            if (a==0){makeScreenOff();}
            common.waitTime(testTime-5);
        }
    }
    /**
     * 停止相册直播
     * 如果发现已经异常中断，会产生一个3段锯齿波
     */
    private void stopGalleryLive()throws Exception{
    	UiObject galleryLiveCancel=common.findViewByText2("Retry");
        if (common.findViewByText2("broadcasting").exists()) {
            UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
            common.waitTime(1);
            common.clickViewByText("Yes");
            common.waitTime(2);
        }
        if (galleryLiveCancel.exists()) {
            logger.info("galleryLiveBreakOff");
            common.clickViewByText("No");
            common.waitTime(2);
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            common.waitTime(1);
            logger.info("SawtoothWaveNoteGalleryLiveBreakOff");
            //出现锯齿状电流波形
            launchCamera();
            for (int i=0;i<3;i++) {
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
        logger.info("已修改视场角为："+VideoAngle);
        common.waitTime(2);
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
        logger.info("已点击静音开关");
        common.waitTime(2);
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
        logger.info("已点击live&save开关");
        common.waitTime(2);
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
        logger.info("已修改为："+UpDown);
        common.waitTime(2);
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
//        common.waitTime(2);
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
//        initDevice();
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
        common.waitTime(2);
    }

    public void testForCurrent() throws Exception {
    	initUIAutomator(this.getName());
    	UiDevice.getInstance().pressHome();
    	common.startLog("*****Start to run " + runcase + " *****");
        String liveQuality480SD="480@25FPS(Bitrate0.3-4 Mbps)",
                liveQuality720HD="720@25FPS(Bitrate1.3-6 Mbps)",
                liveQuality1080HD="1080@25FPS(Bitrate1.5-10 Mbps)";
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
//            makeScreenOn();
//            changeSleepTime("Never");
            switchTo4G();
            storageFormat();//格式化储存空间
            closeWifi();
            //开启相机
            launchCamera();
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
            //四种分辨率灭屏录像2分钟
            makeToasts("start",5);
            configVideoQuality(videoQuality4KP25);//4K亮
            p2pScreenOn();
            p2pScreenOff();//4K灭
            configVideoQuality(videoQuality2KP25);//2K灭
            p2pScreenOff();
            configVideoQuality(videoQuality1080P120);//1080P120FPS
            p2pScreenOff();
            configVideoQuality(videoQuality1080P60);//1080P60FPS
            p2pScreenOff();
            configVideoQuality(videoQuality1080P25);//1080P25FPS
            p2pScreenOn();
            p2pScreenOff();
            configVideoQuality(videoQuality720P120);//720P120FPS
            p2pScreenOn();
            p2pScreenOff();
            configVideoQuality(videoQuality720P60);//720P60FPS
            p2pScreenOff();
            configVideoQuality(videoQuality720P25);//720P25FPS
            p2pScreenOn();
            p2pScreenOff();
          //相册直播
            common.stopCamera();
            common.waitTime(2);
            common.startGallery();
            galleryLiveScreenOn();//4G相册亮屏直播720P
            galleryLiveScreenOff();//4G相册灭屏直播720P
            common.stopGallery();
            openWifi();
            common.startGallery();
            galleryLiveScreenOff();//WIFI相册灭屏直播720P
            common.stopGallery();
            closeWifi();
            launchCamera();
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
            configVideoQuality(videoQuality480P120);//480P120FPS
            p2pScreenOff();
            configVideoQuality(videoQuality480P60);//480P25FPS
            p2pScreenOff();
            configVideoQuality(videoQuality480P25);//480P25FPS
            p2pScreenOff();
            //相册直播&录播
            common.stopCamera();
            common.waitTime(2);
            common.startGallery();
            galleryLiveScreenOff();//4G相册灭屏直播480P
            common.stopGallery();
            openWifi();
            common.startGallery();
            galleryLiveScreenOff();//WIFI相册灭屏直播480P
            common.stopGallery();
            closeWifi();
            launchCamera();
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
//            clickSwitch(switchName[2]);//开启录播        v3暂无该功能；添加需要修改方法
//            p2pScreenOn();
//            p2pScreenOff();
//            clickSwitch(switchName[2]);//关闭录播
            CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[4]);//"Slo_Mo" Modem
            common.waitTime(2);
            p2pScreenOff();
            CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);//"Lapse" Modem
            common.waitTime(2);
            p2pScreenOff();
            CameraAction.cameraVideo();//Video Modem
            common.waitTime(2);
            configVideoQuality(videoQuality1080P25);//1080P30FPS
            common.waitTime(2);
            common.waitTime(testTime);
            //主屏幕亮屏待机
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            common.waitTime(testTime*3);
            storageFormat();
            //直播
            launchCamera();
            CameraAction.cameraLive();;//Live Modem
//            makeToasts("start"+i,5);
//            common.waitTime(2);
            configVideoQuality(liveQuality480SD);
            live2ScreenOn();
            live2ScreenOff();
            configVideoQuality(liveQuality720HD);
            live2ScreenOn();
            live2ScreenOff();
            configVideoQuality(liveQuality1080HD);
            live2ScreenOn();
            live2ScreenOff();
            clickLiveAndSave();//开启直播保存
            common.waitTime(2);
            configVideoQuality(liveQuality480SD);
            live2ScreenOn();
            live2ScreenOff();
            configVideoQuality(liveQuality720HD);
            live2ScreenOn();
            live2ScreenOff();
            configVideoQuality(liveQuality1080HD);
            live2ScreenOn();
            live2ScreenOff();
            clickLiveAndSave();//关闭直播保存
            common.waitTime(1);
            configVideoQuality(liveQuality480SD);
            clickSwitch(switchName[0]);//开启高度计
            live2ScreenOff();
            clickSwitch(switchName[0]);//关闭高度计
            common.waitTime(2);
            clickSwitch(switchName[1]);//开启速度计
            live2ScreenOff();
            clickSwitch(switchName[1]);//关闭速度计
            common.waitTime(2);
            clickLiveMute();  //开启静音开关
            live2ScreenOff();
            clickLiveMute();//关闭静音开关
            clickSwitch(switchName[3]); //开启防抖开关
            live2ScreenOff();
            clickSwitch(switchName[3]);//关闭防抖开关
            clickSwitch(switchName[4]);//开启语音交互
            live2ScreenOff();
            clickSwitch(switchName[4]);//关闭语音交互
            clickSwitch(switchName[5]);//开启为倒置Auto
            live2ScreenOff();
            clickSwitch(switchName[5]);//关闭为倒置Auto
            configVideoAngle(videoAngle[2]);//视场角为超宽
            live2ScreenOff();
            configVideoAngle(videoAngle[1]);//视场角为宽
            live2ScreenOff();
            configVideoAngle(videoAngle[0]);//视场角为普通
            live2ScreenOff();
            configVideoAngle(videoAngle[0]);//视场角为普通(默认)
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            //切换网络模式为3G
            switchTo3G();
            common.waitTime(1);
            launchCamera();
            CameraAction.cameraLive();//Live Modem
            common.waitTime(2);
            configVideoQuality(liveQuality480SD);
            live2ScreenOn();
            live2ScreenOff();
            clickLiveAndSave();//开启直播保存
            live2ScreenOn();
            live2ScreenOff();
            clickLiveAndSave();//关闭直播保存
            common.waitTime(2);
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            openWifi();
            launchCamera();
            CameraAction.cameraLive();//Live Modem
            common.waitTime(2);
            clickLiveAndSave();//开启直播保存
            configVideoQuality(liveQuality480SD);
            live2ScreenOn();
            live2ScreenOff();
            clickLiveAndSave();//关闭直播保存
            common.waitTime(1);
            live2ScreenOn();
            live2ScreenOff();
            configVideoQuality(liveQuality720HD);
            live2ScreenOn();
            live2ScreenOff();
            configVideoQuality(liveQuality1080HD);
            live2ScreenOn();
            live2ScreenOff();

        }
        makeToasts("10秒后关机......",5);
        common.waitTime(10);
        Runtime.getRuntime().exec("reboot -p ");
    }
    public void testOther() throws Exception {
    	initUIAutomator(this.getName());
    	UiDevice.getInstance().pressHome();
    	common.startLog("*****Start to run " + runcase + " *****");
        String liveQuality480SD="480@25FPS(SD)",
                liveQuality480HD="480@25FPS(HD)",
                liveQuality720HD="720@25FPS(HD)";
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
                "Video&Live(beta)",//录播2
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
    	launchCamera();
    	clickSwitch(switchName[0]);//开启高度计
        live2ScreenOff();
        clickSwitch(switchName[0]);//关闭高度计
        common.waitTime(2);
        clickSwitch(switchName[1]);//开启速度计
        live2ScreenOff();
        clickSwitch(switchName[1]);//关闭速度计
        common.waitTime(2);
        clickLiveMute();  //开启静音开关
        live2ScreenOff();
        clickLiveMute();//关闭静音开关
        clickSwitch(switchName[3]); //开启防抖开关
        live2ScreenOff();
        clickSwitch(switchName[3]);//关闭防抖开关
        clickSwitch(switchName[4]);//开启语音交互
        live2ScreenOff();
        clickSwitch(switchName[4]);//关闭语音交互
        clickSwitch(switchName[5]);//开启为倒置Auto
        live2ScreenOff();
        clickSwitch(switchName[5]);//关闭为倒置Auto
        configVideoAngle(videoAngle[2]);//视场角为超宽
        live2ScreenOff();
        configVideoAngle(videoAngle[1]);//视场角为宽
        live2ScreenOff();
        configVideoAngle(videoAngle[0]);//视场角为普通
        live2ScreenOff();
        configVideoAngle(videoAngle[0]);//视场角为普通(默认)
        UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressBack();
        //切换网络模式为3G
        switchTo3G();
        common.waitTime(1);
        launchCamera();
        CameraAction.cameraLive();//Live Modem
        common.waitTime(2);
        configVideoQuality(liveQuality480SD);
        live2ScreenOn();
        live2ScreenOff();
        clickLiveAndSave();//开启直播保存
        live2ScreenOn();
        live2ScreenOff();
        clickLiveAndSave();//关闭直播保存
        common.waitTime(2);
        UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressBack();
        openWifi();
        launchCamera();
        CameraAction.cameraLive();//Live Modem
        common.waitTime(2);
        clickLiveAndSave();//开启直播保存
        configVideoQuality(liveQuality480SD);
        live2ScreenOn();
        live2ScreenOff();
        clickLiveAndSave();//关闭直播保存
        common.waitTime(1);
        live2ScreenOn();
        live2ScreenOff();
        configVideoQuality(liveQuality480HD);
        live2ScreenOn();
        live2ScreenOff();
        configVideoQuality(liveQuality720HD);
        live2ScreenOn();
        live2ScreenOff();
    makeToasts("10秒后关机......",5);
    common.waitTime(10);
    Runtime.getRuntime().exec("reboot -p ");
}

    public static void main(String[] args){
		new UiAutomatorHelper("AppSioeye", "Current.CurrentTestCase", "testOther", "2");
	}
//   
//    public void testDebugging() throws Exception {
//    	initUIAutomator(this.getName());
//    	common.startLog("*****Start to run " + runcase + " *****");
//    	
//    	common.stopCamera();
//    	common.startGallery();
//    	galleryLiveScreenOn();
//        common.infoLog("success");
//    }
}



//    public boolean sendKey(int keyCode, int metaState) {
//        if(DEBUG) {
//            Log.d(LOG_TAG, "sendKey (" + keyCode + ", " + metaState + ")");
//        }
//
//        long eventTime = SystemClock.uptimeMillis();
//        KeyEvent downEvent = new KeyEvent(eventTime, eventTime, 0, keyCode, 0, metaState, -1, 0, 0, 257);
//        if(this.injectEventSync(downEvent)) {
//            KeyEvent upEvent = new KeyEvent(eventTime, eventTime, 1, keyCode, 0, metaState, -1, 0, 0, 257);
//            if(this.injectEventSync(upEvent)) {
//                return true;
//            }
//        }
//        return false;
//    }

