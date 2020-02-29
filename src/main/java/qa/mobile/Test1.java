package qa.mobile;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Test1 {

	AppiumDriver driver;

	@Test
	public void invalidUser() {

		MobileElement userTxt = (MobileElement) driver.findElementByAccessibilityId("test-Username");
		MobileElement pwdTxt = (MobileElement) driver.findElementByAccessibilityId("test-Password");
		MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

		userTxt.sendKeys("InvalidUser");
		pwdTxt.sendKeys("secret_sauce");
		loginBtn.click();

		MobileElement actErrx = (MobileElement) driver.findElementByXPath(
				"//android.view.ViewGroup[@cont" + "ent-desc=\"test-Error message\"]/android.widget.TextView");
		String actErr = actErrx.getText();

		System.out.println(actErr);

		String expErr = "Username and password do not match any user in this service.";

		Assert.assertEquals(actErr, expErr);

	}

	@Test
	public void invalidPwd() {

		MobileElement userTxt = (MobileElement) driver.findElementByAccessibilityId("test-Username");
		MobileElement pwdTxt = (MobileElement) driver.findElementByAccessibilityId("test-Password");
		MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

		userTxt.sendKeys("standard_user");
		pwdTxt.sendKeys("InvalidPwd");
		loginBtn.click();

		MobileElement actErrx = (MobileElement) driver.findElementByXPath(
				"//android.view.ViewGroup[@cont" + "ent-desc=\"test-Error message\"]/android.widget.TextView");
		String actErr = actErrx.getText();

		System.out.println(actErr);

		String expErr = "Username and password do not match any user in this service.";

		Assert.assertEquals(actErr, expErr);

	}

	@Test
	public void validCredentials() {

		MobileElement userTxt = (MobileElement) driver.findElementByAccessibilityId("test-Username");
		MobileElement pwdTxt = (MobileElement) driver.findElementByAccessibilityId("test-Password");
		MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

		userTxt.sendKeys("standard_user");
		pwdTxt.sendKeys("secret_sauce");
		loginBtn.click();

		MobileElement actErrx = (MobileElement) driver
				.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]"
						+ "/preceding-sibling::android.view.ViewGroup/android.widget.TextView");
		String validationTxt = actErrx.getText();

		System.out.println(validationTxt);

		String expTxt = "PRODUCTS";

		Assert.assertEquals(validationTxt, expTxt);

	}

	// first before class is executed to install the app, then test block is added

	@BeforeClass
	public void beforeClass() throws Exception {

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "6.0");
		desiredCapabilities.setCapability("deviceName", "Nexus 5 API 23");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		desiredCapabilities.setCapability("app", "C:\\Users\\snypa\\Downloads\\Android.SauceLabs.Mobile.Sample.app.2.2.1.apk");

//		desiredCapabilities.setCapability("appPackage", "com.swaglabsmobileapp.");
//		desiredCapabilities.setCapability("appActivity", "com.swaglabsapp.SplashActivity.");

		desiredCapabilities.setCapability("appPackage", "com.swaglabsmobileapp");
		desiredCapabilities.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");

//		desiredCapabilities.setCapability("avd", "Nexus_5_API_23");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		driver = new AndroidDriver(url, desiredCapabilities);
		String sessionId = driver.getSessionId().toString();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {

		driver.quit();

	}

}
