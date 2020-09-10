package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
	
	private static final By WISHLIST_BUTTON_XPATH       = By.xpath("//a[contains(@class , 'wishlistTile')]");
	private static final By ADD_TO_CART_BUTTON_XPATH    = By.xpath("//div[contains(@class , 'prices-add-to-cart-actions')]//button[contains(@class , 'add-to-cart')]");
	private static final By CART_MODAL_XPATH            = By.xpath("//div[contains(@class , 'popover-addtocart')]");
	private static final By VIEW_CART_MODAL_XPATH       = By.xpath("//div[@class = 'minicart-footer bg-white mt-2']//div[contains(@class , 'checkout-continue')]//a[@title = 'View Cart']");
	private static final By SHOPPING_BAG_XPATH          = By.xpath("//a[@class = 'minicart-link']");
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jsExec;
	
	public ProductDetailsPage(WebDriver drv) {
		driver = drv;
		wait = new WebDriverWait(driver, 10);
	}

	// Not necessary to use Javascript to click element but I'm using it for practice
	public WishlistPage addProductToWishlist() {
		wait.until(ExpectedConditions.elementToBeClickable(WISHLIST_BUTTON_XPATH));
		jsExec = (JavascriptExecutor) driver;
		String query = "document.querySelector('a.wishlistTile').click()";
		jsExec.executeScript(query);
		return new WishlistPage(driver);
	}

	public void addToCart() {
		WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON_XPATH));
		addToCartButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_MODAL_XPATH));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CART_MODAL_XPATH));
	}

	public CartPage goToCartPage() {
		WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(SHOPPING_BAG_XPATH));
		addToCartButton.click();
		WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(VIEW_CART_MODAL_XPATH));
		viewCartButton.click();
		return new CartPage(driver);
	}
	
	
	
}
