package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Custom_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}

	// @Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}
	
	@Test
	public void TC_03_() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInSecond(2);
		
		// Solution 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		
		// Solution 2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
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