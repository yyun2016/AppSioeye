package one.hardware.Action;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import com.android.uiautomator.core.UiObject;

import one.hardware.Page.Camera;
import one.hardware.Util.Base;

public class LiveAction extends Base {
	public static boolean checkLiveSuccess() throws Exception{
        if (!common.findViewById(Camera.recording_time_id).exists()) {
            common.infoLog("Live Failed");
            common.takeScreen("Live_Failed");
            return false;
            }
        else {
        	common.infoLog("Live succeed");
            common.takeScreen("Live_succeed");
            return true;
        }
	}
	public static boolean checkLiveAndSaveVideo(HashSet<String> resultHashSet) throws Exception{
		if (resultHashSet.size()==1) {
			String videopath = resultHashSet.iterator().next();
			common.infoLog("new file:"+videopath);
			String videoName = new File(videopath).getName();
			common.VideoInfo(videopath);
			FileManagerAction.playVideoByFileManager(videoName);
			
			if (common.findViewByText2("^Can't play this video.*").exists()) {
				common.infoLog(videoName+" 播放失败" + "-Can't play this video");
				common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
				common.infoLog("Can't play this video");
				return false;
			}else {
				common.infoLog(videoName+" 播放成功");
				return true;
			}
		}else {
			return false;
		}
	} 
}
