package javaBasic;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_07_For_ForEach {
	WebDriver driver;
	
	@Test
	public void TC_01_For() {
		for (int i = 0; i <= 9; i++) {
			System.out.println(i);
		}
	}
	
	@Test
	public void TC_02_ForEach() {
		String[] cityName = {"Ha Noi", "Ho Chi Minh", "Da Nang", "Can Tho"};
		
		List<String> cityAddress = new ArrayList<String>();
		
		System.out.println(cityAddress.size());
		
		for (String city : cityName) {
			cityAddress.add(city);
		}
		
		System.out.println(cityAddress.size());
		
		for (String cityAdd : cityAddress) {
			System.out.println(cityAdd);
		}
	}
}
