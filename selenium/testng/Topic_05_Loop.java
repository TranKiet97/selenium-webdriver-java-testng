package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_05_Loop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Random rand = new Random();

	WebDriverWait explicitWait;

	String firstname = "John";
	String lastname = "Wick";
	String fullname = firstname + " " + lastname;
	String password = "123456789";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 3)
	public void TC_01_Register() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).clear();
		String emailAddress = "Automation" + rand.nextInt(99999) + "@gmail.com";
		System.out.println(emailAddress);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		
		driver.findElement(By.cssSelector("a.skip-account>span.label")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
