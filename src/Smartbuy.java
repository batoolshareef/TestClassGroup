import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Smartbuy {

	public WebDriver driver;
	public int numberOfTry = 2;
	SoftAssert softassert = new SoftAssert();
	
	
@BeforeTest
public void this_is_before_test() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
	}

	@Test(groups ="1")
	public void Test_Adding_Item_SAMSUNG_50_inch() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		for(int i=0;i<numberOfTry;i++) {
		driver.findElement(By.xpath(
				"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[3]/div[1]/div/div/form[1]/div[1]/button"))
				.click();
		driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();
		}

	}
	@Test(groups = "1")
	public void we_need_to_check_the_correct_price() {
		String the_single_item_price = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[3]")).getText();
		String [] updated_Price= the_single_item_price.split("JOD");
		String final_updated_price = updated_Price[0].trim();
//	    String price_with_updated = final_updated_price.replace(",", ".");
//	    System.out.println(price_with_updated);
//		int i=Integer.parseInt(final_updated_price);  
		Double val = Double.parseDouble(final_updated_price);
		System.out.println(val * numberOfTry);
		softassert.assertEquals(val * numberOfTry, 538.0);
		softassert.assertAll();
	}
}