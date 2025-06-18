package com.Dream.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	public WebDriver driver;
	private By loadingAnimation = By.id("loadingAnimation");
	private By mainContent = By.id("mainContent");
	private By myDreamsButton = By.id("dreamButton");
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean waitForLoadingToDisappear() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	    return wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingAnimation));
	}

	public boolean isMainContentDisplayed() {
		return driver.findElement(mainContent).isDisplayed();
	}
	
	public boolean isMyDreamsButtonVisible() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(myDreamsButton));
		return driver.findElement(myDreamsButton).isDisplayed();
	}

	public void clickMyDreams() {
		driver.findElement(myDreamsButton).click();
	}
}
