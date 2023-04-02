package testng;
import org.testng.annotations.Test;

public class Topic_06_Depend {

	@Test
	public void Product_01_Create_Product() {
		
	}
	
	@Test(dependsOnMethods = "Product_01_Create_Product")
	public void Product_02_Read_Product() {
		
	}
	
	@Test(dependsOnMethods = "Product_02_Read_Product")
	public void Product_03_Update_Product() {
		
	}
	
	@Test(dependsOnMethods = "Product_03_Update_Product")
	public void Product_04_Delete_Product() {
		
	}
	
}
