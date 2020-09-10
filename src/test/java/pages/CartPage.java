package test.java.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	private static final By CART_ITEM_XPATH = By.xpath("//div[contains(@class , 'card product-info')]");

	
	WebDriver driver;
	WebDriverWait wait;
	
	public CartPage(WebDriver drv) {
		driver = drv;
		wait = new WebDriverWait(driver, 15);
	}
	
	public boolean isCartEmpty() {
		List<WebElement> items = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(CART_ITEM_XPATH, 0));
		return items.size() == 0;
	}
	
	
	
}
