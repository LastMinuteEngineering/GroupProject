package users;

import java.util.ArrayList;

import common.Types.*;
import data.entry.Entry;
import data.entry.EntryFactory;
import data.storage.Storage;

public abstract class UserAccount {
	
	protected Integer age;
	protected String firstName, middleName, lastName;
	protected String id;
	protected String phoneNumber;
	protected AccessLevel accessLevel = AccessLevel.StandardAccess;
	protected ArrayList<String> entries;
	
//
//	User Account functions
//  
	protected String main_divider = "-".repeat(50);
	protected String section_divider = "-".repeat(25);
	public abstract void displayAccountDetails();
	
	protected String getBaseInfo() {
		String string = main_divider
				+ "\nName: \t" + firstName + " " 
					+ middleName.length() == null ?  lastName : 
						middleName + " " + lastName 
				+ "\nId:\t" + id
				+ "\nPhone Number:\t" + phoneNumber
				+ "\nAccess Level:\t" + accessLevel.description
				;
				
		return string;
	};
	
	protected String getQuickInfo() {
		String string = main_divider
				+ "\nName: \t" + firstName + " " 
					+ middleName.length() == null ?  lastName : 
						middleName + " " + lastName 
				+ "\nId:\t" + id
				+ "\nAccess Level:\t" + accessLevel.description
				;
				
		return string;
	};

	
//	
//	Entry functions
//
	public void createEntry(EntryType type) {
		// create entry
		EntryFactory factory = EntryFactory.getInstance();
		Entry entry = factory.createEntry(type);
		
		// add to list.
		entries.add(entry.getId());
		modifyEntry(entry);	
	};
	
	public void displayAllEntries() {
		// display all entries with without all details.
		System.out.println(firstName+"'s Entries \n");
		for (int i = 0; i < entries.size(); i++) {
			String entryId = entries.get(i);
			getEntry(entryId).displayDetails(false);
			
		}
	};
	
	public void displayEntry(String entryId) {
		// display entry in full details.
		getEntry(entryId).displayDetails(true);
			
	};
	
	public void modifyEntry(String entryId) {
		Entry entry = getEntry(entryId);
		modifyEntry(entry);
	};
	
	protected Entry getEntry(String entryId) {
		return Storage.getEntry(entryId);
	};
	
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
	
	
	
}
