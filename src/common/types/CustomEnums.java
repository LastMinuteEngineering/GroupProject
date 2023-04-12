package common.types;

public class CustomEnums {

	public enum EntryType{
		HealthIssueReport("Health Issue Report"),
		DiscussionPost("Discussion Post"),
		SocialEvent("Social Event"),
		Reply("Reply"),
		MentalHealthSupportRequest("Mental Health Support Request");
		
		public final String description;
		
		// assign string version of Entry Type for printing.
		private EntryType(String description) {
			this.description = description;
		}

	}
	
	public enum AccessLevel{
		StandardAccess("Standard Acccess"),
		ElevatedAccess("Elevated Access"),
		AdminAccess("Admin Access");
		
		public final String description;
		
		// assign string version of Access Level for printing.
		private AccessLevel(String description) {
			this.description = description;
		}
	}
	
	public enum EntryStatus{
		Open("Opened"),
		InProgress("In Progress"),
		Completed("Completed"),
		Closed("Closed"),
		Upcoming("Upcoming"),
		Passed("Passed");
		
		public final String description;
		
		// assign string version of Status for printing.
		private EntryStatus(String description) {
			this.description = description;
		}
	}
	
	public enum Urgency{
		Low("Low Priority"),
		Medium("Medium Priority"),
		High("High Priority"),
		Critical("Critical Priority");
		
		public final String description;
		
		// assign string version of Status for printing.
		private Urgency(String description) {
			this.description = description;
		}
	}
	
	
}
