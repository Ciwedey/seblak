package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;

public class InventoryPageTest extends BaseTest{

    @BeforeTest
    public void loginValid() {
        inventoryPage = loginPage.navigateInventoryPage(prop.getProperty("ValidUname").trim(), prop.getProperty("ValidPwd").trim());        
    }

    @Test(priority=1)
    public void InventoryPageUrlTest() {
        String actualUrl = inventoryPage.getInventoryPageUrl();
        Assert.assertEquals(actualUrl, prop.getProperty("urlInventoryPage"));
    }

    @Test(priority=1)
    public void pageTitleTest() {
        String actualTitle = inventoryPage.getPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.INVENTORY_PAGE_TITLE);
    }

    @Test(priority=1)
    public void sortButtonIsExistTest() {
        Assert.assertTrue(inventoryPage.checkSortButton());
    }

    @Test(priority=1)
    public void cartButtonIsExistTest() {
        Assert.assertTrue(inventoryPage.checkCartButton());
    }

    @Test(priority=1)
    public void textProductTest() {
        String actualLabelText = inventoryPage.checkInventoryPageProductLabel();
        Assert.assertEquals(actualLabelText, "Products");
    }

    @Test(priority=2)
    public void sortByNameZtoATest() {
        String actualFirstItemNameText = inventoryPage.sortByNameZtoA();
        Assert.assertEquals(actualFirstItemNameText, "Test.allTheThings() T-Shirt (Red)");
    }

    @Test(priority=2)
    public void sortByNameAtoZTest() {
        String actualFirstItemNameText = inventoryPage.sortByNameAtoZ();
        Assert.assertEquals(actualFirstItemNameText, "Sauce Labs Backpack");
    }

    @Test(priority=2)
    public void sortByPriceLoHiTest() {
        String actualFirstItemNameText = inventoryPage.sortByPriceLoHi();
        Assert.assertEquals(actualFirstItemNameText, "Sauce Labs Onesie");
    }

    @Test(priority=2)
    public void sortByPriceHiLoTest() {
        String actualFirstItemNameText = inventoryPage.sortByPriceHiLo();
        Assert.assertEquals(actualFirstItemNameText, "Sauce Labs Fleece Jacket");
    }

    @DataProvider
    public Object[][] getProductList() {
        return new Object[][] {
            {"Sauce Labs Onesie", "$7.99"},
            {"Sauce Labs Bike Light", "$9.99"},
            {"Sauce Labs Backpack", "$29.99"},
            {"Sauce Labs Bolt T-Shirt", "$15.99"},
            {"Sauce Labs Fleece Jacket", "$49.99"},
            {"Test.allTheThings() T-Shirt (Red)", "$15.99"},
        };
    }

    @Test(priority=3, dataProvider="getProductList")
    public void itemPriceTest(String productName, String productPrice) {
        String getProductPrice = inventoryPage.getItemPrice(productName);
        Assert.assertEquals(getProductPrice, productPrice);
    }
    
    @Test(priority=3, dataProvider="getProductList")
    public void addItemToCart(String productName, String productPrice){
        // Success add item to cart
        Assert.assertTrue(inventoryPage.addToCart(productName));
        // Success remove item from cart
        Assert.assertTrue(inventoryPage.removeBtnClick());
    }    

    @Test(priority=3)
    public void navigateToCartPageTest() {
        shoppingCartPage = inventoryPage.navigateToCartPage();
        String actualUrl = shoppingCartPage.getCartPageUrl();
        Assert.assertEquals(actualUrl, prop.getProperty("urlCartPage"));
        shoppingCartPage.navigateToInventoryPage();
    }

}