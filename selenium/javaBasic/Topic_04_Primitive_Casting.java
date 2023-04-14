package javaBasic;

public class Topic_04_Primitive_Casting {

	public static void main(String[] args) {
		// Ngầm định -> không mất dữ liệu
		//		byte bNumber = 126;
		//		System.out.println(bNumber);
		//		
		//		short sNumber = bNumber;
		//		System.out.println(sNumber);
		//		
		//		int iNumber = sNumber;
		//		System.out.println(iNumber);
		//		
		//		long lNumber = sNumber;
		//		System.out.println(lNumber);
		//		
		//		float fNumber = lNumber;
		//		System.out.println(fNumber);
		//		
		//		double dNumber = fNumber;
		//		System.out.println(dNumber);
		
		// Tường minh
		double dNumber = 6543217898768936672d;
		System.out.println(dNumber);
		
		float fNumber = (float) dNumber;
		System.out.println(fNumber);
		
		int iNumber = (int) fNumber;
		System.out.println(iNumber);
		
	}

}
