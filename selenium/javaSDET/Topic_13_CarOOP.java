package javaSDET;

public class Topic_13_CarOOP {
	private String carCompany;
	private String carType;
	private String fuelType;
	private Float mileAge;
	private Double carPrice;
	
	// Tạo constructor
	protected Topic_13_CarOOP(String carCompany, String carType, String fuelType, Float mileAge, Double carPrice) {
		this.carCompany = carCompany;
		this.carType = carType;
		this.fuelType = fuelType;
		this.mileAge = mileAge;
		this.carPrice = carPrice;
	}
	
	// Tạo getter/ setter
	protected String getCarCompany() {
		return carCompany;
	}
	protected void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
	protected String getCarType() {
		return carType;
	}
	protected void setCarType(String carType) {
		this.carType = carType;
	}
	protected String getFuelType() {
		return fuelType;
	}
	protected void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	protected Float getMileAge() {
		return mileAge;
	}
	protected void setMileAge(Float mileAge) {
		this.mileAge = mileAge;
	}
	protected Double getCarPrice() {
		return carPrice;
	}
	protected void setCarPrice(Double carPrice) {
		this.carPrice = carPrice;
	}
	protected void showCarInfor () {
		System.out.println("Car Company: " + getCarCompany());
		System.out.println("Car Type: " + getCarType());
		System.out.println("Car Fuel Type: " + getFuelType());
		System.out.println("Car Mile Age: " + getMileAge());
		System.out.println("Car Price: " + getCarPrice());
	}
	
	public static void main(String[] args) {
		Topic_13_CarOOP honda = new Topic_13_CarOOP("Honda", "Lead", "Petrol", 150f, 50000000d);
		honda.showCarInfor();
		
		Topic_13_CarOOP toyota = new Topic_13_CarOOP("Toyota", "Camry", "Diasel", 250f, 1250000000d);
		toyota.showCarInfor();
	}
}
