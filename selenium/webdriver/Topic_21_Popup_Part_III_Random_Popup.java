package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Popup_Part_III_Random_Popup {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email = "Automation" + rand.nextInt(9999) + "@gmail.com";

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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Random_Popup_In_DOM_Java_Code_Geeks() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(10);
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		// Vì nó luôn có trong thẻ DOM nên có thể dùng isDisplayed() để kiểm tra được
		if (driver.findElement(lePopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(email);
			driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
			sleepInSecond(5);
			
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(), "Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
			// Đóng Popup đi -> sau 10s tự động đóng 
			sleepInSecond(10);
		}
		
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("button#search-submit")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());
	}

	// @Test
	public void TC_02_Random_Popup_In_DOM_VNK() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
		By vnkPopup = By.cssSelector("div.thrv-page-section");
		if(driver.findElement(vnkPopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.thrv-page-section div.thrv_icon")).click();
			sleepInSecond(2);
		}
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
	}
	
	@Test
	public void TC_03_Random_Popup_Not_In_DOM_Dehieu() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(6);
		By signinPopup = By.cssSelector("div.popup-content");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(driver.findElements(signinPopup).size() > 0 && driver.findElements(signinPopup).get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Donald Trump");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys(email);
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0987654321");
			sleepInSecond(2);
			driver.findElement(By.cssSelector("button#close-popup")).click();
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys("Khóa học Thiết kế tủ điện");
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content h4")).getText(), "Khóa học Thiết kế tủ điện");
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course-content h4")).size(), 1);
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