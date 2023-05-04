package data.entry;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import common.types.CustomEnums.*;
import data.storage.Storage;
import external.EmergencyCommunication;

public class EntryFactory {

	private static EntryFactory instance;
	private Integer entryCount = 0;
	private Storage db;
	private String module = "EntryFactory:\t";

	private EntryFactory() {
		db = Storage.getInstance();
	}

	public static synchronized EntryFactory getInstance() {
		// return singleton object when created.
		if (instance == null) {
			instance = new EntryFactory();
		}

		return instance;
	};

	public Entry createEntry(EntryType type) {
		Entry entry;

		// Call proper method based on type.
		switch (type) {
		case HealthIssueReport :	entry = createHealthIssueReport();
								 	break;
								 	
		case SocialEvent : 			entry = createSocialEvent();
						   			break;
						   			
		case DiscussionPost : 		entry = createDiscussionPost();
							  		break;
							  		
		case MentalHealthSupportRequest : 	entry = createMentalHealthSupportRequest();
											break;
		
		case MentalHealthSurvey : 	entry = createMentalHealthSurvey();
									break;
									
		case Newsletter	:			entry = createNewsletter();
									break;
									
		case SecurityReport :		entry = createSecurityReport();
									break;
											
		case Reply: 				entry = null;
									System.out.println(module + "No parent entry; cancelling operation.");
									break;
									
		default : 					entry = null;
									System.out.println(module + "Invalid type; cancelling operation.");
									break;
		}
			
		
		if (entry != null){
			// add to storage.
			db.addEntry(entry);
		}

		return entry;
	};
	
	// overloaded to create replies with parent entries.
	public Entry createEntry(EntryType type, Entry parent) {
		Entry entry = createReply(parent);

		// add to Storage
		db.addEntry(entry);

		return entry;
	}
	
	
	
	private Entry createHealthIssueReport() {
		return new HealthIssueReport(getId(), getTimeStamp());
	};

	private Entry createSocialEvent() {
		return new SocialEvent(getId(), getTimeStamp());
	};

	private Entry createDiscussionPost() {
		return new DiscussionPost(getId(), getTimeStamp());
	};
	
	private Entry createReply(Entry parent) {
		return new Reply(getId(), getTimeStamp(), parent);

	};
	
	private Entry createMentalHealthSupportRequest() {
		return new MentalHealthSupportRequest(getId(), getTimeStamp());
	};
	
	private Entry createMentalHealthSurvey() {
		return new MentalHealthSurvey(getId(), getTimeStamp());
	}
	
	private Entry createNewsletter() {
		return new Newsletter(getId(), getTimeStamp());
	}
	
	private Entry createSecurityReport() {
		if (SecurityReport.isUrgent()) {
			EmergencyCommunication.contactEmergencyServices();
			return null;
		}else {
			return new SecurityReport(getId(), getTimeStamp());
		}
	}
	
//
//	Utility functions
//	
	private String getId() {
		// get entryCount in string format then increment.
		String id = "" + entryCount;
		entryCount++;
		return id;
	}
	
	private String getTimeStamp() {
		// get time in desired format : "month, data, year hour:minute:second AM/PM"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss a");
		// get current time
		ZonedDateTime now = ZonedDateTime.now( ZoneId.of("GMT-4") ); 
		// return formatted time.
		return now.format(formatter);
	}

}
