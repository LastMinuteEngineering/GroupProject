package test;

import java.util.HashMap;
import common.types.CustomEnums.*;
import common.types.*;
import data.course.*;
import data.entry.*;
import data.storage.*;
import external.Registrar;
import users.*;


public class TestMain {

	public static void main(String[] args) {
		
		// testing Storage
		Storage.createDatabase();
		
		// testing CourseManager
		Registrar.loadCourses();
		CourseManager cm = CourseManager.getInstance();
		cm.displayAvailableCourses();
		
		// testing users
		HashMap<String, UserAccount> users = Registrar.getUsers();
		
		// display details.
		users.get("astar").displayAccountDetails(true);
		users.get("kstewart").displayAccountDetails(true);
		users.get("mstanisclaus").displayAccountDetails(true);
		users.get("sbhattacharyya").displayAccountDetails(true);
		users.get("wds").displayAccountDetails(true);
		users.get("cmitravich").displayAccountDetails(true);
		users.get("pbernhard").displayAccountDetails(true);
		
		// test entries
		users.get("astar").createEntry(EntryType.DiscussionPost);
		users.get("astar").createEntry(EntryType.HealthIssueReport);
		users.get("astar").createEntry(EntryType.SocialEvent);
		users.get("astar").createEntry(EntryType.MentalHealthSupportRequest);
		users.get("astar").createEntry(EntryType.MentalHealthSurvey);
		
		Storage db = Storage.getInstance();
		((DiscussionPost) db.getEntry("0")).addReply();
		
		
		// display entries.
		users.get("astar").displayAllEntries();
		
		
	}
}
