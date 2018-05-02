package com.softserve.edu.opencart.tests;

import org.testng.annotations.Test;

import com.softserve.edu.opencart.tools.RestRequest;

public class RestTest {

    @Test//(dataProvider = "productCurrencyProviderFromDB")
    public void verifyLogging() {
    	RestRequest restRequest= new RestRequest();
    	String result = restRequest.sendRequestPost("https://api.github.com/orgs/dotnet/repos");
    	System.out.println(result);
    }

}
