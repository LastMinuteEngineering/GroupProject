package users;

import java.util.ArrayList;
import java.util.Iterator;

import common.types.CustomEnums.*;
import data.entry.Entry;
import data.entry.EntryFactory;
import data.storage.Storage;
import data.course.Course;
import users.interactions.FriendManager;

public abstract class UserAccount {
	
	protected String firstName, middleName, lastName;
	protected String id;
	protected Integer age;
	protected String phoneNumber;
	protected AccessLevel accessLevel;
	// avoid having to import array list unnecessarily.
	protected ArrayList<String> entryIds;
	protected FriendManager friends;
	
	// common among all user accounts.
	public UserAccount(String firstName, String middleName, String lastName,
			String id, Integer age, String phoneNumber) {
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.id = id;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.accessLevel = AccessLevel.Standard;
		this.entryIds = new ArrayList<>();
	}
	
//
//	User Account functions
//  
	public String getName() {
		String string = firstName + " " 
				+ ( middleName.length() == 0 ?  lastName : 
					middleName + " " + lastName) ;
		return string;
	}
	
	public abstract String getAccountDetails(Boolean fullDetails);
	
	public void displayAccountDetails(Boolean fullDetails) {
		String string = getAccountDetails(fullDetails);
		System.out.println(string);
	};
	
	protected String getBaseInfo() {
		String string = getQuickInfo()
				+ "\tAge: \t" + age
				+ "\n\tPhone Number: \t" + phoneNumber
				+ "\n\tAccess Level: \t" + accessLevel.description
				+ "\n";
				
		return string;
	};
	
	protected String getQuickInfo() {
		String string = ""
				+ "Name: \t" + getName() 
				+ "\n\tId: \t" + id
				+ "\n";
				
		return string;
	};
	
	public void displayFriends() {
		System.out.println("Friends:" 
							+ "\n" + getMultiAccountDetails(friends.getFriends())
							);
	}
	

	
//	
//	Entry functions
//
	public void createEntry(EntryType type) {
		// create entry
		EntryFactory factory = EntryFactory.getInstance();
		Entry entry = factory.createEntry(type);
		
		modifyEntry(entry);	
		// add to list.
		entryIds.add(entry.getId());
	};
	
	public void displayAllEntries() {
		// display all entries with without all details.
		System.out.println(firstName+"'s Entries :\n"
				+ "-".repeat(10));
				
		Iterator<String> entryIdIterator = entryIds.iterator();
		
		// handle case : no entries.
		if (!entryIdIterator.hasNext()) {
			System.out.println("None");
		}
		
		while(entryIdIterator.hasNext()) {
			String entryId = entryIdIterator.next();
			getEntry(entryId).displayDetails(false);
		}
	};
	
	public void displayEntry(String entryId) {
		// display entry in full details.
		getEntry(entryId).displayDetails(true);
			
	};
	
	protected Entry getEntry(String entryId) {
		Storage db = Storage.getInstance();
		return db.getEntry(entryId);
	};
	
	public void modifyEntry(String entryId) {
		Entry entry = getEntry(entryId);
		modifyEntry(entry);
	};
	
	// overload modifyEntry when passed entry object rather than entryId
	protected void modifyEntry(Entry entry) {
		// allow entry to facilitate input and data modification.
		entry.promptForDetails();
	};
	
//	
//	Access control functions
//
	public void changeAccessLevel(AccessLevel level) {
		accessLevel = level;
	};
	
	public Boolean confirmAccess(AccessLevel minAccess) {
		return accessLevel.compareTo(minAccess) >= 0;
	};
	
//
// User -> User Interactions
//
	public void sendFriendRequest(String toAdd, String msg) {
		friends.sendRequest(toAdd, msg, this);
	}
	public void addFriend(UserAccount friend) {
		friends.addFriend(friend);
	}
	
	public void receiveFriendRequest(UserAccount sender, String msg) {
		friends.addRequest(sender, msg);
	}
	
	public void handleFriendRequests() {
		friends.handleFriendRequests(this);
	}
	
	public void removeFriendRequest(UserAccount user) {
		friends.removePendingRequest(user);
	}
	
	
// 
//	Utility functions
//	
	protected String getMultiAccountDetails(Iterator<UserAccount> accountIterator) {
		String string = "-".repeat(10);
		
		// Handle case: no accounts in list.
		if (!accountIterator.hasNext()) {
			return string
					+ "\n\tNone";
		}
		
		
		// cycle through passed accounts.
		while(accountIterator.hasNext()) {
			UserAccount account = accountIterator.next();
			
			// append user name.
			string = string
				+ "\n\t" + account.getName();
		}
		
		return string;
	}
	
	protected String getMultiAccountDetails(Iterator<UserAccount> accountIterator, Boolean fullDetails) {
		
		// Handle case: no accounts in list.
		if (!accountIterator.hasNext()) {
			return "\t" + "-".repeat(10)
					+ "\n\tNone";
		}
		
		String string = "";
		
		// cycle through passed accounts.
		while(accountIterator.hasNext()) {
			UserAccount account = accountIterator.next();
			
			// append user details at specified level.
			string = string
				+ "\t" + "-".repeat(10)
				+ "\n\t" + account.getAccountDetails(fullDetails);
		}
		
		// return without last newline
		return string.substring(0, string.length()-1);
		
	}
	
	protected String getCourseLoadSummary(Iterator<Course> courseIterator, Boolean fullDetails) {
		
		// Handle case: no courses in list.
		if (!courseIterator.hasNext()) {
			return "\t" + "-".repeat(10)
					+ "\n\tNone\n";
		}
		
		String string = "";
				
		// cycle through passed courses.
		while(courseIterator.hasNext()) {
			Course course = courseIterator.next();
			
			// append user details at specified level.
			string = string
				+ "\t" + "-".repeat(10)
				+ "\n\t" + course.getDetails(fullDetails);
		}
		
		return string;
				
	}
	
	
	
}
