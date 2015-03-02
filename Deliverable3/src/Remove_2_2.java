import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Remove_2_2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.amazon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRemove22() throws Exception {
    driver.get(baseUrl + "/");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.id("twotabsearchtextbox")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.id("twotabsearchtextbox")).clear();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("banana slicer");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.cssSelector("input.nav-submit-input")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.xpath("//li[@id='result_0']/div/div/div/div[2]/div/a/h2")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.id("add-to-cart-button")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.id("nav-cart")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.name("submit.delete.C2GTHXQTUK6UGS")).click();
    // Warning: assertTextPresent may require manual changes
    assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*//form\\[@id='activeCartViewForm'\\]/div\\[3\\]/p/span/span/span[\\s\\S]*$"));
    // Warning: assertTextNotPresent may require manual changes
    assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*css=span\\.a-size-medium\\.a-text-bold[\\s\\S]*$"));
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
