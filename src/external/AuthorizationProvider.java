package external;

import java.util.HashMap;
import java.util.Scanner;

import users.UserAccount;

public class AuthorizationProvider {
	
	private static AuthorizationProvider instance;
	HashMap<String, UserAccount> users;
	Scanner input;

	public AuthorizationProvider() {
		users = Registrar.getUsers();
		input = new Scanner(System.in);
	}
	
	public static AuthorizationProvider getInstance() {
		if(instance == null) {
			instance = new AuthorizationProvider();
		}
		return instance;
	}
	
	public UserAccount login() {
		
		// simulate login
		System.out.println("Username:");
		String response = input.nextLine();
		
		System.out.println("Password:");
		String p = input.nextLine();	// not used for demo
		
		return users.getOrDefault(response, null);
		
	}
	
}
