/**
 * 
 */
package one.hardware.Action;

import java.io.IOException;
import java.util.Random;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import android.view.KeyEvent;
import one.hardware.Page.GalleryPage;
import one.hardware.Page.Camera;
import one.hardware.Util.Base;

/** 
* @ClassName: GalleryAction 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Yun.yang
* @date 2018年2月9日 上午9:42:05 
*  
*/
public class GalleryAction extends Base{
	
	public static Boolean checkLiveBottom() throws Exception {
		common.waitTime(2);
        if (!common.findViewById2(GalleryPage.gallery_live_bottom).exists()) {
        	UiDevice.getInstance().click(60,60);//点击屏幕
            common.waitTime(2);
            if (!common.findViewById2(GalleryPage.gallery_live_bottom).exists()){
                return false;
            }
            return true;
        }
        return true;
    }
    /*
    检查相册发起直播，发起成功后即停止
     */
    public static Boolean makeGalleryLive() throws Exception {
        if (!common.findViewById2(GalleryPage.gallery_delete_bottom).exists()){
        	UiDevice.getInstance().click(60,60);
        }
        common.clickViewById(GalleryPage.gallery_live_bottom);
        common.waitTime(2);
        common.clickViewById(GalleryPage.gallery_live);
        common.waitTime(1);
        common.clickViewById(GalleryPage.gallery_skip);
        common.findViewByText2("broadcasting").waitForExists(15000);
        if (!common.findViewByText2("broadcasting").exists()){
        	common.clickViewById(GalleryPage.gallery_live_bottom);
        	common.findViewByText2("broadcasting").waitForExists(15000);
            if (!common.findViewByText2("broadcasting").exists()){
                return false;
            }
            UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);//结束直播
            common.findViewByText2("Yes").waitForExists(2000);
            common.clickViewByText("Yes");
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
            return true;
        }
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);//结束直播
        common.waitTime(1);
        common.clickViewByText("Yes");
        UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressBack();
        return true;
    }
    /*
    发起相册直播，不停止；失败会尝试两次
     */
    public static void startGalleryLive() throws Exception {
    	common.clickViewById(GalleryPage.gallery_live_bottom);
        common.waitTime(2);
        common.clickViewById(GalleryPage.gallery_live);
        common.waitTime(1);
        common.clickViewById(GalleryPage.gallery_ok);
        common.waitTime(1);
        common.findViewByText2("broadcasting").waitForExists(15000);
        if (!common.findViewByText2("broadcasting").exists()){
        	common.clickViewById(GalleryPage.gallery_live_bottom);
        	common.findViewByText2("broadcasting").waitForExists(15000);
            if (!common.findViewByText2("broadcasting").exists()){
            	common.infoLog("两次相册直播失败");
            }
        }
    }
    /*
    结束相册直播
     */
    public static void stopGalleryLive() throws Exception {
    	common.waitTime(1);
        if (common.findViewByText2("broadcasting").exists()) {
        	UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
            common.clickViewByText("Yes");
            UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
        }
        if (common.findViewByText2("Confirm to exit live?").exists()){
        	common.clickViewByText("Yes");
        	UiDevice.getInstance().pressBack();
            UiDevice.getInstance().pressBack();
        }
        if (common.findViewByText2("Already Lived").exists()){
            
        }
    }
    

    /*
    在相册直播中检查按键功能
     */
    public static Boolean checkKeyDuringGalleryLive() throws Exception {
        if (!common.findViewByText2("broadcasting").exists()) {
            GalleryAction.startGalleryLive();
        }
        UiDevice.getInstance().pressBack();//返回键弹出结束提示
        common.waitTime(2);
        common.infoLog("00000000000000000000002222222");
        if (!common.findViewByText2("Confirm to exit live?").exists()){
        	common.infoLog("00000000000000000000002222222");
        	return false;}
        common.infoLog("按返回键，提示:Confirm to exit live?");
        UiDevice.getInstance().pressBack();//返回键消除结束直播提示
        common.waitTime(1);
        common.infoLog("按返回键，消除提示Confirm to exit live?");
        if (common.findViewByText2("Confirm to exit live?").exists()){
        	return false;}
        UiDevice.getInstance().pressBack();
        common.waitTime(1);
        common.infoLog("按返回键，提示:Confirm to exit live?");
        if (!common.findViewByText2("Confirm to exit live?").exists()){
        	return false;}
        common.clickViewByText("No");//cancel
        common.waitTime(1);
        common.infoLog("点击No，消除提示Confirm to exit live?");
        if (!common.findViewByText2("broadcasting").exists()) {
        	return false;}
        UiDevice.getInstance().pressBack();
        common.waitTime(1);
        if (!common.findViewByText2("Confirm to exit live?").exists()){
        	return false;}
        common.clickViewByText("Yes");//cancel
        common.waitTime(1);
        if (!common.findViewByText2("Already Lived").exists()){
        	return false;}
        GalleryAction.startGalleryLive();
        UiDevice.getInstance().pressMenu();//menu键
        common.waitTime(1);
        if (!common.findViewByText2("broadcasting").exists()) {
        	return false;}
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(1);
        if (!common.findViewByText2("Confirm to exit live?").exists()){
        	return false;}
        common.clickViewByText("No");//cancel
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
        common.waitTime(1);
        if (UiDevice.getInstance().isScreenOn()) {
        	return false;}
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
        common.waitTime(1);
        if (!UiDevice.getInstance().isScreenOn()) {
        	return false;}
        UiDevice.getInstance().pressKeyCode(276);//S键
        if (!common.findViewByText2("Confirm to exit live?").exists()) {
        	return false;}
        common.clickViewByText("No");//cancel
        UiDevice.getInstance().pressKeyCode(276);
        if (!common.findViewByText2("Confirm to exit live?").exists()) {
        	return false;}
        common.clickViewByText("Yes");//通过S键结束
        return true;
    }
    
    public static Boolean deleteOneVideo() throws Exception{
        if (!common.findViewById2(GalleryPage.gallery_delete_bottom).exists()){
        	UiDevice.getInstance().click(60, 60);
        }
        common.clickViewById(GalleryPage.gallery_delete_bottom);
        common.waitTime(3);
        if (common.findViewByText2("Do you want to delete this video ?").exists()){
        	common.clickViewByText("Yes");
        	return true;
        }else {
        	return false;
        }
    }
    /*
     * 如720P25FPS右边“支持直播”的标志 -  右边的值
     */
    public static Boolean checkResolutionRightString(String resolution) throws Exception {
        common.ScrollViewByText(resolution);
        UiCollection fatherOB=new UiCollection(new UiSelector().className("android.widget.ListView"));
        int countChild=fatherOB.getChildCount(new UiSelector().resourceId("com.hicam:id/item"));
        for(int i=0;i<countChild;i++){
        	UiObject objectS=fatherOB.getChildByInstance(new UiSelector().className("android.widget.RelativeLayout"), i);
        	UiObject objectTage=objectS.getChild(new UiSelector().resourceId("com.hicam:id/itemText"));
        	common.infoLog(objectTage.getText());
        	String supportText=null;
        	if (objectTage.getText().equals(resolution)) {
        		if (objectS.getChild(new UiSelector().resourceId("com.hicam:id/itemOptionText")).exists()) {
        			supportText=objectS.getChild(new UiSelector().resourceId("com.hicam:id/itemOptionText")).getText();
    				common.infoLog(supportText);
				}else {
					common.findViewById2("com.hicam:id/mode_setting_view").swipeUp(10);
					common.infoLog("滑动还要重来一次，八嘎");
					UiCollection fatherOB2=new UiCollection(new UiSelector().className("android.widget.ListView"));
			        int countChild2=fatherOB2.getChildCount(new UiSelector().resourceId("com.hicam:id/item"));
			        common.infoLog("123123标记1");
			        for(int a=0;a<countChild2;a++){
			        	UiObject objectS2=fatherOB2.getChildByInstance(new UiSelector().className("android.widget.RelativeLayout"), a);
			        	UiObject objectTage2=objectS2.getChild(new UiSelector().resourceId("com.hicam:id/itemText"));
			        	common.infoLog(objectTage2.getText());
			        	if (objectTage2.getText().equals(resolution)) {
			        		if (objectS2.getChild(new UiSelector().resourceId("com.hicam:id/itemOptionText")).exists()) {
			        			supportText=objectS2.getChild(new UiSelector().resourceId("com.hicam:id/itemOptionText")).getText();
			    				common.infoLog(supportText);
			    				}
			        	}
			        }
				}
        		if (supportText.equals("support live")) {
        			common.infoLog("pass");
					return true;
				}else {
					common.infoLog("fail");
					return false;	
				}

				}
			}return false;
        }
    public static void naveToQuality(String mode) throws Exception {
    	CameraAction.navconfig(mode);//普通录像
        CameraAction.cameraSetting();
        common.clickViewByText("Video Quality");
		
	}
    /*
    获取相册中右上角，字符串第几张和总数之间 间隔在该字符串中第几位；用来后期获取总数；第几张数据
     */
    public static int getGalleryIndex() throws Exception {
        String indexText=common.getViewTextById(GalleryPage.gallery_indexText);
        int rankingInt=0;
        char indexTextB[]=indexText.toCharArray();
        for (int i=0;i<indexText.length();i++){
            if (indexTextB[i]=='/'){
                rankingInt=i;
                return rankingInt;
            }
        }
        return rankingInt;
    }
    public static int getRankOfGallery() throws Exception {
        String indexText=common.getViewTextById(GalleryPage.gallery_indexText);
        int rank=Integer.valueOf(indexText.substring(0,getGalleryIndex()));
        return rank;
    }
    public static int getTotalOfGallery() throws Exception {
        String indexText=common.getViewTextById(GalleryPage.gallery_indexText);
        int total=Integer.valueOf(indexText.substring(getGalleryIndex()+1,indexText.length()));
        return total;
    }
    
    public static String getRandomBitrate(int min,int max){
        int randomInt=new Random().nextInt(max-min)+min;
        String random= String.valueOf(randomInt);
        return random;
    }
    public static int getGalleryBitrateMin() throws Exception {
        String bitrateTips=common.getViewTextById(GalleryPage.gallery_bitrate_tips);
//        logger.info("bitrateMin:"+bitrateTips.substring(19,getAppointCharRankInString('k',bitrateTips)));
//        logger.info("bitMinInt:"+Integer.parseInt(bitrateTips.substring(19,getAppointCharRankInString
//                ('k',bitrateTips))));
        return Integer.parseInt(bitrateTips.substring(19,getAppointCharRankInString('k',bitrateTips)));
    }
    public static int getGalleryBitrateMax() throws Exception {
        String bitrateTips=common.getViewTextById(GalleryPage.gallery_bitrate_tips);
//        logger.info("bitrateMaxInt: "+Integer.parseInt(bitrateTips.substring(getAppointCharRankInString
//                ('~',bitrateTips)+1,bitrateTips.length()-5)));
        return Integer.parseInt(bitrateTips.substring(getAppointCharRankInString
                ('~',bitrateTips)+1,bitrateTips.length()-5));
    }
    public static int getAppointCharRankInString(char appointChar,String appointString){
        int rankingInt=0;
        char indexTextB[]=appointString.toCharArray();
        for (int i=0;i<appointString.length();i++){
            if (indexTextB[i]==appointChar){
                rankingInt=i;
//                logger.info("rankingTnt:"+rankingInt);
                return rankingInt;
            }
        }
        return rankingInt;
    }
    public static void navToGalleryBitrateSetting() throws Exception {
        common.startGallery();
        while (!common.findViewById2(GalleryPage.gallery_live_bottom).exists()) {
            common.findViewById2(GalleryPage.gallery_root_view).swipeLeft(60);
            common.waitTime(1);
        }
        common.clickViewById(GalleryPage.gallery_live_bottom);
        common.clickViewById(GalleryPage.gallery_live);
    }
    public static String getStr(String str,String target, int times) {
        int i = 0,s = 0,a =0;
        while (i++ < times-1) {s = str.indexOf(target, s + 1);}
        i=0;
        while (i++ < times) {a = str.indexOf(target, a + 1);}
        return str.substring(s+1,a);
    }
    /*
    录播一段时间
     */
    public static void makeVideoAndLiveSomeTime(int time ) throws Exception {
        CameraAction.navconfig(Camera.nav_menu[1]);
        CameraAction.cameraSetting();
        common.waitTime(1);
        CameraAction.openCompoundButton("Video&Live(beta)");
        common.waitTime(1);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(time);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(2);
        CameraAction.cameraSetting();
        common.waitTime(1);
        CameraAction.openCompoundButton("Video&Live(beta)");
        common.waitTime(1);
    }
    public static void makeSlo_MoSomeTime(int time) throws Exception {
    	CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[4]);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(time);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(2);
    }

    public static void makeLapseSomeTime(int time,String VideoQuality,int timeLapse) throws Exception {
    	CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);
    	CameraAction.cameraSetting();
    	common.waitTime(1);
    	common.clickViewByText("Video Quality");
    	common.ScrollViewByText(VideoQuality);
    	common.clickViewByText(VideoQuality);
    	common.waitTime(1);
    	common.ScrollViewByText("Time Lapse");
    	common.clickViewByText("Time Lapse");
    	common.ScrollViewByText(timeLapse+"s");
    	common.clickViewByText(timeLapse+"s");
    	UiDevice.getInstance().pressBack();
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(time*timeLapse);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(2);
    }

    public static void makeLapseSomeTime(int time,int timeLapse) throws Exception {
    	CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[5]);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(time);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(2);
    }
 
    public static void makeVideoSomeTime(int time,String videoQuality) throws Exception {
    	CameraAction.configVideoQuality(videoQuality);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(time);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(2);
    }

    public static void makeVideoSomeTime(int time) throws Exception {
    	CameraAction.navconfig(Camera.nav_menu[1]);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(time);
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        common.waitTime(2);
    }
    /*
     * 检查相册中前三段视频是否是没有live标志
     */
 	public static Boolean check3VideoLiveButton() throws Exception{
 		common.startGallery();
         if (!GalleryAction.checkLiveBottom()){
         	common.findViewById2(GalleryPage.gallery_root_view).swipeLeft(60);
             if (!GalleryAction.checkLiveBottom()){
             	common.findViewById2(GalleryPage.gallery_root_view).swipeLeft(60);
                 if (GalleryAction.checkLiveBottom()){
                 	return false;
                 	}else{
                 		common.stopGallery();
                 		return true;
                 		}
             }else {return false;}
         }else {return false;}
 	}
//    /*
//    获取信号强度，并在屏幕上显示出来toast；返回
//     */
//    public static int get4GSignalStrength() throws IOException {
//        String [] dumpInformation=Runtime.getRuntime().exec("dumpsys telephony.registry").split
//                ("mSignalStrength=SignalStrength: ");
////        logger.info(dumpInformation[1].substring(0,dumpInformation[1].indexOf(" gsm|lte")));
//        int signalStrength=Integer.valueOf(getStr(dumpInformation[1]," ",9))+3;
////        logger.info("signalStrengthInt:"+signalStrength);
//        String signalStrengthString= String.valueOf(signalStrength);
//        String command = String.format("am broadcast -a com.sioeye.alert.action -e message %s -e time %d",signalStrengthString,5);
//        Runtime.getRuntime().exec(command);
//        return signalStrength;
//    }

}
