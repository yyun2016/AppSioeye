package one.hardware.Testcase;

import java.util.HashSet;

import com.ckt.demo.UiAutomatorHelper;

import one.hardware.Action.CameraAction;
import one.hardware.Util.Base;

/**
 * 图片设置
 *   "18M(4:3)",
	 "13M(16:9)",
	 "8M(4:3)"};
 */
public class PhotoCase extends Base{
	//参数说明：photoSize= "18M(4:3)"，a,b为对应的照片比例，即a=4,b=3
	private void PhotoConfig(String photoSize,int a,int b) throws Exception{
		try{
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();
			
			CameraAction.configImageSize(photoSize);
			String photo_path = CameraAction.getPhotoPath();
			HashSet<String> beforeTakePhotoList = common.FileList(photo_path);
			common.cameraKey();
			sleep(2000);
			HashSet<String> afterTakePhotoList = common.FileList(photo_path);
			HashSet<String> resultHashSet = common.result(afterTakePhotoList, beforeTakePhotoList);
			
			if (resultHashSet.size()==1) {
				String photopath = resultHashSet.iterator().next();
				double hw = common.getPicHeightWidth(photopath);
				double exp= a/b;
				if (hw==exp) {
					common.infoLog(photopath+" -拍照成功");
					common.passcase();
				}else {
					common.infoLog(photopath+" -拍照失败");
					common.failcase(runcase);
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
	/**
	 * 打开防抖。自动翻转开关-多次拍照
	 * @param photoSize：照片大小
	 * @param a：照片比例
	 * @param b：照片比例
	 * @throws Exception
	 */
	private void photosAndAntiShakeConfig(String photoSize,int a,int b) throws Exception{
		try{
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();
			
			CameraAction.configImageSize(photoSize);
			CameraAction.cameraSetting();
			CameraAction.openCompoundButton("Anti-shake");
			
			String photo_path = CameraAction.getPhotoPath();
			HashSet<String> beforeTakePhotoList = common.FileList(photo_path);
			for(int i=0;i<10;i++){
				common.cameraKey();
				common.waitTime(3);
			}
			sleep(2000);
			HashSet<String> afterTakePhotoList = common.FileList(photo_path);
			HashSet<String> resultHashSet = common.result(afterTakePhotoList, beforeTakePhotoList);
			
			if (resultHashSet.size()==10) {
				String photopath = resultHashSet.iterator().next();
				double hw = common.getPicHeightWidth(photopath);
				double exp= a/b;
				if (hw==exp) {
					common.infoLog(photopath+" -拍照成功");
					common.passcase();
				}else {
					common.infoLog(photopath+" -拍照失败");
					common.failcase(runcase);
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
	
	private void photosAndAutoConfig(String photoSize,int a,int b) throws Exception{
		try{
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();
			
			CameraAction.configImageSize(photoSize);
			CameraAction.cameraSetting();
			
			CameraAction.openCompoundButton("Auto");
			String photo_path = CameraAction.getPhotoPath();
			HashSet<String> beforeTakePhotoList = common.FileList(photo_path);
			for(int i=0;i<10;i++){
				common.cameraKey();
				common.waitTime(2);
			}
			sleep(2000);
			HashSet<String> afterTakePhotoList = common.FileList(photo_path);
			HashSet<String> resultHashSet = common.result(afterTakePhotoList, beforeTakePhotoList);
			
			if (resultHashSet.size()==10) {
				String photopath = resultHashSet.iterator().next();
				double hw = common.getPicHeightWidth(photopath);
				double exp= a/b;
				if (hw==exp) {
					common.infoLog(photopath+" -拍照成功");
					common.passcase();
				}else {
					common.infoLog(photopath+" -拍照失败");
					common.failcase(runcase);
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
	 *  18M(4:3)
	 */
	public void testPhoto18M43() throws Exception{
		PhotoConfig("18M(4:3)",4,3);
		
	}
	/*
	 *  13M(16:9)
	 */
	public void testPhoto13M169() throws Exception{
		PhotoConfig("13M(16:9)",16,9);
	}
	/*
	 *  8M(4:3)
	 */
	public void testPhoto8M43() throws Exception{
		PhotoConfig("8M(4:3)",4,3);
			
	}
	/*
	 * 防抖+连续拍照18M(4:3)
	 */
	public void testPhotoAndAntiShake18M43ByManyTimes() throws Exception{

		photosAndAntiShakeConfig("18M(4:3)",4,3);
	}
	/*
	 * 防抖+连续拍照13M(16:9)
	 */
	public void testPhotoAndAntiShake13M169ByManyTimes() throws Exception{

		photosAndAntiShakeConfig("13M(16:9)",16,9);
	}
	/*
	 * 防抖+连续拍照8M(4:3)
	 */
	public void testPhotoAndAntiShake8M43ByManyTimes() throws Exception{
		photosAndAntiShakeConfig("8M(4:3)",4,3);
	}
	
	/*
	 * 自动翻转+连续拍照18M(4:3)
	 */
	public void testPhotoAndAuto18M43ByManyTimes() throws Exception{
		photosAndAutoConfig("18M(4:3)",4,3);
	}
	/*
	 * 自动翻转+连续拍照13M(16:9)
	 */
	public void testPhotoAndAuto13M169ByManyTimes() throws Exception{
		photosAndAutoConfig("13M(16:9)",16,9);
	}
	/*
	 * 自动翻转+连续拍照8M(4:3)
	 */
	public void testPhotoAndAuto8M43ByManyTimes() throws Exception{
		photosAndAutoConfig("8M(4:3)",4,3);
	}


	public static void main(String args[]){

		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.PhotoCase", "testPhotoAndAntiShake18M43ByManyTimes", "2");
	}
}
