package one.hardware.Testcase;

import java.io.File;
import java.util.HashSet;

import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Action.FileManagerAction;
import one.hardware.Action.VideoNode;
import one.hardware.Util.Base;

public class VideoCase extends Base{
/*
 * 
 *String quality,String angle			 
 * 		     视频质量
 * 		  "480@25FPS",
 *		  "480@60FPS",
 *        "480@120FPS",
 *        "720@25FPS",
 *        "720@60FPS",
 *        "720@120FPS",
 *        "1080@25FPS",
 *        "1080@60FPS",
 *        "1080@120FPS",
 *        "2K@25FPS",
 *        "4K@25FPS",
 *        
 *         视场角
 *         "Super Wide",
 *         "Wide",
 *         "Medium"
 *       
 *   普通录像模式下设置不同的分辨率和视场角，录制视频，并检查     
 *         
 * */
	private void videoQualityAngle(String quality,String angle) throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.pmclear();
			common.startCamera();
			
			CameraAction.configVideoQuality(quality);
			CameraAction.configVideoAngle(angle);
			CameraAction.cameraVideo();
			
			HashSet<String> beforeTakeVideoList = common.FileList("/sdcard/video");
			common.cameraKey();
			CameraAction.cameraRecordTime();
			sleep(10000);
			CameraAction.cameraRecordTime();
			common.cameraKey();
			sleep(5000);
			HashSet<String> afterTakeVideoList = common.FileList("/sdcard/Video");
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
			
			if (resultHashSet.size()==1) {
				String videopath = resultHashSet.iterator().next();
				common.infoLog("new file:"+videopath);
				String videoName = new File(videopath).getName();
				common.VideoInfo(videopath);
				FileManagerAction.playVideoByFileManager(videoName);
				
				if (common.findViewByText2("^Can't play this video.*").exists()) {
					common.infoLog(videoName+" 播放失败" + "-Can't play this video");
					common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
					common.failcase(runcase);
					throw new Exception("FindObject" + "Can't play this video");
				}else {
					common.infoLog(videoName+" 播放成功");
					common.passcase();
				}
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	
	/*
	 * 超宽视场角，各个分辨率，录制并检查
	 * */
	public void testV48025fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[0];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	public void testV48060fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[1];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	public void testV480120fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[2];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	public void testV72025fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[3];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	public void testV72060fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[4];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	
	public void testV720120fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[5];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	
	public void testV108025fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[6];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	
	public void testV108060fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[7];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	
	
	public void testV1080120fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[8];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	
	
	public void testV2K25fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[9];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	
	public void testV4K25fpsSuperWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[10];
		String angle =one.hardware.Page.Camera.video_Angle[0];
		videoQualityAngle(quality,angle);
	}
	
	
	/*
	 * 宽视场角，各个分辨率，录制并检查   
	 * */
	public void testV48025fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[0];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	public void testV48060fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[1];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	public void testV480120fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[2];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	public void testV72025fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[3];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	public void testV72060fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[4];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	
	public void testV720120fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[5];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	
	public void testV108025fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[6];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	
	public void testV108060fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[7];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	
	
	public void testV1080120fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[8];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	
	
	public void testV2K25fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[9];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}
	
	public void testV4K25fpsWide() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[10];
		String angle =one.hardware.Page.Camera.video_Angle[1];
		videoQualityAngle(quality,angle);
	}	
	
	/*
	 * 普通视场角，各个分辨率，录制并检查  
	 * */
	public void testV48025fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[0];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	public void testV48060fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[1];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	public void testV480120fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[2];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	public void testV72025fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[3];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	public void testV72060fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[4];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	
	public void testV720120fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[5];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	
	public void testV108025fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[6];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	
	public void testV108060fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[7];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	
	
	public void testV1080120fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[8];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	
	
	public void testV2K25fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[9];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
	
	public void testV4K25fpsMedium() throws Exception{
		String quality = one.hardware.Page.Camera.video_quality[10];
		String angle =one.hardware.Page.Camera.video_Angle[2];
		videoQualityAngle(quality,angle);
	}
		
	


	public static void main(String[] args){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.VideoCase", "", "1");
	}



}



