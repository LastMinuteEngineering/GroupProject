package external;

public class EmergencyCommunication {
	
	// utilize mobile phone services to call first responders
	public static void contactEmergencyServices() {
		notifyCampusSecurity();
		callLawEnforcement();
	};
	
	
	private static void callLawEnforcement() {
		// use cell services to call local law enforcement.
	}
	
	private static void notifyCampusSecurity() {
		// send device location and urgent warning to Campus Security.
	}
	
}
