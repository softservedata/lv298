package com.softserve.edu.opencart.pages;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.tools.RegexUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public abstract class AHeaderBlock {
    private class CurrencyComponent {

        // *********Locators*********
        private static final String LIST_CURRENCY_ELEMENTS_CSS = "button" +
                ".currency-select.btn.btn-link.btn-block";

        // *********Web Elements*********
        @FindBy(css = LIST_CURRENCY_ELEMENTS_CSS)
        private List<WebElement> currencyElements;

        // *********Constructor*********
        public CurrencyComponent() {
            PageFactory.initElements(driver, this);
        }

        // *********Currency Elements*********
        public List<WebElement> getCurrencyElements() {
            return currencyElements;
        }

        public WebElement getCurrencyElementByName(Currencies currencyName) {
            WebElement result = null;
            for (WebElement current : getCurrencyElements()) {
                if (getWebElementText(current).toLowerCase().trim()
                        .contains(currencyName.toString().toLowerCase().trim
                                ())) {
                    result = current;
                    break;
                }
            }
            // TODO Develop Custom Exception. Use Constants
            if (result == null) {
                throw new RuntimeException("Currency not found");
            }
            return result;
        }

        public List<String> getCurrencyElementsText() {
            List<String> result = new ArrayList<>();
            for (WebElement current : getCurrencyElements()) {
                result.add(getWebElementText(current));
            }
            return result;
        }

        public void clickCurrencyElementByName(Currencies currencyName) {
            clickWebElement(getCurrencyElementByName(currencyName));
        }
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private class AccountInComponent {

        // *********Locators*********
        //TODO make locators simpler
        private static final String BTN_REGISTER_XPATH =
                "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains" +
                        "(@href,'route=account/register')]";
        private static final String BTN_LOGIN_XPATH =
                "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains" +
                        "(@href,'route=account/login')]";
        // *********Web Elements*********
        @FindBy(xpath = BTN_REGISTER_XPATH)
        private WebElement register;

        @FindBy(xpath = BTN_LOGIN_XPATH)
        private WebElement login;

        // *********Constructor*********
        public AccountInComponent() {
            PageFactory.initElements(driver, this);
        }

        // *********Register*********
        public WebElement getRegister() {
            return register;
        }

        public String getRegisterText() {
            return getWebElementText(getRegister());
        }

        public void clickRegister() {
            clickWebElement(getRegister());
        }

        // *********Login*********
        public WebElement getLogin() {
            return login;
        }

        public String getLoginText() {
            return getWebElementText(getLogin());
        }

        public void clickLogin() {
            clickWebElement(getLogin());
        }
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private class AccountOutComponent {

        // *********Locators*********
        //TODO make locators simpler
        private static final String BTN_MY_ACCOUNT_LOGIN_XPATH =
                "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains" +
                        "(@href,'route=account/account')]";
        private static final String BTN_LOGOUT_XPATH =
                "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains" +
                        "(@href,'route=account/logout')]";
        // *********Web Elements*********
        @FindBy(xpath = BTN_MY_ACCOUNT_LOGIN_XPATH)
        private WebElement myAccountLogin;

        @FindBy(xpath = BTN_LOGOUT_XPATH)
        private WebElement logout;

        // *********Constructor*********
        public AccountOutComponent() {
            PageFactory.initElements(driver, this);
        }

        // *********My Account Login*********
        public WebElement getMyAccountLogin() {
            return myAccountLogin;
        }

        public String getMyAccountLoginText() {
            return getWebElementText(getMyAccountLogin());
        }

        public void clickMyAccountLogin() {
            clickWebElement(getMyAccountLogin());
        }

        // *********Logout*********
        public WebElement getLogout() {
            return logout;
        }

        public String getLogoutText() {
            return getWebElementText(getLogout());
        }

        public void clickLogout() {
            clickWebElement(getLogout());
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    protected class ProductActionNotification {

        // *********Locators*********
        private static final String BTN_CLOSE_CLASS_NAME = "close";
        private static final String DIV_MESSAGE_CONTAINER_CSS = ".alert" +
                ".alert-success";

        // *********Web Elements*********
        @FindBy(css = DIV_MESSAGE_CONTAINER_CSS)
        private WebElement messageContainer;

        @FindBy(className = BTN_CLOSE_CLASS_NAME)
        private WebElement closeMessage;

        // *********Constructor*********
        public ProductActionNotification() {
            PageFactory.initElements(driver, this);
        }

        // *********Message Container*********
        public WebElement getMessageContainer() {
            return messageContainer;
        }

        public void clickMessageContainer() {
            clickWebElement(getMessageContainer());
        }

        public String getMessageContainerText() {
            return getWebElementText(getMessageContainer());
        }

        // *********Close Message*********
        public WebElement getCloseMessage() {
            return closeMessage;
        }

        public void clickCloseMessage() {
            clickWebElement(getCloseMessage());
        }

    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // *********AHeaderBlock Fields********
    private static final String IP = "192.168.0.108";
//    public static final String BASE_URL = String.format("http://%s/op",IP);
    public static final String BASE_URL = "http://setopencart.epizy.com";
    public static final Logger LOGGER = LoggerFactory.getLogger(AHeaderBlock
            .class);

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //
    public static final String ATTRIBUTE_VALUE = "value";

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // *********Locators*********
    private static final String BTN_CURRENCY_CSS = ".btn.btn-link"
            +".dropdown-toggle";
    private static final String BTN_MY_ACCOUNT_CSS = "ul.list-inline li"
            +".dropdown a.dropdown-toggle";
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private static final String ANCH_LOGO_CSS = "#logo a";
    private static final String INPUT_SEARCH_FIELD_NAME = "search";
    private static final String BTN_SEARCH_CSS = ".btn.btn-default.btn-lg";
    private static final String BTN_WISH_LIST_ID = "wishlist-total";
    private static final String LIST_MYACCOUNTITEMS_CSS = ".dropdown-menu"
            +".dropdown-menu-right li";
    protected WebDriver driver;
    protected CurrencyComponent currencyComponent;
    protected AccountInComponent accountInComponent;
    protected AccountOutComponent accountOutComponent;
    protected ProductActionNotification productActionNotification;

    // *********Web Elements*********
    @FindBy(css = BTN_CURRENCY_CSS)
    private WebElement currency;
    @FindBy(css = BTN_MY_ACCOUNT_CSS)
    private WebElement myAccount;
    @FindBy(css = ANCH_LOGO_CSS)
    private WebElement logo;
    @FindBy(name = INPUT_SEARCH_FIELD_NAME)
    private WebElement searchField;
    @FindBy(css = BTN_SEARCH_CSS)
    private WebElement searchButton;
    @FindBy(id = BTN_WISH_LIST_ID)
    private WebElement wishList;
    @FindBy(css = LIST_MYACCOUNTITEMS_CSS)
    private List<WebElement> myAccountItems;

    // *********Constructor*********
    public AHeaderBlock(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //TODO develop WebElement Wrapper
    // *********Atomic operations*********
    private void scrollToWebElement(final WebElement webElement) {

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.scrollTo("+webElement.getLocation().x+","+webElement.getLocation().y+")");

        //Wait after scroll
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickWebElement(final WebElement webElement) {
        scrollToWebElement(webElement);
        webElement.click();
    }

    public void clearWebElement(final WebElement webElement) {
        scrollToWebElement(webElement);
        webElement.clear();
    }

    public String getWebElementText(final WebElement webElement) {
        scrollToWebElement(webElement);
        return webElement.getText();
    }

    public String getWebElementTextWithAttribute(final WebElement webElement,
                                                 final String attribute) {
        scrollToWebElement(webElement);
        return webElement.getAttribute(attribute);
    }

    public void sendWebElementText(final WebElement webElement, final String text) {
        scrollToWebElement(webElement);
        webElement.sendKeys(text);
    }

    // *********AHeaderBlock Getters********
    public ProductActionNotification getProductActionNotification() {
        return productActionNotification;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // *********Currency*********
    public WebElement getCurrency() {
        return currency;
    }

    public void clickCurrency() {
        clickWebElement(getCurrency());
        currencyComponent = new CurrencyComponent();
    }

    // *********My Account*********
    public WebElement getMyAccount() {
        return myAccount;
    }

    public void clickMyAccount() {
        clickWebElement(getMyAccount());
    }

    public String getMyAccountText() {
        return getWebElementText(getMyAccount());
    }

    public int countMyAccountItems() {
        clickSearchField();
        clickMyAccount();
        clickSearchField();
        return myAccountItems.size();
    }

    public boolean isLogged() {
        return countMyAccountItems() != 2;
    }

    // *********Logo*********
    public WebElement getLogo() {
        return logo;
    }

    public void clickLogo() {
        clickWebElement(getLogo());
    }

    // *********Search Field*********
    public WebElement getSearchField() {
        return searchField;
    }

    public void sendSearchFieldText(String text) {
        sendWebElementText(getSearchField(), text);
    }

    public void clearSearchField() {
        clearWebElement(getSearchField());
    }

    public void clickSearchField() {
        clickWebElement(getSearchField());
    }

    public String getSearchFieldText() {
        return getWebElementTextWithAttribute(getSearchField(),
                ATTRIBUTE_VALUE);
    }

    // *********Search Button*********
    public WebElement getSearchButton() {
        return searchButton;
    }

    public String getSearchButtonText() {
        return getWebElementText(getSearchButton());
    }

    public void clickSearchButton() {
        clickWebElement(getSearchButton());
    }

    // *********Wish List*********
    public WebElement getWishList() {
        return wishList;
    }

    public String getWishListText() {
        return getWebElementText(getWishList());
    }

    public void clickWishList() {
        clickWebElement(getWishList());
    }

    public int wishListAmount() {
        return RegexUtils.extractFirstNumber(getWishListText());
    }

    // *********Business Logic*********
    protected void chooseCurrency(Currencies currencyName) {
        clickCurrency();
        currencyComponent.clickCurrencyElementByName(currencyName);
    }

    public HomePage gotoHomePage() {
        clickLogo();
        return new HomePage(driver);
    }

    public WishListPage gotoWishListPage() {
        clickWishList();
        return new WishListPage(driver);
    }

    public LoginPage gotoWishListPageLogout() {
        clickWishList();
        return new LoginPage(driver);
    }

    public SearchPage searchByProduct(IProduct product) {
        sendSearchFieldText(product.getSearchKey());
        clickSearchButton();
        return new SearchPage(driver);
    }

    private void loadLoginPage(AHeaderBlock head) {
        head.clickSearchField();
        head.clickMyAccount();
        accountInComponent = new AccountInComponent();
        accountInComponent.clickLogin();
    }

    public LoginPage gotoLoginPage() {
        if (isLogged()) {
            HomePage homePage = signoutToHomePage();
            loadLoginPage(homePage);
        } else {
            loadLoginPage(this);
        }
        return new LoginPage(driver);
    }

    public HomePage signoutToHomePage() {
        if (isLogged()) {
            clickSearchField();
            clickMyAccount();
            accountOutComponent = new AccountOutComponent();
            accountOutComponent.clickLogout();
            AccountLogoutPage accountLogoutPage = new AccountLogoutPage(driver);
            accountLogoutPage.clickLogo();
        } else {
            clickLogo();
        }
        return new HomePage(driver);
    }

    public HomePage loginToHomePage(IUser user) {
        return signoutToHomePage()
                .gotoLoginPage()
                .successLogin(user)
                .gotoHomePage();
    }

    public boolean isWishListContainsProduct(IProduct product) {
        boolean rezult = false;
        WishListPage wishListPage = this.gotoWishListPage();
        rezult = wishListPage.isProductInList(product);
        wishListPage.gotoHomePage();
        return rezult;
    }

    public String productActionNotificationText() {
        return getProductActionNotification().getMessageContainerText();
    }

    public void productActionNotificationClose() {
        getProductActionNotification().clickCloseMessage();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


}