package javaSDET;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_02_Data_Type {
//	Primitive Type
	byte bNumber = 1;
	short sNumber = 1500;
	int	iNumber = 15000;
	long lNumber = 150000;
	float fNumber = 15.98f;
	double	dNumber = 15.98;
	
	char	cChar = '1';
	
	boolean	bStatus = true;
	
//	Reference Type	-> có function đi kèm
//	1. String
	String address = "Ho Chi Minh";
//	2. Array
	String[]	studentAddress = {address, "Ha Noi", "Da Nang"};
	Integer[]	studentNumber = {15, 20, 50};
//	3. Class (topic là biến đại diện cho class Topic_02_Data_Type)
	Topic_02_Data_Type topic; 
//	4. Interface (driver là biến đại diện cho WebDriver)
	WebDriver driver;
//	5. Object
	Object	object;
//	6. Collection: List (lưu trùng)/ Set (không lưu trùng)/ Queue/ Map
	List<WebElement>	homePageLinks = driver.findElements(By.tagName("a"));
	Set<String>	allWindows = driver.getWindowHandles();

	int Number = 20; 
	public void clickToElement() {
		address.trim();
		studentAddress.clone();
		driver.getCurrentUrl();
		homePageLinks.size();
	}
	
	public static void main(String[] args) {
//		Kiểu tham trị (cách lưu trữ dữ liệu của Primitive Type)
		int x = 5;
		int y = x;
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		y = 10;
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
//		Kiểu tham chiếu (cách lưu trữ dữ liệu của Reference Type)
		Topic_02_Data_Type a = new Topic_02_Data_Type();	// Khởi tạo 1 vùng nhớ
		Topic_02_Data_Type b = a;	// class b trỏ vào vùng nhớ của a						
		System.out.println("Number = " + a.Number);
		System.out.println("Number = " + b.Number);
		
		b.Number = 15;
		System.out.println("Number = " + a.Number);
		System.out.println("Number = " + b.Number);
	}

}
