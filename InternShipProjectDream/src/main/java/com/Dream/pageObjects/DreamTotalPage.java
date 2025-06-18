package com.Dream.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DreamTotalPage {

	public WebDriver driver;

	private By dreamssummary = By.cssSelector("table tbody tr");
	
	public DreamTotalPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<String> getfinalDreamType() {
		List<WebElement> dreamss=driver.findElements(dreamssummary);
		List<String> types=new ArrayList<>();
		for(WebElement dream:dreamss) {
			String type=dream.findElements(By.tagName("td")).get(0).getText();
			types.add(type);
		}
		return types;
	}
	
	public List<String> getDreamTypeCount() {
		List<WebElement> dreamss=driver.findElements(dreamssummary);
		List<String> count=new ArrayList<>();
		for(WebElement dream:dreamss) {
			String type=dream.findElements(By.tagName("td")).get(1).getText();
			count.add(type);
		}
		return count;
	}
}
