package org.task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazonone {

	static WebDriver driver;
	static  long startTime;
	@BeforeClass  (groups= "common")
	public static void lanch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@BeforeMethod (groups= "common")
	public static void BeforeMethod() {
		System.out.println("BeforeMethod");
		startTime = System.currentTimeMillis();
	}
	
	
	@AfterMethod (groups= "common")
	public void AfterMethod() {
		System.out.println("AfterMethod");
		long endTime = System.currentTimeMillis();
		long ee = endTime - startTime;
		System.out.println("after method");
		System.out.println("Time taken :"+ee);
	}

	@AfterClass (groups= "common")
	public  static void screen() throws IOException{
		DateTimeFormatter dth = DateTimeFormatter.ofPattern("HHmmss");LocalDateTime
		now = LocalDateTime.now();
		String format = dth.format(now);
		System.out.println(format);
		
		
		
		System.out.println("After class");
		TakesScreenshot tk = (TakesScreenshot)driver;
		File Source = tk.getScreenshotAs(OutputType.FILE);
	    File dest = new File(".//target//report"+format+".png");
		FileUtils.copyFile(Source, dest);
		
		driver.quit();
	
	}
	@Test(groups = "smoke")
	public void amazonsearch() throws Throwable{
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles",Keys.ENTER);
	
	Thread.sleep(2000);
	
	
	
	
	
	
//	public void test1() {
//		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
//		
//		search.driver.findElement(By.id("twotabsearchtextbox"));
//		
//		WebElement textValue = driver.findElement(By.xpath("//span[contains(text(),'Price and other details may vary based on product size and color.')]"));
//		Assert.assertEquals("Price and other details may vary based on product size and color",textValue.getText());
	}

}


