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
 * ˯��ʱ������15s 60s 10min Never
 * */



public class Sleeptime_setting extends Base {
	
	/*
	 * ����settings->device->sleep time->���ò�ͬ��Ϣ��ʱ�䣬���ȴ�
	 * ruixiang.xu 20170724
	 * 
	 * 	string_sleep_time ��������Ϣ��
	 *  int_sleep_time  ���ڵȴ�Ϣ��
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
			common.infoLog("�ȴ�Ϣ��ʱ��"+sleep);

			sleep(time+4000); // Ϊ�˵ȴ��������ߣ�����4s�ȴ�ʱ��
			
			
			if(time!=700) {
				
				if (!common.device.isScreenOn())
				{
					common.passcase();
				}else {
					common.failcase(runcase);
				}
				
				
				
			}else {
				common.infoLog("����never������");
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
	 * 15sϢ��
	 */
	public void test15Seconds() throws Exception {
		String sleep=one.hardware.Page.SettingPage.string_sleep_time[0];
		int time=one.hardware.Page.SettingPage.int_sleep_time[0];
		navToSleepTime(sleep, time);
	}
	
	/*
	 * 60sϢ��
	 */
	public void test60Seconds() throws Exception {
		String sleep=one.hardware.Page.SettingPage.string_sleep_time[1];
		int time=one.hardware.Page.SettingPage.int_sleep_time[1];
		navToSleepTime(sleep, time);
	}
	
	/*
	 * 10����Ϣ��
	 */
	public void test10minutes() throws Exception {
		String sleep=one.hardware.Page.SettingPage.string_sleep_time[2];
		int time=one.hardware.Page.SettingPage.int_sleep_time[2];
		navToSleepTime(sleep, time);
	}
	
	/*
	 * never������Ļ
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
	
	