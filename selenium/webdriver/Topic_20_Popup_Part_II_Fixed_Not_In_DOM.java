package webdriver;

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

public class Topic_20_Popup_Part_II_Fixed_Not_In_DOM {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Fixed_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		By signinPopup = By.cssSelector("div[class*='ReactModal__Content']");
		Assert.assertEquals(driver.findElements(signinPopup).size(), 0);
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		Assert.assertTrue(driver.findElement(signinPopup).isDisplayed());
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Tiếp Tục']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess")).getText(), "Số điện thoại không được để trống");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("img.close-img")).click();
		Assert.assertEquals(driver.findElements(signinPopup).size(), 0);
	}

	@Test
	public void TC_02_Fixed_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		By createNewAccPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		Assert.assertEquals(driver.findElements(createNewAccPopup).size(), 0);
		
		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		Assert.assertEquals(driver.findElements(createNewAccPopup).size(), 1);
		
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Tran");
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Kiet");
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("input[name='reg_passwd__']")).sendKeys("12345678");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("15"); 
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Aug");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("2000");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(createNewAccPopup).size(), 0);
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