package javaOOP;

public class Topic_03_Non_Access_Modifier {
	// Static: Variable/ Method
	static String browserName = "Chrome";
	final static String REGISTER_BUTTON = "";
	
	// Non Static
	protected String serverName = "Testing";
	
	// Constant
	final String colorCar = "red";

	public static void main(String[] args) {
		System.out.println(browserName);
		sendKeyToElement();
		
		Topic_03_Non_Access_Modifier object = new Topic_03_Non_Access_Modifier();
		System.out.println(object.serverName);
		object.clickToElement();
		System.out.println(object.colorCar);
	}
	
	public void clickToElement () {
		
	}
	
	public static void sendKeyToElement() {
		
	}
	
}
