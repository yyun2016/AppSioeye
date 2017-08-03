package one.hardware.Testcase;
/**
 * 20min慢速录制自动停止
 * */
import one.hardware.Util.Base;
import java.util.HashSet;
import com.ckt.demo.UiAutomatorHelper;
import one.hardware.Action.CameraAction;
public class Video_Slo_Mo_20m extends Base{
	/**
	 * 20min慢速录制自动停止
	 * */
	public void testSloMo20min()throws Exception{
		try {
			String navm = one.hardware.Page.Camera.nav_menu[4];
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			CameraAction.navconfig(navm);
			HashSet<String> beforeTakeVideoList = common.FileList(CameraAction.getVideoPath());
			common.cameraKey();
			common.infoLog("等待20min");
			common.waitTime(1210);
			HashSet<String> afterTakeVideoList = common.FileList(CameraAction.getVideoPath());
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
			boolean lx1= common.findViewById2("com.hicam:id/recording_time2").exists();
			if (lx1||resultHashSet.size()!=1)
			{
				if (lx1) {
					common.infoLog("recordingTimeExisting");
				}
				if (resultHashSet.size()!=1) {
					common.infoLog("视频保存失败 ");
				}
				common.cameraKey();
				common.failcase(runcase);
			}
			else {
				common.passcase();
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			common.handleException(e.getMessage());
			// TODO: handle exception
		}
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.Video_Slo_Mo_20m", "testSloMo20m", "2");
	}

}
