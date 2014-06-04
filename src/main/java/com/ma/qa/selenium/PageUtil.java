package com.ma.qa.selenium;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class PageUtil {
	private static String separator = System.getProperties().getProperty("file.separator");
	
	public static String captureScreen(WebDriver wd, String directory, String format){
		//Date date = new Date();
		//String nowStr = new Timestamp(date.getTime()).toString();
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR)).trim();
		String month = String.valueOf(c.get(Calendar.MONTH)).trim();
		String day = String.valueOf(c.get(Calendar.DATE)).trim();
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY)).trim();
		String mint = String.valueOf(c.get(Calendar.MINUTE)).trim();
		String second = String.valueOf(c.get(Calendar.SECOND)).trim();
		String nowStr = year + month + day + "_" + hour + mint + second;
		
		String imgName = directory + separator + nowStr + format;
	
		File screenFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(screenFile, new File(imgName));
		}catch (IOException e){
			e.printStackTrace();
		}
		
		return imgName;
	}
}
