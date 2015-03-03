import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

//Scenario: 2 items in cart, change quantity of the first item from 1 to 2 while logged in
public class ChangeQuant_3_4 {
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
  public void testChangeQuant34() throws Exception {
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
    
    
    driver.findElement(By.id("twotabsearchtextbox")).click();
    driver.findElement(By.id("twotabsearchtextbox")).clear();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("banana slicer");
    driver.findElement(By.cssSelector("input.nav-submit-input")).click();
    driver.findElement(By.xpath("//li[@id='result_0']/div/div/div/div[2]/div/a/h2")).click();
    driver.findElement(By.id("add-to-cart-button")).click();
    driver.findElement(By.id("twotabsearchtextbox")).clear();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("nicolas cage pillow");
    driver.findElement(By.cssSelector("input.nav-submit-input")).click();
    driver.findElement(By.xpath("//li[@id='result_0']/div/div/div/div[2]/div/a/h2")).click();
    driver.findElement(By.id("add-to-cart-button")).click();
    driver.findElement(By.cssSelector("span.nav-cart-button.nav-sprite")).click();
    
    
    String oldSubtotal = "";
    int attempts = 0;
    while(attempts < 5) {
        try {
        	oldSubtotal = driver.findElement(By.className("sc-price")).getText();
        	break;
        } catch(Exception e) {
        }
        attempts++;
    }
    
    
    
    driver.findElement(By.id("a-autoid-2-announce")).click();
    driver.findElement(By.id("dropdown1_1")).click();
    
    
    String quantity = driver.findElement(By.className("a-dropdown-prompt")).getText();
    
    String itemCost = "";
    attempts = 0;
    while(attempts < 5) {
        try {
        	 itemCost = driver.findElement(By.xpath("//form[@id='activeCartViewForm']/div[2]/div/div[4]/div[2]/div[2]/p/span")).getText();
            break;
        } catch(Exception e) {
        }
        attempts++;
    }
    
    String newSubtotal = "";
    attempts = 0;
    while(attempts < 5) {
        try {
        	newSubtotal = driver.findElement(By.className("sc-price")).getText();
        	break;
        } catch(Exception e) {
        }
        attempts++;
    }
    
    
    oldSubtotal = oldSubtotal.substring(1, oldSubtotal.length());
    newSubtotal = newSubtotal.substring(1, newSubtotal.length());
    itemCost = itemCost.substring(1, itemCost.length());
    
    assertEquals(Double.parseDouble(newSubtotal) - Double.parseDouble(oldSubtotal), Double.parseDouble(itemCost), 0.01);
    assertEquals(2, Integer.parseInt(quantity));
  }

  @After
  public void tearDown() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.cssSelector("span.nav-logo-base.nav-sprite")).click();
		driver.findElement(By.cssSelector("#nav-cart > span.nav-button-title.nav-button-line1")).click();
		
	    List<WebElement> inputList = driver.findElements(By.xpath("//input"));
	    String toDelete = "";
	    boolean hasDeletable = true;
	    boolean deleted = false;
	    
	    while(hasDeletable)
		{
		    for (WebElement we: inputList)
		    {
		    	String name = we.getAttribute("name");
		    	//If we've found the item to delete....
		    	if (name.startsWith("submit.delete"))
		    	{
		    		toDelete = name;
		    		driver.findElement(By.name(toDelete)).click();
		    		driver.navigate().refresh();
		    		//driver.switchTo().alert().accept();
		    		deleted = true;
		    		break;
		    	}
		    }
		    if(deleted)
		    {
		    	inputList = driver.findElements(By.xpath("//input"));
		    	deleted = false;
		    }
		    else
		    {
		    	hasDeletable = false; 
		    }
		}		  
	    driver.get(baseUrl + "/");
	    driver.get(baseUrl + "/gp/flex/sign-out.html/ref=nav_youraccount_signout?ie=UTF8&action=sign-out&path=%2Fgp%2Fyourstore%2Fhome&signIn=1&useRedirectOnSuccess=1");
	  
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
