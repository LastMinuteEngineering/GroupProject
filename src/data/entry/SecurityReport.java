package data.entry;

import java.util.Scanner;

public class SecurityReport extends HealthIssueReport {

	public SecurityReport(String id, String creation) {
		super(id, creation);
	}
	
	public static Boolean isUrgent() {
		// prompt user for status.
		Scanner in = new Scanner(System.in);
		
		System.out.println("Is this an emergency? (Enter yes or no)");
		String response = in.nextLine();
		in.close();
		
		// user may not be able to type full word. Accept any word starting with Y.
		if ( response.toLowerCase().charAt(0) == 'y') {
			return true;
		}
		
		return false;
	}
	
}
