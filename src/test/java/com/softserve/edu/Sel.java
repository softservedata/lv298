package com.softserve.edu;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Sel {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    	System.out.println("PATH: " + Sel.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
        System.setProperty("webdriver.chrome.driver",
        		Sel.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
                //"chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("\t\t\tclass TestNg01: @BeforeClass beforeClass()");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        System.out.println("\t\t\tclass TestNg01: @AfterClass afterClass()");
    }

    @Test
    public void testSel() throws Exception {
        // System.setProperty("webdriver.chrome.driver",
        // "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
        // WebDriver driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //
        driver.get("https://www.google.com.ua/");
        driver.findElement(By.id("lst-ib")).clear();
        driver.findElement(By.id("lst-ib")).sendKeys("selenium ide download");
        driver.findElement(By.id("lst-ib")).submit();
        // driver.findElement(By.name("btnK")).click();
        driver.findElement(By.linkText("Downloads - Selenium")).click();
        WebElement actual = driver.findElement(By.cssSelector("a[name=\"selenium_ide\"] > p"));
        String expected = "Selenium IDE is a Firefox plugin which records and plays back user interactions with the browser. Use this to either create simple scripts or assist in exploratory testing. It can also export Remote Control or WebDriver scripts, though they tend to be somewhat brittle and should be overhauled into some sort of Page Object-y structure for any kind of resiliency.";
        Assert.assertEquals(actual.getText(), expected);
        // driver.quit();
        Thread.sleep(2000);
    }

    //@Test
    public void testOms() throws Exception {
        //
        driver.get("http://ssu-oms.training.local:8180/OMS/");
        driver.findElement(By.name("j_username")).clear();
        driver.findElement(By.name("j_username")).sendKeys("iva");
        driver.findElement(By.name("j_password")).clear();
        driver.findElement(By.name("j_password")).sendKeys("qwerty");
        driver.findElement(By.name("submit")).click();
        //
        WebElement actual = driver.findElement(By.xpath("//td[text()='First name']/following-sibling::td"));
        String expected = "ivanka";
        System.out.println("***actual.getText()= " + actual.getText());
        Assert.assertEquals(actual.getText(), expected);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a.spec")).click();
        Thread.sleep(2000);
    }

}
