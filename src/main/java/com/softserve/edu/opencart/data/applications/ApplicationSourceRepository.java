package com.softserve.edu.opencart.data.applications;

import com.softserve.edu.opencart.tools.SystemPropertyUtils;

import java.sql.Driver;
import java.sql.SQLException;

public final class ApplicationSourceRepository {

    private static final  String DB_CONNECTION_ERROR = "DB Connection Error, %s";
    private static volatile ApplicationSourceRepository instance = null;
    private static final int IMPLICIT_WAIT_TIME_OUT = 10;
    private static final String IP = "192.168.43.169";
//    private static final String IP = "192.168.0.108";

    // *********Constructor*********
    private ApplicationSourceRepository() {
    }

    public static ApplicationSourceRepository get() {
        if (instance == null) {
            synchronized (ApplicationSourceRepository.class) {
                if (instance == null) {
                    instance = new ApplicationSourceRepository();
                }
            }
        }
        return instance;
    }

    // *********Repository*********
    public static IApplicationSource defaultParameters() {
        return epizyChrome();
    }

    public static IApplicationSource epizyChrome() {
        Driver jdbcDriver;
        try {
            jdbcDriver = new com.mysql.jdbc.Driver();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
        }
        return ApplicationSource.get()
                .setBrowserName("ChromeTemporary") //"ChromeProfile"
                .setDriverPath(ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1))
                .setBaseUrl(String.format("http://%s/op/",IP))
//                .setBaseUrl("http://setopencart.epizy.com")
                //.setBaseUrl("http://atqc-shop.epizy.com")
                .setImplicitWaitTimeOut(IMPLICIT_WAIT_TIME_OUT)
                .setDatabaseUrl("jdbc:mysql://192.168.43.169:3306/opencart2")
//                .setDatabaseUrl("jdbc:mysql://192.168.0.108:3306/opencart2")
//                .setDatabaseUrl("jdbc:mysql://192.168.0.108:3306/opencart")
                .setDatabaseLogin("svehetc")
                .setDatabasePassword(SystemPropertyUtils.getExistingProperty("database-password", "DATABASE_PASSWORD"))
                .setJdbcDriver(jdbcDriver)
                .build();
    }
}
