package com.softserve.edu.opencart.tests;

import org.testng.annotations.Test;

import com.softserve.edu.opencart.rest.CartRest;
import com.softserve.edu.opencart.tools.RestRequest;

public class RestTest {

    //@Test//(dataProvider = "productCurrencyProviderFromDB")
    public void verifyLogging() {
    	RestRequest restRequest= new RestRequest();
    	String result = restRequest.sendRequestPost("https://api.github.com/orgs/dotnet/repos");
    	System.out.println(result);
    }

    @Test
    public void verifyCart() {
    	CartRest cartRest = new CartRest();
    	System.out.println("RESULT:\n" 
    			+ cartRest.getAllFromCart("Ze6zE0rtz1BAGXswTnOUX5IxXw6ypkDg"));
    }

}
