package javaSDET;

public class Topic_11_String {

	public static void main(String[] args) {
		// Khai báo kiểu nguyên thủy
		String s1 = "Cat";
		String s2 = s1;
		// Khởi tạo theo kiểu đối tượng
		String s3 = new String("Cat");
		
		// So sáng giá trị và vùng nhớ
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		// Chỉ so sánh giá trị
		System.out.println(s1.equals(s3));
		
		String schoolName = "Automation Testing";
		String schoolAddress = "Ho Chi Minh City";
		
		System.out.println("Độ dài của mảng: " + schoolName.length());
		
		System.out.println("Lấy ra một kí tự: " + schoolName.charAt(0));
		
		System.out.println("Nối 2 chuỗi: " + schoolName.concat(schoolAddress)); // Thường dùng phép cộng chuỗi hơn dùng hàm này
		
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tuyệt đối: " + schoolName.equals("Automation Testing"));
		
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tương đối: " + schoolName.equalsIgnoreCase("AUTOMATION Testing"));
		
		System.out.println("Có bắt đầu bằng 1 kí tự/ chuỗi kí tự: " + schoolName.startsWith("Automa"));
		
		System.out.println("Có chứa 1 kí tự/ chuỗi kí tự: " + schoolName.contains("oma"));
		
		System.out.println("Có kết thúc 1 kí tự/ chuỗi kí tự: " + schoolName.endsWith("Testing"));
		
		System.out.println("Vị trí 1 kí tự/ chuỗi kí tự trong cuỗi: " + schoolName.indexOf("Testing"));
		
		System.out.println("Tách ra 1 chuoi con: " + schoolName.substring(11, 15));
		
		String result = "Viewing 48 of 132 results";
		String results[] = result.split(" ");	// Tách cuỗi thành mảng dựa vào kí tự hoặc chuỗi kí tự
		System.out.println("Số lượt view: " + results[1]);

		System.out.println("Tách ra 1 chuoi con: " + schoolName.replace("Testing", "Framework"));
		
		// Lấy giá trị chuỗi chuyển về float và ngược lại
		String productPrice = "$1000.00";
		productPrice = productPrice.replace("$", "");
		float productPriceF = Float.parseFloat(productPrice);
		System.out.println(productPriceF);
		productPrice = String.valueOf(productPriceF);
		System.out.println(productPrice);
		
		String helloWorld = "    \n   \t   Hello World      ";
		System.out.println(helloWorld.trim());	// Loại bỏ khoảng trắng thừ ở đầu và cuối chuỗi
	}

}
