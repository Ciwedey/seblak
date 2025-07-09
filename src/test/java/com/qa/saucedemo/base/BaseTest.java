package com.qa.saucedemo.base;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.saucedemo.factory.PlaywrightFactory;
import com.qa.saucedemo.pages.CheckoutStepOnePage;
import com.qa.saucedemo.pages.InventoryPage;
import com.qa.saucedemo.pages.LoginPage;
import com.qa.saucedemo.pages.ShoppingCartPage;

// As a parrent class for init properties, browser before and browser after test
public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected Properties prop; // init properties

    protected LoginPage loginPage; // login page reference
    protected InventoryPage inventoryPage; // inventory page reference
    protected ShoppingCartPage shoppingCartPage; // Shopping cart page reference
    protected CheckoutStepOnePage checkoutStepOnePage; // Checkout step one page page reference

    @BeforeTest
    public void setup() throws IOException{
        pf = new PlaywrightFactory();
        prop = pf.init_prop(); 
        page = pf.initBrowserSettings(prop);
        loginPage = new LoginPage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }


}
