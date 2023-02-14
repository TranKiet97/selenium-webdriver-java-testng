package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser_Commands {
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
	public void TC_01_Browser() {
		driver.close();
		driver.quit();
		driver.findElement(By.id(""));
		driver.findElements(By.id(""));
		driver.get("");
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();
		driver.manage().logs().get(LogType.BROWSER);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().setSize(null);
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://google.com");
	}

	@Test
	public void TC_02_Element() {
		
	}
	
	@Test
	public void TC_03_Tips() {
//		Nhóm 1 - hàm tương tác
		driver.findElement(By.id("")).sendKeys("");
//		Nhóm 2 - hàm lấy dữ liệu
		driver.findElement(By.id("")).getText();
//		Nhóm 3 - hàm kiểm tra dữ liệu
		driver.findElement(By.id("")).isDisplayed();
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}