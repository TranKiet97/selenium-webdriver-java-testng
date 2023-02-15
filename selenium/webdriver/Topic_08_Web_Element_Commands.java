package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element_Commands {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_WebElement(){
//		1 - Chỉ tương tác lên element 1 lần (không khai báo biến) **
		driver.findElement(By.id("send2")).click();
//		2 - Element dùng lại nhiều lần trong cùng 1 trang (khai báo biến) -> không nên define biến dùng trong nhiều trang
//		khi chuyển sang trang khác thì trạng thái đã được cập nhật
		WebElement loginButton = driver.findElement(By.id("send2"));
		loginButton.isDisplayed();
		loginButton.getCssValue("");
		
		List<WebElement> textboxes = driver.findElements(By.id(""));
//		Xóa dữ liệu trong textbox/ textarea/ dropdown (editable) **
		driver.findElement(By.id("")).clear();
		
//		Nhập dữ liệu trong textbox/ textarea/ dropdown (editable) **
		driver.findElement(By.id("")).sendKeys("");
		
		driver.findElement(By.id("")).findElement(By.className(""));
		driver.findElement(By.id("")).findElements(By.className(""));
		
//		Lấy value của attribute **
		driver.findElement(By.id("")).getAttribute("placeholder");
		
		
//		GUI -> ưu tiên thấp -> ít apply làm auto
//		Lấy thuộc tính Css của element
		driver.findElement(By.id("")).getCssValue("background-color");
		
//		Lấy kích thước của element
		Dimension loginButtonSize = driver.findElement(By.id("")).getSize();
		
//		Lấy ra tọa độ bên ngoài so với độ phân giải
		Point loginButtonLocation = driver.findElement(By.id("")).getLocation();
		
		
		Rectangle loginButtonRectangle = driver.findElement(By.id("")).getRect();
		loginButtonSize = loginButtonRectangle.getDimension();
		loginButtonLocation = loginButtonRectangle.getPoint();
		
		
		File screenshotFile = driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);
		String screenshotBase64 = driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);
	
//		Lấy ra tên thẻ khi dùng các loại locator mà không biết trước tên thẻ
		String searchTextboxTagname = driver.findElement(By.cssSelector("#search")).getTagName();
		driver.findElement(By.xpath("//" + searchTextboxTagname + "[@id='email']"));
		
//		get text của thẻ hiện tại và các thẻ con bên trong **
		driver.findElement(By.cssSelector("#search")).getText();
		
//		Áp dụng cho tất cả element
//		element có hiển thị trên màn hình không **
		driver.findElement(By.cssSelector("#search")).isDisplayed();
//		Kiểm tra element có thao tác được không (chỉ đọc)
		driver.findElement(By.cssSelector("#search")).isEnabled();
//		Áp dụng cho 3 loại element: checkbox/ radio/ dropdown (select) **
		driver.findElement(By.cssSelector("#search")).isSelected();
		
//		Chỉ apply cho form/ element trong form
		driver.findElement(By.cssSelector("#search")).submit();
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}