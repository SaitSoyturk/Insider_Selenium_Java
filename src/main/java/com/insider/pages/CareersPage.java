package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
public class CareersPage extends BaseMethods {
    private final By locationsBlock = By.cssSelector("#career-our-location");
    private final By teamsBlock = By.cssSelector("#career-find-our-calling");
    private final By lifeAtInsiderBlock = By.xpath("//section[@data-id='a8e7b90']");
    private final By seeAllTeamsButton = By.cssSelector("#career-find-our-calling .btn");
    private final By jobTitles = By.cssSelector(".job-title a h3");

    public CareersPage(WebDriver driver){
        super(driver);
    }
    public CareersPage verifyCareersPageOpened(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://useinsider.com/careers/");
        isElementExist(locationsBlock);
        isElementExist(teamsBlock);
        isElementExist(lifeAtInsiderBlock);
        return this;
    }
    public CareersPage selectTeam(String jobTitle){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(seeAllTeamsButton));
        wait.until(ExpectedConditions.numberOfElementsToBe(jobTitles,15));
        executor.executeScript("arguments[0].click();", getElementWithTextInList(jobTitles, jobTitle));
        return this;
    }
}
