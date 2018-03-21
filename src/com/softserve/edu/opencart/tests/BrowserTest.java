package com.softserve.edu.opencart.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.pages.HomePage;

public class BrowserTest {

    @Test
    public void testChrome3UserProfile() throws Exception {
        System.out.println("HOME_PATH getenv = "
                + System.getenv("HOMEPATH"));
        System.setProperty("webdriver.chrome.driver",
                "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
        // String userProfile =
        // "C:\\Users\\yharasym\\AppData\\Local\\Google\\Chrome\\User
        // Data\\Default\\";
        String userProfile = System.getenv("HOMEPATH")
                + "\\AppData\\Local\\Google\\Chrome\\User Data";
                //+ "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
        System.out.println("userProfile PATH: " + userProfile);
        ChromeOptions options = new ChromeOptions();
        ////options.addArguments("--start-maximized");
        ////options.addArguments("--no-proxy-server");
        // options.addArguments("--no-sandbox");
        // options.addArguments("--disable-web-security");
        ////options.addArguments("--ignore-certificate-errors");
        // options.addArguments("--disable-extensions");
        //options.addArguments("\""+"user-data-dir=" + userProfile+"\""); // Work in Linux, MacOS, etc. Do not working on Windows.
        options.addArguments("--user-data-dir=" + userProfile); // Working in Windows.
        //options.addArguments("--user-data-dir=" + userProfile); // Work in Linux, MacOS, etc. Do not working on Windows.
        WebDriver driver = new ChromeDriver(options);
        //
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get("http://atqc-shop.epizy.com");
        //driver.navigate().to("http://atqc-shop.epizy.com");
        //
        HomePage homePage = HomePage.load(driver, "http://atqc-shop.epizy.com");
        homePage.sendSearchFieldText("mac");
        homePage.enterSearchField();
        //homePage.clickSearchButton();
        Thread.sleep(4000);
        System.out.println("***" + homePage.getProductComponentTexts());
        //driver.quit();
    }

}

