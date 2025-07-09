package com.qa.saucedemo.pages;

import com.microsoft.playwright.Page;

public class ShoppingCartPage {
    
    private final Page page;

    // 1. String locator - Object repository
    private final String btnContinueShopping = "//*[@class='btn_secondary' and text()='Continue Shopping']";
    private final String btnCheckout = "//*[@class='btn_action checkout_button']";
    private final String pageHeaderTxt = "//*[@class='subheader']";
    private final String hamburgerButton = "div[class='bm-burger-button']";
    private final String itemName = "//*[@class='inventory_item_name']";
    private final String itemPrice = "div[class=inventory_item_price]";
    private final String btnRemove = "//*[@class='btn_secondary cart_button']";
    
    
    // 2. Page constructor
    public ShoppingCartPage(Page page) {
        this.page = page;
    }

    // 3. Page action or methods
    public String getCartPageUrl() {
        String url = page.url();
        System.out.println("Shopping cart Page URL: " + url);
        return url;
    }

    public String getPageTitle() {
        String title = page.title();
        return title;
    }

    public boolean btnCheckoutIsExist() {
        return page.isVisible(btnCheckout);
    }

    public String checkHeaderText() {
        return page.textContent(pageHeaderTxt);
    }

    public boolean btnHamburgerIsExist() {
        return page.isVisible(hamburgerButton);
    }

    public boolean btnContinueShoppingIsExist(){
        return page.isVisible(btnContinueShopping);
    }

    public String getItemName() {
        return page.textContent(itemName);
    }

    public String getItemPrice() {
        return page.textContent(itemPrice);
    }

    public InventoryPage navigateToInventoryPage() {
        page.click(btnContinueShopping);
        return new InventoryPage(page);
    }

    public void btnRemoveClick() {
        page.click(btnRemove);
    }

    public CheckoutStepOnePage navigateToCheckoutPage() {
        page.click(btnCheckout);
        return new CheckoutStepOnePage(page);
    }

}
