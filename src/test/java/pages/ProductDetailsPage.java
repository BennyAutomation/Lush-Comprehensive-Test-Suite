package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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
	private static final By WRITE_REVIEW_BUTTON_XPATH   = By.xpath("//a[contains(@class , 'pr-snippet-write-review-link')]");
	private static final By GALLERY_LEFT_ARROW_XPATH    = By.xpath("//div[@class = 'swiper-button-prev']");
	private static final By FIRST_GALLERY_IMAGE_XPATH   = By.xpath("(//div[contains(@class , 'pdp-main-images')]//div[contains(@class , 'swiper-slide')])[2]");
	private static final By SECOND_GALLERY_IMAGE_XPATH  = By.xpath("(//div[contains(@class , 'pdp-main-images')]//div[contains(@class , 'swiper-slide')])[3]");
	
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

	public ReviewPage writeReview() {
		WebElement writeReviewButton = wait.until(ExpectedConditions.elementToBeClickable(WRITE_REVIEW_BUTTON_XPATH));
		writeReviewButton.click();
		return new ReviewPage(driver);
	}

	public boolean canNavigateGallery() {
		try {
			shiftGallery("right");
			wait.until(ExpectedConditions.attributeContains(FIRST_GALLERY_IMAGE_XPATH, "class", "swiper-slide-prev"));
			wait.until(ExpectedConditions.attributeContains(SECOND_GALLERY_IMAGE_XPATH, "class", "swiper-slide-active"));
			shiftGallery("left");
			wait.until(ExpectedConditions.attributeContains(FIRST_GALLERY_IMAGE_XPATH, "class", "swiper-slide-active"));
			wait.until(ExpectedConditions.attributeContains(SECOND_GALLERY_IMAGE_XPATH, "class", "swiper-slide-next"));
		} catch (TimeoutException ex) {
			return false;
		}
		return true;
	}
	
	private void shiftGallery(String direction) {
		wait.until(ExpectedConditions.elementToBeClickable(GALLERY_LEFT_ARROW_XPATH));
		jsExec = (JavascriptExecutor) driver;
		String query = "";
		switch (direction) {
		case "left":
			query = "document.querySelector('div.swiper-button-prev').click()";
			break;
		case "right":
			query = "document.querySelector('div.swiper-button-next').click()";
			break;
		default:
			throw new IllegalArgumentException("Please use keyword 'left' or 'right' in shiftGallery method.");
		}
		jsExec.executeScript(query);
	}

	
}
