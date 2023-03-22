package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Textbox_Textarea_Exercise {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname, employeeID, username, password;
	String passportID, issuedDate, expiryDate, comment;

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
		firstname = "Donald";
		lastname = "Trump";
		username = "MrTrump" + rand.nextInt(9999);
		password = "Tru@$102";
		
		passportID = "135066835";
		issuedDate = "2018-04-17";
		expiryDate = "2028-04-16";
		comment ="Employer Passport\nIdentification Number";
	}

	@Test
	public void TC_01_OrangeSRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.name("firstName")).sendKeys(firstname);
		driver.findElement(By.name("lastName")).sendKeys(lastname);
		
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(10);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportID);
		driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).sendKeys(issuedDate);
		driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).sendKeys(expiryDate);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + passportID + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + issuedDate + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + expiryDate + "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportID);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issuedDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + passportID + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + issuedDate + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + expiryDate + "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportID);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issuedDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}