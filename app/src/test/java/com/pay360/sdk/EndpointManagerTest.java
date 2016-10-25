/*
 * Copyright (c) 2015. PayPoint
 */

package com.pay360.sdk;

import com.pay360.sdk.library.network.EndpointManager;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class EndpointManagerTest extends TestCase {

    @Test
    public void testMiteUrl()  {
        Assert.assertEquals("https://api.mite.paypoint.net:2443",
                EndpointManager.getEndpointUrl(EndpointManager.Environment.MITE));
    }

    @Test
    public void testProductionUrl()  {
        Assert.assertEquals("https://api.paypoint.net",
                EndpointManager.getEndpointUrl(EndpointManager.Environment.PRODUCTION));
    }
}
