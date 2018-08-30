package seleniumEcommerce;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class EcommerceTestNGScript {
	
  @BeforeMethod
  public void init() {
    	System.setProperty("webdriver.chrome.driver",Util.loc);
    	Util.driver = new ChromeDriver();
    	Util.driver.manage().timeouts()
		.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
    	Util.driver.get(Util.url);
  }
  
 @Test
  public void homeTitle(){
	  	String actualHomeTitle;
	  	actualHomeTitle = Util.homeTitle().getText().trim();
	  	assertEquals(actualHomeTitle, Util.expectedHomeTitle);
	}
  
  @Test
  public void mobileTitle(){
	  	String actualMobileTitle;
	  	actualMobileTitle = Util.mobileTitle().getText();
	  	assertEquals(actualMobileTitle, Util.expectedMobileTitle);
	}
  
  @Test
  public void sortByMobileItemsVerify() throws InterruptedException{
	  
	  List<String> afterItems = new ArrayList<String>();
	  afterItems.add(0, "IPHONE");
	  afterItems.add(1, "SAMSUNG GALAXY");
	  afterItems.add(2, "SONY XPERIA");
	  
	  List<String> beforeItems = new ArrayList<String>();
	  beforeItems.addAll(Util.sortMobileItems());
	  
	  for(int i=0; i<beforeItems.size();i++){
		  assertEquals(beforeItems.get(i),afterItems.get(i));
		}
  	}
  
  @Test
  public void comparingCostXperiaBetweenListAndDetail(){
	  Util.mobileClick();
	  
	  String experiaActualCostListPage = Util.readListCostXperia();
	  String experiaActualCostDetailPage = Util.readDetailCostXperia();
	
	  assertEquals(experiaActualCostListPage, Util.expectedXperiaCost);
	  assertEquals(experiaActualCostDetailPage, Util.expectedXperiaCost);
	  
  }
  
  @Test
  public void addToCartErrorAndEmpty(){
	  String actualErrorMsg = Util.addToCartVerifyError();
	  assertEquals(actualErrorMsg, Util.expectedAddToCartErrorMsg);
	  String actualEmptyMsg = Util.emptyCart();
	  assertEquals(actualEmptyMsg, Util.expectedAddToCartEmptyMsg);
  }
  
  @Test
  public void popUpWindowAddToCompare() throws InterruptedException {
	  	Util.addToCompare();
	    for (String handle : Util.driver.getWindowHandles())
	    {
		  Util.driver.switchTo().window(handle);
	    }
	    
	    Util.popupWindow();
	    Thread.sleep(1000);
	   
	    try{
	    	assertEquals(Util.compareMobile1, Util.insidePopupMobile1);
		   } catch (Exception e) {
		    	e.printStackTrace();	    	
		   }	
	    
	    try{	    	
	    	assertEquals(Util.compareMobile2, Util.insidePopupMobile2);
		   } catch (Exception e) {
		    	e.printStackTrace();	    	
		   }	
	    	    
	    Util.driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div[2]/button/span/span")).click();
	    for (String handle : Util.driver.getWindowHandles())
	    {
	    	Util.driver.switchTo().window(handle);
	    }	    
  	}

  @AfterMethod
  public void tearDown() {
	  Util.driver.close();
  }

}
