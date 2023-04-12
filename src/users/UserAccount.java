package users;

import java.util.ArrayList;
import java.util.Iterator;

import common.types.CustomEnums.*;
import data.entry.Entry;
import data.entry.EntryFactory;
import data.storage.Storage;

public abstract class UserAccount {
	
	protected Integer age;
	protected String firstName, middleName, lastName;
	protected String id;
	protected String phoneNumber;
	protected AccessLevel accessLevel = AccessLevel.StandardAccess;
	protected ArrayList<String> entryIds;
	
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
				+ "\n\tPhone Number: \t" + phoneNumber
				+ "\n\tAccess Level: \t" + accessLevel.description
				;
				
		return string;
	};
	
	protected String getQuickInfo() {
		String string = ""
				+ "Name: \t" + firstName + " " 
					+ middleName.length() == null ?  lastName : 
						middleName + " " + lastName 
				+ "\n\tId: \t" + id
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
		
		modifyEntry(entry);	
		// add to list.
		entryIds.add(entry.getId());
	};
	
	public void displayAllEntries() {
		// display all entries with without all details.
		System.out.println(firstName+"'s Entries \n");
		
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
	
	
	
}
