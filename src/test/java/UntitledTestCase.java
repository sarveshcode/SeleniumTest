
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

public class UntitledTestCase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {

//        System.out.println("-->> Launching Firefox Browser.");
//        System.setProperty("webdriver.gecko.driver", "/Users/sarvesh/Downloads/geckodriver");
//        baseUrl = "http://localhost:8080/edaakhil";
//        driver = new FirefoxDriver();

        System.out.println("-->> Launching Firefox Browser With Headless Option.");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        System.setProperty("webdriver.gecko.driver", "/Users/sarvesh/Downloads/geckodriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        driver = new FirefoxDriver(firefoxOptions);



    }

    @Test
    public void testUntitledTestCase() throws Exception {
        System.out.println("-->> Test Start");

        Map<String, String> users = new HashMap<String, String>();
        users.put("root", "root");
        users.put("admin", "admin");
        users.put("root123", "root123");

        for(Map.Entry<String, String> entry : users.entrySet()) {

            System.out.println("-->> USER: "+entry.getKey()+" | PASSWORD: "+entry.getValue());

            driver.get("http://localhost:8080/edaakhil/");
            driver.findElement(By.xpath("//div[@id='j_idt11:j_idt12']/ul/li[3]/a/span")).click();
            driver.findElement(By.id("txtEmailId")).click();
            driver.findElement(By.id("txtEmailId")).clear();

//        driver.findElement(By.id("txtEmailId")).sendKeys("sar1234");
            driver.findElement(By.id("txtEmailId")).sendKeys(entry.getKey());
            driver.findElement(By.id("txtPassword")).clear();
//        driver.findElement(By.id("txtPassword")).sendKeys("Nic@123");
            driver.findElement(By.id("txtPassword")).sendKeys(entry.getValue());

            driver.findElement(By.id("j_idt44")).click();

            try {
                String loginMessage = driver.findElement(By.xpath("//div[@id='messages']/div/ul/li/span")).getText();
                System.out.println("-->> LoginFailedMessage: " + loginMessage);
            } catch (NoSuchElementException ex) {
                driver.findElement(By.linkText("File a New Case")).click();
                driver.findElement(By.id("j_idt29")).click();
                System.out.println("-->> New Case File.");
            }

        }


    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
