package com.qa.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage {
    
    private final Page page;

    // 1. String locator - Object repository
    private final String productLabel = "div[class=\"product_label\"]";
    private final String gotoCartButton = "svg[data-icon=\"shopping-cart\"]";
    private final String sortButton = "select[class=\"product_sort_container\"]";
    private final String firstIndexItemName = "(//div[@class='inventory_item_name'])[1]";
    private final String logoutButton = "id=\"logout_sidebar_link\"";
    private final String hamburgerButton = "div[class=\"bm-burger-button\"]";
    private final String buttonRemove = "//*[@class='btn_secondary btn_inventory' and text()=\"REMOVE\"]";
    private final String cartPageButton = "//*[@class='svg-inline--fa fa-shopping-cart fa-w-18 fa-3x ']";


    // 2. Page constructor
    public InventoryPage(Page page) {
        this.page = page;
    }

    // 3. Page action or methods
    public void redirectToInventoryPageUrl() {
        page.navigate("https://www.saucedemo.com/v1/inventory.html");
    }

    public String getInventoryPageUrl() {
        String url = page.url();
        System.out.println("Inventory Page URL: " + url);
        return url;
    }

    public String getPageTitle() {
        String title = page.title();
        return title;
    }

    public String checkInventoryPageProductLabel() {
        return page.textContent(productLabel);
    }

    public Boolean checklogoutButton() {
        page.click(hamburgerButton);
        return page.isVisible(logoutButton);
    }

    public Boolean checkSortButton() {
        return page.isVisible(sortButton);
    }

    public Boolean checkCartButton(){
        return page.isVisible(gotoCartButton);
    }

    public String sortByNameAtoZ() {
        page.click(sortButton);
        page.selectOption(sortButton, "az");
        return page.textContent(firstIndexItemName);
    }

    public String sortByNameZtoA() {
        page.click(sortButton);
        page.selectOption(sortButton, "za");
        return page.textContent(firstIndexItemName);
    }

    public String sortByPriceLoHi() {
        page.click(sortButton);
        page.selectOption(sortButton, "lohi");
        return page.textContent(firstIndexItemName);
    }

    public String sortByPriceHiLo() {
        page.click(sortButton);
        page.selectOption(sortButton, "hilo");
        return page.textContent(firstIndexItemName);
    }

    public boolean removeBtnClick() {
        page.click(buttonRemove);
        return page.isHidden(buttonRemove);
    }

    public boolean addToCart(String targetProductName) {
        
        // Get all product containers
        Locator products = page.locator(".inventory_item");
        int count = products.count();

        for (int i = 0; i < count; i++) {
        Locator product = products.nth(i);

        // Get the product name inside this container
        String name = product.locator(".inventory_item_name").textContent().trim();

            if (name.equals(targetProductName)) {
            // Click the 'ADD TO CART' button inside this container
            product.locator("button.btn_inventory").click();
            System.out.println("Clicked ADD TO CART for: " + name);
            break; // Stop after the first match
            }
        }
        return page.isVisible(buttonRemove);
    }

    public String getItemPrice(String targetProductName) {
        
        String itemPrice = null;
        // Get all product containers
        Locator products = page.locator(".inventory_item");
        int count = products.count();

        for (int i = 0; i < count; i++) {
            Locator product = products.nth(i);        

            // Get the product name inside this container
            String name = product.locator(".inventory_item_name").textContent().trim();

            if (name.equals(targetProductName)) {
                // Get the price inside this container
                itemPrice = product.locator("div.inventory_item_price").textContent().trim();
                System.out.println("Product name: " + targetProductName + " Have Price: " +itemPrice);
                break; // Stop after the first match
            }
        }
        return itemPrice;
    }

    public ShoppingCartPage navigateToCartPage() {
        page.click(cartPageButton);
        return new ShoppingCartPage(page);
    }

}
