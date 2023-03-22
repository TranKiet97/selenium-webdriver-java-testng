package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Selenium_Wait_Implicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			// Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			// MAC OS 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Not_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
	}

	@Test
	public void TC_02_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
	}
	
	@Test
	public void TC_03_More_Time() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}