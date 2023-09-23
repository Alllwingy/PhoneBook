package starttests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import starttests.utils.RandomGenerator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    // @BeforeSuite - init and teardown
    // locators
    // methods common (example fill form)

    WebDriver driver;

    @BeforeSuite
    public void initial() {

        driver = new ChromeDriver();
        driver.navigate().to("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    String navigationLoginButtonAnd = "//*[@href='/login']";
    String byEmailLocator = "//input[@name='email']";
    String byPasswordLocator = "//input[@name='password']";
    String loginButtonAnd = "//button[@name='login']";
    String registrationButtonAnd = "//button[@name='registration']";
    String navigationContactsButtonActiveTest = "//a[@href='/contacts']";

    List<String> listOfEmails;

    @Test
    public void findInputForRegistration(String locator) {

        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
        element.clear();

        if (locator.contains("email"))
            element.sendKeys(RandomGenerator.generateEmail(12));
        else if (locator.contains("password"))
            element.sendKeys("Task$12345");
    }

    public void findInputForLogin(String locator) throws IOException {

        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
        element.clear();

        if (locator.contains("email"))
            element.sendKeys(RandomGenerator.readlistOfEmailsFromFile());
        else
            element.sendKeys("Task$12345");
    }

    @AfterSuite
    public void teardown() {

        driver.quit();
    }
}
