package com.channelnewsasia.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	public WebDriver driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driverBinary/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void validateNewsItem() {
		// Validate news item
		WebElement eleFirstNewsItem = driver.findElement(By.xpath("(//h3[@class='teaser__heading']/a)[1]"));
		Assert.assertTrue(eleFirstNewsItem.isDisplayed(), "News item not displayed");
		String strFirstNewsItem = eleFirstNewsItem.getText();

		// Click First news item
		eleFirstNewsItem.click();

		WebElement eleArticleTitle = driver.findElement(By.xpath("//h1[@class='article__title']"));
		String strArticleTitle = eleArticleTitle.getText();
		// Validate news page
		Assert.assertEquals(strArticleTitle, strFirstNewsItem, "News item title and Article title did not match");

		// Scroll to load two more news items
		int counter = 0;
		while (counter < 1000) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,125)");
			int size = driver.findElements(By.xpath("//h1[@class='article__title']")).size();
			if (size >= 3) {
				break;
			}
			counter++;
		}

		WebElement elethirdArticle = driver.findElement(By.xpath("(//h1[@class='article__title'])[3]"));
		String strthirdArticle = elethirdArticle.getText();
		WebElement btnReadFullStory = elethirdArticle.findElement(By.xpath(
				".//ancestor::div[2]//following-sibling::div[@class='article__read-full-story-wrapper']//button"));
		wait.until(ExpectedConditions.elementToBeClickable(btnReadFullStory)).click();

		// Validate Page title matches with news title
		String title = driver.getTitle();
		Assert.assertEquals(title, strthirdArticle, "Article title and Page title did not match");
	}

}
