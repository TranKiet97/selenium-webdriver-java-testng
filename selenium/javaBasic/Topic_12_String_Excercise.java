package javaBasic;

public class Topic_12_String_Excercise {

	public static void main(String[] args) {
		String courseName = "Automation Testing 2023";
		char courseNameArray[] = courseName.toCharArray();
		int countUpper = 0;
		int countNum = 0;
		for (char character : courseNameArray) {
			if(character >= 'A' && character <= 'Z') {
				countUpper++;
			} else if(character >= '0' && character <= '9'){
				countNum++;
			}
		}
		System.out.println("Số kí tự in hoa: " + countUpper);
		System.out.println("Số kí tự là chữ số: " + countNum);
	}

}
