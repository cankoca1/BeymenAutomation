package com.automation.cucumber.steps;

import com.automation.pages.BeymenPage;
import com.automation.utility.Utility;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BeymenSteps extends Utility {

    BeymenPage beymenPage = new BeymenPage();


    @Given("I am on homepage")
    public void iAmOnHomepage() {
        String actualSolution = driver.getTitle();
        String expectedSolution = "Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu";
        System.out.println("Webpage title: " + actualSolution);
        Assert.assertEquals(expectedSolution, actualSolution);
    }


    @Then("^I pick a random item and add it to cart$")
    public void ıPickARandomItemAndAddItToCart() throws InterruptedException {
        beymenPage.pickRandomItemAndAddItToCard();
    }


    @Then("^I add more item and empty the cart$")
    public void ıAddMoreItemAndEmptyTheCart() throws InterruptedException {
        beymenPage.addMoreItemAndEmptyTheCart();
    }

    @Then("^I enter \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\",\"([^\"]*)\" word to searchbox$")
    public void ıEnterAndWordToSearchbox(int row1, int column1, int row2, int column2) throws InterruptedException {
        beymenPage.enterWordToSearchbox(row1, column1, row2, column2);
    }
}
