package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Frame_Iframe {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Kyna_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(1);
		By facebookIframe = By.xpath("//iframe[contains(@src,'kyna.vn')]");
		
		Assert.assertTrue(driver.findElement(facebookIframe).isDisplayed());
		
		driver.switchTo().frame(driver.findElement(facebookIframe));
		String likeAmount = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(likeAmount, "165K likes");
		
		By chatIframe = By.xpath("//iframe[contains(@src, '/webchat.caresoft.vn')]");
		driver.switchTo().parentFrame();
		// driver.switchTo().frame("cs_chat_iframe");  // By Id
		driver.switchTo().frame(driver.findElement(chatIframe));
		driver.findElement(By.cssSelector("div.button_bar")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Donald Trump");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys("Hello!\nI am Trump");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		sleepInSecond(3);
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}

	@Test
	public void TC_02_HDFC_Bank_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		// By HDFCLoginFrame = By.xpath("//frame[contains(@src, 'RSNBLogin')]");
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("Donald97");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		driver.findElement(By.cssSelector("input#fldPasswordDispId")).sendKeys("Donald97");
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