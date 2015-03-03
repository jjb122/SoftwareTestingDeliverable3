import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Remove_2_3 {
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
    System.out.println("COST OF ITEM TO DELETE IS " + cost);
    System.out.println("Original subtotal: " + origSubtotal);
   
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
    
    System.out.println("New subtotal: " + newSubtotal);
    
    int beginOrigIndex = origSubtotal.indexOf('$');
    int beginNewIndex = newSubtotal.indexOf('$');
    
    
    
    double originalPrice = Double.parseDouble(origSubtotal.substring(beginOrigIndex+1, origSubtotal.length()));
    double newPrice = Double.parseDouble(newSubtotal.substring(beginNewIndex+1, newSubtotal.length()));
    
    
    System.out.println("Orig: " + originalPrice);
    System.out.println("New: " + newPrice);
    System.out.println(newSubtotal.substring(0, 17));
    System.out.println("Cost subtraction: " + (originalPrice - newPrice));
    System.out.println("Cost of nic cage: " + cost);
    
    
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
