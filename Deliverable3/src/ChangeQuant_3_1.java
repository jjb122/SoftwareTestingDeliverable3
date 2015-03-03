import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ChangeQuant_3_1 {
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
  public void testRemove31() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("twotabsearchtextbox")).click();
    driver.findElement(By.id("twotabsearchtextbox")).clear();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("nicolas cage pillow");
    driver.findElement(By.cssSelector("input.nav-submit-input")).click();
    driver.findElement(By.xpath("//li[@id='result_0']/div/div/div/div[2]/div/a/h2")).click();
    driver.findElement(By.id("add-to-cart-button")).click();
    driver.findElement(By.cssSelector("#nav-cart > span.nav-button-title.nav-button-line2")).click();
    driver.findElement(By.id("a-autoid-2-announce")).click();
    driver.findElement(By.id("dropdown1_1")).click();
    
    
    String quantity = driver.findElement(By.className("a-dropdown-prompt")).getText();
    
    String origCost = "";
    int attempts = 0;
    while(attempts < 5) {
        try {
        	 origCost = driver.findElement(By.xpath("//form[@id='activeCartViewForm']/div[2]/div/div[4]/div[2]/div[2]/p/span")).getText();
            break;
        } catch(Exception e) {
        }
        attempts++;
    }
    
    String doubleCost = "";
    attempts = 0;
    while(attempts < 5) {
        try {
        	doubleCost = driver.findElement(By.className("sc-price")).getText();
        	break;
        } catch(Exception e) {
        }
        attempts++;
    }
    
    
    
    doubleCost = doubleCost.substring(1, doubleCost.length());
    origCost = origCost.substring(1, origCost.length());
    
    assertEquals(Double.parseDouble(doubleCost), Double.parseDouble(origCost)*2, 0);
    assertEquals(2, Integer.parseInt(driver.findElement(By.className("a-dropdown-prompt")).getText()));
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
