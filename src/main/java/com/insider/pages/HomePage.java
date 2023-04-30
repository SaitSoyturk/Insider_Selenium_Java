package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
public class HomePage extends BaseMethods {
    private final String homePageUrl = "https://useinsider.com/";
    private final By acceptCookies = By.cssSelector("#wt-cli-accept-all-btn");
    private final By navigationBarMenus = By.id("mega-menu-1");
    private final By subMenus = By.cssSelector(".dropdown-item .flex-row a h5");
    public HomePage(WebDriver driver){
        super(driver);
    }
    public HomePage goToHomePage(){
        driver.get(homePageUrl);

        if(isElementExist(acceptCookies)) {
            click(acceptCookies);
        }
        return this;
    }
    public HomePage verifyHomePageOpened(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://useinsider.com/");
        isElementExist(navigationBarMenus);
        return this;
    }
    public HomePage goToSubMenuInNavBarMenus(String menuName, String subMenu){
        WebElement category = getElementWithTextInList(navigationBarMenus, menuName);
        (category).click();
        getElementWithTextInList(subMenus, subMenu).click();
        return this;
    }
}
