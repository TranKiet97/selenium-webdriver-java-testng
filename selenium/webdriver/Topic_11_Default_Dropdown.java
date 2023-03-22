package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Default_Dropdown {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, email, companyName, password;
	String day, month, year;

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

		firstName = "Donald";
		lastName = "Trump";
		email = "MrTrump" + rand.nextInt(9999) + "@gmail.com";
		companyName = "The Trump Organization";
		password = "D0n@lD102";

		day = "14";
		month = "June";
		year = "1946";
	}

	@Test
	public void TC_01_Facebook() {
		driver.get("https://www.facebook.com/signup");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("20");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Apr");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1997");
	}

	@Test
	public void TC_02_Nopcommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(day);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		driver.findElement(By.className("ico-login")).click();
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector(".login-button")).click();
		
		driver.findElement(By.className("ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getOptions().size(), 32);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getOptions().size(), 13);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getOptions().size(), 112);
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}