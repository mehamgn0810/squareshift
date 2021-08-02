package com.channelnewsasia.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateAsiaNews_SingaporeEdition extends BaseTest {

	@Test
	public void validateAsiaNews_SingaporeEdition() {
		driver.get("https://www.channelnewsasia.com/news/international");

		
		WebElement eleHomeSwitcher = driver.findElement(By.id("home-switcher"));
		Select sel = new Select(eleHomeSwitcher);
		String strSelectedOption = sel.getFirstSelectedOption().getText();
		if (strSelectedOption.equalsIgnoreCase("Singapore Edition")) {
			sel.selectByVisibleText("International Edition");
			boolean isDisplayed = false;
			try {
				WebElement eleFirstNewsItem = driver.findElement(By.xpath("(//div[@class='u-grid is-full-width']//a[@class='teaser__category' and text()='Asia'])[1]"));
				wait.until(ExpectedConditions.visibilityOf(eleFirstNewsItem));
				isDisplayed = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
						
			Assert.assertTrue(isDisplayed, "International Edition not displayed");
		}
		eleHomeSwitcher = driver.findElement(By.id("home-switcher"));
		sel = new Select(eleHomeSwitcher);
		sel.selectByVisibleText("Singapore Edition");

		int size = driver
				.findElements(By.xpath(
						"//div[@class='u-grid is-full-width']//a[@class='teaser__category' and text()='Singapore']"))
				.size();
		Assert.assertTrue(size > 0, "Singapore Edition not displayed");

		validateNewsItem();
	}

}
