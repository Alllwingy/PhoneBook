package tests;

import manager.AppleManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.RandomGenerator;

public class TestBase {

    static AppleManager apple = new AppleManager();
    RandomGenerator random = new RandomGenerator();

    @BeforeSuite
    public void before() {

        apple.setUp();
    }

    @AfterSuite
    public void after() {

        apple.tearDown();
    }
}
