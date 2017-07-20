package one.hardware.Action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.android.uiautomator.core.UiDevice;

import android.os.Environment;
import android.util.Property;
import one.hardware.Page.Account;
import one.hardware.Util.Base;

public class AccountAction extends Base {
	private static Logger logger=Logger.getLogger(AccountAction.class.getName());

	/**
	 * Click com.hicam:id/logout_btn按钮
	 */
	public static  void logout_btn() throws Exception {
		common.clickViewById(one.hardware.Page.Account.logout_btn);
	}
	/**
	 * 输入 com.hicam:id/login_id_input
	 */
	public static  void login_id_input(String username) throws Exception {
		common.findViewById(one.hardware.Page.Account.login_id_input).setText(username);
	}
	/**
	 * 输入 com.hicam:id/login_password_input
	 */
	public static  void login_password_input(String passwd) throws Exception {
		common.findViewById(one.hardware.Page.Account.login_password_input).setText(passwd);
	}
	/**
	 * Click com.hicam:id/logout_btn按钮
	 */
	public static  void login_btn_login() throws Exception {
		common.clickViewById(one.hardware.Page.Account.login_btn_login);
	}
	public static void loginAccount(String username,String passwd) throws Exception{
		login_id_input(username);
		login_password_input(passwd);
		login_btn_login();
	}
	public static boolean isLoginSuccess(){
		boolean isSuccess = false;
		for (int i = 0; i < 20; i++) {
			if (common.findViewByText2("^Account").exists()) {
				isSuccess= true;
				break;
			}else {
				common.infoLog("Login wait 1 seconds");
				common.waitTime(1);
			}
		}
		return isSuccess;
	}
	public static String getUserName(){
        String config= Environment.getExternalStorageDirectory()+ File.separator+"config.properties";
        logger.info(config);
        String userName=getValueByKey(config,"user_name");
        logger.info("userName:"+userName);
        return userName;
    }

    public static String getPassword(){
        String config= Environment.getExternalStorageDirectory()+ File.separator+"config.properties";
        String user_password=getValueByKey(config,"user_password");
        logger.info(config);
        logger.info("user_password:"+user_password);
        return user_password;
    }
    public static String getValueByKey(String filePath, String key) {
		Properties pps = new Properties();
		File file=new File(filePath);
		if (file.exists()){
			try {
				InputStream in = new BufferedInputStream (new FileInputStream(filePath));
				pps.load(in);
				String value = pps.getProperty(key);
				System.out.println(key + " = " + value);
				return value;
			}catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
	}
    public static boolean isLogin() throws Exception{
		boolean isLogin = false;
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[0]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Account");
		common.clickViewByText("Account");
			if (common.findViewByText2("^Log out").exists()) {
				isLogin= true;
				logger.info("已经登陆");
				UiDevice.getInstance().pressBack();
			}else {
				isLogin = false;
				common.infoLog("没有登录");
				UiDevice.getInstance().pressBack();
			}
		common.waitTime(1);
		return isLogin;
	}
    /**
	 * 判断直播账号是否登录
	 * @throws Exception 
	 */
	public static boolean islogin() throws Exception{
		CameraAction.navconfig(one.hardware.Page.Camera.nav_menu[0]);
		CameraAction.cameraSetting();
		common.ScrollViewByText("Account");
		common.clickViewByText("Account");
		if (common.findViewById(Account.login_btn_login).exists()) {
            System.out.println("not login");
            return false;
        } else {
            System.out.println("Already login");
            return true;
        }
	}
}
