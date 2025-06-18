package com.Dream.tests;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Dream.pageObjects.DreamsDiaryPage;
import com.Dream.pageObjects.HomePage;



class TC_02_VerifyDiaryEntries extends com.Dream.base.BaseTest {

	private static final Logger logger=LogManager.getLogger(TC_01_VerifyHomePage.class);

	@Test
	public void verifyDreamLogTable() {
		logger.info("Test Started: verifyDreamLogTable");

		HomePage homePage = new HomePage(driver);
		DreamsDiaryPage diaryPage = new DreamsDiaryPage(driver);

		logger.info("Waiting for loading animation to disappear...");
		Assert.assertTrue(homePage.waitForLoadingToDisappear(), "Loading animation did not disappear in time");
		logger.info("Loading animation disappeared.");

		logger.info("Checking if 'My Dreams' button is visible...");
		Assert.assertTrue(homePage.isMyDreamsButtonVisible(), "'My Dreams' button is not visible");
		logger.info("'My Dreams' button is visible.");

		logger.info("Clicking on 'My Dreams' button...");
		homePage.clickMyDreams();

		// Switch to the new window
		logger.info("Switching to new window with title 'Dreams Diary'...");
		Set<String> windows = driver.getWindowHandles();
		for (String handle : windows) {
			driver.switchTo().window(handle);
			logger.debug("Switched to window with title: {}", driver.getTitle());
			if (driver.getTitle().equals("Dreams Diary")) {
				logger.info("Found 'Dreams Diary' window.");
				break;
			}
		}

		// Verify count of dreams
		int dreamCount = diaryPage.getDreamsCount();
		logger.info("Retrieved dream count: {}", dreamCount);
		Assert.assertEquals(dreamCount, 10, "Dream count mismatch");

		List<String> dreamTypes = diaryPage.getDreamType();
		List<String> dreamNames = diaryPage.getDreamName();
		List<String> dreamDays = diaryPage.getDays();

		// Validate types are only "Good" or "Bad"
		logger.info("Validating dream types...");
		boolean allTypesValid = dreamTypes.stream().allMatch(type -> type.equals("Good") || type.equals("Bad"));
		Assert.assertTrue(allTypesValid, "Some dream types are neither 'Good' nor 'Bad'");
		logger.info("All dream types are valid: Good or Bad");

		// Validate none of the values are null or empty
		logger.info("Validating dream data for null or empty entries...");
		for (int i = 0; i < dreamCount; i++) {
			Assert.assertNotNull(dreamNames.get(i), "Dream name is null at index " + i);
			Assert.assertNotNull(dreamDays.get(i), "Dream day is null at index " + i);
			Assert.assertNotNull(dreamTypes.get(i), "Dream type is null at index " + i);
			logger.debug("Entry {}: Name='{}', Day='{}', Type='{}'",
					i, dreamNames.get(i), dreamDays.get(i), dreamTypes.get(i));
		}

		logger.info("Test Completed: verifyDreamLogTable");
	}
}
