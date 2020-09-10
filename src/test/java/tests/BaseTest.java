package test.java.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public static final String SITE_URL         = "https://www.lush.ca/en/";
	public static final String SRP_URL          = "new/new-products/";
	public static final By COOKIE_BUTTON_XPATH  = By.xpath("//div[contains(@class , 'cookie-warning-messaging')]//a[contains(@class , 'cookie-warning-messaging')]");
	
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","c:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void openPage(String pageURL) {
		driver.get(SITE_URL + pageURL);
		closeCookie();
	}
	
	private void closeCookie() {
		WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON_XPATH));
		cookieButton.click();
	}
}
