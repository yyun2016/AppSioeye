package one.hardware.Action;

import one.hardware.Util.Common;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;

import one.hardware.Util.Base;

public class SettingAction extends Base {
	public static void navToStorage() throws Exception {
        Common.waitTime(2);
        common.clickViewByText("Device");
        common.ScrollViewByText2("Storage");
        common.clickViewByText("Storage");
	}
	
	public static void navToWifi() throws Exception {
        common.startSettings();
        common.clickViewByText("Connection");
        common.ScrollViewByText2("Wi-Fi");
        common.clickViewByText("Wi-Fi");
    }
	public static void navToPreferredNetworkType() throws Exception {
        common.startSettings();
        common.clickViewByText("Connection");
//        common.waitTime(2);
        common.ScrollViewByText2("SimCard Info");
        common.clickViewByText("SimCard Info");
        common.clickViewByText("Preferred network type");
    }
	public static void navToSleepTime() throws Exception {
        common.startSettings();
        common.clickViewByText("Device");
        common.waitTime(2);
        
        common.clickViewByText("Display");
        common.waitTime(2);
        common.ScrollViewByText2("Sleep");
        common.clickViewByText("Sleep");
    }
}
