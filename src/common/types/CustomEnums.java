package common.types;

public class CustomEnums {
	
	private static String module = "CustomEnums:\t";

	public enum EntryType{
		HealthIssueReport("Health Issue Report"),
		DiscussionPost("Discussion Post"),
		SocialEvent("Social Event"),
		Reply("Reply"),
		MentalHealthSupportRequest("Mental Health Support Request"),
		MentalHealthSurvey("Mental Health Survey"),
		Invalid("Invalid");
		
		public final String description;
		
		// assign string version of Entry Type for printing.
		private EntryType(String description) {
			this.description = description;
		}
		
		public static EntryType toEnum(String description) {
			EntryType type;
			
			switch (description) {
			case "Health Issue Report" : 			type = EntryType.HealthIssueReport;
													break;
													
			case "Social Event" : 					type = EntryType.SocialEvent;	
													break;
			
			case "Discussion Post" : 				type = EntryType.DiscussionPost;
													break;

			case "Reply" : 							type =  EntryType.Reply;
													break;
													
			case "Mental Health Support Request" : 	type =  EntryType.MentalHealthSupportRequest;	
													break;
			
			case "Mental Health Survey" : 			type =  EntryType.MentalHealthSurvey;	
													break;
													
			default :								type = EntryType.Invalid;
													System.out.println(module + "Invalid identifier.");
													break;
			}
			
			return type;
		}

	}
	
	public enum AccessLevel{
		Standard("Standard Acccess"),
		Elevated("Elevated Access"),
		Admin("Admin Access"),
		Invalid("Invalid");
		
		public final String description;
		
		// assign string version of Access Level for printing.
		private AccessLevel(String description) {
			this.description = description;
		}
		
		public static AccessLevel toEnum(String description) {
			AccessLevel type;
			
			switch (description) {
			case "Standard Access" : 	type = AccessLevel.Standard;
										break;
										
			case "Elevated Access" : 	type = AccessLevel.Elevated;
										break;
										
			case "Admin Access" : 		type = AccessLevel.Admin;
										break;
										
			default :					type = AccessLevel.Invalid;
										System.out.println(module + "Invalid identifier.");
										break;
			}
			
			return type;
		}
		
	}
	
	public enum EntryStatus{
		Open("Opened"),
		InProgress("In Progress"),
		Completed("Completed"),
		Closed("Closed"),
		Upcoming("Upcoming"),
		Passed("Passed"),
		Unreviewed("Waiting For Review"),
		Refused("Not Accepted"),
		Accepted("Accepted"),
		Invalid("Invalid");
		
		public final String description;
		
		// assign string version of Status for printing.
		private EntryStatus(String description) {
			this.description = description;
		}
		
		public static EntryStatus toEnum(String description) {
			EntryStatus type;
			
			switch (description) {
			case "Opened" : 			type = EntryStatus.Open;
										break;
										
			case "In Progress" : 		type = EntryStatus.InProgress;
										break;
										
			case "Completed" : 			type = EntryStatus.Completed;
										break;
										
			case "Closed" : 			type = EntryStatus.Closed;
										break;
										
			case "Upcoming" : 			type = EntryStatus.Upcoming;
										break;
										
			case "Passed" : 			type = EntryStatus.Passed;
										break;
										
			case "Waiting For Review" : type = EntryStatus.Unreviewed;
										break;
										
			case "Not Accepted" : 		type = EntryStatus.Refused;
										break;
										
			case "Accepted" : 			type = EntryStatus.Accepted;
										break;
										
			default :					type = EntryStatus.Invalid;
										System.out.println(module + "Invalid identifier.");
										break;
			}
			
			return type;
		}
	}
	
	public enum Urgency{
		Low("Low Priority"),
		Medium("Medium Priority"),
		High("High Priority"),
		Critical("Critical Priority"),
		Invalid("Invalid");
		
		public final String description;
		
		// assign string version of Status for printing.
		private Urgency(String description) {
			this.description = description;
		}
		
		public static Urgency toEnum(String description) {
			
			Urgency type;
			switch (description) {
			case "Low Priority" : 		type = Urgency.Low;
										break;
										
			case "Medium Priority" : 	type = Urgency.Medium;
										break;
										
			case "High Priority" : 		type = Urgency.High;
										break;
										
			case "Critical Priority" : 	type = Urgency.Critical;
										break;
										
			default :					type = Urgency.Invalid;
										System.out.println(module + "Invalid identifier.");
										break;
			}
			
			return type;
		}
	}
	
	public enum RequestResponse{
		Accept("Accept"),
		Reject("Reject"),
		Invalid("Invalid");
		
		public final String description;
		
		// assign string version of Access Level for printing.
		private RequestResponse(String description) {
			this.description = description;
		}
		
		public static RequestResponse toEnum(String description) {
			RequestResponse type;
			
			switch (description) {
			case "Accept" : 			type = RequestResponse.Accept;
										break;
										
			case "Reject" : 			type = RequestResponse.Reject;
										break;
										
			default :					type = RequestResponse.Invalid;
										System.out.println(module + "Invalid identifier.");
										break;
			}
			
			return type;
		}
		
	}
	
	
	
}
