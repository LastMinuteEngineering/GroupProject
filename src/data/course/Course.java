package data.course;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;

import common.types.Grade;
import users.UserAccount;

public class Course {
	
	private String prefix;
	private String numb;
	private String name;
	private Integer capacity;
	private HashMap<UserAccount, Grade> students;
	private UserAccount instructor;
	
	private static String module; 
	
	public Course(String prefix, String numb, String name, Integer capacity) {
		this.prefix = prefix;
		this.numb = numb;
		this.name = name;
		this.capacity = capacity;
		this.students = new HashMap<>();
		module = prefix + " " + numb + ":\t"; 
	}
	

//
//	Getters
//		
	public String getPrefix() {
		return this.prefix;
	}
	
	public String getNumb() {
		return this.numb;
	}
	
	public Iterator<UserAccount> getStudents(){
		// create list to house student accounts.
		ArrayList<UserAccount> students = new ArrayList<>();
		
		// add each student from hashmap into list.
		Iterator<Entry<UserAccount, Grade>> studentsIterator = this.students.entrySet().iterator();
		
		while(studentsIterator.hasNext()) {
			students.add(studentsIterator.next().getKey());
		}
		
		return students.iterator();
	}
	
	
	public UserAccount getInstructor() {
		return instructor;
	}
	
	public String getStudentGrade(UserAccount student, Boolean letterGrade) {
		
		// ensure student is in course.
		if (!inCourse(student)) {
			return "Student not in course";
		}
		
		// convert grade to user-specified format.
		String grade = letterGrade ? students.get(student).getLetterGrade() : "" + students.get(student).getNumberGrade();
		return grade;
	}
	
//
//	Setters
//	
	public void addStudent(UserAccount student) {
		
		// ensure course not filled past capacity.
		if (students.size() >= capacity) {
			System.out.println(module+ "Course full; cancelling operation." );
			return;
		}
		// ensure student not already in course.
		if (inCourse(student)) {
			System.out.println(module+ "Student already in course; cancelling operation." );
			return;
		}
		
		students.put(student, new Grade(100));
	}
	
	public void changeInstructor(UserAccount instructor) {
		this.instructor = instructor;
	}
	
//	
//	Full Course Access/Printing functions
//	
	
	public void displayDetails(Boolean fullDetails) {
		String string = getDetails(fullDetails);
		System.out.println(string);
	}
	
	public String getDetails(Boolean fullDetails) {
		
		//	Get extra course info, if specified.
		String string = getQuickInfo();
		if (fullDetails) {
			string = string
			+ "\tCourse Capacity: \t" + students.size() + "/" + capacity
			+ "\n\tInstructor: \t" + instructor
			+ "\n\tStudents: ";
			
			Iterator<UserAccount> studentIterator = getStudents();
			while (studentIterator.hasNext()) {
				UserAccount student = studentIterator.next();
				string= string
				+ "\n\t" + student.getAccountDetails(false) 
				+ "\nGrade:\t" + this.students.get(student).getLetterGrade()
				+ "\n";
			}
		}
		
		return string;
		
	}
	
	protected String getQuickInfo() {
		// Get simple course Info.		
		String string = ""
				+ "Course Name:\t" + name
				+ "\n\tCourse Prefix: \t" + prefix 
				+ "\n\tCourse Number:\t" + numb
				+ "\n";
				
		return string;
	};
	
//	
//	Utility
//	
	private Boolean inCourse(UserAccount student) {
		return students.getOrDefault(student, null) != null;
	}
	
}
