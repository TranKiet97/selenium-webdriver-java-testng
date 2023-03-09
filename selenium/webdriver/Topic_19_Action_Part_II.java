package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Action_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Select_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-selectee"));
		/*
		 * 1- Click vào source và giữ chuột
		 * 2- Di chuột tới target
		 * 3- Nhả chuột trái ra
		 * 4- Execute
		 */
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(7)).release().perform();
		sleepInSecond(2);
		
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 8);
	}

	@Test
	public void TC_02_Select_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// Chạy được cho MAC/ WINDOWS
		Keys key = null;
		if(osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		List<WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-selectee"));
		/*
		 * 1- Nhấn phím Ctrl xuống
		 * 2- Click chọn các số random
		 * 3- Nhả phím Ctrl ra
		 */
		action.keyDown(key).perform();
		action.click(listNumber.get(1))
			  .click(listNumber.get(3))
			  .click(listNumber.get(4))
		      .click(listNumber.get(8)).perform();
		action.keyUp(key).perform();
		
		sleepInSecond(2);
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 4);
	}
	
	@Test
	public void TC_03_() {
		
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