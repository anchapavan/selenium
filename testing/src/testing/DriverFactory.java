package testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.internal.Systematiser;

public class DriverFactory {
	
	WebDriver driver;
	
	public DriverFactory(WebDriver driver) {
		this.driver=driver;
		
	}
	 
	public WebDriver openDriver() throws IOException {
		
		System.out.println(System.getProperty("user.dir"));

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/config.properties");
		Properties pr=new Properties();
		pr.load(fis);
		String browser=pr.getProperty("browser");
	
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		}else {
			System.setProperty("webdriver.IE.Driver",System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		
		return driver;
	}

}
