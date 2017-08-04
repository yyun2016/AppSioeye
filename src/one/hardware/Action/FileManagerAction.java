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
		common.waitTime(1);
		common.findViewByText(videoName);
		common.clickViewByText(videoName);
		common.clickViewByText("Gallery");
		if (common.findViewByText2("Just once").exists()){
		common.clickViewByText("Just once");
		}
		CameraAction.playVideoBtn().clickAndWaitForNewWindow();
		common.waitTime(2);
		//Can't play this video.
		//android:id/message
		//android:id/button1
	}
}
