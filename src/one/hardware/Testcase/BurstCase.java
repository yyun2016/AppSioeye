package one.hardware.Testcase;

import java.util.HashSet;

import com.ckt.demo.UiAutomatorHelper;

import android.os.Environment;
import one.hardware.Action.CameraAction;
import one.hardware.Page.Camera;
import one.hardware.Util.Base;

public class BurstCase extends Base{
	/**
	 * 测试照片质量18M 10张连拍
	 * 1.检查是否有增加10张照片
	 * 2.检查图片长宽比例
	 * @throws Exception
	 * 其他case类似
	 */
	public void testBurst10P18M43() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();
			
			CameraAction.configBurstImageSize(Camera.imsge_size[0]);
			CameraAction.configBurstRate(Camera.burst[0]);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
				common.infoLog("存在SD卡；path："+burstPath);
			}else {
				burstPath="/storage/sdcard0/Photo";
				common.infoLog("不存在SD卡；path："+burstPath);
			}
			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(12000);;
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);
			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=10) {
				common.infoLog("expect图片总数：10张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：10张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 4/3;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst20P18M43() throws Exception{
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(Camera.imsge_size[0]);
			CameraAction.configBurstRate(Camera.burst[1]);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(15000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=20) {
				common.infoLog("expect图片总数：20张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：20张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 16/9;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst30P18M43() throws Exception{
		String burstcfg = one.hardware.Page.Camera.burst[2];
		String imgesz =one.hardware.Page.Camera.imsge_size[0];
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(imgesz);
			CameraAction.configBurstRate(burstcfg);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(20000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=30) {
				common.infoLog("expect图片总数：30张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：30张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 16/9;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst10P13M169() throws Exception{
		String burstcfg = one.hardware.Page.Camera.burst[0];
		String imgesz =one.hardware.Page.Camera.imsge_size[1];
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(imgesz);
			CameraAction.configBurstRate(burstcfg);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(12000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=10) {
				common.infoLog("expect图片总数：10张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：10张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 4/3;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst20P13M169() throws Exception{
		String burstcfg = one.hardware.Page.Camera.burst[1];
		String imgesz =one.hardware.Page.Camera.imsge_size[1];
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(imgesz);
			CameraAction.configBurstRate(burstcfg);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(16000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=20) {
				common.infoLog("expect图片总数：20张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：20张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 4/3;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst30P13M169() throws Exception{
		String burstcfg = one.hardware.Page.Camera.burst[2];
		String imgesz =one.hardware.Page.Camera.imsge_size[1];
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(imgesz);
			CameraAction.configBurstRate(burstcfg);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(20000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=30) {
				common.infoLog("expect图片总数：30张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：30张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 4/3;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst10P8M43() throws Exception{
		String burstcfg = one.hardware.Page.Camera.burst[0];
		String imgesz =one.hardware.Page.Camera.imsge_size[2];
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(imgesz);
			CameraAction.configBurstRate(burstcfg);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(12000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=10) {
				common.infoLog("expect图片总数：10张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：10张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 16/9;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst20P8M43() throws Exception{
		String burstcfg = one.hardware.Page.Camera.burst[1];
		String imgesz =one.hardware.Page.Camera.imsge_size[2];
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(imgesz);
			CameraAction.configBurstRate(burstcfg);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(16000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=20) {
				common.infoLog("expect图片总数：20张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：20张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 16/9;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			//common.backToIdl();
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public void testBurst30P8M43() throws Exception{
		String burstcfg = one.hardware.Page.Camera.burst[2];
		String imgesz =one.hardware.Page.Camera.imsge_size[2];
		try {
			initUIAutomator(this.getName());
			common.startLog("*****Start to run " + runcase + " *****");
			common.initDevice();
			common.startCamera();
			common.deletePhoto();

			CameraAction.configImageSize(imgesz);
			CameraAction.configBurstRate(burstcfg);
			String burstPath=null;
			if (common.isExistSDCard()) {//判断是否存在SD卡
				burstPath="/storage/sdcard1/Photo";
			}else {
				burstPath="/storage/sdcard0/Photo";
			}

			HashSet<String> beforeTakeVideoList = common.FileList(burstPath);
			common.cameraKey();
			sleep(20000);
			HashSet<String> afterTakeVideoList = common.FileList(burstPath);
			HashSet<String> resultHashSet = common.result(afterTakeVideoList, beforeTakeVideoList);

			common.findViewById(one.hardware.Page.Camera.camera_setting_shortcut_id);
			int piccnt = resultHashSet.size();
			if (piccnt!=30) {
				common.infoLog("expect图片总数：30张-实际图片数量为："+piccnt);
				common.failcase(runcase);
			}else {
				common.infoLog("expect图片总数：30张-Success");
				for (String photopath : resultHashSet) {
					double hw = common.getPicHeightWidth(photopath);
					double exp= 4/3;
					if (hw==exp) {
						common.infoLog(photopath+" -图片比列验证成功");
						common.passcase();
					}else {
						common.infoLog(photopath+" -图片比列验证失败");
						common.failcase(runcase);
					}
				}
			}
			common.startLog( "*****End to run " + runcase + " *****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			common.handleException(e.getMessage());
		}
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", "one.hardware.Testcase.BurstCase", "", "1");
	}
}
