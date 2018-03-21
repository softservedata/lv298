package com.softserve.edu.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.Currencies;

public abstract class AHeaderBlock {

    
// ----------------------- notification message ------------
    public class NotificationMessage {
        private WebElement notifacation;

        public NotificationMessage() {
            notifacation = driver.findElement(By.cssSelector(".alert.alert-success"));
        }
        
        public WebElement getNotificationMessage() {
            return notifacation;
        }
        
        public String getNotificationMessageText() {
            return notifacation.getText();
        }
        
        public boolean isSuccess() {//TODO
//            String note = notifacation.getAttribute("class");
//            if(getNotificationMessageText().toLowerCase().contains("success")) {
//                return true;
//            } else {
//                return false;
//            }
            return notifacation.getAttribute("class").contains("alert-success");
        }
        
        public void closeNotificationMessage() {
            driver.findElement(By.cssSelector(".alert.alert-success .close")).click();
        }
    }
    
    protected NotificationMessage notificationMessage; 
    
    public String NotificationMessageText() {
        return notificationMessage.getNotificationMessageText();
    }
   
    public boolean isNotificationSuccess() {
        NotificationMessage notificationMessage = new NotificationMessage();
        return notificationMessage.isSuccess();
    }
    
    
 // --------------------- mini cart component ----------
    private class MiniCartComponent {
        
        private List<WebElement> miniCartProductElements;
        public MiniCartComponent() {
            miniCartProductElements =
             driver.findElements(By.cssSelector("table.table.table-striped tr"));
        }
        
        public List<WebElement> getMiniCartProductElements() {
            return miniCartProductElements;
        }
        public int getMiniCartProductElementsNumber() {
            return miniCartProductElements.size();
        }
        public void clickName(String productName) {
            for (WebElement current : getMiniCartProductElements()) {
                if (current.getText().toLowerCase().trim().contains(
                        productName.toString().toLowerCase().trim())) {
                    current.click();;
                    break;
                }
            }
        }
        public WebElement getMiniCartElementByName(String productName) {
            WebElement result = null;
            for (WebElement current : getMiniCartProductElements()) {
                if (current.getText().toLowerCase().trim().contains(
                        productName.toString().toLowerCase().trim())) {
                    result = current;
                    break;
                }
            }
            // TODO Develop Custom Exception. Use Constants
            if (result == null) {
                throw new RuntimeException("Product with this name not found");
            }
            return result;
        }
        
    }
    
// currency component
    private class CurrencyComponent {

        private List<WebElement> currencyElements;

        public CurrencyComponent() {
            currencyElements = driver.findElements(By.cssSelector(
                    "button.currency-select.btn.btn-link.btn-block"));
        }

        // currencyElements
        public List<WebElement> get—urrencyElements() {
            return currencyElements;
        }

        public WebElement get—urrencyElementByName(Currencies currencyName) {
            WebElement result = null;
            for (WebElement current : get—urrencyElements()) {
                if (current.getText().toLowerCase().trim().contains(
                        currencyName.toString().toLowerCase().trim())) {
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

        public List<String> get—urrencyElementsText() {
            List<String> result = new ArrayList<>();
            for (WebElement current : get—urrencyElements()) {
                result.add(current.getText());
            }
            return result;
        }

        public void click—urrencyElementByName(Currencies currencyName) {
            get—urrencyElementByName(currencyName).click();
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private class AccountInComponent {

        private WebElement register;
        private WebElement login;

        public AccountInComponent() {
            register = driver.findElement(By.xpath(
                    "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(@href,'route=account/register')]"));
            login = driver.findElement(By.xpath(
                    "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(@href,'route=account/login')]"));
        }

        // register
        public WebElement getRegister() {
            return register;
        }

        public String getRegisterText() {
            return getRegister().getText();
        }

        public void clickRegister() {
            getRegister().click();
        }

        // login
        public WebElement getLogin() {
            return login;
        }

        public String getLoginText() {
            return getLogin().getText();
        }

        public void clickLogin() {
            getLogin().click();
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private class AccountOutComponent {

        private WebElement myAccountLogin;
        private WebElement logout;

        public AccountOutComponent() {
            myAccountLogin = driver.findElement(By.xpath(
                    "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(@href,'route=account/account')]"));
            logout = driver.findElement(By.xpath(
                    "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(@href,'route=account/logout')]"));
        }

        // myAccountLogin
        public WebElement getMyAccountLogin() {
            return myAccountLogin;
        }

        public String getMyAccountLoginText() {
            return getMyAccountLogin().getText();
        }

        public void clickMyAccountLogin() {
            getMyAccountLogin().click();
        }

        // logout
        public WebElement getLogout() {
            return logout;
        }

        public String getLogoutText() {
            return getLogout().getText();
        }

        public void clickLogout() {
            getLogout().click();
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public final String ATTRIBUTE_VALUE = "value";
    //
    protected WebDriver driver;
    //
    // private WebElement currency;
    // private WebElement myAccount
    // private WebElement logo;
    // private WebElement searchField;
    // private WebElement searchButton;
    //
    protected CurrencyComponent currencyComponent;
    protected AccountInComponent accountInComponent;
    protected AccountOutComponent accountOutComponent;

    protected MiniCartComponent miniCartComponent;
//    protected NotificationMessage notificationMessage; 

    public AHeaderBlock(WebDriver driver) {
        this.driver = driver;
        // currency =
        // driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
        // myAccount = driver.findElement(By.cssSelector("ul.list-inline
        // li.dropdown a.dropdown-toggle"));
        // logo = driver.findElement(By.cssSelector("#logo a"));
        // searchField = driver.findElement(By.name("search"));
        // searchButton =
        // driver.findElement(By.cssSelector(".btn.btn-default.btn-lg"));
        //
        // initWebElements();
        verifyWebElements();
    }

    private void initWebElements() {
        // currency =
        // driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
        // myAccount = driver.findElement(By.cssSelector("ul.list-inline
        // li.dropdown a.dropdown-toggle"));
        // logo = driver.findElement(By.cssSelector("#logo a"));
        // searchField = driver.findElement(By.name("search"));
        // searchButton =
        // driver.findElement(By.cssSelector(".btn.btn-default.btn-lg"));
    }

    private void verifyWebElements() {
        getCurrency();
        getMyAccount();
        getSearchField();
        getSearchButton();
        getMiniCartBtn();
//        getMiniCartComponent();
    }

    

    public WebElement getMiniCartBtn() {
      return driver.findElement(By.cssSelector(
              ".btn.btn-inverse.btn-block.btn-lg.dropdown-toggle"));
    }
    public void clickMiniCart() {
        getMiniCartBtn().click();
        miniCartComponent = new MiniCartComponent();
    }
 // *---------------getMiniCartComponent
    public MiniCartComponent getMiniCartComponent() {
        return miniCartComponent;
    }
    
    public String getMiniCartText() {
        return getMiniCartBtn().getText();
    }

    // currency
    public WebElement getCurrency() {
        // return currency;
        return driver
                .findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
    }

    public String getCurrencyText() {
        return getCurrency().getText();
    }

    public void clickCurrency() {
        getCurrency().click();
        currencyComponent = new CurrencyComponent();
    }

    // myAccount
    public WebElement getMyAccount() {
        // return myAccount;
        return driver.findElement(
                By.cssSelector("ul.list-inline li.dropdown a.dropdown-toggle"));
    }

    public String getMyAccountText() {
        return getMyAccount().getText();
    }

    public void clickMyAccount() {
        // clickSearchField();
        getMyAccount().click();
//         accountInComponent = new AccountInComponent();
    }

    public int countMyAccountItems() {
        clickSearchField();
        getMyAccount().click();
        return driver
                .findElements(
                        By.cssSelector(".dropdown-menu.dropdown-menu-right li"))
                .size();
    }

    public boolean isLogged() {
        return countMyAccountItems() != 2;
    }

    // logo
    public WebElement getLogo() {
        // return logo;
        return driver.findElement(By.cssSelector("#logo a"));
    }

    public void clickLogo() {
        getLogo().click();
    }

    // searchField
    public WebElement getSearchField() {
        // return searchField;
        return driver.findElement(By.name("search"));
    }

    public String getSearchFieldText() {
        return getSearchField().getAttribute(ATTRIBUTE_VALUE);
    }

    public void sendSearchFieldText(String text) {
        getSearchField().sendKeys(text);
    }

    public void clearSearchField() {
        getSearchField().clear();
    }

    public void clickSearchField() {
        getSearchField().click();
    }

    public void enterSearchField() {
        getSearchField().sendKeys(Keys.ENTER);
    }

    public void submitSearchField() {
        getSearchField().submit();
    }

    // searchButton
    public WebElement getSearchButton() {
        // return searchButton;
        return driver
                .findElement(By.cssSelector("button.btn.btn-default.btn-lg"));
    }

    public String getSearchButtonText() {
        return getSearchButton().getText();
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }

    // Business Logic
    public int getNumbersOfProductInCart() {//TODO
        return miniCartComponent.getMiniCartProductElementsNumber();
    }
    
    
    protected void chooseCurrency(Currencies currencyName) {
        clickCurrency();
        currencyComponent.click—urrencyElementByName(currencyName);
    }

    public HomePage gotoHomePage() {
        clickLogo();
        return new HomePage(driver);
    }

    public SearchPage searchByProduct(String productName) {
        sendSearchFieldText(productName);
        clickSearchButton();
        // submitSearchField();
        // enterSearchField();
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

}
