package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class QAPage extends BaseMethods {
    private final By seeAllJobsButton = By.cssSelector(".flex-column .button-group .btn");
    public QAPage(WebDriver driver){
        super(driver);
    }
    public void clickSeeAllJobsBtn(){
        click(seeAllJobsButton);
    }
}
