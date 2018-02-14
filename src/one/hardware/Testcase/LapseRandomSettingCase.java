package one.hardware.Testcase;

/**随机执行Lapse的设置里面所有的操作,然后切换为Video，再切换回Lapse时查看之前的设置是否保存成功*/

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.ckt.demo.UiAutomatorHelper;
import one.hardware.Action.CameraAction;
import one.hardware.Page.Camera;
import one.hardware.Util.Base;
import java.lang.Math;
import java.util.logging.Logger;

public class LapseRandomSettingCase extends Base {
	
	public String getRightValue(String text) throws UiObjectNotFoundException{
		String value="";
		UiCollection videos = new UiCollection(new UiSelector().className(Camera.scrollView));
		int count = videos.getChildCount(new UiSelector().className(Camera.relativeLayout));
		for (int instance = 0; instance < count; instance++) {
			UiObject uiObject = videos.getChildByInstance(new UiSelector().className(Camera.relativeLayout),instance);
			UiObject sObject = uiObject.getChild(new UiSelector().className(Camera.TextView));
			if (uiObject.exists() && uiObject.isEnabled() == true&& sObject.exists()) {
				if (sObject.getText().equals(text)) {
					UiObject dataObj = uiObject.getChild(new UiSelector().className(Camera.TextView).index(1));
					value=dataObj.getText();
				}
			}
		}
		return value;
	}
	public boolean isExistVideoQuality(String vquality){
		boolean isexist =false;
		return isexist;
	}
	/**
	 * 随机20次修改延时录像设置
	 * 检查是否修改成功
	 * @throws Exception
	 */
	public void  testRandomChangeLapseSetting() throws Exception  {		
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();				 		 
			CameraAction.navconfig(Camera.nav_menu[5]);  

			for(int i=1;i<20;i++){	
				common.infoLog("第"+i+"次循环开始");
				int lapsesize = Camera.lapse_time.length;
				int anglesize = Camera.video_Angle.length;		
				int qualitysize = Camera.lapse_quality.length;			 
				int l =(int)(Math.random()*(lapsesize-1)); 			
				int a =(int)(Math.random()*(anglesize-1));			
				int q =(int)(Math.random()*(qualitysize-1));				

				//value to set
				String expect_lapse_quality =Camera.lapse_quality[q];
				common.infoLog(" 期望lapseuality为："+expect_lapse_quality);
				String expect_lapse_angle = Camera.video_Angle[a];
				common.infoLog(" 期望lapseAngle为："+expect_lapse_angle);
				String expect_lapse_lapsetime = Camera.lapse_time[l];
				//随机的视频质量当前机器是否支持,如果不支持，跳过
				if (CameraAction.isExistVideoQuality(expect_lapse_quality)) {
					//action
					CameraAction.configVideoQuality(5,expect_lapse_quality);
					common.infoLog("startConfigAngle");
					CameraAction.configVideoModeAndAngle(5,expect_lapse_angle);
					CameraAction.configTimeLapse(5,expect_lapse_lapsetime);

					CameraAction.navconfig(Camera.nav_menu[5]);				
					CameraAction.cameraSetting();

					//value get
					String active_lapse_lapsetime = getRightValue("Time Lapse");
					String active_lapse_angle = getRightValue("Video Angle");
					String active_lapse_quality = getRightValue("Video Quality");

					common.infoLog("quality|"+expect_lapse_quality+"|"+active_lapse_quality);
					common.infoLog("angle|"+expect_lapse_angle+"|"+active_lapse_angle);
					common.infoLog("lapsetime|"+expect_lapse_lapsetime+"|"+active_lapse_lapsetime);
					
					if (!expect_lapse_lapsetime.equals(active_lapse_lapsetime)){
						common.infoLog("Time Lapse和之前设置的不一样，不通过");
						common.infoLog("expect is:"+"["+expect_lapse_lapsetime+"]");
						common.infoLog("active is:"+"["+active_lapse_lapsetime+"]");
						common.failcase(runcase);
						break;
					}else {
						if (!expect_lapse_angle.equals(active_lapse_angle)){
							common.infoLog("Video Angle和之前设置的不一样，不通过");
							common.infoLog("expect is:"+"["+expect_lapse_angle+"]");
							common.infoLog("active is:"+"["+active_lapse_angle+"]");
							common.failcase(runcase);
							break;
						}else {
							if (!expect_lapse_quality.equals(active_lapse_quality)){
								common.infoLog("Video Quality和之前设置的不一样，不通过");
								common.infoLog("expect is:"+"["+expect_lapse_quality+"]");
								common.infoLog("active is:"+"["+active_lapse_quality+"]");
								common.failcase(runcase);
								break;
							}
						}									
					}		
					common.infoLog("第"+i+"次循环结束");
				}			
			}
			common.passcase();
			//no exception
			common.startLog( "*****End to run "  + runcase +"*****");
		}catch (Exception e) {
			common.handleException(e.getMessage());	
		}			
	}
//	public void testConfigAngle() throws Exception{
//		initUIAutomator(this.getName());
//		common.startLog("*****Start to run " + runcase + " *****");
//		common.initDevice();
//		common.startCamera();
//		CameraAction.configVideoModeAndAngle(5,"medium");
//	}
//	
//	
	
	public static void main(String[] args) {
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.LapseRandomSettingCase", "", "1");
	}

}
