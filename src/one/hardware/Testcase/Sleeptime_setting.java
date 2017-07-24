package one.hardware.Testcase;
import com.android.uiautomator.core.UiDevice;
/**
 * 睡眠时间设置15s 60s 10min Never
 * */
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.ckt.demo.UiAutomatorHelper;

import android.text.style.ClickableSpan;
import android.widget.AbsListView.SelectionBoundsAdjuster;
import android.widget.ScrollView;
import one.hardware.Util.Base;

public class Sleeptime_setting extends Base {
	
	/*
	 * 启动settings->device->sleep time
	 * ruixiang.xu 20170724
	 * 
	 * 
	 * 
	 */
	public void navToSleepTime()throws Exception{
		
		
		try {
			
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
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
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			common.handleException(e.getMessage());
			
		}
		
		
		/*int i,j,k;
		System.out.println("苦逼寻找setting中");
		outer:
			for (k=1;k>0;k++)
			{
				if (k%2 == 0)
				{
					for (i=k;i>0;i--)
					{
						UiObject sObject = new UiObject(new UiSelector().className(one.hardware.Page.SettingPage.settings_class_name));
						sObject.swipeLeft(5);
						System.out.println("Orz..");
						if (common.findViewByText2("Settings").exists() == true)
						{
							System.out.println("o(∩_∩)o 找到了");
							break outer;
						}	
					}
				}
				else{
					for (j=k;j>0;j--)
					{
						UiObject sObject = new UiObject(new UiSelector().className("android.widget.FrameLayout"));
						sObject.swipeRight(10);
						System.out.println("Orz.");
						if (common.findViewByText2("Settings").exists() == true)
						{
							System.out.println("o(∩_∩)o 找到了");
							break outer;
						}				
					}		
				}
			}*/
		
	}
	
	
	/*
	 * 15s息屏 ，最后设置为常亮
	 */
	public void test15Seconds() throws Exception{
		try {
			initUIAutomator(this.getName());

			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			navToSleepTime();
			common.findViewByText("15 seconds").click();
			common.infoLog("等待15s");
			common.waitTime(16);
			if (!common.device.isScreenOn())
			{
				common.passcase();
			}
			else {
				common.failcase(runcase);
			}
		} catch (Exception e) {
			// TODO: Auto-generated catch block
			common.handleException(e.getMessage());
		}
		finally {
			common.device.wakeUp();
			common.findViewByText("Never").click();
			common.startLog( "*****End to run " + runcase + " *****");
		}
	}
	
	
	/*
	 * 60s息屏 ，最后设置为常亮
	 */
	public void test60Seconds() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			navToSleepTime();
			common.findViewByText("60 seconds").click();
			common.infoLog("等待60s");
			common.waitTime(61);
			if (!common.device.isScreenOn())
			{
				common.passcase();
			}
			else {
				common.failcase(runcase);
			}
		} catch (Exception e) {
			// TODO: Auto-generated catch block
			common.handleException(e.getMessage());
		}
		finally {
			common.device.wakeUp();
			common.findViewByText("Never").click();
			common.startLog( "*****End to run " + runcase + " *****");
		}
	}
	
	/*
	 * 10分钟息屏 ，最后设置为常亮
	 */
	public void test10minutes() throws Exception{
		try {
			initUIAutomator(this.getName());

			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			navToSleepTime();
			common.findViewByText("10 minutes").click();
			common.infoLog("等待10min");
			common.waitTime(601);
			if (!common.device.isScreenOn())
			{
				common.passcase();
			}
			else {
				common.failcase(runcase);
			}
		} catch (Exception e) {
			// TODO: Auto-generated catch block
			common.handleException(e.getMessage());
		}
		finally {
			common.device.wakeUp();
			common.findViewByText("Never").click();
			common.startLog( "*****End to run " + runcase + " *****");
		}
	}
	
	/*
	 * 常亮屏 ，亮屏10分钟后，检查屏幕状态，是亮屏则通过；
	 * 最后设置为常亮
	 */
	public void testNever() throws Exception{
		try {
			initUIAutomator(this.getName());

			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			navToSleepTime();
			common.findViewByText("Never").click();
			common.infoLog("等待10min");
			common.waitTime(601);
			if (common.device.isScreenOn())
			{
				common.passcase();
			}
			else {
				common.failcase(runcase);
			}
		} catch (Exception e) {
			// TODO: Auto-generated catch block
			common.handleException(e.getMessage());
		}
		finally {
			common.device.wakeUp();
			common.findViewByText("Never").click();
			common.startLog( "*****End to run " + runcase + " *****");
		}
	}
	//never设置
	public static void main(String args[]){
		new UiAutomatorHelper("one.hardware.Testcase", "one.hardware.Testcase.Sleeptime_setting", "", "1");
	}

}
