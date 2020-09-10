package test.java.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishlistPage {

	private static final By WISHLIST_ITEM_XPATH = By.xpath("//div[contains(@class , 'card product-info')]");
	
	WebDriver driver;
	WebDriverWait wait;
	
	public WishlistPage(WebDriver drv) {
		driver = drv;
		wait = new WebDriverWait(driver, 15);
	}

	public boolean isWishlistEmpty() {
		List<WebElement> items = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(WISHLIST_ITEM_XPATH, 0));
		return items.size() == 0;
	}

}
