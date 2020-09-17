package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewPage {

	private static final String URL_STRING_FRACTION   = "WriteReviewPage";
	private static final By REVIEW_STARS_XPATH        = By.xpath("//div[contains(@class , 'pr-rating-stars')]");
	
	WebDriver driver;
	WebDriverWait wait;
	
	public ReviewPage(WebDriver drv) {
		driver = drv;
		wait = new WebDriverWait(driver, 10);
	}

	public boolean canLeaveReview() {
		try {
			wait.until(ExpectedConditions.urlContains(URL_STRING_FRACTION));
			wait.until(ExpectedConditions.visibilityOfElementLocated(REVIEW_STARS_XPATH));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
