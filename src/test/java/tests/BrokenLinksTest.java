package test.java.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenLinksTest {

	private static final String URL = "https://***********.com/";
	private static final By LINK_TAG = By.tagName("a");
	
	
	WebDriver driver;
	WebDriverWait wait;
	int brokenLinks;
	int validLinks;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","c:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 30);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void BrokenLinkTest() {
        driver.get(URL);
        
        List<WebElement> allLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LINK_TAG));
        System.out.println("# of links = " + allLinks.size());
        
        brokenLinks = validLinks = 0;
        for (WebElement link : allLinks) {
        	verifyLinkStatus(link.getAttribute("href"));
        }
        System.out.println("BROKEN: " + brokenLinks);
        System.out.println("VALID: " + validLinks);
	}
	
	
	private void verifyLinkStatus(String urlString) {
		int status = 404;
		
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection)link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if (status != 200) {
			brokenLinks++;
			System.out.println(status + ": " + urlString);
		}
		else {
			validLinks++;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
