package com.Dream.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Dream.base.BaseTest;
import com.Dream.pageObjects.DreamTotalPage;
import com.Dream.pageObjects.DreamsDiaryPage;
import com.Dream.pageObjects.HomePage;



public class TC_03_VerifySummaryStats extends BaseTest{

    private static final Logger logger = LogManager.getLogger(TC_03_VerifySummaryStats.class);

    @Test
    public void verifyDreamCountConsistency() {
    	
        logger.info("Test Started: verifyDreamCountConsistency");

        HomePage homePage = new HomePage(driver);
        DreamsDiaryPage diaryPage = new DreamsDiaryPage(driver);
        DreamTotalPage totalPage = new DreamTotalPage(driver);

        logger.info("Waiting for loading animation to disappear...");
        Assert.assertTrue(homePage.waitForLoadingToDisappear(), "Loading animation did not disappear in time");
        logger.info("Loading animation disappeared.");

        logger.info("Checking 'My Dreams' button visibility...");
        Assert.assertTrue(homePage.isMyDreamsButtonVisible(), "'My Dreams' button is not visible");
        logger.info("'My Dreams' button is visible. Clicking it...");
        homePage.clickMyDreams();

        // Switch to Diary window
        logger.info("Switching to 'Dreams Diary' window...");
        Set<String> windows = driver.getWindowHandles();
        for (String handle : windows) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals("Dreams Diary")) {
                logger.info("Switched to 'Dreams Diary' window.");
                break;
            }
        }

        List<String> dreams = diaryPage.getDreamName();
        logger.info("Retrieved dream names: {}", dreams);

        List<String> dreamTypes = diaryPage.getDreamType();
        logger.info("Retrieved dream types: {}", dreamTypes);

        long goodCount = dreamTypes.stream().filter(type -> type.equals("Good")).count();
        long badCount = dreamTypes.stream().filter(type -> type.equals("Bad")).count();

        logger.info("Diary Page Dream Count: Good = {}, Bad = {}", goodCount, badCount);

        // Switch to Total page
        logger.info("Switching to 'Dreams Total' window...");
        for (String handle : windows) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals("Dreams Total")) {
                logger.info("Switched to 'Dreams Total' window.");
                break;
            }
        }

        List<String> typeLabels = totalPage.getfinalDreamType();
        List<String> typeCounts = totalPage.getDreamTypeCount();

        logger.info("Retrieved Type Labels: {}", typeLabels);
        logger.info("Retrieved Type Counts: {}", typeCounts);

        int totalGood = 0, totalBad = 0, totalDreams = 0, recurringDreams = 0;

        for (int i = 0; i < typeLabels.size(); i++) {
            String label = typeLabels.get(i);
            int count = Integer.parseInt(typeCounts.get(i));
            switch (label) {
                case "Good Dreams": totalGood = count; break;
                case "Bad Dreams": totalBad = count; break;
                case "Total Dreams": totalDreams = count; break;
                case "Recurring Dreams": recurringDreams = count; break;
            }
        }

        logger.info("Total Page Counts -> Good: {}, Bad: {}, Total: {}, Recurring: {}", totalGood, totalBad, totalDreams, recurringDreams);

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String dream : dreams) {
            if (!seen.add(dream)) {
                duplicates.add(dream);
            }
        }

        logger.info("Detected recurring dreams: {}", duplicates);

        Set<String> expectedDuplicates = new HashSet<>();
        expectedDuplicates.add("Flying over mountains");
        expectedDuplicates.add("Lost in maze");

        logger.info("Expected recurring dreams: {}", expectedDuplicates);

        // Final Assertions
        logger.info("Asserting dream counts and recurring dreams...");

        Assert.assertEquals(totalGood, goodCount, "Mismatch in Good Dreams count");
        logger.info("Good dreams count matches.");

        Assert.assertEquals(totalBad, badCount, "Mismatch in Bad Dreams count");
        logger.info("Bad dreams count matches.");

        Assert.assertEquals(totalDreams, goodCount + badCount, "Mismatch in Total Dreams count");
        logger.info("Total dreams count matches.");

        Assert.assertEquals(recurringDreams, duplicates.size(), "Mismatch in Recurring Dreams count");
        logger.info("Recurring dreams count matches.");

        Assert.assertEquals(duplicates, expectedDuplicates, "Mismatch in Recurring Dreams List");
        logger.info("Recurring dreams list matches expected.");

        logger.info("Test Completed: verifyDreamCountConsistency");
    }
}
