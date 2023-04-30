package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.lang.*;
import java.util.List;
public class PositionsPage extends BaseMethods {
    private final By filterByLocation = By.id("select2-filter-by-location-container");
    private final By filterByDepartment = By.xpath("//span[@title='Quality Assurance']");
    private final By positions = By.cssSelector(".position-list-item");
    private final By positionsDepartment = By.cssSelector(".position-department");
    private final By positionsLocation = By.cssSelector(".position-location");
    private final By buttonsLocator = By.cssSelector(".btn");
    public PositionsPage(WebDriver driver){
        super(driver);
    }
    public PositionsPage filterJobs(String location){
        waitToElement(filterByDepartment);
        click(filterByLocation);
        WebElement state = driver.findElement(By.xpath("//*[@class='select2-results__option'][text()='"+location+"']"));
        state.click();
        return this;
    }
    public PositionsPage checkPresenceOfPositions(){
        scrollToPage();
        waitFor(2);
        List<WebElement> elements = driver.findElements(positions);
        Assert.assertTrue(elements.size()>0);
        return this;
    }
    public PositionsPage verifyPositionsContainsJobTitle(String jobTitle){
        getElementsText(positions).forEach(e -> {
            Assert.assertTrue(e.contains(jobTitle));
        });
        return this;
    }
    public PositionsPage verifyDepartmentsText(String jobTitle){
        getElementsText(positionsDepartment).forEach(e -> {
            Assert.assertTrue(e.equals(jobTitle));
        });
        return this;
    }
    public PositionsPage verifyLocationsText(String location){
        getElementsText(positionsLocation).forEach(e -> {
            Assert.assertTrue(e.equals(location));
        });
        return this;
    }
    public WebElement getApplyButton(){
        List<WebElement> elements = driver.findElements(positions);
        WebElement applyButton=null;
        for (WebElement position : elements) {
            applyButton = position.findElement(buttonsLocator);
        }
        return applyButton;
    }
    public PositionsPage clickApplyButton(){
        getApplyButton().click();
        return this;
    }
}
