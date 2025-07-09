package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.utils.AppHelpers;

public class CheckoutStepOnePageTest extends BaseTest{
    AppHelpers utils = new AppHelpers();
    
    @BeforeTest
    public void loginAndNavigateToTestPage() {
        inventoryPage = loginPage.navigateInventoryPage(prop.getProperty("ValidUname").trim(), prop.getProperty("ValidPwd").trim());
        inventoryPage.addToCart("Sauce Labs Onesie");
        shoppingCartPage = inventoryPage.navigateToCartPage();
        checkoutStepOnePage = shoppingCartPage.navigateToCheckoutPage();
    }

    @Test(priority=1)
    public void checkoutStepOnePageUrlTest() {
        String url = checkoutStepOnePage.getCheckoutOnePageUrl();
        Assert.assertEquals(url, prop.getProperty("urlCheckoutStepOne"));
    }

    @Test(priority=1)
    public void headerCheckoutPageTextTest() {
        String header = checkoutStepOnePage.getCheckoutStepOnePageHeader();
        Assert.assertEquals(header, "Checkout: Your Information");
    }

    @Test(priority=2)
    public void successInputFirstName() {
        String userInput = utils.generateRandomFirstName();
        String actualInput = checkoutStepOnePage.getInputFirstNameValue(userInput);
        Assert.assertEquals(actualInput, userInput);
    }

    @Test(priority=2)
    public void successInputLastName() {
        String userInput = utils.generateRandomLastName();
        String actualInput = checkoutStepOnePage.getInputLastNameValue(userInput);
        Assert.assertEquals(actualInput, userInput);
    }

    @Test(priority=2)
    public void successInputZipCode() {
        String userInput = String.valueOf(utils.generateZipCode());
        String actualInput = checkoutStepOnePage.getInputZipCodeValue(userInput);
        Assert.assertEquals(actualInput, userInput);
    }
}
