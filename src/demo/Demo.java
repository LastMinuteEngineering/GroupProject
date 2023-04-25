package demo;

import common.types.CustomEnums.EntryType;
import data.storage.Storage;
import external.AuthorizationProvider;
import external.Registrar;
import users.UserAccount;
import data.entry.Entry;
import data.entry.MentalHealthSurvey;

public class Demo {

	public static void main(String[] args) {
		
		
		Storage.createDatabase();
		
		// spoof api call to load course
		Registrar.loadCourses();
		
		// spoof call to auth service to load user data after login
		AuthorizationProvider authprovider = AuthorizationProvider.getInstance();
		UserAccount user1 = authprovider.login();
		
		
		user1.displayAccountDetails(true);
		
		user1.createEntry(EntryType.MentalHealthSurvey);
		
		
		Storage db = Storage.getInstance();
		Entry survey = db.getEntry("0");
		
		((MentalHealthSurvey) survey).respond();
		((MentalHealthSurvey) survey).respond();
		
		survey.displayDetails(true);
		
		
	}
}
