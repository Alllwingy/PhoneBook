package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class AppleManager {

    WebDriver driver;
    UserHelper userHelper;
    Logger logger = LoggerFactory.getLogger(AppleManager.class);

    public void setUp() {

        driver = new ChromeDriver();
        driver.navigate().to("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        userHelper = new UserHelper(driver);
    }

    public void navigateToMainPage() {

        driver.navigate().to("https://telranedu.web.app/home");
    }

    public UserHelper getUserHelperToApply() {

        return userHelper;
    }

    public void tearDown() {

        driver.quit();
    }
}
