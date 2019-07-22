package it.org.techtime.jira.easysso.seleniumtests.suiteutils;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class SuiteFailListener implements IInvokedMethodListener {

    private static volatile boolean failing;

    public SuiteFailListener() {
        failing = false;
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if(failing) {
            throw new RuntimeException("Test skipped due to Smoke tests failure in the suite");

        }

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        if (! testResult.isSuccess()) {
            failing = true;
        }
        if ((failing) && (testResult.getThrowable().getMessage().contains("Test skipped"))) {
            testResult.setStatus(ITestResult.SKIP);
        }
    }
}
