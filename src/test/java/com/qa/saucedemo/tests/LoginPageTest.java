package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;


// Test Login page
public class LoginPageTest extends BaseTest{

    @Test(priority=1)
    public void loginPageTitleTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority=1)
    public void loginPageUrlTest() {
        String actualUrl = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualUrl, prop.getProperty("url"));
    }

    @Test(priority=2)
    public void loginPageErrorLoginTest() {
        String alertErrorLogin = loginPage.submitErrorLogin(prop.getProperty("InvalidUname").trim(), prop.getProperty("ValidPwd").trim());
        Assert.assertEquals(alertErrorLogin, AppConstants.ERROR_LOGIN_USER_LOCKED);
    }

    @Test(priority=2)
    public void loginPageInvalidPwdTest() {
        String actualAlertErrorLogin = loginPage.submitErrorLogin(prop.getProperty("ValidUname").trim(), prop.getProperty("InvalidPwd").trim());
        Assert.assertEquals(actualAlertErrorLogin, AppConstants.ERROR_LOGIN_USER_CRED_INVALID);
    }

    @Test(priority=2)
    public void loginPageInvalidUnameTest() {
        String actualAlertErrorLogin = loginPage.submitErrorLogin(prop.getProperty("Usernotfound").trim(), prop.getProperty("ValidPwd").trim());
        Assert.assertEquals(actualAlertErrorLogin, AppConstants.ERROR_LOGIN_USER_CRED_INVALID);
    }

    @Test(priority=3)
    public void loginPageSuccessLoginTest() {
        Assert.assertTrue(loginPage.doLogin(prop.getProperty("ValidUname").trim(), prop.getProperty("ValidPwd").trim()));
    }

}
