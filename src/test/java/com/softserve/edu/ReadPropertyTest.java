package com.softserve.edu;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.tools.DBReader;

public class ReadPropertyTest {

    @Test
    public void testProperty() throws Exception {
    	// Reading from maven-surefire-plugin
        System.out.println("surefire.reports.directory = "
                + System.getProperty("surefire.reports.directory"));
        System.out.println("selenium-version = "
                + System.getProperty("selenium-version"));
        System.out.println("database-password = "
                + System.getProperty("database-password"));
    	// Reading from OS
        System.out.println("DATABASE_PASSWORD = "
                + System.getenv("DATABASE_PASSWORD"));
        for (List<String> row : new DBReader("select * from customer_login;").getAllCells()) {
            System.out.println(row);
        }
        Assert.assertTrue(true);
    }
    
}
