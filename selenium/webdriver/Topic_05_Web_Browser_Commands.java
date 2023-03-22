package webdriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Commands {
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
	public void TC_01_Browser() throws MalformedURLException {
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
		
		Point point = driver.manage().window().getPosition();
		point.getX();
		point.getY();
		driver.manage().window().setPosition(new Point(1990,1987));
		
		Dimension dimension = driver.manage().window().getSize();
		dimension.getHeight();
		dimension.getWidth();
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://google.com");
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("");
		driver.navigate().to(new URL(""));
		
		driver.switchTo().alert();
	}

	@Test
	public void TC_02_Tips() {
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