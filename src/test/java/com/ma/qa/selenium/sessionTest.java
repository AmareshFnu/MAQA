package com.ma.qa.selenium;

import java.util.Set;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;


public class sessionTest {
	
	private static final String testType = "appium";
	private static final String appDir = "/Users/Anson/Documents/Testing_App";
	private static final String app = "BBC_ST.app";
	private static final String url = null;
	private static final String capBrowserName = "iOS";
	private static final String capDevice = "iOS";
	private static final String capPlatform = "Mac";
	private static final String capVersion = "7.1";
	private static final String contextStr1 = "NATIVE_APP";			//for leave webview
	private static final String contextStr2 = "WEBVIEW_1";			//for enter webview
	private SeleniumSession session = new SeleniumSession(testType, appDir, app, url, capBrowserName, capDevice, capPlatform, capVersion);
	private AppiumDriver driver;
	
	
  @Test
  public void f() throws InterruptedException {
	  try {
		  MobileElement scrollview = new MobileElement((RemoteWebElement)driver.findElementsByClassName("UIAScrollView").get(0), driver);
		  scrollview.findElementsByClassName("UIAButton").get(0).click();
		  //scrollview.findElementsByClassName("UIAButton").get(1).click();
		  driver.findElementsByClassName("UIAScrollView").get(0).findElements(By.className("UIAButton")).get(1).click();
		  CommonUtil.sleep(1);
		  
		  Set <String> contexts = driver.getContextHandles();
		  for(String context : contexts)
			  System.out.println(context);
		  driver.context(contextStr2);
		  
		  CommonUtil.sleep(2);
	
		  driver.findElement(By.id("page_1_1_1")).click();
		  driver.context(contextStr2);
		  
		  //WebElement drop = driver.findElementsByClassName("UIAScrollView").get(0).findElements(By.className("UIAImage")).get(2);
		  //int len = driver.findElementsByClassName("UIAScrollView").get(0).findElements(By.className("UIAImage")).size();
		  
		  MobileElement wv = new MobileElement((RemoteWebElement)driver.findElementsByClassName("UIAScrollView").get(0), driver);
		  int len = wv.findElementsByClassName("UIAImage").size();
		  len = driver.findElements(By.className("UIAScrollView")).get(0).findElements(By.className("UIAImage")).size();
		  System.out.println("UIAImage size: " + len);
		  WebElement drag = driver.findElementByName("A1.3 1.1.1a 03");
		  WebElement drop = driver.findElements(By.className("UIAScrollView")).get(0).findElements(By.className("UIAImage")).get(2);
		  int x = driver.manage().window().getSize().getWidth();
		  int y = driver.manage().window().getSize().getHeight();
		  
		  
		  int sX = (int) (drag.getLocation().x + drag.getSize().width/2);
		  int sY = (int) (drag.getLocation().y + drag.getSize().height/2);
		  int eX = (int) (drop.getLocation().x + drop.getSize().width/2);
		  int eY = (int) (drop.getLocation().y + drop.getSize().height/2);
		  int duration = 1500;
		  
		  driver.swipe(sX, sY, eX, eY, duration);
		  
		  CommonUtil.sleep(5);
	  }
	  catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
  }
  @BeforeMethod
  public void setUp() throws Exception {
	  driver = (AppiumDriver) session.getSession();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
