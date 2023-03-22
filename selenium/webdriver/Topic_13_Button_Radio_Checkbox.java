package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Button_Radio_Checkbox {
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

	// @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.className("popup-login-tab-login")).click();
		By loginBtn = By.className("fhs-btn-login");
		
		Assert.assertFalse(driver.findElement(loginBtn).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys("0388765678");
		driver.findElement(By.id("login_password")).sendKeys("0388765678");
		
		Assert.assertTrue(driver.findElement(loginBtn).isEnabled());
		Color loginBtnColor = Color.fromString(driver.findElement(loginBtn).getCssValue("background-color"));
		Assert.assertEquals(loginBtnColor.asHex().toUpperCase(), "#C92127");
	}

	@Test
	public void TC_02_Default_Checkbox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Click chọn 1 checkbox
		checkToCheckbox(By.xpath("//label[contains(text(), 'High Blood Pressure')]/preceding-sibling::input"));
		// Click chọn 1 radio btn
		checkToCheckbox(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input"));
		// Verify checkbox/ radio btn
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'High Blood Pressure')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).isSelected());
		// Bỏ chọn checkbox
		unCheckToCheckbox(By.xpath("//label[contains(text(), 'High Blood Pressure')]/preceding-sibling::input"));
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(), 'High Blood Pressure')]/preceding-sibling::input")).isSelected());
	}

	@Test
	public void TC_03_Default_Checkbox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement>	allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		for (WebElement checkbox : allCheckboxes) {
			if(checkbox.getAttribute("value").startsWith("A")) {
				checkbox.click();
				System.out.println(checkbox.getAttribute("value"));
			}
		}
		for (WebElement checkbox : allCheckboxes) {
			if(checkbox.getAttribute("value").startsWith("A")) {
				Assert.assertTrue(checkbox.isSelected());
			}
		}
	}
	
	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unCheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}