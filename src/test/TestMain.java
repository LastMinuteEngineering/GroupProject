package test;

import common.types.CustomEnums.*;
import common.types.*;
import data.course.*;
import data.entry.*;
import data.storage.*;
import users.*;

public class TestMain {

	public static void main(String[] args) {
		
		// testing Storage
		Storage.createDatabase();
		
		// testing CourseManager
		CourseManager cm = CourseManager.getInstance();
		cm.addCourse("CSE", "3421", "Design Methods", 30);
		cm.addCourse("CSE", "4010", "Senior Project", 15);
		cm.displayAvailableCourses();
		
		// testing users
		Instructor drBhattacharyya = new Instructor("Siddartha", "Bhattacharyya", "903XXXXXX", 45, "(321) XXX-XXXX");
		Instructor drShoaff = new Instructor("William", "D", "Shoaff", "903XXXXXX", 55, "(321) XXX-XXXX");
		
		Instructor drBernhard = new Instructor("Phillip", "J", "Bernhard","903XXXXXX", 55, "(321) XXX-XXXX");
		Staff cheryl = new Staff("Cheryl", "Mitravich","903XXXXXX", 45, "(321) XXX-XXXX");

		Student angel = new Student("Angel", "Star", "903XXXXXXX", 21, "(321) XXX-XXXX");
		Student kyle = new Student("Kyle", "W", "Stewart", "903XXXXXXX", 21, "(321) XXX-XXXX");
		Student malakai = new Student("Malakai", "J", "Stanisclaus", "903XXXXXXX", 21, "(321) XXX-XXXX");
		
		malakai.changeAccessLevel(AccessLevel.Admin);
		kyle.changeAccessLevel(AccessLevel.Elevated);
		
		// assign courses
		angel.addCourse("CSE", "3421");
		kyle.addCourse("CSE", "3421");
		malakai.addCourse("CSE", "3421");
		drBhattacharyya.addCourse("CSE", "3421");
		
		kyle.addCourse("CSE", "4010");
		malakai.addCourse("CSE", "4010");
		drShoaff.addCourse("CSE", "4010");

		// adviser/advisee
		malakai.changeAdvisor(drBhattacharyya);
		drBhattacharyya.addAdvisee(malakai);
		
		// supervisor/subordinate
		cheryl.changeSupervisor(drBernhard);
		drBernhard.addSubordinate(cheryl);
		
		drBhattacharyya.changeSupervisor(drBernhard);
		drBernhard.addSubordinate(drBhattacharyya);
		
		drShoaff.changeSupervisor(drBernhard);
		drBernhard.addSubordinate(drShoaff);
		
		
		// display details.
		angel.displayAccountDetails(true);
		kyle.displayAccountDetails(true);
		malakai.displayAccountDetails(true);
		drBhattacharyya.displayAccountDetails(true);
		drShoaff.displayAccountDetails(true);
		cheryl.displayAccountDetails(true);
		drBernhard.displayAccountDetails(true);
		
		//create entry
		angel.createEntry(EntryType.DiscussionPost);
		
		// display entries.
		angel.displayAllEntries();
		
		
		
		
		
		
	}
}
