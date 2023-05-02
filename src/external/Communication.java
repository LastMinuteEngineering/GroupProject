package external;

public abstract class Communication {
	
	String departmentName;
	String phoneNumber;
	String address;
	
	public Communication(String name, String number, String address) {
		this.departmentName = name;
		this.phoneNumber = number;
		this.address = address;
	}
	
	
	// utilize mobile phone services to call first responders
	public void contactEmergencyServices() {
		call();
	};
	
	
	private void call() {
		// use cell services
	}
	
}
