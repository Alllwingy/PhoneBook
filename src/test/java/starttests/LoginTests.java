package starttests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    @Test
    public void loginTests() throws IOException, InterruptedException {

        driver.findElement(By.xpath(navigationLoginButtonAnd)).click();

        findInputForLogin(byEmailLocator);
        findInputForLogin(byPasswordLocator);

        driver.findElement(By.xpath(loginButtonAnd)).click();

        WebElement testIfPresent = driver.findElement(By.xpath(navigationContactsButtonActiveTest));
        String actualText = testIfPresent.getText().trim().toUpperCase();
        String expectedText = "CONTACTS".toUpperCase();

        Assert.assertEquals(actualText, expectedText);
    }
}
