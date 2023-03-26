package webdriver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_34_Wait_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;

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
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
	}

	// @Test
	public void TC_01_Page_Ready_Ajax_Loading() {
		driver.get("https://api.orangehrm.com/");
		// Wait to loading icon invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}

	@Test
	public void TC_02_Page_Ready_Test_Project() {
		driver.get("https://blog.testproject.io/");
		
		// Hover chuột vào field search
		action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		String keyword = "Selenium";
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postArticles = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement postArticle : postArticles) {
			Assert.assertTrue(postArticle.getText().contains(keyword));
		}
	}
	
	// @Test
	public void TC_03_Page_Ready_Admin_Nopcommerce() {
		driver.get("https://admin-demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Password")).clear();
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
	}

	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jsLoad) && explicitWait.until(jQueryLoad);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}