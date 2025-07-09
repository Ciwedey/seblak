package com.qa.saucedemo.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
    // Playwright playwright;
    // Browser browser;
    // BrowserContext browserContext;
    // Page page;
    Properties prop;

    // Init Java Thread Local variable
    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    // Get thread local copy
    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }
    public static Browser getBrowser() {
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }
    public static Page getPage() {
        return tlPage.get();
    }

    
    // This method is used to initialize browser
    public Page initBrowserSettings(Properties prop) {

        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is : " + browserName);
        
        // playwright = Playwright.create(); // Without thread local
        tlPlaywright.set(Playwright.create()); // Set Thread Local copy for playwright
        
        // Set browser thread local copy
        switch (browserName.toLowerCase()) {
            case "chromium" -> tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            case "firefox" -> tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            case "safari" -> tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            case "chrome" -> tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                
            default -> System.out.println("Please pass the right browser name .....");
        }
        
        // browserContext = browser.newContext();
        // page = browserContext.newPage();
        // page.navigate(prop.getProperty("url").trim());

        // Set browser context thread local copy
        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());

        // return page;
        return getPage();
    }

    // This method use to initialize properties from config file
    public Properties init_prop() throws IOException {
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return prop;        
    }

}
