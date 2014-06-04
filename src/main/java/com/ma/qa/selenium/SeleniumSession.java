package com.ma.qa.selenium;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumSession {
	
	/**
	 * @param args
	 */
	private String sessionType = null;
	private String appDir = null;
	private String app = null;
	private String url = null;
	private String browserNAME = null;
	private String device = null;
	private String capPLATFORM = null;
	private String capVERSION = null;
	private String os = System.getProperty ("os.name");
	private static String separator = System.getProperties().getProperty("file.separator");
	
	public SeleniumSession(String sessionType, String appDir, String app, String url,
			String browserNAME, String device, String capPLATFORM,
			String capVERSION) {
		super();
		this.sessionType = sessionType;
		this.appDir = appDir;
		this.app = app;
		this.url = url;
		this.browserNAME = browserNAME;
		this.device = device;
		this.capPLATFORM = capPLATFORM;
		this.capVERSION = capVERSION;
	}

	// for selenium website test
	public SeleniumSession(String sessionType, String url, String browserNAME) {
		super();
		this.sessionType = sessionType;
		this.url = url;
		this.browserNAME = browserNAME;
	}

	public Object getSession() throws MalformedURLException{
		WebDriver session = null;
		if (this.sessionType.trim().equalsIgnoreCase("appium")){
			if ((this.url == null) || (this.url.length() == 0)){
				File appDir = new File(this.appDir);
			    File app = new File(appDir, this.app);
			    DesiredCapabilities capabilities = new DesiredCapabilities();
			    capabilities.setCapability(CapabilityType.BROWSER_NAME, this.browserNAME);
			    capabilities.setCapability(CapabilityType.VERSION, this.capVERSION);
			    capabilities.setCapability(CapabilityType.PLATFORM, this.capPLATFORM);
			    capabilities.setCapability("device", this.device);
			    capabilities.setCapability("app", app.getAbsolutePath());
			    session = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			}
		}
		else if(this.sessionType.trim().equalsIgnoreCase("selenium")){
			if (this.browserNAME.trim().equalsIgnoreCase("firefox")){
				session = new FirefoxDriver();
				session.get(this.url);
			}
			if (this.browserNAME.trim().equalsIgnoreCase("ie")){
				//add code here
			}
			if (this.browserNAME.trim().equalsIgnoreCase("chrome")){
				//add code here
			}
			if (this.browserNAME.trim().equalsIgnoreCase("safari")){
				//add code here
			}
		}
		
		return session;
	}

}
