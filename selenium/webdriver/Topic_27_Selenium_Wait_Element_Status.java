package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Selenium_Wait_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

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
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Visible_Displayed_Visibility() {
		// Có trên UI (bắt buộc) && Có trong HTML (bắt buộc)
		// Chờ cho email address textbox hiển thị trong vòng 10 giây
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.net");
	}

	// @Test
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		// Không có trên UI (bắt buộc) && Có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		// Chờ cho Re-enter email textbox không hiển thị trong 10 giây
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[aria-label='Re-enter email address']")));
	}
	
	// @Test
	public void TC_03_Invisible_Undisplayed_Invisibility_II() {
		// Không có trên UI (bắt buộc) && Không có trong HTML  
		driver.get("https://www.facebook.com/");
		// Chờ cho Re-enter email textbox không hiển thị trong 10 giây
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[aria-label='Re-enter email address']")));
	}
	
	@Test
	public void TC_04_Presence_I() {
		// Có trên UI && Có trong HTML (bắt buộc)
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.net");
	}
	
	@Test
	public void TC_05_Presence_II() {
		// Không có trên UI && Có trong HTML (bắt buộc)
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		// Chờ cho Re-enter email textbox không hiển thị trong 10 giây
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[aria-label='Re-enter email address']")));
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