package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static utils.LogUtils.logInfo;

public class BaseMethods {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected String firstTab;
    protected ArrayList<String> newTab;

    public BaseMethods(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(this.driver);
        this.wait = new WebDriverWait(driver, ofSeconds(5));
        firstTab = driver.getWindowHandle();
    }
    public void click(By element) {
        waitToElement(element).click();
    }
    public boolean isElementExist(By element) {
        return driver.findElements(element).size() > 0;
    }
    public WebElement waitToElement(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void scrollToPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
    }
    public WebElement getElementWithTextInList(By element, String name){
        List<WebElement> elements = driver.findElements(element);

        for(WebElement item : elements){

            logInfo(item.getText());
            if (item.getText().contains(name)){
                return item;
            }
        }

        logInfo("Given text is not exists");
        return null;
    }
    public List<String> getElementsText(By locator) {

        List<WebElement> elems = driver.findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }
    public void switchToTab() {
        newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(firstTab);
        driver.switchTo().window(newTab.get(0));
    }
}
