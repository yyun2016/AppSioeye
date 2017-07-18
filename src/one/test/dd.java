package one.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.ckt.demo.UiAutomatorHelper;

public class dd  extends UiAutomatorTestCase{
	public void testA() throws UiObjectNotFoundException{
		/*UiObject Setting = new UiObject(new UiSelector().text("Settings"));
		Setting.swipeLeft(2);*/
		UiObject Setting1 = new UiObject(new UiSelector().text("Settings"));
		UiObject Setting = new UiObject(new UiSelector().textContains("ett"));
		UiObject Settingreg = new UiObject(new UiSelector().textMatches("^Sett"));
		
		UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.sioeye.sioeyeapp:id/swipeLayout"));
		scrollable.setMaxSearchSwipes(100);
		scrollable.scrollIntoView(new UiSelector().text("elom"));
	}
	public static void main(String args[]){
		new UiAutomatorHelper("AppSioeye", " one.test.dd", "", "2");
		//new UiAutomatorHelper("AppSioeye", " one.test.ImageTestCase", "", "3");
	}
}
