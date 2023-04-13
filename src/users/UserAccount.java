package users;

import java.util.ArrayList;
import java.util.Iterator;

import common.types.CustomEnums.*;
import data.entry.Entry;
import data.entry.EntryFactory;
import data.storage.Storage;
import data.course.Course;

public abstract class UserAccount {
	
	protected String firstName, middleName, lastName;
	protected String id;
	protected Integer age;
	protected String phoneNumber;
	protected AccessLevel accessLevel;
	// avoid having to import array list unnecessarily.
	protected ArrayList<String> entryIds;
	
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
	
	public abstract String getAccountDetails(Boolean fullDetails);
	
	public void displayAccountDetails(Boolean fullDetails) {
		String string = getAccountDetails(fullDetails);
		System.out.println(string);
	};
	
	protected String getBaseInfo() {
		String string = getQuickInfo()
				+ "\tPhone Number: \t" + phoneNumber
				+ "\n\tAccess Level: \t" + accessLevel.description
				+ "\n";
				
		return string;
	};
	
	protected String getQuickInfo() {
		String string = ""
				+ "Name: \t" + firstName + " " 
					+ middleName.length() == null ?  lastName : 
						middleName + " " + lastName 
				+ "\n\tId: \t" + id
				+ "\n";
				
		return string;
	};

	
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
		System.out.println(firstName+"'s Entries \n\t");
		
		Iterator<String> entryIdIterator = entryIds.iterator();
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
//	Utility functions
//	
	protected String getMultiAccountDetails(Iterator<UserAccount> accountIterator, Boolean fullDetails) {
		
		// Handle case: no accounts in list.
		if (!accountIterator.hasNext()) {
			return "None";
		}
		
		String string = "";
		
		// cycle through passed accounts.
		while(accountIterator.hasNext()) {
			UserAccount account = accountIterator.next();
			
			// append user details at specified level.
			string = string
				+ "\t" + account.getAccountDetails(fullDetails);
		}
		
		return string;
		
	}
	
	protected String getCourseLoadSummary(Iterator<Course> courseIterator, Boolean fullDetails) {
		
		// Handle case: no courses in list.
		if (!courseIterator.hasNext()) {
			return "None";
		}
		
		String string = "";
				
		// cycle through passed courses.
		while(courseIterator.hasNext()) {
			Course course = courseIterator.next();
			
			// append user details at specified level.
			string = string
				+ "\t" + course.getDetails(fullDetails);
		}
		
		return string;
				
	}
	
	
	
}
