package com.channelnewsasia.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindWeather extends BaseTest{
	
	@Test
	public void findWeather() {

		driver.get("https://www.channelnewsasia.com/news/international");
		List<WebElement> listAllSections = driver.findElements(By.xpath("//em[@class='button-main-nav__button-open-text' and text()='All Sections']"));
		for(WebElement ele: listAllSections) {
			if(ele.isDisplayed()) {
				ele.click();
				break;
			}
		}
		
		driver.findElement(By.linkText("Weather")).click();
		Assert.assertTrue(getWeather("Singapore")!=null, "Unable to find Weather for the given country");		
	}
	
	public String getWeather(String countryName) {
		
		String condition = null;
		try {
			List<WebElement> eleWeatherTableRows = driver.findElements(By.xpath("//div[@class='w-data-info-holder']//table//tr"));
			for(WebElement ele: eleWeatherTableRows) {
				String strCountry = ele.findElement(By.xpath("./td[1]")).getText();
				if(strCountry.equalsIgnoreCase(countryName)) {
					condition = ele.findElement(By.xpath("./td[4]")).getText();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return condition;
		
	}

}
