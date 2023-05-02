package demo;

import common.types.CustomEnums.EntryType;
import data.storage.Storage;
import external.AuthorizationProvider;
import users.UserAccount;
import data.entry.Entry;
import data.entry.MentalHealthSurvey;

public class Demo {

	public static void main(String[] args) {
		
		
		Storage.createDatabase();
		
		// spoof call to auth service to load user data after login
		AuthorizationProvider authprovider = AuthorizationProvider.getInstance();
		UserAccount user1 = authprovider.login();
		
		
		user1.displayAccountDetails(true);
		
		user1.createEntry(EntryType.MentalHealthSurvey);
		
		
		// add two responses
		Storage db = Storage.getInstance();
		Entry survey = db.getEntry("0");
		
		System.out.println("\nAdding Two Reponses\n");
		((MentalHealthSurvey) survey).respond();
		System.out.println("\n");
		((MentalHealthSurvey) survey).respond();
		
		
		System.out.println("\nDisplay all Survey details and Responses\n");
		survey.displayDetails(true);
		
		
	}
}
