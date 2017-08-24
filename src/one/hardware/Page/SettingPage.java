package one.hardware.Page;

import one.hardware.Util.Base;

public class SettingPage extends Base{
	public static String storage_used_tx = "com.android.settings:id/storage_used_tx";
    public static String storage_avial_tx ="com.android.settings:id/storage_avial_tx";
    public static String storage_delete_img ="com.android.settings:id/storage_delete_img";
    public static String wifi_connected_state="com.android.settings:id/state";
    public static String wifi_switch_state="com.android.settings:id/switchState";
    public static String category_content="com.android.settings:id/category_content";
    public static String wifi_boottom="com.mediatek:id/imageswitch";
    public static String wifi_password="com.android.settings:id/password";

    //galleryPage
    public static String video_timeText="com.hicam.gallery:id/timeText";
    public static String gallery_live_bottom="com.hicam.gallery:id/gallery_bottom_share_im";
    public static String gallery_live="com.hicam.gallery:id/gallery_live";
    public static String gallery_live_retry="android:id/button1";
    public static String gallery_live_cancel="android:id/button2";

    //settingsPage    
    public static String settings_class_name="android.widget.FrameLayout";
    public static String settings_package_name="com.android.settings";
    public static String device_id="com.android.settings:id/device";
    
   
    //  休眠时间字符型数组
    public static String[] string_sleep_time={
    		"15 seconds",
    		"60 seconds",
    		"10 minutes",
    		"Never" };
   
    
    //休眠时间相应的等待时长，单位ms
    public static int [] int_sleep_time= {
    		15000,
    		60000,
    		600000,
    		612000,		
    };
	
	
    
    
    
}
