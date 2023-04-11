package data.entry;

import common.Types.*;
import data.storage.Storage;

public class EntryFactory {

	private static EntryFactory instance;
	private Integer entryCount = 0;
	private Storage db;

	public EntryFactory() {
		db = new Storage("www.LastMinuteEngineering.com/storage", 100);
	}

	public static EntryFactory getInstance() {
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
		entryCount++;

		// add to storage.
		db.addEntry(entry);

		return entry;
	};

//	private Entry createHealthIssueReport() {
//		
//	};

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

}
