package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Selenium_Wait_findElement_findElements {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FindElement() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// - Tìm thấy duy nhất 1 element/ node -> tìm thấy và thao tác trực tiếp lên node đó/ không chờ hết timeout
		driver.findElement(By.cssSelector("input#email"));
		
		// - Tìm thấy nhiều hơn 1 element/ node -> thao tác với duy nhất node đầu tiên
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("auto@gmail.net");
		
		// - Không tìm thấy element/ node -> cơ chế tìm lại mỗi 0.5s cho đến hết thời gian timeout -> đánh fail/ throw exception
		driver.findElement(By.cssSelector("input[type='check']")).sendKeys("auto@gmail.net");
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// - Tìm thấy duy nhất 1 element/ node -> tìm thấy và lưu vào list có size = 1/ không chờ hết timeout
		List<WebElement> element = driver.findElements(By.cssSelector("input#email"));
		System.out.println(element.size());
		
		// - Tìm thấy nhiều hơn 1 element/ node -> tìm thấy và lưu vào list element tương ứng
		List<WebElement> elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println(elements.size());
		
		// - Không tìm thấy element/ node -> cơ chế tìm lại mỗi 0.5s cho đến hết thời gian timeout -> không đáng fail vẫn chạy step tiếp theo/ return list rỗng
		List<WebElement> emptyElements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println(emptyElements.size());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}