package com.softserve.edu.opencart.pages;

import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.applications.IApplicationSource;
import com.softserve.edu.opencart.tools.BrowserWrapper;
import org.testng.internal.thread.ThreadTimeoutException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class Application {
    private final String DB_CONNECTION_ERROR = "DB Connection Error, %s";

    // Use Singleton, Repository
    private static volatile Application instance;
    // Fields
    private IApplicationSource applicationSource;
    private HashMap<Long, BrowserWrapper> browsers;
    private Connection connection;

    private Application(IApplicationSource applicationSource) {
        this.applicationSource = applicationSource;
        browsers = new HashMap<>();
    }

    public static Application get() {
        return get(null);
    }

    public static Application get(IApplicationSource applicationSource) {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    if (applicationSource == null) {
                        applicationSource = ApplicationSourceRepository.Chrome();
                    }
                    instance = new Application(applicationSource);
                    instance.initBrowser(applicationSource);
                }
            }
        }
        return instance;
    }

    public static void remove() {
        if (instance != null) {
            for (Long key : instance.browsers.keySet()) {
                BrowserWrapper browserWrapper = instance.browsers.get(key);
                if (browserWrapper != null) {
                    browserWrapper.quit();
                    instance.browsers.put(key, null);
                }
            }
            if (instance.connection != null) {
                try {
                    instance.connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(String.format(instance.DB_CONNECTION_ERROR, e));
                }
            }
            instance = null;
        }
    }



    public BrowserWrapper getBrowser() {
        BrowserWrapper browserWrapper = browsers.get(Thread.currentThread().getId());
        if(browserWrapper == null){
            browserWrapper = new BrowserWrapper(applicationSource);
            browsers.put(Thread.currentThread().getId(), browserWrapper);
        }
        return browserWrapper;
    }

    private void initBrowser(IApplicationSource applicationSource) {
        BrowserWrapper browser = new BrowserWrapper(applicationSource);
        browsers.put(Thread.currentThread().getId(), browser);
    }

    // Pages

    public HomePage loadHomePage() {
        return HomePage.load(getBrowser().getDriver(), applicationSource.getBaseUrl());
    }

    //DB


    public Connection getConnection() {
        if(connection == null) {
            try {
                DriverManager.registerDriver(getApplicationSource().getJdbcDriver());
                connection = DriverManager.getConnection(getApplicationSource().getDatabaseUrl(),
                        getApplicationSource().getDatabaseLogin(),
                        getApplicationSource().getDatabasePassword());
            }
            catch (SQLException e){
                throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
            }
        }
        return connection;
    }

    // getters
    public IApplicationSource getApplicationSource() {
        return applicationSource;
    }
}