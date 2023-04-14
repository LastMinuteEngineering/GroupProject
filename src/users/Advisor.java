package users;

import java.util.ArrayList;

public class Advisor extends Staff {
	
	private ArrayList<UserAccount> advised;
	
	// overloaded constructors with or without middle name.
	public Advisor(String firstName, String middleName, String lastName,
			String id, Integer age, String phoneNumber) {
		super(firstName, middleName, lastName, id, age, phoneNumber);
		
		this.advised = new ArrayList<>();
	}
	
	public Advisor(String firstName, String lastName,
			String id, Integer age, String phoneNumber) {
		this(firstName,"",lastName, id, age, phoneNumber);
	}	

	@Override
	public String getAccountDetails(Boolean fullDetails) {
		
		// get user info at specified level.
		String string = super.getAccountDetails(fullDetails);
		
		if (fullDetails) {
			string = string
			+ "\tAdvisees:"
			+ "\n" + getMultiAccountDetails(advised.iterator(), false)
			+ "\n";
		}
		return string;
	}
	
	public void addAdvisee (UserAccount advisee) {
		// ensure advisee not already in list.
		if (advised.contains(advisee)) {
			System.out.println(firstName + " " + lastName + ":\t\"I'm already advising this person.\"" );
			return;
		}
		
		advised.add(advisee);	
	}
	
	public ArrayList<UserAccount> getAdviseed(){
		return advised;
	}
	

}
