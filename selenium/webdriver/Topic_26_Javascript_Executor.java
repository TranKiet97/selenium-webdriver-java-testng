package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}

		@Test
		public void TC_01_Techpanda() {
			navigateToUrlByJS("http://live.techpanda.org/");
			sleepInSecond(6);
			
			Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
			Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
			
			hightlightElement("//a[text()='Mobile']");
			clickToElementByJS("//a[text()='Mobile']");
			sleepInSecond(6);
			
			hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
			clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
			sleepInSecond(6);
			
			Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
			
			hightlightElement("//a[text()='Customer Service']");
			clickToElementByJS("//a[text()='Customer Service']");
			sleepInSecond(6);
			
			hightlightElement("//input[@id='newsletter']");
			scrollToElementOnDown("//input[@id='newsletter']");
			sleepInSecond(6);
			
			sendkeyToElementByJS("//input[@id='newsletter']", "Autotest" + randomNumber() + "@gmail.net");
			hightlightElement("//button[@title='Subscribe']");
			clickToElementByJS("//button[@title='Subscribe']");
			sleepInSecond(6);
			
			Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
			
			navigateToUrlByJS("http://demo.guru99.com/v4/");
			sleepInSecond(6);
			Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
		}
	
		public Object executeForBrowser(String javaScript) {
			return jsExecutor.executeScript(javaScript);
		}
	
		public String getInnerText() {
			return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
		}
	
		public boolean areExpectedTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor
					.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
			return textActual.equals(textExpected);
		}
	
		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}
	
		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}
	
		public void hightlightElement(String locator) {
			WebElement element = getElement(locator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,"border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
		}
	
		public void clickToElementByJS(String locator) {
			jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		}
	
		public void scrollToElementOnTop(String locator) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
		}
	
		public void scrollToElementOnDown(String locator) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
		}
	
		public void sendkeyToElementByJS(String locator, String value) {
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
		}
	
		public void removeAttributeInDOM(String locator, String attributeRemove) {
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
		}
	
		public String getElementValidationMessage(String locator) {
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
		}
	
		public boolean isImageLoaded(String locator) {
			boolean status = (boolean) jsExecutor.executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
					getElement(locator));
			return status;
		}
	
		public WebElement getElement(String locator) {
			return driver.findElement(By.xpath(locator));
		}
	
		public void sleepInSecond(long timeout) {
			try {
				Thread.sleep(timeout * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public int randomNumber() {
			return new Random().nextInt(9999);
		}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}