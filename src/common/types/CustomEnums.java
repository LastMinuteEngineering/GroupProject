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
		
		public static EntryType toEnum(String description) {
			switch (description) {
			case "Health Issue Report" : return EntryType.HealthIssueReport;
			case "Social Event" : return EntryType.SocialEvent;			
			case "Discussion Post" : return EntryType.DiscussionPost;
			case "Reply" : return EntryType.Reply;
			case "Mental Health Support Request" : return EntryType.MentalHealthSupportRequest;			
			}
			
			// return Reply as default
			return EntryType.Reply;
		}

	}
	
	public enum AccessLevel{
		Standard("Standard Acccess"),
		Elevated("Elevated Access"),
		Admin("Admin Access");
		
		public final String description;
		
		// assign string version of Access Level for printing.
		private AccessLevel(String description) {
			this.description = description;
		}
		
		public static AccessLevel toEnum(String description) {
			switch (description) {
			case "Standard Access" : return AccessLevel.Standard;
			case "Elevated Access" : return AccessLevel.Elevated;
			case "Admin Access" : return AccessLevel.Admin;
			}
			
			// return Standard as default
			return AccessLevel.Standard;
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
		
		public static EntryStatus toEnum(String description) {
			switch (description) {
			case "Opened" : return EntryStatus.Open;
			case "In Progress" : return EntryStatus.InProgress;
			case "Completed" : return EntryStatus.Completed;
			case "Closed" : return EntryStatus.Closed;
			case "Upcoming" : return EntryStatus.Upcoming;
			case "Passed" : return EntryStatus.Passed;
			}
			
			// return Open as default
			return EntryStatus.Open;
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
		
		public static Urgency toEnum(String description) {
			switch (description) {
			case "Low Priority" : return Urgency.Low;
			case "Medium Priority" : return Urgency.Medium;
			case "High Priority" : return Urgency.High;
			case "Critical Priority" : return Urgency.Critical;
			}
			
			// return Low as default
			return Urgency.Low;
		}
	}
	
	
}
