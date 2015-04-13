package com.epam.examples.runner;

import org.jbehave.web.selenium.DelegatingWebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Locale;
/**
 * Created by Lili on 12.04.2015.
 */
public class PropertyWebDriverProvider extends DelegatingWebDriverProvider {

    public enum Browser {
        CHROME, IE
    }

    public void initialize() {
        Browser browser = Browser.valueOf(Browser.class, System.getProperty("browser", "ie").toUpperCase(usingLocale()));
        delegate.set(createDriver(browser));
    }

    private WebDriver createDriver(Browser browser) {
        switch (browser) {

            case CHROME:
                return createChromeDriver();
            case IE:
            default:
                return createInternetExplorerDriver();
        }

    }
    protected ChromeDriver createChromeDriver() {
        return new ChromeDriver();
    }

    protected InternetExplorerDriver createInternetExplorerDriver() {
        return new InternetExplorerDriver();
    }

    protected Locale usingLocale() {
        return Locale.getDefault();
    }

}
