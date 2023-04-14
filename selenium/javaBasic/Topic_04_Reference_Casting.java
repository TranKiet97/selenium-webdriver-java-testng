package javaBasic;

public class Topic_04_Reference_Casting {
	public String studentName;
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void showStudentName() {
		System.out.println("Student name: " + studentName);
	}
	
	public static void main(String[] args) {
		Topic_04_Reference_Casting firstStudent = new Topic_04_Reference_Casting();
		Topic_04_Reference_Casting secondStudent = new Topic_04_Reference_Casting();
		
		firstStudent.setStudentName("Nguyen Van A");
		secondStudent.setStudentName("Le Van B");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
		// Casting
		firstStudent = secondStudent;
		secondStudent.setStudentName("Bui Van C");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
	}

}
