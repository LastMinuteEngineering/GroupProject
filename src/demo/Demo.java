package demo;

import data.storage.Storage;
import external.AuthorizationProvider;
import external.Registrar;
import users.UserAccount;

public class Demo {

	public static void main(String[] args) {
		
		
		Storage.createDatabase();
		
		// spoof api call to load course
		Registrar.loadCourses();
		
		// spoof call to auth service to load user data after login
		AuthorizationProvider authprovider = AuthorizationProvider.getInstance();
		UserAccount user = authprovider.login();
		
		
		user.displayAccountDetails(true);
		
		// TODO : demo use case
	}
}
