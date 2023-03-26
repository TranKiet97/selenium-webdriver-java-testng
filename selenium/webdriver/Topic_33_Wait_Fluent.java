package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_33_Wait_Fluent {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

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
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Dynamic_Loading() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElement("//div[@id='start']//button", 15, 100).click();
		Assert.assertEquals(findElement("//div[@id='finish']//h4", 15, 100).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Count_Down_Best_Practice() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countDown = findElement("//div[@id='javascript_countdown_time']", 15, 100);
		fluentElement = new FluentWait<WebElement>(countDown);
		fluentElement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(1000))
				.ignoring(NoSuchElementException.class);
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}

	public WebElement findElement(String xpathLocator, long timeout, long pollingTime) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Set Timeout and Frequency
		fluentDriver.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		// Apply Condition
		WebElement element = fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
		return element;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}