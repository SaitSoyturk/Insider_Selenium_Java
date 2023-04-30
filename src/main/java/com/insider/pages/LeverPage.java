package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
public class LeverPage extends BaseMethods {

    private final String expectedLeverPageUrl = "https://jobs.lever.co/useinsider/0ba4065b-955a-4661-ad4a-f32479f63757";
    private final By header = By.cssSelector("div[class='posting-headline'] h2");
    public LeverPage(WebDriver driver){
        super(driver);
    }
    public void verifyLeverPageOpened(){
        switchToTab();
        Assert.assertEquals(driver.getCurrentUrl(),expectedLeverPageUrl);
        Assert.assertTrue(driver.findElement(header).getText().contains("Quality Assurance"));
    }
}
