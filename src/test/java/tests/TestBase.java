package tests;

import manager.AppleManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    static AppleManager apple = new AppleManager();

    @BeforeSuite
    public void before() {

        apple.setUp();
    }

    @AfterSuite
    public void after() {

        apple.tearDown();
    }
}
