package external;

import java.util.HashMap;

import common.types.CustomEnums.*;
import users.*;
import data.course.*;

public class Registrar {
	
	public static void loadCourses() {
		CourseManager cm = CourseManager.getInstance();
		cm.addCourse("CSE", "3421", "Design Methods", 30);
		cm.addCourse("CSE", "4010", "Senior Project", 15);
	}

	public static HashMap<String, UserAccount> getUsers() {
		
		HashMap<String, UserAccount> users = new HashMap<>();
		
		// testing users
		
		Instructor drBhattacharyya = new Instructor("Siddartha", "Bhattacharyya", "903XXXXXX", 45, "(321) XXX-XXXX");
		Instructor drShoaff = new Instructor("William", "D", "Shoaff", "903XXXXXX", 55, "(321) XXX-XXXX");
		
		Instructor drBernhard = new Instructor("Phillip", "J", "Bernhard","903XXXXXX", 55, "(321) XXX-XXXX");
		Staff coordinatorMitravich = new Staff("Cheryl", "Mitravich","903XXXXXX", 45, "(321) XXX-XXXX");

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
		coordinatorMitravich.changeSupervisor(drBernhard);
		drBernhard.addSubordinate(coordinatorMitravich);
		
		drBhattacharyya.changeSupervisor(drBernhard);
		drBernhard.addSubordinate(drBhattacharyya);
		
		drShoaff.changeSupervisor(drBernhard);
		drBernhard.addSubordinate(drShoaff);
		
	
		users.put("sbhattacharyya", drBhattacharyya);
		users.put("wds", drShoaff);
		users.put("pbernhard", drBernhard);
		users.put("cmitravich", coordinatorMitravich);

		users.put("astar", angel);
		users.put("kstewart", kyle);
		users.put("mstanisclaus", malakai);
		
		return users;
	}
}
