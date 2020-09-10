package test.java.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

	private static final By PRODUCT_CARD_XPATH          = By.xpath("//div[@class = 'product']");
	private static final By PRODUCT_CARD_NAME_XPATH     = By.xpath(".//h3[@class = 'product-tile-name']/a");
	private static final By PRODUCT_CARD_DESC_XPATH     = By.xpath(".//div[@class = 'product-tile-tagline']");
	private static final By PRODUCT_CARD_PRICE_XPATH    = By.xpath(".//span[@class = 'selectr-label']");
	private static final By RESULTS_COUNT_XPATH         = By.xpath("(//div[contains(@class, 'result-count')]//span)[1]");
	private static final By SCENT_FILTER_XPATH          = By.xpath("//div[@id = 'refinement-scent']//li//button");
	private static final By RESET_BUTTON_XPATH          = By.xpath("//button[contains(@class , 'reset')]");
	private static final By WISHLIST_BUTTON_XPATH       = By.xpath("//a[contains(@class , 'wishlistTile')]");
	
	WebDriver driver;
	WebDriverWait wait;
	
	public SearchResultsPage(WebDriver drv) {
		driver = drv;
		wait = new WebDriverWait(driver, 15);
	}

	public List<WebElement> getProducts() {
		List<WebElement> productList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_CARD_XPATH));
		return productList;
	}

	public boolean isProductInfoDisplayed(List<WebElement> productCards) {
		for (WebElement productCard : productCards) {
			String name = productCard.findElement(PRODUCT_CARD_NAME_XPATH).getText();
			String desc = productCard.findElement(PRODUCT_CARD_DESC_XPATH).getText();
			String price = productCard.findElement(PRODUCT_CARD_PRICE_XPATH).getText();
			
			if (name.isEmpty() || desc.isEmpty() || price.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public int getResultCount() {
		WebElement countText = wait.until(ExpectedConditions.visibilityOfElementLocated(RESULTS_COUNT_XPATH));
		return Integer.parseInt(countText.getText().replaceAll("\\D", ""));
	}

	public void filterProductsByScent() {
		clickButtonAndWait(SCENT_FILTER_XPATH);
	}

	public void resetFilters() {
		clickButtonAndWait(RESET_BUTTON_XPATH);
	}
	
	private void clickButtonAndWait(By locator) {
		String countText = wait.until(ExpectedConditions.visibilityOfElementLocated(RESULTS_COUNT_XPATH)).getText();
		WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
		resetButton.click();
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(RESULTS_COUNT_XPATH, countText)));
	}

	public WishlistPage addProductToWishlist() {
		WebElement wishlistButton = wait.until(ExpectedConditions.elementToBeClickable(WISHLIST_BUTTON_XPATH));
		wishlistButton.click();
		return new WishlistPage(driver);
	}

	public ProductDetailsPage followFirstResult() {
		WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(PRODUCT_CARD_XPATH));
		firstResult.click();
		return new ProductDetailsPage(driver);
	}
	
	
	
	
}
