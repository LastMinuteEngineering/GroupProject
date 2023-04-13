package users;

import java.util.ArrayList;

public class Staff extends UserAccount {
	
	private UserAccount supervisor;
	private ArrayList<UserAccount> subordinates;
	
	// overloaded constructors with or without middle name.
	public Staff(String firstName, String middleName, String lastName,
			String id, Integer age, String phoneNumber) {
		super(firstName, middleName, lastName, id, age, phoneNumber);
		
		this.subordinates = new ArrayList<>();
	}
	
	public Staff(String firstName, String lastName,
			String id, Integer age, String phoneNumber) {
		this(firstName,"",lastName, id, age, phoneNumber);
	}	
	

	@Override
	public String getAccountDetails(Boolean fullDetails) {
		// get user info at specified level.
		String string = fullDetails? getQuickInfo() : getBaseInfo();
		
		if (fullDetails) {
			string = string
			+ "\tSupervisor:\t\n" + (supervisor == null ? 
					"\t" + "-".repeat(10)
					+ "\n\tNone\n": 
						"\t" + "-".repeat(10) +
						"\n\t" +supervisor.getAccountDetails(false))
			+ "\tSubordinates:"
			+ "\n" + getMultiAccountDetails(subordinates.iterator(), false)
			+ "\n";
		}
		return string;
	}
	
	public ArrayList<UserAccount> getSubordinates(){
		return subordinates;
	}
	
	public UserAccount getSupervisor() {
		return supervisor != null ? supervisor : null;
	}
	
	public void changeSupervisor(UserAccount supervisor) {
		// ensure passed supervisor is not the one already set.
		if (this.supervisor == supervisor) {
			System.out.println(firstName + " " + lastName + ":\t\"This person is already my supervisor.\"" );
			return;
		}
		this.supervisor = supervisor;
	}
	
	public void addSubordinate(UserAccount subordinate) {
		// ensure subordinate not already in list.
		if (subordinates.contains(subordinate)) {
			System.out.println(firstName + " " + lastName + ":\t\"This person is already one of my subordinates.\"" );
			return;
		}
		subordinates.add(subordinate);
	}

}
