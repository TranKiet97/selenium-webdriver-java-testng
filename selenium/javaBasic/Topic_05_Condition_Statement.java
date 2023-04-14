package javaBasic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class Topic_05_Condition_Statement {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@Test
	public void TC_01_If() {
		boolean status = 5 > 3;
		System.out.println(status);
		if(status) {
			System.out.println("Go to if");
		}
		
		WebDriver driver = new FirefoxDriver();
		
		// Element luôn có trong DOM dù popup hiển thị hay không
		WebElement salePopup = driver.findElement(By.id(""));
		if(salePopup.isDisplayed()) {
			
		}
		
		// Element không có trong DOM khi popup không hiển thị
		List<WebElement> salePopups = driver.findElements(By.id(""));
		if(salePopups.size() > 0 && salePopups.get(0).isDisplayed()) {
			
		}
		
		// check to checkbox
		WebElement languageCheckbox = driver.findElement(By.id(""));
		if(!languageCheckbox.isSelected()) {
			languageCheckbox.click();
		}
	}
	
	@Test
	public void TC_02_If_Else() {
		// Nếu driver start với browser như Chrome/ Firefox/ Edge/ Safari thì dùng hàm click thông thường (builtin) của Selenium
		// Nếu driver là IE thì dùng hàm click của JavascriptExecutor
		
		System.setProperty("webdriver.ie.driver", projectPath + "\\browserDrivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		if(driver.toString().contains("internet explorer")) {
			System.out.println("Click by Javascript Executor");
		} else {
			System.out.println("Click by Selenium WebElement");
		}
	}

	@Parameters("browser")
	@Test
	public void TC_03_If_Else_If(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Please input correct the browser name");
		}
	}
}
