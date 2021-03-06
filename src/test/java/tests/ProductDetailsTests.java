package test.java.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.pages.CartPage;
import test.java.pages.ProductDetailsPage;
import test.java.pages.ReviewPage;
import test.java.pages.SearchResultsPage;
import test.java.pages.WishlistPage;

public class ProductDetailsTests extends BaseTest {
	
	@Test
	public void canAddProductToWishlist() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		ProductDetailsPage pdp = srp.followFirstResult();
		WishlistPage wlp = pdp.addProductToWishlist();
		Assert.assertFalse(wlp.isWishlistEmpty(), "[[[Filters have not been reset!]]]");
	}
	
	@Test
	public void canAddProductToCart() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		ProductDetailsPage pdp = srp.followFirstResult();
		pdp.addToCart();
		CartPage cp = pdp.goToCartPage();
		Assert.assertFalse(cp.isCartEmpty(), "[[[Product was not added to Cart!]]]");
	}
	
	@Test
	public void isUserRedirectedToReviewForm() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		ProductDetailsPage pdp = srp.followFirstResult();
		ReviewPage rp = pdp.writeReview();
		Assert.assertTrue(rp.canLeaveReview(), "[[[User was not redirected to review form!]]]");
	}
	
	@Test
	public void doGalleryArrowsChangePicture() {
		openPage(SRP_URL);
		SearchResultsPage srp = new SearchResultsPage(driver);
		ProductDetailsPage pdp = srp.followFirstResult();
		Assert.assertTrue(pdp.canNavigateGallery(), "[[[Gallery arrows do not change image!]]]");
	}
	

	

	
	
	
	
	
	
}
