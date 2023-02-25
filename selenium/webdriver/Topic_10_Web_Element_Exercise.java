package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Web_Element_Exercise {
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
	
	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(By.id("email")).isDisplayed()) {
			System.out.println("Email Textbox is displayed");
			driver.findElement(By.id("email")).sendKeys("Automation Testing");
		} else {
			System.out.println("Email Textbox is not displayed");
		}
		
		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			System.out.println("Age under 18 is displayed");
			driver.findElement(By.id("under_18")).click();
		} else {
			System.out.println("Age under 18 is not displayed");
		}
		
		if (driver.findElement(By.id("edu")).isDisplayed()) {
			System.out.println("Education Textarea is displayed");
			driver.findElement(By.id("edu")).sendKeys("Automation Testing");
		} else {
			System.out.println("Education Textarea is not displayed");
		}
		
		if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
			System.out.println("Name: User5 is displayed");
		} else {
			System.out.println("Name: User5 is not displayed");
		}
	}
	
	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(By.id("disable_password")).isEnabled()) {
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is disabled");
		}
		
		if (driver.findElement(By.id("mail")).isEnabled()) {
			System.out.println("Email Textbox is enabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}
		
		if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Radio Button is enabled");
		} else {
			System.out.println("Radio Button is disabled");
		}
		
		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println("Development Checkbox is enabled");
		} else {
			System.out.println("Development Checkbox is disabled");
		}
	}
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());

		driver.findElement(By.id("java")).click();
		
		Assert.assertTrue(!driver.findElement(By.id("java")).isSelected());
	}
	
	@Test
	public void TC_04_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup");
		
		driver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']//button[@aria-label='Close']")).click();
		
		driver.findElement(By.id("email")).sendKeys("Automation@gmail.com");
		driver.findElement(By.id("new_password")).sendKeys("");
		driver.findElement(By.id("create-account-enabled")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("aaa");
		driver.findElement(By.id("create-account-enabled")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("AAA");
		driver.findElement(By.id("create-account-enabled")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("123");
		driver.findElement(By.id("create-account-enabled")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("#@$");
		driver.findElement(By.id("create-account-enabled")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
	
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("12345678");
		driver.findElement(By.id("create-account-enabled")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).getText(), "8 characters minimum");
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Q123ab@#$");
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char completed']/span")).getText(), "");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char completed']/span")).getText(), "");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char completed']/span")).getText(), "");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char completed']/span")).getText(), "");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).getText(), "");
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
