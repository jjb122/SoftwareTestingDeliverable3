import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

//Scenario: 1 item in the cart, removes 1 item while logged in 
public class Remove_2_5 {
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
  public void testRemove25() throws Exception {
    driver.get(baseUrl + "/ref=nav_logo");
    
    
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
    
    driver.findElement(By.id("twotabsearchtextbox")).clear();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("banana slicer");
    driver.findElement(By.cssSelector("input.nav-submit-input")).click();
    driver.findElement(By.xpath("//li[@id='result_0']/div/div/div/div[2]/div/a/h2")).click();
    driver.findElement(By.id("add-to-cart-button")).click();
    driver.findElement(By.id("nav-cart")).click();
    
    
    
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
  
    String wasRemoved = driver.findElement(By.cssSelector("div.sc-list-item-removed-msg.a-padding-medium > div > span.a-size-base")).getText();
    wasRemoved = wasRemoved.substring(wasRemoved.length() - 31, wasRemoved.length());
    
    //Name of item may be too long to display, so we should not check the exact name. Just see if something was
    //removed.
    assertEquals("was removed from Shopping Cart.", wasRemoved);
    
    //Checks if cart is empty and subtotal is 0.
    assertEquals("Subtotal (0 item): $0.00", driver.findElement(By.cssSelector("span.a-size-medium.a-text-bold")).getText());
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
