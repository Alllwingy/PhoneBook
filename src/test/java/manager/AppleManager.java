package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AppleManager {

    WebDriver driver;
    UserHelper userHelper;

    public void setUp() {

        driver = new ChromeDriver();
        driver.navigate().to("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        userHelper = new UserHelper(driver);
    }

    public UserHelper getUserHelper() {

        return userHelper;
    }

    public void tearDown() {

        driver.quit();
    }
}
