package data.entry;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import common.types.CustomEnums.*;
import data.storage.Storage;

public class EntryFactory {

	private static EntryFactory instance;
	private Integer entryCount = 0;
	private Storage db;

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
//		switch type:
//			createEntryType()
		
		// add to storage.
		db.addEntry(entry);

		return entry;
	};

	// TODO
	private Entry createHealthIssueReport() {
		
		return new HealthIssueReport(getId(), getTimeStamp());
		
	};

//	private Entry createSocialEvent() {
//	
//	};

//	private Entry createDiscussionPost() {
//	
//	};

//	private Entry createDiscussionPostReply() {
//	
//	};

//	private Entry createMentalHealthSupportRequest() {
//	
//	};
	
	
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
