package com.automation.cucumber;

import com.automation.drivermanager.ManageDriver;
import com.automation.propertyreader.PropertyReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends ManageDriver {

    @Before
    public void setUp() {
        selectBrowser(PropertyReader.getInstance().getProperty("browser"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            closeBrowser();
        }
    }
}