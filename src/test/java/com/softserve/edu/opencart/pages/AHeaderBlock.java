package com.softserve.edu.opencart.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.tests.SmokeTest;
import com.softserve.edu.opencart.tools.RegexUtils;
//import com.softserve.edu.opencart.pages.AHeaderBlock.ProductActionNotification;

public abstract class AHeaderBlock {
    
// ----------------------- notification message ------------
    public class NotificationMessage {//TODO
        private WebElement notifacation;
        public static final String NOTIFACATION = ".alert.alert-success";
        public static final String NOTIFACATION_CLOSE = ".alert.alert-success .close";
        public NotificationMessage() {
            notifacation = driver.findElement(By.cssSelector(NOTIFACATION));
        }
        
        public WebElement getNotificationMessage() {
            return notifacation;
        }
        
        public String getNotificationMessageText() {
            return notifacation.getText();
        }
        
        public boolean isSuccess() {
            return notifacation.getAttribute("class").contains("alert-success");
        }
        
        public void closeNotificationMessage() {
            driver.findElement(By.cssSelector(NOTIFACATION_CLOSE)).click();
        }
    }
    
//TODO mini product component of MiniCart
    
    public class MiniProductCopmonentOfMinicartBtn {
        private WebElement productLayout;
        public static final String MINI_CART_TOTAL_PRICE = ".table.table-bordered tr:nth-child(4) > td:nth-child(2)";
        
        MiniProductCopmonentOfMinicartBtn(WebElement productLayout) {
            this.productLayout = productLayout;
        }
        //TODO selectors
        
        public WebElement getName() {
            return productLayout.findElement(By.cssSelector(".text-left a"));
        }
        public void deleteProduct() {
            productLayout.findElement(By.cssSelector(".text-center button")).click();
        }
        public String getNameText() {
            return getName().getText();
        }
        public void clickName() {
            getName().click();
        }
        public String getTotalPrice() {
            return driver.findElement(By.cssSelector(MINI_CART_TOTAL_PRICE)).getText();
        }
    }

    
 // --------------------- mini cart component ----------
    
    public class MiniCartComponent {
        public static final String EMPTY_CART_MESSAGE = "Your shopping cart is empty!";
        public static final String MINI_CART_PRODUCT = "table.table.table-striped tr";
        
        protected List<MiniProductCopmonentOfMinicartBtn> miniProductCopmonentOfMinicartBtn;
        
        public MiniCartComponent() {
            initProductComponents();
        }
        private void initProductComponents() {
            miniProductCopmonentOfMinicartBtn = new ArrayList<MiniProductCopmonentOfMinicartBtn>();
            for (WebElement current : driver.findElements(By.cssSelector(MINI_CART_PRODUCT))) {
                miniProductCopmonentOfMinicartBtn.add(new MiniProductCopmonentOfMinicartBtn(current));
            }
        }

        public List<MiniProductCopmonentOfMinicartBtn> getProductComponents() {
            return miniProductCopmonentOfMinicartBtn;
        }

        public List<MiniProductCopmonentOfMinicartBtn> getMiniProductCopmonentOfMinicartBtn() {
            return miniProductCopmonentOfMinicartBtn;
            
        }
        public boolean isCartEmpty() {
            return driver.findElement(By.cssSelector(".dropdown-menu.pull-right li p")).getText().contains(EMPTY_CART_MESSAGE);
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
        public List<WebElement> getСurrencyElements() {
            return currencyElements;
        }

        public WebElement getСurrencyElementByName(Currencies currencyName) {
            WebElement result = null;
            for (WebElement current : getСurrencyElements()) {
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

        public List<String> getСurrencyElementsText() {
            List<String> result = new ArrayList<>();
            for (WebElement current : getСurrencyElements()) {
                result.add(current.getText());
            }
            return result;
        }

        public void clickСurrencyElementByName(Currencies currencyName) {
            getСurrencyElementByName(currencyName).click();
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
    
    public NotificationMessage notificationMessage; 
    
    public MiniProductCopmonentOfMinicartBtn miniProductCopmonentOfMinicartBtn;
    
    public String NotificationMessageText() {
        return notificationMessage.getNotificationMessageText();
    }
   
    //======= getter for notification ===
    public NotificationMessage getNotificationMessage() {
        return notificationMessage;
    }
    
    public boolean isNotificationSuccess() {//TO DO if null = false
        NotificationMessage notificationMessage = new NotificationMessage();
//        return getNotificationMessage().isSuccess();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return notificationMessage.isSuccess();
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

    public MiniCartComponent miniCartComponent;
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
    public void clickMiniCart() {//TODO
        getMiniCartBtn().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    //TODO Business Logic
    // Business Logic
    public void getPriceAmountFromMiniCart() {
//      return miniProductCopmonentOfMinicartBtn.getTotalPrice();
      System.out.println(miniProductCopmonentOfMinicartBtn.getTotalPrice());
  }
    
    public HomePage deleteAllProductFromCart() {
        for (MiniProductCopmonentOfMinicartBtn current : miniCartComponent.getMiniProductCopmonentOfMinicartBtn()) {
            current.deleteProduct();
        }
        return new HomePage(driver);
    }
    
    public int getMiniCartProductElementsNumber() {
        return miniCartComponent.miniProductCopmonentOfMinicartBtn.size();
    }
    
    public boolean isMiniShoppingCartEmpty() {
        return miniCartComponent.isCartEmpty();
    }
    
    protected void chooseCurrency(Currencies currencyName) {
        clickCurrency();
        currencyComponent.clickСurrencyElementByName(currencyName);
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
