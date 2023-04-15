package javaOOP;

public class Topic_02_Variable_Property_Method {
	// Access Modifier
	// Data Type
	// Variable Name
	// Variable Value
	
	// Biến toàn cục
	private String studentName = "Automation FC";	
	// Access Modifier: Default	
	int studentID = 123123;			
	// Dùng và gán lại được
	public static String studentAddress = "Ho Chi Minh";
	// Dùng trong phạm vi class
	private static String studentPhone = "0987654321";
	// Không cho phép gán lại (override)
	final String country = "Viet Nam";
	// Hằng số và có thể truy cập rộng rãi cho tất cả instance/ thread
	static final float PI_NUMBER = 3.14f;
	// static Method
	public static void main(String[] args) {	

	}
	// non-static Method
	public void display() {	
		// Biến cục bộ
		String studentName = "Automation FC";	
	}
	// Constructor
	protected Topic_02_Variable_Property_Method() {	
		// Biến cục bộ
		String studentName = "Automation FC";	
	}
}
