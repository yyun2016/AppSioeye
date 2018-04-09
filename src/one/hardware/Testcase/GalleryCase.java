/**
 * 
 */
package one.hardware.Testcase;
import com.ckt.demo.UiAutomatorHelper;

import com.android.uiautomator.core.UiDevice;

import android.view.KeyEvent;
import one.hardware.Page.GalleryPage;
import one.hardware.Action.AccountAction;
import one.hardware.Action.CameraAction;
import one.hardware.Action.GalleryAction;
import one.hardware.Page.Account;
import one.hardware.Page.Camera;
import one.hardware.Util.Base;
import one.hardware.Util.Common;

/** 
* @ClassName: GalleryCase 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Yun.yang
* @date 2018年2月9日 上午9:34:16 
*  
*/
public class GalleryCase extends Base{

	/**
	 * Name:main
	 * Description:
	 * author yun.yang
	 * date 2018年2月9日上午9:34:16
		 */

//	    @BeforeClass
//	    public static void beforeClassLogin() throws Exception {
//	        common.initIris4GWithoutDelete();
//	        if (!AccountAction.isLogin()) {
//	            AccountAction.loginAccount(Constant.getUserName("sioeye_id"),Constant.getPassword("sioeye_password"));}
//	        CameraAction.makeSlo_MoSomeTime(5);
//	        common.startGallery();
//	        GalleryAction.makeGalleryLive();
//	    }
//	    @Before
//	    public void setup() throws Exception {
//	        common.initIris4GWithoutDelete();
//	    }
	    
	/*检查相册中位于第一和第二的两个视频的live标志，第一个应该没有直播标志，第二个有，并且发起相册直播
    */
   public void checkTowVideoLiveButtonAndLive() throws Exception {
       common.startGallery();
       if (!GalleryAction.checkLiveBottom()){
           common.findViewById2(GalleryPage.gallery_root_view).swipeLeft(60);
           if (GalleryAction.checkLiveBottom()){
               if (!GalleryAction.makeGalleryLive()){
               	common.failcase(runcase);
               }else{common.passcase();}
           }else {common.failcase(runcase);}
       }else {common.failcase(runcase);}
   }
   
	/*
    录制两段延时视频，在相册中，第一段无法直播；第二段可以直播；即先录制可以直播的视频再录制不能直播的视频
    1.检查live标志是否正确；
    2.检查第二段是否能够发起相册直播
     */
	    public void checkTwoLapseVideoLiveButtonAndLive(String VideoQuality,int timeLapse) throws Exception {
	        GalleryAction.makeLapseSomeTime(12,VideoQuality,timeLapse);
	        GalleryAction.makeLapseSomeTime(3,timeLapse);
	        checkTowVideoLiveButtonAndLive();
	    }
	    
	    
	    public static void main(String[] args){
			new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.GalleryCase", "testGalleryClipAndLiveWithNotLogin", "2");
		}
	    /*
	    case 1
	    检查慢速录制的大于10秒和小于10秒视频，相册是否有live选项；大于10秒的视频发起相册直播
	     */
	    public void testCheckSlo_MoVideoLiveButton() throws Exception {
	    	try {
	    	initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDeviceWihtoutDeleteVideo();
			common.startCamera();
			
			GalleryAction.makeSlo_MoSomeTime(5,Camera.lapse_quality[0]);
			GalleryAction.makeSlo_MoSomeTime(1,Camera.lapse_quality[0]);
	        checkTowVideoLiveButtonAndLive();
	        common.startLog( "*****End to run " + runcase + " *****");
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
	    	}
	    }
	    /* case 2
	    检查录制的10s延时录制的大于10秒和小于10秒视频，相册是否有live选项；大于10秒的视频发起相册直播
	     */
	    public void testCheckLapse10sVideoLiveButton() throws Exception {
	    	try {
	    	initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDeviceWihtoutDeleteVideo();
			common.startCamera();
			
	        checkTwoLapseVideoLiveButtonAndLive(Camera.lapse_quality[0],GalleryPage.timeLapse[3]);//480P、10s延时
	        common.startLog( "*****End to run " + runcase + " *****");
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
	    	}
	    }
	    /* case 3
	    检查录制的2s延时录制的大于10秒和小于10秒视频，相册是否有live选项；大于10秒的视频发起相册直播
	     */
	    public void testCheckLapse2sVideoLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				
				checkTwoLapseVideoLiveButtonAndLive(Camera.lapse_quality[0],GalleryPage.timeLapse[0]);//480P、2s延时
				common.startLog( "*****End to run " + runcase + " *****");
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
	    	}
	    }
	    /* case 4
	    检查录制的3s延时录制的大于3秒和小于10秒视频，相册是否有live选项；大于10秒的视频发起相册直播
	     */
	    public void testCheckLapse3sVideoLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				
				checkTwoLapseVideoLiveButtonAndLive(Camera.lapse_quality[0],GalleryPage.timeLapse[1]);//480P、3s延时
				common.startLog( "*****End to run " + runcase + " *****");
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
	    		common.handleException(e.getMessage());
	    	}
	    }
	    /* case 5
	    检查录制的5s延时录制的大于5秒和小于10秒视频，相册是否有live选项；大于10秒的视频发起相册直播
	     */
	    public void testCheckLapse5sVideoLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				
				checkTwoLapseVideoLiveButtonAndLive(Camera.lapse_quality[0],GalleryPage.timeLapse[2]);//480P、5s延时
				common.startLog( "*****End to run " + runcase + " *****");
	    		} catch (Exception e) {
	    		// TODO Auto-generated catch block
	    		common.handleException(e.getMessage());
	    	}
	    }
	    /* case 6
	    检查720P延时录制的大于2秒和小于10秒视频，相册是否有live选项；大于10秒的视频发起相册直播
	     */
	    public void testCheckLapse720PVideoLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				
				checkTwoLapseVideoLiveButtonAndLive(Camera.lapse_quality[1],GalleryPage.timeLapse[0]);//720P、2s延时
				common.startLog( "*****End to run " + runcase + " *****");
	    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		common.handleException(e.getMessage());
	    	}
	    }
	    /* case 7
	    检查录制的大于2秒延时1080P延时视频是否可直播
	     */
	    public void testCheckLapse1080PVideoLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				
				GalleryAction.makeLapseSomeTime(12,Camera.lapse_quality[2],GalleryPage.timeLapse[0]);
				common.startGallery();
				if (GalleryAction.checkLiveBottom()){
	            common.failcase(runcase);
				}else{common.passcase();}
				common.startLog( "*****End to run " + runcase + " *****");
	        
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			common.handleException(e.getMessage());
	    		}
	    }
	    /* Case 8
	    检查普通720P录像视频
	     */
	    public void testCheckVideo720PLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				
	        GalleryAction.makeVideoSomeTime(12,Camera.video_quality[3]);//720P25FPS
	        GalleryAction.makeVideoSomeTime(2);
	        checkTowVideoLiveButtonAndLive();
	        
	        common.startLog( "*****End to run " + runcase + " *****");
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			common.handleException(e.getMessage());
    		}
	    }
	    /* Case 9
	    检查普通480P录像视频
	     */
	    public void testCheckVideo480PLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				
	    	GalleryAction.makeVideoSomeTime(12,Camera.video_quality[0]);//480P25FPS
	    	GalleryAction.makeVideoSomeTime(2);
	        checkTowVideoLiveButtonAndLive();
	        
	        common.startLog( "*****End to run " + runcase + " *****");
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			common.handleException(e.getMessage());
    		}
	    }
	    /* case 10
	    检查1080P25FPS 720P60FPS、480P120FPS +480P60FPS+720P120FPS+1080P60FPS + 1080P120FPS +2K +4K  普通录像视频是否无live标志
	     */
	    public void testCheckVideoWrongResolutionLiveButton() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
			
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[1]);//480P60FPS
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[2]);//480P120FPS
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[4]);//720P60FPS
				if (!GalleryAction.check3VideoLiveButton()) {
					common.failcase(runcase);
				}
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[5]);//720P120FPS
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[6]);//1080P25FPS
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[7]);//1080P60FPS
				if (!GalleryAction.check3VideoLiveButton()) {
					common.failcase(runcase);
				}
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[8]);//1080P120FPS
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[9]);//2K
				GalleryAction.makeVideoSomeTime(12,Camera.video_quality[10]);//4K
				GalleryAction.check3VideoLiveButton();
				if (GalleryAction.check3VideoLiveButton()) {
					common.passcase();
				}else{
					common.failcase(runcase);
				}
				common.startLog( "*****End to run " + runcase + " *****");
    			} catch (Exception e) {
    			// TODO Auto-generated catch block
    				common.handleException(e.getMessage());
    				}
	    }
	    /*case 11  
	    case编号：SI-1863:中断直播
	    相册直播中按键操作;返回键、电源键、拍照键、S键
	     */
	    public void testClickKeyDuringGalleryLive() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				
				common.startCamera();
				GalleryAction.makeSlo_MoSomeTime(15,Camera.lapse_quality[0]);
				common.startGallery();
				GalleryAction.checkLiveBottom();
				GalleryAction.startGalleryLive();
				if (!GalleryAction.checkKeyDuringGalleryLive()) {
					GalleryAction.stopGalleryLive();
					common.failcase(runcase);
				} else {
					GalleryAction.stopGalleryLive();
					common.passcase();
				}
				
				common.startLog( "*****End to run " + runcase + " *****");
			} catch (Exception e) {
			// TODO Auto-generated catch block
				common.handleException(e.getMessage());
				}
	    }
	    /* case 12
	    case编号：SI-2001:检查‘支持直播’标志
	    检查“支持直播”标志；普通录像模式720P25FPS；480P25FPS   延时录像720P30FPS；480P30FPS；慢速480P30FPS
	     */
	    public void testCheckSupport() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				common.startCamera();
				int testResult=0;
				
	    	GalleryAction.naveToQuality(Camera.nav_menu[1]);//普通
	        if (!GalleryAction.checkResolutionRightString(Camera.video_quality[3])){//720P25FPS
	            testResult=testResult+1;}
	        if (!GalleryAction.checkResolutionRightString(Camera.video_quality[0])){//480P25FPS
	        	testResult=testResult+1;}
	        GalleryAction.naveToQuality(Camera.nav_menu[5]);//延时
	        if (!GalleryAction.checkResolutionRightString(Camera.lapse_quality[1])){//720P30FPS
	        	testResult=testResult+1;}
	        if (!GalleryAction.checkResolutionRightString(Camera.lapse_quality[0])){//480P30FPS
	        	testResult=testResult+1;}
	        GalleryAction.naveToQuality(Camera.nav_menu[4]);//慢速
	        if (!GalleryAction.checkResolutionRightString(Camera.lapse_quality[0])){//480P30FPS
	        	testResult=testResult+1;}
	        if (testResult==0) {
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
//	    /*case 13  V3无录播
//	    case编号：SI-1865:录播
//	    检查录播视频，是否存在“已直播”标志，以及发起直播
//	     */
//	    public void testVideoAndLiveMakeGalleryLive() throws Exception {
//	    	try {
//		    	initUIAutomator(this.getName());
//				common.startLog("*****Start to run " + runcase + " *****");
//				common.initDeviceWihtoutDeleteVideo();
//				common.startCamera();
//				int testResult=0;
//				
//	    	GalleryAction.makeVideoAndLiveSomeTime(13);
//	        common.startGallery();
//	        if (!common.findViewByText2("Already Lived").exists()){
//	            if (!GalleryAction.makeGalleryLive()){
//	            	common.failcase(runcase);
//	            }
//	        }else {
//	        	common.failcase(runcase);
//	        }
//	        common.startLog( "*****End to run " + runcase + " *****");
//			} catch (Exception e) {
//			// TODO Auto-generated catch block
//				common.handleException(e.getMessage());
//				}
//	    }
	    /*case 14   未完
	    case编号：SI-1874:删除视频
	    检查删除已直播的视频
	     */
	    public void testDeleteAlreadyLivedVideo() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				
				
	        common.startGallery();
	        while (!common.findViewByText2("Already Lived").exists()) {
	            common.findViewById2(GalleryPage.gallery_root_view).swipeLeft(60);
	            common.waitTime(1);
	        }
	        int originalRank=GalleryAction.getRankOfGallery();
	        common.infoLog("originalRank:"+originalRank);
	        int originalTotal=GalleryAction.getTotalOfGallery();
	        common.infoLog("originalTotal:"+originalTotal);
	        GalleryAction.deleteOneVideo();
	        if (originalTotal==GalleryAction.getTotalOfGallery()+1){
	            if (originalRank==GalleryAction.getRankOfGallery()+1||(originalRank==GalleryAction.getRankOfGallery()
	                    &&originalRank==1)){
	            	common.passcase();
	            }else {common.failcase(runcase);}
	        }else {common.failcase(runcase);}
	        
	        common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
		// TODO Auto-generated catch block
			common.handleException(e.getMessage());
			}
	    }
	    /*case 15
	    用例编号：SI-2287:设置为固定码率（码率上下限相等）
	     1.随机设置一个固定码率；及前后码率值设置为一样；
	     */
	    public void testCheckGalleryFixedBitrate() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				
				GalleryAction.navToGalleryBitrateSetting();
				String inputNum = GalleryAction.getRandomBitrate(GalleryAction.getGalleryBitrateMin(),GalleryAction.getGalleryBitrateMax());
				GalleryAction.clearText(GalleryPage.gallery_bitrate_custom_min);
				common.findViewById2(GalleryPage.gallery_bitrate_custom_min).setText(inputNum);
				GalleryAction.clearText(GalleryPage.gallery_bitrate_custom_max);
				common.findViewById2(GalleryPage.gallery_bitrate_custom_max).setText(inputNum);
				if (common.findViewById2(GalleryPage.gallery_bitrate_error_tips).exists()){
					common.failcase(runcase);
				}
				common.passcase();
				common.startLog( "*****End to run " + runcase + " *****");
	    	} catch (Exception e) {
		// TODO Auto-generated catch block
			common.handleException(e.getMessage());
			}
	    }
	    /*case 16
	    用例编号：SI-2286:检查码率边界值
	    1设置比最小码率更小的值  ； 2.设置比最大码率更大的值 3.设置允许的范围值
	     */
	    public void testCheckGalleryBitrateBoundaryValue() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				
				GalleryAction.navToGalleryBitrateSetting();
				String smaller = GalleryAction.getRandomBitrate(0,GalleryAction.getGalleryBitrateMin());
				String bigger= GalleryAction.getRandomBitrate(GalleryAction.getGalleryBitrateMax(),99999999);
				GalleryAction.clearText(GalleryPage.gallery_bitrate_custom_min);
				common.findViewById2(GalleryPage.gallery_bitrate_custom_min).setText(smaller);
				if (!common.findViewById2(GalleryPage.gallery_bitrate_error_tips).exists()){
					common.failcase(runcase);
				}
				GalleryAction.clearText(GalleryPage.gallery_bitrate_custom_min);
				common.findViewById2(GalleryPage.gallery_bitrate_custom_min).setText(GalleryAction.getRandomBitrate
	                (GalleryAction.getGalleryBitrateMin(), GalleryAction.getGalleryBitrateMax()));
				if (common.findViewById2(GalleryPage.gallery_bitrate_error_tips).exists()){
					common.failcase(runcase);
				}
				GalleryAction.clearText(GalleryPage.gallery_bitrate_custom_max);
				common.findViewById2(GalleryPage.gallery_bitrate_custom_max).setText(bigger);
				if (!common.findViewById2(GalleryPage.gallery_bitrate_error_tips).exists()){
					common.failcase(runcase);
				}
				GalleryAction.clearText(GalleryPage.gallery_bitrate_custom_max);
				common.findViewById2(GalleryPage.gallery_bitrate_custom_max).setText(GalleryAction.getRandomBitrate
	                (GalleryAction.getGalleryBitrateMin(), GalleryAction.getGalleryBitrateMax()));
				if (common.findViewById2(GalleryPage.gallery_bitrate_error_tips).exists()){
	        	common.failcase(runcase);
				}
				common.passcase();
				common.startLog( "*****End to run " + runcase + " *****");
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			common.handleException(e.getMessage());
	    		}
	    }
	    /* case 17
	    case编号：SI-1870:直播中亮灭屏   相册直播中亮灭屏， 亮灭屏同相机熄屏设置一致  5次
	     */
	    public void testCheckGalleryLiveWithScreenOffOn() throws Exception {
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				
	        common.startGallery();
	        while (!GalleryAction.checkLiveBottom()) {
	            common.findViewById2(GalleryPage.gallery_root_view).swipeLeft(60);}
	        GalleryAction.startGalleryLive();
	        for (int i=0;i<10;i++){
	            UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
	            common.waitTime(1);
	        }
	        common.waitTime(2);
	        if (UiDevice.getInstance().isScreenOn()){
	        	GalleryAction.stopGalleryLive();
	        	common.passcase();
	        }else {
	        	common.failcase(runcase);
	            UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);//亮屏
	            GalleryAction.stopGalleryLive();
	        }
	        common.startLog( "*****End to run " + runcase + " *****");
			} catch (Exception e) {
			// TODO Auto-generated catch block
				common.handleException(e.getMessage());
				}
	    }
	    /* case 18
	     case编号：SI-1883:未登录账号      1录制一段720@25、或者480@25的普通视频 2点击直播button  3 登录账号  4 正常跳转
		到剪辑直播和立即直播界面
		立即直播登陆后跳转到码率选择页面
	     */
	    public void testGalleryLiveNowWithNotLogin() throws Exception{
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				AccountAction.logout();
				common.startGallery();
				
				GalleryAction.navToGalleryLoginWithLiveNow();
		        GalleryAction.galleryLogin();
		        if (!AccountAction.loginSuccess()) {
					common.failcase(runcase);}
		        if (common.findViewByText2("Login").exists()) {
		        	common.failcase(runcase);}
		        if (!common.findViewByText2("Skip").exists()) {
		        	common.failcase(runcase);}
		        else{
		        	common.passcase();}

				common.startLog( "*****End to run " + runcase + " *****");
			} catch (Exception e) {
			// TODO Auto-generated catch block
				common.handleException(e.getMessage());
				}
	    }
	    /* case 19
	     case编号：SI-1883:未登录账号      1录制一段720@25、或者480@25的普通视频 2点击直播button  3 登录账号  4 正常跳转
		到剪辑直播和立即直播界面
		剪切并直播登陆后跳转到 剪切页面
	     */
	    public void testGalleryClipAndLiveWithNotLogin() throws Exception{
	    	try {
		    	initUIAutomator(this.getName());
				common.startLog("*****Start to run " + runcase + " *****");
				common.initDeviceWihtoutDeleteVideo();
				AccountAction.logout();
				common.startGallery();
				
				GalleryAction.navToGalleryLoginWithClipAndLive();
		        GalleryAction.galleryLogin();
		        if (!AccountAction.loginSuccess()) {
					common.failcase(runcase);}
		        if (common.findViewByText2("Login").exists()) {
		        	common.failcase(runcase);}
		        if (!common.findViewByText2("View").exists()) {
		        	common.failcase(runcase);}
		        else{
		        	common.passcase();}

				common.startLog( "*****End to run " + runcase + " *****");
			} catch (Exception e) {
			// TODO Auto-generated catch block
				common.handleException(e.getMessage());
				}
	    }
	    /*case 20
	    case编号：SI-2006:延时视频剪辑后直播  1延时视频剪辑后发起直播
	     */
	    
	    
	    
	    

}
