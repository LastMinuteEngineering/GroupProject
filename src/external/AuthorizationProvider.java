package external;

import java.util.HashMap;
import java.util.Scanner;

import users.UserAccount;

public class AuthorizationProvider {

	private Registrar registrar;
	private Scanner input;
	private static AuthorizationProvider instance;

	public AuthorizationProvider() {
		this.registrar = Registrar.getInstance();
		this.input = new Scanner(System.in);
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
		
		return registrar.findUser(response);
		
	}
	
}
