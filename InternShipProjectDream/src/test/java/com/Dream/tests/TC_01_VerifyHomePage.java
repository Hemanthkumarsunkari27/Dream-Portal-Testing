package com.Dream.tests;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Dream.base.BaseTest;
import com.Dream.pageObjects.HomePage;


public class TC_01_VerifyHomePage extends BaseTest {

	private static final Logger logger=LogManager.getLogger(TC_01_VerifyHomePage.class);

	@Test
	public void verifyDreamPortalHome() {
		
		logger.info("Test Started: verifyDreamPortalHome");

		HomePage homePage = new HomePage(driver);

		logger.info("Waiting for loading animation to disappear...");
		boolean isLoadingGone = homePage.waitForLoadingToDisappear();
		Assert.assertTrue(isLoadingGone, "Loading animation should disappear");
		logger.info("Loading animation disappeared.");
		
		logger.info("Checking visibility of Main content...");
		boolean isMainContentsVisible = homePage.isMainContentDisplayed();
		Assert.assertTrue(isMainContentsVisible, "Main content should be visible");
		logger.info("Main content is visible.");

		logger.info("Checking visibility of 'My Dreams' button...");
		boolean isMyDreamsVisible = homePage.isMyDreamsButtonVisible();
		Assert.assertTrue(isMyDreamsVisible, "'My Dreams' button should be visible");
		logger.info("'My Dreams' button is visible.");

		String originalWindow = driver.getWindowHandle();
		logger.info("Stored original window handle: {}", originalWindow);

		logger.info("Clicking on 'My Dreams' button...");
		homePage.clickMyDreams();

		logger.info("Switching to new window...");
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(originalWindow)) {
				driver.switchTo().window(handle);
				logger.info("Switched to new window with handle: {}", handle);

				String currentUrl = driver.getCurrentUrl();
				logger.info("Current URL in new window: {}", currentUrl);

				boolean isExpectedPage = currentUrl.contains("dreams-diary.html") || currentUrl.contains("dreams-total.html");
				Assert.assertTrue(isExpectedPage, "Expected 'dreams-diary.html' or 'dreams-total.html' page, but got: " + currentUrl);
				logger.info("Redirected to expected page.");

				break;
			}
		}

		logger.info("Test Completed: verifyDreamPortalHome");
	}

}
