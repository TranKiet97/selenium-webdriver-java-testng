package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Window_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Handle_With_GUID() {
		driver.get("https://automationfc.github.io/basic-form/index.html#");
		String mainPageWindowId = driver.getWindowHandle();
		System.out.println(mainPageWindowId);
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String Id : allWindowIDs) {
			if(!Id.equals(mainPageWindowId)) {
				driver.switchTo().window(Id);
				sleepInSecond(2);
			}
		}
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.switchTo().window(mainPageWindowId);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html#");
	}

	// @Test
	public void TC_02_Handle_With_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html#");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		switchToWindowByPageTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getCurrentUrl(), "https://tiki.vn/");
		
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html#");
	}
	
	// @Test
	public void TC_03_Handle_Kyna_Window() {
		driver.get("https://skills.kynaenglish.vn/");
		By iframeFacebook = By.xpath("//iframe[contains(@src, '/kyna.vn')]");
		driver.switchTo().frame(driver.findElement(iframeFacebook));
		driver.findElement(By.cssSelector("a[title='Kyna.vn']")).click();
		sleepInSecond(6);
		switchToWindowByPageTitle("Kyna.vn | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn/");
		
		switchToWindowByPageTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getCurrentUrl(), "https://skills.kynaenglish.vn/");
		
		driver.switchTo().parentFrame(); // Có hoặc không có cũng được
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='k-footer']//a[contains(@href, '/kynavn')]")));
		sleepInSecond(6);
		switchToWindowByPageTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");	
	}
	
	// @Test
	public void TC_04_Handle_Live_Guru_Window() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		By compareSamsungGalaxy = By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']");
		By compareSonyXperia = By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']");
		
		driver.findElement(compareSamsungGalaxy).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(compareSonyXperia).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(6);
		
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeWindowByPageTitle("Products Comparison List - Magento Commerce"); // Driver vẫn còn ở trang Compare
		// driver.findElement(By.xpath("//button[@title='Close Window']")).click(); 
		
		switchToWindowByPageTitle("Mobile");
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		driver.switchTo().alert().accept();
		sleepInSecond(3);		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
	}
	
	@Test
	public void TC_05_() {
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		switchToWindowByPageTitle("Login");
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		Assert.assertEquals(driver.findElements(By.xpath("//span[text()='This field is required']")).size(), 2);
		closeWindowByPageTitle("Login");
		switchToWindowByPageTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		driver.findElement(By.id("searchword")).sendKeys("Automation");
		driver.findElement(By.cssSelector("button[title='Tìm kiếm']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span.headword span")).getText(), "automation");
	}
	
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String ID : allWindowIDs) {
			driver.switchTo().window(ID);
			String actualPageTitle = driver.getTitle();
			System.out.println(actualPageTitle); // Debug
			if(actualPageTitle.equals(expectedPageTitle)) {
				sleepInSecond(6);
				break;
			}
		}
	}
	
	public void closeWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String ID : allWindowIDs) {
			driver.switchTo().window(ID);
			String actualPageTitle = driver.getTitle();
			if(actualPageTitle.equals(expectedPageTitle)) {
				driver.close();;
				break;
			}
		}
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