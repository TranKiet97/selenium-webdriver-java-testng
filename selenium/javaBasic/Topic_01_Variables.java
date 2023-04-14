package javaBasic;

import java.util.Scanner;

public class Topic_01_Variables {
	
	static	int studentNumber;
	static	boolean	statusValue;
	static final String BROWSER_NAME = "Chrome";
	static	int	studentPrice;
	
	protected	String	studentName = "Tran Anh Kiet";
	
	public static void main(String[] args) {
		int studentPrice = 5;
		
		System.out.println(studentPrice);
		
		System.out.println(studentNumber);
		System.out.println(statusValue);
		System.out.println(Topic_01_Variables.BROWSER_NAME);
		System.out.println(Topic_01_Variables.statusValue);
		System.out.println(Topic_01_Variables.studentPrice);
		
		Topic_01_Variables topic_1 = new Topic_01_Variables();
		System.out.println(topic_1.studentName);
		
		Scanner	scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println(name);
		System.out.print(name);

	}
	
	public String getStudentName() {
		return this.studentName;
	}
	
	public void setStudentName(String stdName) {
		this.studentName = stdName;
	}

}
