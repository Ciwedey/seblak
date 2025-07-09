package com.qa.saucedemo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    
    private final Page page;

    // 1. String locator - Object repository
    private final String nameInput = "input[id=\"user-name\"]";
    private final String passwordInput = "input[id=\"password\"]";
    private final String loginButton = "input[id=\"login-button\"]";
    private final String alertErrorlogin = "h3[data-test=\"error\"]";
    private final String logoutButton = "a[id=\"logout_sidebar_link\"]";
    private final String hamburgerButton = "div[class=\"bm-burger-button\"]";

    // 2. Page constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // 3. Page action or methods
    public String getLoginPageTitle() {
        String title = page.title();
        System.out.println("Page title: " + title);
        return title;
    }

    public String getLoginPageUrl() {
        String url = page.url();
        System.out.println("Page URL: " + url);
        return url;
    }

    public InventoryPage navigateInventoryPage(String appUname, String appPwd ) {
        System.out.println("App Credential: " + appUname + " : " + appPwd);
        page.fill(nameInput, appUname);
        page.fill(passwordInput, appPwd);
        page.click(loginButton);
        return new InventoryPage(page);
    }

    public String submitErrorLogin(String appUname, String appPwd) {
        System.out.println("App Credential : " + appUname + " : " + appPwd);
        page.fill(nameInput, appUname);
        page.fill(passwordInput, appPwd);
        page.click(loginButton);
        String errorMessage = page.textContent(alertErrorlogin);
        System.out.println("The error message is: " + errorMessage);
        return errorMessage;
    }

    public Boolean doLogin(String appUname, String appPwd) {
        System.out.println("App Credential: " + appUname + " : " + appPwd);
        page.fill(nameInput, appUname);
        page.fill(passwordInput, appPwd);
        page.click(loginButton);
        page.click(hamburgerButton);
        if(page.isVisible(logoutButton)){
            System.out.println("User login successfully ... ");
            return true;
        }
        return false;
    }

}
