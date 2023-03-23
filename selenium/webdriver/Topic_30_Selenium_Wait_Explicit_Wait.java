package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Selenium_Wait_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String flagFile = "flag.jpg";
	String morningFile = "morning.jpg";
	String mountainFile = "mountain.jpg";
	String seaFile = "sea.jpg";
	
	String flagFilePath = projectPath + "\\uploadFiles\\" + flagFile;
	String morningFilePath = projectPath + "\\uploadFiles\\" + morningFile;
	String mountainFilePath = projectPath + "\\uploadFiles\\" + mountainFile;
	String seaFilePath = projectPath + "\\uploadFiles\\" + seaFile;

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
		explicitWait = new WebDriverWait(driver, 40);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Element_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		explicitWait = new WebDriverWait(driver, 5);
		// Hiển thị message
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
	}

	// @Test
	public void TC_02_Element_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Không hiển thị icon loading
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
	}

	// @Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// Wait until the calendar is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		// Verify text in Selected Dates field
		Assert.assertTrue(driver.findElement(By.cssSelector("span.label")).getText().contains("No Selected Dates to display."));
		
		// Wait until day 22 is clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='22']")));
		driver.findElement(By.xpath("//a[text()='22']")).click();
		// Wait until ajax loading invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='22']")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(),"Wednesday, March 22, 2023");
	}
	
	@Test
	public void TC_04_Upload_Files() {
		driver.get("https://gofile.io/uploadFiles");
		// wait until Add Files button is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(flagFilePath + "\n" + morningFilePath);
		// Wait cho các loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		// Wait cho hiển thị success message
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']")).isDisplayed());
		// Wait cho link có thể click + click vào link
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'https://gofile.io')]"))).click();
		// Wait + verify cho play button hiển thị 
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='flag.jpg']/parent::a/parent::div/following-sibling::div//button[contains(@class, 'filesContentOptionPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='morning.jpg']/parent::a/parent::div/following-sibling::div//button[contains(@class, 'filesContentOptionPlay')]"))).isDisplayed());		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
