package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseHelper {

    WebDriver driver;
    public WebDriverWait wait;


    public BaseHelper(WebDriver driver) {

        this.driver = driver;
    }

    private WebElement findElementBy(By locator) {

        return driver.findElement(locator);
    }

    private List<WebElement> findElementsBy(By locator) {

        return driver.findElements(locator);
    }

    public WebElement getElement(By locator) {

        return findElementBy(locator);
    }

    public void click_Mouse(By locatorAnd) {

        findElementBy(locatorAnd).click();
    }

    public void click_Action(By locator) {

        Actions action = new Actions(driver);
        action.moveToElement(findElementBy(locator)).click().perform();
    }

    public void click_Action(By locator, int right, int down) {

        Rectangle rectangle = findElementBy(locator).getRect();
        int x = rectangle.getX() + rectangle.getWidth() / right;
        int y = rectangle.getY() + rectangle.getHeight() / down;
//        int x = rectangle.getX() + right;
//        int y = rectangle.getY() + down;

        Actions action = new Actions(driver);
        action.moveByOffset(x,y).click().perform();
    }

    public void pause(int seconds) {

        try {

            Thread.sleep(seconds * 1_000L);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }
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

    public boolean isElementPresent(By locator) {

        return !findElementsBy(locator).isEmpty();
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

    public String alert(int seconds) {

        wait = new WebDriverWait(driver, seconds);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();

        return text;
    }

    public String alert(int seconds, boolean accept) {

        wait = new WebDriverWait(driver, seconds);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        if (accept)
            alert.accept();
        else
            alert.dismiss();

        return text;
    }

    public void alert(int seconds, String text, boolean accept) {

        wait = new WebDriverWait(driver, seconds);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);

        if (accept)
            alert.accept();
        else
            alert.dismiss();
    }
}