package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			// Window
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver111.exe");
		} else {
			// MAC OS 
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver111");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}

	// @Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
	
	// @Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String courseName = "Fullstack Selenium in Java";
		alert.sendKeys(courseName);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName);
	}
	
	// @Test
	public void TC_04_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		driver.get("https://the-internet.herokuapp.com/");
		String authenUrl =  driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		String[] arrayUrl = authenUrl.split("//");
		authenUrl = arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
		System.out.println(authenUrl);
		driver.get(authenUrl);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
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