package com.qa.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutStepOnePage {
    
    private final Page page;

    // 1. String locator - Object repository
    private final String headerPage = "//*[@class=\"subheader\"]";
    private final String inputUserfirstName = "//input[@id=\"first-name\"]";
    private final String inputUserLastName = "//input[@id=\"first-name\"]";
    private final String InputUserZipCode = "//input[@id=\"postal-code\"]";
    private final String btnCancel = "//a[@class=\"cart_cancel_link btn_secondary\"]";
    private final String btnContinue = "//input[@type=\"submit\"]";

   
    // 2. Page constructor
    public CheckoutStepOnePage(Page page) {
        this.page = page;
    }

    // 3. Page action or methods
    public String getCheckoutOnePageUrl() {
        String url = page.url();
        System.out.println("Checkout Step One Page URL: " + url);
        return url;
    }

    public String getCheckoutStepOnePageHeader() {
        return page.textContent(headerPage);
    }

    public void inputUsrInfoAndSubmit(String firstName, String lastName, String zipCode ) {
        page.fill(inputUserfirstName, firstName);
        page.fill(inputUserLastName, lastName);
        page.fill(InputUserZipCode, zipCode);
        page.click(btnContinue);
    }

    public ShoppingCartPage cancelAndNavigateToCart() {
        page.click(btnCancel);
        return new ShoppingCartPage(page);
    }

    public String getInputFirstNameValue(String firstName) {
        Locator lctFirstName = page.locator(inputUserfirstName);
        lctFirstName.fill(firstName);
        return lctFirstName.inputValue();
    }

    public String getInputLastNameValue(String lastName) {
        Locator lctLastName = page.locator(inputUserLastName);
        lctLastName.fill(lastName);
        return lctLastName.inputValue();
    }

    public String getInputZipCodeValue(String zipCode) {
        Locator lctZipCode = page.locator(InputUserZipCode);
        lctZipCode.fill(zipCode);
        return lctZipCode.inputValue();
    }
}
