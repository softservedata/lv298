package com.softserve.edu.opencart.data.applications;


import com.softserve.edu.opencart.tools.SystemPropertyUtils;

import java.sql.Driver;
import java.sql.SQLException;

public final class ApplicationSourceRepository {
    final static String BASE_URL = "http://192.168.56.101/";
    final static String CHROME_WINDOWS_DRIVER_RELATIVE_PATH = "/chromedriver-windows-32bit.exe";
    final static String DB_CONNECTION_ERROR = "DB Connection Error, %s";

    private ApplicationSourceRepository() {
    }

    public static IApplicationSource defaultParameters() {
        return Chrome();
    }


    public static IApplicationSource ChromeWithoutUI(){
        Driver jdbcDriver;
        try {
            jdbcDriver = new com.mysql.jdbc.Driver();
        } catch (SQLException e) {
            throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
        }
        System.out.println("***PASS: " + SystemPropertyUtils.getExistingProperty("database-password", "DATABASE_PASSWORD"));
        return new ApplicationSource("ChromeWithoutUI",
                ApplicationSourceRepository.class.getResource(CHROME_WINDOWS_DRIVER_RELATIVE_PATH).getPath().substring(1),
                10,
                "http://192.168.56.101/",
                "jdbc:mysql://192.168.56.101:3306/opencart",
                "ocuser",
                SystemPropertyUtils.getExistingProperty("database-password", "DATABASE_PASSWORD"),
                jdbcDriver
        );
    }

    public static IApplicationSource Chrome() {
        Driver jdbcDriver;
        try {
            jdbcDriver = new com.mysql.jdbc.Driver();
        } catch (SQLException e) {
            throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
        }
        System.out.println("***PASS: " + SystemPropertyUtils.getExistingProperty("database-password",
                                                                            "DATABASE_PASSWORD"));

        return new ApplicationSource("ChromeTemporary",
                ApplicationSourceRepository.class.getResource(CHROME_WINDOWS_DRIVER_RELATIVE_PATH).getPath().substring(1),
                10,
                "http://192.168.56.101/",
                "jdbc:mysql://192.168.56.101:3306/opencart",
                "ocuser",
                SystemPropertyUtils.getExistingProperty("database-password",
                                                        "DATABASE_PASSWORD"),
                jdbcDriver
        );
    }
}
