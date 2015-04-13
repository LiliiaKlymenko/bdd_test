package com.epam.examples.steps;

import com.epam.tests.pages.PageFactory;
import com.epam.tests.pages.SearchPage;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertTrue;

//Mapping Textual Scenario Steps to Java Methods via annotations

public class SearchSteps {

    private final PageFactory pages;
    private SearchPage searchPage;

    public SearchSteps(PageFactory pages) {
        this.pages = pages;
    }

    @BeforeScenario
    public void setupPages() {
        searchPage = pages.newSearchPage();
    }

    @Given("I am on search page")
    public void openSearchPage() {
        searchPage.open();
    }

        // @When("I have a search results")
        // {
        //      assertTrue(searchPage.verifySearchResults());
        // }

    @When("Enter the value in Search field $value")
    public void setSearchParameters(String value) {
        searchPage.typeSearchParameters(value);
    }

    @When("click on search button")
    public void clickButtonSearch() {
        searchPage.clickSearchButton();
    }

    @When("Select the first result in search field")
      public void clickFirstResult() {
        searchPage.clickFirstResult();
    }

    @Then("verify the search results")
    public void verifySearchResultShown() {
        assertTrue(searchPage.verifySearchResults());
    }


    @Then("verify the message")
    public void messageIsShown() {
        assertTrue(searchPage.verifyEmptySearchMessage());
    }

    @Then("verify redirection to result page")
    public void verifyRedirection() {
        assertTrue(searchPage.verifyRedirection());
    }

}
