/*
 * Copyright (c) 2015. PayPoint
 */

package com.paypoint.sdk;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.paypoint.sdk.library.payment.PaymentManager;
import com.paypoint.sdk.library.payment.request.CustomerDetails;
import com.paypoint.sdk.library.payment.request.FinancialServices;
import com.paypoint.sdk.library.payment.request.MakePaymentRequest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RunWith(CustomRobolectricRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class MakePaymentRequestTest  {

    /**
     * Checks the correct JSON is generated for Financial Services block
     * @throws Exception
     */
    @Test
    public void testFinancialServices() throws Exception {
        MakePaymentRequest request = new MakePaymentRequest();

        request.setFinancialServices(new FinancialServices()
                .setAccountNumber("123ABC")
                .setDateOfBirth("19870818")
                .setPostCode("BS20")
                .setSurname("Smith"));

        Gson gson = new Gson();

        String json = gson.toJson(request).replace("\n", "").replace("\r", "").replace(" ", "");

        String expected =  readRawFile(R.raw.fin_services_json).replace("\n", "").replace("\r", "").replace(" ", "");

        // check JSON contains the correct financial services element
        Assert.assertTrue(expected.contains(json));
    }

    /**
     * Checks the correct JSON is generated for Customer block
     * @throws Exception
     */
    @Test
    public void testCustomerDetails() throws Exception {
        MakePaymentRequest request = new MakePaymentRequest();

        request.setCustomer(new CustomerDetails()
                .setDateOfBirth("1900-01-01")
                .setEmail("test@paypoint.com")
                .setTelephone("01225 123456"));

        Gson gson = new Gson();

        String json = gson.toJson(request).replace("\n", "").replace("\r", "").replace(" ", "");

        String expected =  readRawFile(R.raw.customer_details_json).replace("\n", "").replace("\r", "").replace(" ", "");

        // check JSON contains the correct financial services element
        Assert.assertTrue(expected.contains(json));
    }

    private String readRawFile(int fileRes) throws IOException {

        StringBuilder sb = new StringBuilder();

        Resources resources = Robolectric.application.getResources();
        InputStream in = resources.openRawResource(fileRes);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }

        return sb.toString();
    }
}
