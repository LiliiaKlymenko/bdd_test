package com.epam.tests.pages;

import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {

    private WebDriverProvider driverProvider;

    public PageFactory(WebDriverProvider driverProvider) {
        this.driverProvider = driverProvider;
    }

    public SearchPage newSearchPage() {
        return new SearchPage(driverProvider);
    }

}
