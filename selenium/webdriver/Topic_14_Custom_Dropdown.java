package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String speedItem;

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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		speedItem = "Medium";
	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// Chọn item cho speed dropdown
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", speedItem);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), speedItem);
	}
	
	// @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropdown("i.dropdown.icon", "div.item>span", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Stevie Feliciano");
		
		selectItemInDropdown("i.dropdown.icon", "div.item>span", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Jenny Hess");
		
		selectItemInDropdown("i.dropdown.icon", "div.item>span", "Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Christian");	
	}
	
	// @Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");	
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterAndSelectItemInDropdown("input.search", "div.item>span", "Argentina");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Argentina");
		
		enterAndSelectItemInDropdown("input.search", "div.item>span", "Aland Islands");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Aland Islands");
		
		enterAndSelectItemInDropdown("input.search", "div.item>span", "Belarus");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Belarus");
	}
	
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		//	Locator phải lấy để đại diện cho tất cả item	
		//	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		//	List<WebElement>	speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		List<WebElement>	speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		for (WebElement speedItem : speedDropdownItems) {
			if(speedItem.getText().trim().equals(expectedTextItem)) {
				sleepInSecond(1);
				speedItem.click();
				break;
			}
		}
	}
	
	public void enterAndSelectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(textboxCss)).clear();;
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		//	Locator phải lấy để đại diện cho tất cả item	
		//	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		//	List<WebElement>	speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		List<WebElement>	speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		for (WebElement speedItem : speedDropdownItems) {
			if(speedItem.getText().trim().equals(expectedTextItem)) {
				sleepInSecond(1);
				speedItem.click();
				break;
			}
		}
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