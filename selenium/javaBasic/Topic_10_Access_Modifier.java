package javaBasic;

public class Topic_10_Access_Modifier {

	public static void main(String[] args) {
		Topic_09_Getter_Setter topic_09 = new Topic_09_Getter_Setter();
		topic_09.setCarName("Toyota");
		String newCar = topic_09.getCarName();
		System.out.println(newCar);
	}

}
