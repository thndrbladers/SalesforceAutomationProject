package com.salesforce.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
	
	public static WebDriver driver;
	public static FileInputStream fileInput;
	public static Properties prop;
	public static ChromeOptions chromeOptions;
	
	public TestBase() {
		try {
			prop=new Properties();
			fileInput=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\salesforce\\config\\config.properties");
			prop.load(fileInput);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void initialization() {
		
		switch(prop.getProperty("browser")) {
		
		case "chrome":
			chromeOptions=new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			System.setProperty(prop.getProperty("system_property_chrome"), System.getProperty("user.dir")+prop.getProperty("chromedriver_path"));
			driver=new ChromeDriver(chromeOptions);
			break;
		
		case "firefox":
			System.setProperty(prop.getProperty("system_property_firefox"),System.getProperty("user.dir")+ prop.getProperty("geckodriver_path"));
			driver=new FirefoxDriver();
			break;
			
		case "geckodriver":
			System.setProperty(prop.getProperty("system_property_edge"),System.getProperty("user.dir")+ prop.getProperty("msedgedriver_path"));
			driver=new EdgeDriver();
			break;
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.valueOf(prop.getProperty("pageLoadTimeOut"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.valueOf(prop.getProperty("implicitlyWait"))));
		driver.get(prop.getProperty("url"));
		
	}
	
	public static void close() {
		driver.quit();
		try {
			fileInput.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
