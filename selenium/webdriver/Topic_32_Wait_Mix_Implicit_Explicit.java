package webdriver;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_32_Wait_Mix_Implicit_Explicit {
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
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Element_Found() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://www.facebook.com/");
		
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
		
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit: " + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian kết thúc của implicit: " + getTimeStamp());
	}

	// @Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit: " + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của implicit: " + getTimeStamp());
		}
	}
	
	// @Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		// 3.1 - Implicit = Explicit
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// explicitWait = new WebDriverWait(driver, 5);
		
		// 3.2 - Implicit < Explicit
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// explicitWait = new WebDriverWait(driver, 8);
		
		// 3.3 - Implicit > Explicit
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");    
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
		}
	}

	// @Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
		}
	}

	@Test
	public void TC_05_Element_Not_Found_Explicit_webElement() {
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
		}
	}

	@Test
	public void TC_06_() {
		
	}
	// Show ra time-stamp tại thời điểm gọi hàm này
	public String getTimeStamp() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return date.format(formatDateTime).toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}