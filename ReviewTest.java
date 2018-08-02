package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import testAutomation.HomePage;
import testAutomation.ReviewPage;

public class ReviewTest {
    public static WebDriver driver;
    public static ReviewPage reviewPage;
    public static HomePage homePage;

    @BeforeClass
    public static void setup() {
	System.setProperty("webdriver.chrome.driver",
		"C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
	driver = new ChromeDriver();
	homePage = new HomePage(driver);
	reviewPage = new ReviewPage(driver);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://atqc-shop.epizy.com");
    }
    
    @Test
    public void reviewTest() {
	homePage.clickProductTab();
	homePage.clickReviewsTab();
	String reviewBoxMessage = reviewPage.getrewiewMassege();
	Assert.assertEquals("There are no reviews for this product.", reviewBoxMessage);
	reviewPage.inputName("Tania");
	reviewPage.inputReview("review review review v review review");
	reviewPage.clickratingButton();
	reviewPage.clickreviewButton();
	String rewiewMassegeChek = reviewPage.chekRewiewMassege();
	Assert.assertEquals("Thank you for your review. It has been submitted to the webmaster for approval.", rewiewMassegeChek);

	
    }

    @AfterClass
    public static void tearDown() {
	driver.quit();
    }
}
