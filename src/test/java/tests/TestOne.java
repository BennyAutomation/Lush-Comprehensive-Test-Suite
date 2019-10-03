package test.java.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestOne {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","c:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	@Test
	public void tOne() {
		driver.get("https://www.google.com");
	}
	
	
	
}
