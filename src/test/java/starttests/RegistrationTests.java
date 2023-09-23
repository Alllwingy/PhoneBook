package starttests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @Test
    public void registrationTests() {

        driver.findElement(By.xpath(navigationLoginButtonAnd)).click();

        findInputForRegistration(byEmailLocator);
        findInputForRegistration(byPasswordLocator);

        driver.findElement(By.xpath(registrationButtonAnd)).click();

        WebElement testIfPresent = driver.findElement(By.xpath(navigationContactsButtonActiveTest));
        String actualText = testIfPresent.getText().trim().toUpperCase();
        String expectedText = "CONTACTS".toUpperCase();

        Assert.assertEquals(actualText, expectedText);
    }
}
