package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {

    WebDriver driver;

    public HelperBase(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement findElementBy(By locator) {

        return driver.findElement(locator);
    }

    public List<WebElement> findElementsBy(By locator) {

        return driver.findElements(locator);
    }

    public void clickOn(By locatorAnd) {

        findElementBy(locatorAnd).click();
    }

    public void fill(By locator, String text) {

        WebElement elementTo = findElementBy(locator);
        elementTo.click();
        elementTo.clear();
        elementTo.sendKeys(text);
    }

    public String getTextBy(By locator) {

        return findElementBy(locator).getText().trim().toUpperCase();
    }

    public boolean isResultsEquals(By locator, String expectedResult) {

        String actualResult = getTextBy(locator);
        expectedResult = expectedResult.toUpperCase();

        if (expectedResult.equals(actualResult))
            return true;
        else {

            System.out.println("expected result: " + expectedResult + "\nactual result: " + actualResult);
            return false;
        }
    }
}
