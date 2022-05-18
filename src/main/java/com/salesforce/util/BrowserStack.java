package com.salesforce.util;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.salesforce.base.TestBase;

public class BrowserStack extends TestBase{

	public static final String URL = "https://" + prop.getProperty("bs_username") + ":" + prop.getProperty("bs_password") + prop.getProperty("browserStack_URL");
	public static DesiredCapabilities caps;

	public static DesiredCapabilities getBrowserStackConfiguration() {

		caps = new DesiredCapabilities();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		caps.setCapability("browser", prop.getProperty("remote_browser"));
		caps.setCapability("browser_version", prop.getProperty("remote_browser_version"));
		caps.setCapability("os", prop.getProperty("remote_os"));
		caps.setCapability("os_version", prop.getProperty("remote_os_version"));
		
		caps.setCapability("project", "SalesforceProject");
		caps.setCapability("build", "1");
		
		
		
		caps.setCapability("browserstack.debug", "true");  // for enabling visual logs
		caps.setCapability("browserstack.console", "info");  // to enable console logs at the info level. You can also use other log levels here
		caps.setCapability("browserstack.networkLogs", "true");  // to enable network logs to be logged

		return caps;
	}

}
