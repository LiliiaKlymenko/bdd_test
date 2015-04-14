package com.epam.tests.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

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
                .xpath(".//div[@class='compPagination']/span")).isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            ex.getMessage();
            return false;
        }
    }

    public boolean verifyEmptySearchMessage() {
        try {
            findElement(By.xpath(".//div[@class='compText mb-15 fz-m fc-4th']/p")).isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            ex.getMessage();
            return false;
        }
    }

    public boolean verifyRedirection() {
       try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yucs-logo-ani")));
           if (findElement(By.id("yucs-logo-ani")).isDisplayed())
                return true;
            else
                return false;
      } catch (NoSuchElementException ex) {
          ex.getMessage();
          return false;
      }
    }

    public void clickFirstResult() {
        try {

            findElement(By.id("search-submit")).click();
            findElement(By
                    .xpath(".//div[@id='web']/ol[1]//h3[@class='title']/a")).click();

          for (String winHandle : getWindowHandles()) {
             switchTo().window(winHandle);

      }


        } catch (NoSuchElementException ex) {
            ex.getMessage();
        }
    }
}
