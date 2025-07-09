package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;

public class ShoppingCartPageTest extends BaseTest {
    
    @BeforeTest
    public void loginPrerequisite() {
        inventoryPage = loginPage.navigateInventoryPage(prop.getProperty("ValidUname").trim(), prop.getProperty("ValidPwd").trim());
        String inventoryActualUrl = inventoryPage.getInventoryPageUrl();
        Assert.assertEquals(inventoryActualUrl, prop.getProperty("urlInventoryPage"));
        shoppingCartPage = inventoryPage.navigateToCartPage();
        // String cartPageActualUrl = shoppingCartPage.getCartPageUrl();
        // Assert.assertEquals(cartPageActualUrl, prop.getProperty("urlCartPage"));
    }

    @Test(priority=1)
    public void shoppingCartPageUrlTest() {
        String cartPageActualUrl = shoppingCartPage.getCartPageUrl();
        Assert.assertEquals(cartPageActualUrl, prop.getProperty("urlCartPage"));

    }

    @Test(priority=1)
    public void checkPageTitleTest() {
        String pageTitle = shoppingCartPage.getPageTitle();
        Assert.assertEquals(pageTitle, AppConstants.SHOPPING_CART_PAGE_TITLE );
    }

    @Test(priority=1)
    public void btnCheckoutIsExistTest() {
        Assert.assertTrue(shoppingCartPage.btnCheckoutIsExist());
    }

    @Test(priority=1)
    public void checkHeaderTextTest() {
        Assert.assertEquals(shoppingCartPage.checkHeaderText(), "Your Cart");
    }

    @Test(priority=1)
    public void btnHamburgerIsExistTest() {
        Assert.assertTrue(shoppingCartPage.btnHamburgerIsExist());
    }

    @Test(priority=1)
    public void btnContinueShoppingIsExistTest() {
        Assert.assertTrue(shoppingCartPage.btnContinueShoppingIsExist());
    }

    @Test(priority=2)
    public void successNavigateToInventoryPage() {
        inventoryPage = shoppingCartPage.navigateToInventoryPage();
        Assert.assertEquals(inventoryPage.getInventoryPageUrl(), prop.getProperty("urlInventoryPage"));
    }

    @DataProvider
    public Object [][] productList() {
        return new Object[][] {
            {"Sauce Labs Onesie", "$7.99"},
            {"Sauce Labs Bike Light", "$9.99"},
            {"Sauce Labs Backpack", "$29.99"},
            {"Sauce Labs Bolt T-Shirt", "$15.99"},
            {"Sauce Labs Fleece Jacket", "$49.99"},
            {"Test.allTheThings() T-Shirt (Red)", "$15.99"},
        };
    }

    @Test(priority=3, dataProvider="productList")
    public void itemAddSuccessTest(String productName, String productPrice) {
        inventoryPage.redirectToInventoryPageUrl();
        inventoryPage.addToCart(productName);
        shoppingCartPage = inventoryPage.navigateToCartPage();
        Assert.assertEquals(shoppingCartPage.getItemName(), productName);
        shoppingCartPage.btnRemoveClick();
    }

    @Test(priority=3)
    public void successNavigateCheckout() {
        inventoryPage.redirectToInventoryPageUrl();
        inventoryPage.addToCart("Sauce Labs Onesie");
        shoppingCartPage = inventoryPage.navigateToCartPage();
        checkoutStepOnePage = shoppingCartPage.navigateToCheckoutPage();
        String actualUrl = checkoutStepOnePage.getCheckoutOnePageUrl();
        Assert.assertEquals(actualUrl, prop.getProperty("urlCheckoutStepOne") );
    }
}
