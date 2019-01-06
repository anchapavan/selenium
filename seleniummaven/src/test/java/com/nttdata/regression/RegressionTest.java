package com.nttdata.regression;


import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.plaf.FileChooserUI;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import com.nttdata.driverfactory.DriverFactory;

public class RegressionTest {

	WebDriver driver = null;

	@BeforeMethod
	public void browserLaunch() throws IOException {
	DriverFactory df=new DriverFactory(driver);
	driver=df.openDriver();
	}

	@Test(priority=-1,enabled=true)
	public void allquestions() throws IOException, InterruptedException {
	

		driver.manage().window().maximize();
		driver.get("https://g01.digialm.com//OnlineAssessment/index.html?1383@@M18");
		WebDriverWait Wait = new WebDriverWait(driver, 20);
		Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Sign In']"))).click();

		// window handles
		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows.size());
		System.out.println(driver.getWindowHandle());
		String currentwindow = driver.getWindowHandle();

		for (String window : windows) {
			if (window.equalsIgnoreCase(currentwindow)) { 

			} else {
				driver.switchTo().window(window);
			}
		}
		Wait.until(ExpectedConditions.presenceOfElementLocated(By.id("instPaginationa"))).click();
		System.out.println(driver.getTitle());

		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getWindowHandle());
		WebElement element = driver.findElement(By.id("defaultLanguage"));
		Select se = new Select(element);
		se.selectByValue("8");
		System.out.println(se.getFirstSelectedOption().getSize());

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//input[@id='disclaimer']"))).click().build().perform();

		WebElement ele = driver.findElement(By.xpath("//a[@id='readylink']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		
		Thread.sleep(4000);
		String Expected = "General";
		String actoutput = driver.findElement(By.xpath("//div[@id='g0']")).getAttribute("title");

		try {
			// Assert.assertEquals(actoutput, Expected);
		} catch (Exception e) {

		}
		WebElement quest = driver.findElement(By.xpath("//span[@id='viewQPButton']"));
		quest.click();
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("C:\\Users\\DEVSAHI\\Desktop\\Automation\\Dev.png"));

		// FileUtils.copyFile(screenshotFile, new File("filename_with_path"));

		List<WebElement> allquestions = driver
				.findElements(By.xpath("//div[@class='viewQPDiv']/table/tbody/tr[1]/td[2]"));
		for (int i = 0; i < allquestions.size(); i++) {

			System.out.println(allquestions.get(i).getText());

		}
	}

	@Test(enabled=false)
	public void TC_002_LangauageAlret() throws IOException, Exception {
		
		driver.manage().window().maximize();
		driver.get("https://g01.digialm.com//OnlineAssessment/index.html?1383@@M18");
		WebDriverWait Wait = new WebDriverWait(driver, 20);
		Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Sign In']"))).click();

		// window handles
		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows.size());
		System.out.println(driver.getWindowHandle());
		String currentwindow = driver.getWindowHandle();
		
		Thread.sleep(3000);

		for (String window : windows) {
			if (window.equalsIgnoreCase(currentwindow)) {

			} else {
				driver.switchTo().window(window);
			}
		}
		Wait.until(ExpectedConditions.presenceOfElementLocated(By.id("instPaginationa"))).click();
		System.out.println(driver.getTitle());

		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getWindowHandle());
		/*
		 * WebElement element=driver.findElement(By.id("defaultLanguage")); Select
		 * se=new Select(element); se.selectByValue("1");
		 * System.out.println(se.getFirstSelectedOption().getText());
		 */

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//input[@id='disclaimer']"))).click().build().perform();

		WebElement ele = driver.findElement(By.xpath("//a[@id='readylink']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(5000);
	driver.findElement(By.xpath("//a[@id='okButton']")).click();
//		Alert alr=driver.switchTo().alert();
//		System.out.println(alr.getText());
//		alr.accept();
	}
	@AfterMethod
	public void closebrowser() {
		driver.quit();
	}

}
