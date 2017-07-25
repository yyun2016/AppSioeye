package one.hardware.Testcase;

import java.io.File;
import java.util.HashSet;

import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Action.FileManagerAction;
import one.hardware.Action.VideoNode;
import one.hardware.Util.Base;

public class LapseCase extends Base{
	public static void main(String[] args) {
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.LapseCase", "testTLap2s48030AllAngle", "2");		
	}
	/**
	 * Name:testLapse2s48030AllAngle
	 * Description:延时录像延时为2秒，480P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:39:16
	 */
	public void testLapse2s48030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[0];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[0]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(2)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse2s72030AllAngle
	 * Description:延时录像延时为2秒，720P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:40:57
	 */
	public void testLapse2s72030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[1];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[0]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(2)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse2s108030AllAngle
	 * Description:延时录像延时为2秒，1080P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:41:27
	 */
	public void testLapse2s108030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[2];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[0]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(2)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse2s2K30AllAngle
	 * Description:延时录像延时为2秒，2k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:42:02
	 */
	public void testLapse2s2K30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[3];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[0]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(2)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse2s4k30AllAngle
	 * Description:延时录像延时为2秒，4k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:42:32
	 */
	public void testLapse2s4k30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[4];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[0]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(2)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse3s48030AllAngle
	 * Description:延时录像延时为3秒，480P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:42:57
	 */
	public void testLapse3s48030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[0];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[1]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(2)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(1080, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse3s72030AllAngle
	 * Description:延时录像延时为3秒，720P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:43:23
	 */
	public void testLapse3s72030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[1];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[1]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(3)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse3s108030AllAngle
	 * Description:延时录像延时为3秒，1080P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:43:45
	 */
	public void testLapse3s108030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[2];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[1]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(3)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse3s2k30AllAngle
	 * Description:延时录像延时为3秒，2k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:44:08
	 */
	public void testLapse3s2k30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[3];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[1]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(3)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse3s4k30AllAngle
	 * Description:延时录像延时为3秒，4k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:44:36
	 */
	public void testLapse3s4k30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[4];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[1]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(3)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse5s48030AllAngle
	 * Description:延时录像延时为5秒，480P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:45:00
	 */
	public void testLapse5s48030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[0];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[2]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(3)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse5s72030AllAngle
	 * Description:延时录像延时为5秒，720P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:45:33
	 */
	public void testLapse5s72030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[1];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[2]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(3)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(1080, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse5s108030AllAngle
	 * Description:延时录像延时为5秒，1080P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:45:54
	 */
	public void testLapse5s108030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[2];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[2]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(5)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse5s2k30AllAngle
	 * Description:延时录像延时为5秒，2k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:46:16
	 */
	public void testLapse5s2k30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[3];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[2]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(5)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse5s4k30AllAngle
	 * Description:延时录像延时为5秒，4k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:46:39
	 */
	public void testLapse5s4k30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[4];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[2]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(5)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse10s48030AllAngle
	 * Description:延时录像延时为10秒，480P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:47:00
	 */
	public void testLapse10s48030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[0];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(5)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse10s72030AllAngle
	 * Description:延时录像延时为10秒，720P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:47:22
	 */
	public void testLapse10s72030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[1];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(5)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse10s108030AllAngle
	 * Description:延时录像延时为10秒，1080P30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:47:57
	 */
	public void testLapse10s108030AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[2];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(5000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(5)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(1080, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse10s2k30AllAngle
	 * Description:延时录像延时为10秒，2k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:48:16
	 */
	public void testLapse10s2k30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[3];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(11000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(10)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testLapse10s4k30AllAngle
	 * Description:延时录像延时为10秒，4k30、全部视场角录像，验证录像
	 * author yun.yang
	 * date 2017年7月25日下午10:48:35
	 */
	public void testLapse10s4k30AllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.lapse_quality[4];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(5,quality);
				CameraAction.configVideoModeAndAngle(5, t);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(11000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(10)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	
	/**
	 * Name:testVideo48025AndLapse2sAllAngle
	 * Description:录像模式为48025切换到Lapse进行录像
	 * author yun.yang
	 * date 2017年7月25日下午10:23:44
	 */
	public void testVideo48025AndLapse2sAllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.video_quality[0];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(quality);
				CameraAction.configVideoAngle(angle);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[0]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(11000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(10)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(480, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testVideo72060AndLapse10sAllAngle
	 * Description:录像模式为72060切换到Lapse进行录像
	 * author yun.yang
	 * date 2017年7月25日下午10:34:28
	 */
	public void testVideo72060AndLapse10sAllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.video_quality[4];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(quality);
				CameraAction.configVideoAngle(angle);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(11000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(10)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testVideo108025AndLapse10sAllAngle
	 * Description:录像模式为108025切换到Lapse进行录像
	 * author yun.yang
	 * date 2017年7月25日下午10:35:29
	 */
	public void testVideo108025AndLapse10sAllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.video_quality[6];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(quality);
				CameraAction.configVideoAngle(angle);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(11000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(10)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(720, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	/**
	 * Name:testVideo1080120Lapse10sAllAngle
	 * Description:录像模式为1080120切换到Lapse进行录像
	 * author yun.yang
	 * date 2017年7月25日下午10:37:53
	 */
	public void testVideo1080120Lapse10sAllAngle() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			String videoFilePath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				videoFilePath="/storage/sdcard1/Video";
			}else {
				videoFilePath="/storage/sdcard0/Video";
			}
			boolean result = true;
			String quality = one.hardware.Page.Camera.video_quality[8];
			int anglesize = one.hardware.Page.Camera.video_Angle.length;
			for (int t = 0; t <anglesize; t++) {
				String angle =one.hardware.Page.Camera.video_Angle[t];
				common.infoLog("start to test angle-"+angle);
				common.initDevice();
				common.startCamera();
				CameraAction.configVideoQuality(quality);
				CameraAction.configVideoAngle(angle);
				CameraAction.configTimeLapse(one.hardware.Page.Camera.lapse_time[3]);
				
				HashSet<String> beforeTakeVideoList = common.FileList(videoFilePath);
				common.cameraKey();
				sleep(10000);
				CameraAction.cameraRecordTime();
				sleep(11000);
				boolean lapstatus = true;
				for (int i = 0; i < 20; i++) {
					if (!CameraAction.checklapsevalue(10)) {
						lapstatus=false;
					}else {
						lapstatus=true;
					}
					sleep(2000);
				}
				CameraAction.cameraRecordTime();
				common.cameraKey();
				sleep(3000);
				
				if (lapstatus) {
					HashSet<String> afterTakeVideoList = common.FileList(videoFilePath);
					HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
					if (resultHashSet.size()==1) {
						String videopath = resultHashSet.iterator().next();
						common.infoLog("new file:"+videopath);
						String videoName = new File(videopath).getName();
						VideoNode activeNode = common.VideoInfo(videopath);
						if (common.checkVideoInfo(1080, activeNode)) {
							common.infoLog("video info check success-"+videopath);
							FileManagerAction.playVideoByFileManager(videoName);
							if (common.findViewByText2("^Can't play this video.*").exists()) {
								common.infoLog(videoName+" 播放失败" + "-Can't play this video");
								common.findViewById2("android:id/button1").clickAndWaitForNewWindow();
								common.failcase(runcase);
								throw new Exception("FindObject" + "Can't play this video");
							}else {
								common.infoLog(videoName+" 播放成功");
								result= true;
							}
						}else {
							common.infoLog("video info check failed"+videopath);
							result= false;
							break;
						}
					}else {
						result= false;
						break;
					}
				}else {
					result= false;
					break;
				}
			}
			if (result) {
				common.passcase();
			}else {
				common.failcase(runcase);
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
}
