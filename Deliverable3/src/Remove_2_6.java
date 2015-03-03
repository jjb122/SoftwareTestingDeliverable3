import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Remove_2_6 {
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
  public void testRemove23() throws Exception {
    driver.get(baseUrl + "/");
    
    assertEquals(driver.findElement(By.id("nav-signin-text")).getText(), "Sign in");
    assertEquals(Integer.parseInt(driver.findElement(By.id("nav-cart-count")).getText()), 0);
    
    driver.findElement(By.id("nav-signin-title")).click();
    driver.findElement(By.id("ap_email")).clear();
    driver.findElement(By.id("ap_email")).sendKeys("roarblahblah@gmail.com");
    driver.findElement(By.id("ap_password")).clear();
    driver.findElement(By.id("ap_password")).sendKeys("testing123");
    driver.findElement(By.id("signInSubmit-input")).click();
    driver.findElement(By.cssSelector("span.nav-logo-base.nav-sprite")).click();
    driver.get(baseUrl + "/");
    
    assertNotEquals(driver.findElement(By.id("nav-signin-text")).getText(), "Sign in");
    
    
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
    driver.findElement(By.id("twotabsearchtextbox")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.id("twotabsearchtextbox")).clear();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("nicolas cage pillow");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.cssSelector("input.nav-submit-input")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.xpath("//li[@id='result_0']/div/div/div/div[2]/div/a/h2")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.id("add-to-cart-button")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=_e_07mn | ]]
    driver.findElement(By.cssSelector("#nav-cart > span.nav-button-title.nav-button-line2")).click();
    
    
    
    String cost = driver.findElement(By.className("sc-product-price")).getText();
    cost = cost.substring(1, cost.length());
    String origSubtotal = driver.findElement(By.cssSelector("p.a-spacing-none.a-spacing-top-mini > span.a-size-medium.a-text-bold")).getText();

   
    List<WebElement> inputList = driver.findElements(By.xpath("//input"));
    String toDelete = "";
    
    for (WebElement we: inputList){
    	String name = we.getAttribute("name");
    	//If we've found the item to delete....
    	if (name.startsWith("submit.delete")){
    		toDelete = name;
    		break;
    	}
    }
    
    driver.findElement(By.name(toDelete)).click();

    //This just keeps trying until we successfully can read the DOM element containing the subtotal. We have to
    //do this, because it doesn't let me access it immediately. It seems to think it's still being edited.
    String newSubtotal = "";
    int attempts = 0;
    while(attempts < 5) {
        try {
            newSubtotal = driver.findElement(By.cssSelector("p.a-spacing-none.a-spacing-top-mini > span.a-size-medium.a-text-bold")).getText();
            break;
        } catch(Exception e) {
        }
        attempts++;
    }
    
    /*
    driver.navigate().refresh();
    driver.switchTo().alert().accept();
    String newSubtotal = driver.findElement(By.cssSelector("p.a-spacing-none.a-spacing-top-mini > span.a-size-medium.a-text-bold")).getText();
    */
    
    
    int beginOrigIndex = origSubtotal.indexOf('$');
    int beginNewIndex = newSubtotal.indexOf('$');
    
    
    
    double originalPrice = Double.parseDouble(origSubtotal.substring(beginOrigIndex+1, origSubtotal.length()));
    double newPrice = Double.parseDouble(newSubtotal.substring(beginNewIndex+1, newSubtotal.length()));
    
    assertEquals("Subtotal (1 item)", newSubtotal.substring(0, 17));
    assertEquals(originalPrice - newPrice, Double.parseDouble(cost), 0.01);
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
