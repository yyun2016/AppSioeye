package one.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Environment;
import android.os.RemoteException;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.ckt.demo.UiAutomatorHelper;

public class test3  extends UiAutomatorTestCase{
	public byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);//png类型
		return baos.toByteArray();
	}

	// 写到sdcard中
	public void write(byte[] bs,String destPath) throws IOException{
		FileOutputStream out=new FileOutputStream(new File(destPath));
		out.write(bs);
		out.flush();
		out.close();
	}
	public Bitmap convertToBitmap(String path,int x ,int y,int h,int w) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		// 设置为ture只获取图片大小
		opts.inJustDecodeBounds = true;
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		// 返回为空
		BitmapFactory.decodeFile(path, opts);
		opts.inJustDecodeBounds = false;
		WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
		return Bitmap.createBitmap(weak.get(), x,y,w,h);
	}
	private static String getTimeToString() {
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		// 设置时间对象的格式
		String timeToString = time.format(date);
		return timeToString;
	}
	public void takeScreenshotRectangle(String srcpath,int sx,int sy,int ex ,int ey){
		int x = sx;
		int y = sy;
		double h = ey-sy;
		double w = ex-sx;
		System.out.println(x+"-"+y);
		System.out.println("h"+h);
		System.out.println("w"+w);
		Bitmap bitmap1 = convertToBitmap(srcpath,x,y,(int)w,(int)h);
		try {
			byte[] bytes = Bitmap2Bytes(bitmap1);
			String cgpath ="/sdcard/"+getTimeToString()+".png"; 
			System.out.println(cgpath);
			write(bytes,cgpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String  takeScreenshotRectangle(String srcpath,Rect rectangle){
		String cgpath = null;
		int x = rectangle.left;
		int y = rectangle.top;
		double h = rectangle.right-rectangle.left;
		double w = rectangle.bottom-rectangle.top;
		System.out.println(x+"-"+y);
		System.out.println("h"+h);
		System.out.println("w"+w);
		Bitmap bitmap1 = convertToBitmap(srcpath,x,y,(int)w,(int)h);
		try {
			byte[] bytes = Bitmap2Bytes(bitmap1);
			cgpath ="/sdcard/"+getTimeToString()+".png"; 
			System.out.println(cgpath);
			write(bytes,cgpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cgpath;
	}
	 private static int getColorPicel(int x, int y, String path) {
	        Bitmap mBitmap = BitmapFactory.decodeFile(path);
	        int color = mBitmap.getPixel(y, y);
	        return color;
	    }
	public void testA() throws UiObjectNotFoundException{

		getUiDevice().takeScreenshot(new File("/sdcard/close.png"));
		UiObject uiObject = new UiObject(new UiSelector().text("Anti-shake"));
		
		System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());
		
		//takeScreenshotRectangle("/sdcard/close.png", uiObject.getBounds());
		Rect s = new Rect();
		s.set(238, 45, 303, 77);
		
		
		takeScreenshotRectangle("/sdcard/close.png", s);
		int color = getColorPicel(s.centerX(), s.centerY(), "/sdcard/close.png");
		System.out.println("color is -"+color);
		
		/*getUiDevice().takeScreenshot(new File("/sdcard/close.png"));
		takeScreenshotRectangle("/sdcard/close.png", 9, 31, 94, 91);*/
	}
	public void testScreen() throws IOException, RemoteException, InterruptedException{
		boolean isScreenOn = UiDevice.getInstance().isScreenOn();
		
	/*	System.out.println(UiDevice.getInstance().isScreenOn());
		getUiDevice().pressKeyCode(26);
		Thread.sleep(5000);
		System.out.println(UiDevice.getInstance().isScreenOn());
		getUiDevice().pressKeyCode(26);
		Thread.sleep(5000);
		System.out.println(UiDevice.getInstance().isScreenOn());*/
		
	/*	System.out.println( UiDevice.getInstance().getDisplayRotation());
		Thread.sleep(5000);
		UiDevice.getInstance().setOrientationRight();
		//System.out.println( UiDevice.getInstance().getDisplayRotation());
		UiDevice.getInstance().setOrientationNatural();
		UiDevice.getInstance().setOrientationNatural();
		UiDevice.getInstance().setOrientationNatural();
		UiDevice.getInstance().setOrientationNatural();
		UiDevice.getInstance().setOrientationNatural();
		
		UiDevice.getInstance().setOrientationNatural();
		UiDevice.getInstance().setOrientationNatural();
		UiDevice.getInstance().setOrientationNatural();*/
	}
	public void tests(){
		
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", " one.test.test3", "testScreen", "3");
		//new UiAutomatorHelper("AppSioeye", " one.test.ImageTestCase", "", "3");
	}
}
