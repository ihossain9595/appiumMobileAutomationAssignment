package com.qa.test.calculator;

import com.qa.util.BaseTest;

public class BaseCalculatorTest extends BaseTest {

    @Override
    protected String getAppPackage() {
        return "com.google.android.calculator";
    }

    @Override
    protected String getAppActivity() {
        return "com.android.calculator2.Calculator";
    }
}
