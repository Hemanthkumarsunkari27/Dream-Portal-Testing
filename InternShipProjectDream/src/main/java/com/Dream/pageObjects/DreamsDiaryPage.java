package com.Dream.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DreamsDiaryPage {
	public WebDriver driver;

	private By dreams = By.cssSelector("table tbody tr");
	public DreamsDiaryPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getDreamsCount() {
		List<WebElement> types=driver.findElements(dreams);
		return types.size();
	}
	
	public List<String> getDreamName() {
		List<WebElement> dreamss=driver.findElements(dreams);
		List<String> names=new ArrayList<>();
		for(WebElement dream:dreamss) {
			String name=dream.findElements(By.tagName("td")).get(0).getText();
			names.add(name);
		}
		return names;
	}
	
	public List<String> getDays() {
		List<WebElement> dreamss=driver.findElements(dreams);
		List<String> days=new ArrayList<>();
		for(WebElement dream:dreamss) {
			String day=dream.findElements(By.tagName("td")).get(1).getText();
			days.add(day);
		}
		return days;
	}

	public List<String> getDreamType() {
		List<WebElement> dreamss=driver.findElements(dreams);
		List<String> types=new ArrayList<>();
		for(WebElement dream:dreamss) {
			String type=dream.findElements(By.tagName("td")).get(2).getText();
			types.add(type);
		}
		return types;
	}

}
