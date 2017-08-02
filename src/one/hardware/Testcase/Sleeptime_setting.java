package one.hardware.Testcase;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.ckt.demo.UiAutomatorHelper;

import android.text.style.ClickableSpan;
import android.widget.AbsListView.SelectionBoundsAdjuster;
import android.widget.ScrollView;
import one.hardware.Util.Base;

/**
 * ˯测试休眠时间：15s 60s 10min Never
 * */



public class Sleeptime_setting extends Base {
	
	/*
	 * 打开settings->device->sleep time->设置不同的休眠时间
	 * ruixiang.xu 20170724
	 * 
	 * 	string_sleep_time 选择不同的休眠时间，字符型数组  "15 seconds","60 seconds","10 minutes","Never"
	 *  int_sleep_time  设置不同的休眠时间后，等待响应的时间，int型数组，单位是ms。
	 * 
	 */
	private void navToSleepTime(String sleep,int time)throws Exception{
		
		
		try {
			
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			device.wakeUp();
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().pressBack();
			
			common.initDevice();
			common.startSettings();
			common.clickViewByText("Device");
			common.ScrollViewByText("Display");
			common.clickViewByText("Display");
			common.ScrollViewByText2("Sleep");
			common.clickViewByText("Sleep");
			
			common.findViewByText(sleep).click();
			common.infoLog("正在测试"+sleep);

			sleep(time+4000); // 等待响应的休眠时间
			
			
			if(time!=700) {
				
				if (!common.device.isScreenOn())
				{
					common.passcase();
				}else {
					common.failcase(runcase);
				}
				
				
				
			}else {
				common.infoLog("正在测试never休眠时间项");
				if (common.device.isScreenOn())
				{
					common.passcase();
				}
				else {
					common.failcase(runcase);
				}
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			common.handleException(e.getMessage());
			
		}finally {
			device.wakeUp();
			common.findViewByText("Never").click();
			common.startLog( "*****End to run " + runcase + " *****");
		}
		
	}
	
	
	/*
	 * 15s
	 */
	public void test15Seconds() throws Exception {
		String sleep=one.hardware.Page.SettingPage.string_sleep_time[0];
		int time=one.hardware.Page.SettingPage.int_sleep_time[0];
		navToSleepTime(sleep, time);
	}
	
	/*
	 * 60s
	 */
	public void test60Seconds() throws Exception {
		String sleep=one.hardware.Page.SettingPage.string_sleep_time[1];
		int time=one.hardware.Page.SettingPage.int_sleep_time[1];
		navToSleepTime(sleep, time);
	}
	
	/*
	 * 10分钟
	 */
	public void test10minutes() throws Exception {
		String sleep=one.hardware.Page.SettingPage.string_sleep_time[2];
		int time=one.hardware.Page.SettingPage.int_sleep_time[2];
		navToSleepTime(sleep, time);
	}
	
	/*
	 * never
	 */
	public void testNever() throws Exception {
		String sleep=one.hardware.Page.SettingPage.string_sleep_time[3];
		int time=one.hardware.Page.SettingPage.int_sleep_time[3];
		navToSleepTime(sleep, time);
	}
	
	
	public static void main(String args[]){
		new UiAutomatorHelper("one.hardware.Testcase", "one.hardware.Testcase.Sleeptime_setting", "test15Seconds", "1");
	}

}
	
	