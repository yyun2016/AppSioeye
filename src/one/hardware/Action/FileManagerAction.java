package one.hardware.Action;

import java.io.File;

import one.hardware.Util.Base;

public class FileManagerAction extends Base {

	/**
	 * Click launcher_application_id按钮
	 */
	public static  void playVideoByFileManager(String videoName) throws Exception {
		common.startFileManager();
		if (common.isExistSDCard()) {
			common.findViewByText("SD card").clickAndWaitForNewWindow();
		}else{
		common.findViewByText("Rapid Storage").clickAndWaitForNewWindow();
		}
		common.ScrollViewByTextForFileManage("Video");
		common.clickViewByText("Video");
		common.findViewByText(videoName);
		common.clickViewByText(videoName);
		if (common.findViewByText2("Gallery").exists()) {
			common.clickViewByText("Gallery");
			if (common.findViewByText2("Always").exists()){
				common.clickViewByText("Always");
			}
		}
		
		CameraAction.playVideoBtn().clickAndWaitForNewWindow();
		common.waitTime(2);
		//Can't play this video.
		//android:id/message
		//android:id/button1
	}
}
