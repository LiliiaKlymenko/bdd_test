package com.epam.tests.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class SearchPage extends WebDriverPage {

    WebDriverWait wait;

    public SearchPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        wait = new WebDriverWait(driverProvider.get(), 30);
        PageFactory.initElements(driverProvider.get(), this);
    }

    public void open() {
        get("http://www.yahoo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-submit")));

    }

    public void typeSearchParameters(String searchParameters) {
        findElement(By.id("p_13838465-p")).sendKeys(searchParameters);
    }

    public void clickSearchButton() {
        findElement(By.id("search-submit")).click();
    }

    public boolean verifySearchResults() {
        try {
            findElement(By
                .xpath("//h2[contains(., 'Search results')]")).isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            ex.getMessage();
            return false;
        }
    }

    public boolean verifyEmptySearchMessage() {
        try {
            findElement(By
                .xpath("//h2[contains(., 'We did not find results for:')]")).isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            ex.getMessage();
            return false;
        }
    }

    public void clickFirstResult() {
        try {
            findElement(By
                    .xpath("//h2[contains(., 'Search results')]")).click();
        }
        catch (NoSuchElementException ex) {
            ex.getMessage();
        }
    }

    public boolean verifyRedirection() {
        try {
            if (!(getTitle()).contains("Yahoo"))
                return true;
            else
            return false;
        }
        catch (NoSuchElementException ex) {
            ex.getMessage();
            return false;
        }
    }
}
