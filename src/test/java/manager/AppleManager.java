package manager;

import lombok.Getter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigurationProperties;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class AppleManager {

    static EventFiringWebDriver driver;
    @Getter
    UserHelper userHelper;
    @Getter
    ContactHelper contactHelper;
    Logger logger = LoggerFactory.getLogger(AppleManager.class);
    static String browser;

    public AppleManager() {

        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void setUp() {

        if (browser.equals(BrowserType.CHROME)) {

            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("CHROME driver");

        } else if (browser.equals(BrowserType.FIREFOX)) {

            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("FIREFOX driver");
        }

        driver.navigate().to(ConfigurationProperties.getProperty("url"));
        logger.info("open: " + ConfigurationProperties.getProperty("url") + " Start testing: " + LocalDateTime.now());

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.register(new WDListener());

        userHelper = new UserHelper(driver);
        contactHelper = new ContactHelper(driver);
    }

    public void navigateToMainPage() {

        driver.navigate().to("https://telranedu.web.app/home");
    }

    public void refresh() {

        driver.navigate().refresh();
    }

    public void tearDown() {

        driver.quit();
    }

    public boolean isPageUrlHome() {

        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl + "----------------- url");
        return currentUrl.equals(ConfigurationProperties.getProperty("url"));
    }
}
