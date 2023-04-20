package demo;

import data.storage.Storage;
import external.AuthorizationProvider;
import external.Registrar;
import users.UserAccount;

public class Demo {

	public static void main(String[] args) {
		
		Storage.createDatabase();
		
		Registrar.loadCourses();
		
		// spoof login
		AuthorizationProvider authprovider = AuthorizationProvider.getInstance();
		UserAccount user = authprovider.login();
		user.displayAccountDetails(true);
		
		// TODO : demo use case
		
	}
}
