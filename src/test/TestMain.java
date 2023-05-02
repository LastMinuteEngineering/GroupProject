package test;

import common.types.CustomEnums.*;
import data.course.*;
import data.entry.*;
import data.storage.*;
import external.Registrar;


public class TestMain {

	public static void main(String[] args) {
		
		// testing Storage
		Storage.createDatabase();
		
		// testing CourseManager
		Registrar registrar = Registrar.getInstance();
		CourseManager cm = CourseManager.getInstance();
		cm.displayAvailableCourses();
		
		// testing users
		registrar.findUser("astar").displayAccountDetails(true);
		registrar.findUser("kstewart").displayAccountDetails(true);
		registrar.findUser("mstanisclaus").displayAccountDetails(true);
		registrar.findUser("sbhattacharyya").displayAccountDetails(true);
		registrar.findUser("wds").displayAccountDetails(true);
		registrar.findUser("cmitravich").displayAccountDetails(true);
		registrar.findUser("pbernhard").displayAccountDetails(true);
		
		// test entries
		registrar.findUser("astar").createEntry(EntryType.DiscussionPost);
		registrar.findUser("astar").createEntry(EntryType.HealthIssueReport);
		registrar.findUser("astar").createEntry(EntryType.SocialEvent);
		registrar.findUser("astar").createEntry(EntryType.MentalHealthSupportRequest);
		registrar.findUser("astar").createEntry(EntryType.MentalHealthSurvey);
		
		Storage db = Storage.getInstance();
		((DiscussionPost) db.getEntry("0")).addReply();
		
		
		// display entries.
		registrar.findUser("astar").displayAllEntries();
		
		
	}
}
