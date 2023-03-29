package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Shopper_01_Manage_Product {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
	}

	@Test(groups = {"admin", "product"})
	public void Product_01_Create_Product() {
		
	}
	
	@Test(groups = {"admin", "product"})
	public void Product_02_View_Product() {
		
	}
	
	@Test(groups = {"admin", "product"})
	public void Product_03_Update_Product() {
		
	}
	
	@Test(groups = {"admin", "product"})
	public void Product_04_Delete_Product() {
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
	
}
