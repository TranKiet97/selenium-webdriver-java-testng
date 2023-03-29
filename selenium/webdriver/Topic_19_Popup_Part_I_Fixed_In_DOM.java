package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_19_Popup_Part_I_Fixed_In_DOM {
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
		// Turn off notifications in fire-fox
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		
		driver = new FirefoxDriver(options);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Fixed_In_DOM_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		// Click vào Login Button
		driver.findElement(By.cssSelector("button.login_")).click();
		// Wait until Popup visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loginPopup));
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		// Wait until error message visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-login-panel")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	}

	@Test
	public void TC_02_Fixed_In_DOM_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		// Click vào Login Button
		driver.findElement(By.cssSelector("a.login-btn")).click();
		// Wait until Popup visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loginPopup));
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		// Wait until error message visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#password-form-login-message")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}