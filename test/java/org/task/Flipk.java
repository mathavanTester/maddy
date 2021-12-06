package org.task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipk {
	
	@DataProvider(name ="mobile name")
public Object[][]getData(){
		return new Object[][] {{"realme"}};
	}
	static WebDriver driver;
	static  long startTime;
	
	
	@BeforeClass (groups= "common")
	public static void lanch() {
		System.out.println("Before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	

	@BeforeMethod (groups= "common")
	public void beforeMethod() {
		System.out.println("before Method");
		startTime = System.currentTimeMillis();
		
	}
	@Test(priority = -1,groups = "smoke",dataProvider="mobile name")
	
	
	
	
	public void login(String name){
		
		try {
			
			WebElement button = driver.findElement(By.xpath("//button[text()='✕']"));
	          button.isDisplayed();
		button.click();
		
		}
		catch (Exception e) {
			System.out.println("pop up is not dispaly");
			
		}
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys("name",Keys.ENTER);
	
		WebElement mobileName = driver.findElement(By.xpath("(//div[contains(text(),'realme Narzo 50A')])[1]"));
		mobileName.click();
     //    mobileName = mobileName.getText();
	
	
		}

	
	@Test(priority = 0)
	public void WindowHandl() {
		
		
		String parentURL = driver.getWindowHandle();
		Set<String> childURL = driver.getWindowHandles();
		for(String child : childURL) {
			
			
			if(!parentURL.equals(child)) {
				
				
				driver.switchTo().window(child);
			}
		}
	}

		@Test(priority = 1,enabled = false,groups ="sanity")
		public void validation() {
			
			
		WebElement MobileName2 = driver.findElement(By.xpath("(//span[contains(text(),'realme')])[1]"));
		

	String name = MobileName2.getText();
		System.out.println(name);
	Assert.assertTrue(MobileName2.isDisplayed());
	Assert.assertEquals("realme Narzo 50A (Oxygen Green, 128 GB)   (4 GB RAM)",name);
		}
		
		
		
	@Test(priority = 2)
	
	public void srolldown() {
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollTc(0,document.body.scrollheight)");
		
	}
	@Test(priority = 3,invocationCount = 5)
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
	}
	
	@AfterMethod (groups= "common")
	public void afterMethod() {
		
	long endTime = System.currentTimeMillis();
	long ee = endTime - startTime;
	System.out.println("after method");
	System.out.println("Time taken :"+ee);
	}

	@AfterClass (groups= "common")
	public static void closeBrowser() throws  IOException{
		
		
	
	driver.quit();
	
	}
	
	
	
	
	
	
}