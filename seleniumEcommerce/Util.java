package seleniumEcommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	/* You can change the Path of Chrome based on your environment here */
	public static final String loc = "C:\\chromedriver_win32\\chromedriver.exe";
	
	/* You can change the url path here */
	public static final String url = "http://live.guru99.com/";
	
	public static final int WAIT_TIME = 20; 
	public static final String expectedHomeTitle = "THIS IS DEMO SITE FOR";
	public static final String expectedMobileTitle = "MOBILE";
	public static final String expectedXperiaCost = "$100";
	public static final String expectedAddToCartErrorMsg = "* The maximum quantity allowed for purchase is 500.";
	public static final String expectedAddToCartEmptyMsg = "SHOPPING CART IS EMPTY";
			
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static String compareMobile1;
	public static String compareMobile2;
	public static String insidePopupMobile1;
	public static String insidePopupMobile2;
	
	//public static Select mobileItems;
	public static List<WebElement> items = new ArrayList<WebElement>();
	public static List<String> itemsSort = new ArrayList<String>();

	
	public static WebElement homeTitle(){
		WebElement actualTitle = driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div[1]/div/div/h2"));
		return actualTitle;
	}
	
	public static void mobileClick(){
		WebElement onMobileClick = driver.findElement(ByXPath.xpath("//*[@id='nav']/ol/li[1]/a"));
		onMobileClick.click();
	}
	
	public static WebElement mobileTitle(){
		mobileClick();
		WebElement actualTitle = driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[1]/h1"));
		return actualTitle;
	}
	
	public static List<String> sortMobileItems(){
		mobileClick();
		Select mobileItems = new Select(driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")));
		mobileItems.selectByVisibleText("Name");
		items.addAll(driver.findElements(ByXPath.xpath("//*[@class='product-name']")));
		
		for(int i=0; i<items.size();i++){
			itemsSort.add(items.get(i).getText());
		}
		Collections.sort(itemsSort);
		return itemsSort;
	}
	
	public static String readListCostXperia(){
		String experiaCost = driver.findElement(ByXPath.xpath("//*[@id='product-price-1']/span")).getText().substring(0, 4);
		return experiaCost;
	}
		
	public static String readDetailCostXperia(){
		String experiaCost2 = driver.findElement(ByXPath.xpath("//*[@id='product-price-1']/span")).getText().substring(0, 4);
		return experiaCost2;
	}
	
	public static String addToCartVerifyError(){
		mobileClick();
		
		driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/button")).click();
		
		driver.findElement(ByXPath.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();
		driver.findElement(ByXPath.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");
		
		driver.findElement(ByXPath.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();
		
		String errorMsg = driver.findElement(ByXPath.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[2]/p")).getText();
		return errorMsg;
		
	}
	
	public static String emptyCart(){
		driver.findElement(ByXPath.xpath("//*[@id='empty_cart_button']")).click();
		String empty = driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div[1]/h1")).getText();
		return empty;
	}
	
	public static void addToCompare() throws InterruptedException{
		mobileClick();
		compareMobile1 = driver.findElement(ByXPath.xpath("//h2/a[@title='Sony Xperia']")).getText();
		driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a")).click();;
		
		Thread.sleep(1000);
		compareMobile2 = driver.findElement(ByXPath.xpath("//h2/a[@title='IPhone']")).getText();
		driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")).click();
		
		Thread.sleep(1000);
		driver.findElement(ByXPath.xpath("//*[@id='top']/body/div/div/div[2]/div/div[3]/div[1]/div[2]/div/button/span/span")).click();
		
	    Thread.sleep(1000);
	}
	
	public static void popupWindow(){
		insidePopupMobile1 = driver.findElement(ByXPath.xpath("//*[@id='product_comparison']/tbody[1]/tr[1]/td[1]/h2/a")).getText();		
		insidePopupMobile2 = driver.findElement(ByXPath.xpath("//*[@id='product_comparison']/tbody[1]/tr[1]/td[2]/h2/a")).getText();
	}
}
