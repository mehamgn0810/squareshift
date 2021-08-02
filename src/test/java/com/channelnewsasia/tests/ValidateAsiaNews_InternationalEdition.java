package com.channelnewsasia.tests;

import org.testng.annotations.Test;

public class ValidateAsiaNews_InternationalEdition extends BaseTest{

	

	@Test
	public void validateAsiaNews_InternationalEdition() {

		driver.get("https://www.channelnewsasia.com/");
		validateNewsItem();
	}

	

	
}
