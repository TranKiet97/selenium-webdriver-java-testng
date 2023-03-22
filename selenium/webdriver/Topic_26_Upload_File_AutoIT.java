package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Upload_File_AutoIT {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
	
	String autoITFirefoxOneTimePath = projectPath + "\\autoITScript\\firefoxUploadOneTime.exe";
	String autoITChromeOneTimePath = projectPath + "\\autoITScript\\chromeUploadOneTime.exe";
	String autoITFirefoxMultipleTimePath = projectPath + "\\autoITScript\\firefoxUploadMultiple.exe";
	String autoITChromeMultipleTimePath = projectPath + "\\autoITScript\\chromeUploadMultiple.exe";
	
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
	public void TC_01_Jquery_File_Upload_One_File_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// Click to open "open file dialog"
		driver.findElement(By.cssSelector("span.btn-success")).click();
		// Load file
		if(driver.toString().contains("firefox")){
			Runtime.getRuntime().exec(new String[]{autoITFirefoxOneTimePath, flagFilePath});
		} else {
			Runtime.getRuntime().exec(new String[]{autoITChromeOneTimePath, flagFilePath});
		}
		// Verify load file successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + flagFile + "']")).isDisplayed());
		// Click to upload
		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
			sleepInSecond(4);
		}
		// Verify upload successful
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + flagFile + "']")).isDisplayed());
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + flagFile + "')]"));
	}

	@Test
	public void TC_02_Jquery_File_Upload_Multiple_File_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// Click to open "open file dialog"
		driver.findElement(By.cssSelector("span.btn-success")).click();
		// Load file
		if(driver.toString().contains("firefox")){
			Runtime.getRuntime().exec(new String[]{autoITFirefoxMultipleTimePath, flagFilePath, morningFilePath});
		} else {
			Runtime.getRuntime().exec(new String[]{autoITChromeMultipleTimePath, flagFilePath, morningFilePath});
		}
		// Verify load file successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + flagFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + morningFile + "']")).isDisplayed());
		// Click to upload
		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
			sleepInSecond(4);
		}
		// Verify upload successful
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + flagFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + morningFile + "']")).isDisplayed());
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + flagFile + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + morningFile + "')]"));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", driver.findElement(By.xpath(locator)));
		return status;
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