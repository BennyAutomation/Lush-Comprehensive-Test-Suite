package test.java.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.pages.SearchResultsPage;
import test.java.pages.WishlistPage;

public class SearchResultsTests extends BaseTest {
		
	@Test
	public void areResultsDisplayingProductInfo() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		List<WebElement> productCards = srp.getProducts();
		Assert.assertTrue(srp.isProductInfoDisplayed(productCards), "[[[Products are missing information!]]]");
	}
	
	@Test
	public void doFiltersLimitResults() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		int initialResultCount = srp.getResultCount();
		srp.filterProductsByScent();
		Assert.assertTrue(initialResultCount > srp.getResultCount(), "[[[Filters have not limited results!]]]");
	}
	
	@Test
	public void doesResetButtonRemoveFilters() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		srp.filterProductsByScent();
		int initialResultCount = srp.getResultCount();
		srp.resetFilters();
		Assert.assertTrue(initialResultCount < srp.getResultCount(), "[[[Filters have not been reset!]]]");
	}
	
	@Test
	public void canProductsBeAddedToWishlist() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		WishlistPage wlp = srp.addProductToWishlist();
		Assert.assertFalse(wlp.isWishlistEmpty(), "[[[Product was not added to Wishlist!]]]");
	}
}
