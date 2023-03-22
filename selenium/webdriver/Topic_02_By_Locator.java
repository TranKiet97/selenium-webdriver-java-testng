package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_By_Locator {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email")).sendKeys("test@automation.com");
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("search-button"));
	}
	
	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("login[username]"));
	}
	
	@Test
	public void TC_04_Tagname() {
		driver.findElement(By.tagName("a"));
	}
	
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("SEARCH TERMS"));
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		driver.findElement(By.partialLinkText("SEARCH"));
	}
	
	@Test
	public void TC_07_Css() {
		// Css với id
		driver.findElement(By.cssSelector("input#email"));
		// Css với class
		driver.findElement(By.cssSelector("div.new-users"));
		// Css với name
		driver.findElement(By.cssSelector("input[name='login[password]']"));
		// Css với tagname
		driver.findElement(By.cssSelector("a"));
		// Css với linktext
		driver.findElement(By.cssSelector("a[title='Search Terms']"));
		// Css với partial linktext
		driver.findElement(By.cssSelector("a[title*='Terms']"));
	}
	
	@Test
	public void TC_08_XPath() {
		// XPath với id
		driver.findElement(By.xpath("//input[@id='email']"));
		// XPath với class
		driver.findElement(By.xpath("//div[@class='col-1 new-users']"));
		// XPath với name
		driver.findElement(By.xpath("//input[@name='login[password]']"));
		// XPath với tagname
		driver.findElement(By.xpath("//a"));
		// XPath với linktext
		driver.findElement(By.xpath("//a[@title='Search Terms']"));
		driver.findElement(By.xpath("//a[text()='Search Terms']"));
		// XPath với partial linktext
		driver.findElement(By.xpath("//a[contains(@title,'Terms')]"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}