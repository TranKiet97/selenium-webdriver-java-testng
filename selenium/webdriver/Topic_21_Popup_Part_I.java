package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
public class Topic_21_Popup_Part_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			// Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			// MAC OS 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		// Turn off notifications in fire-fox
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		
		driver = new FirefoxDriver(options);
		explicitWait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Fixed_In_DOM_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.login_")).click();
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	}

	// @Test
	public void TC_02_Fixed_In_DOM_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
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