package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Web_Browser_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			// Window
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			// MAC OS 
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Url() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_04_Page_Source() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
