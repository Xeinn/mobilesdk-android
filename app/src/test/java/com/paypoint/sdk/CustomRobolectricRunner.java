/*
 * Copyright (c) 2015. PayPoint
 */

package com.paypoint.sdk;

/**
 * Who:  Pete
 * When: 23/04/2015
 * What:
 */
import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;
import java.util.Properties;

public class CustomRobolectricRunner extends RobolectricTestRunner {
    public CustomRobolectricRunner(Class<?> testClass)
            throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String path = "src/main/AndroidManifest.xml";

        // android studio has a different execution root for tests than pure gradle
        // so we avoid here manual effort to get them running inside android studio
        if (!new File(path).exists()) {
            path = "app/" + path;
        }

        config = overwriteConfig(config, "manifest", path);
        return super.getAppManifest(config);
    }

    protected Config.Implementation overwriteConfig(
            Config config, String key, String value) {
        Properties properties = new Properties();
        properties.setProperty(key, value);
        return new Config.Implementation(config,
                Config.Implementation.fromProperties(properties));
    }
}
