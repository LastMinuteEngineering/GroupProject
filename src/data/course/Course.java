package data.course;

import java.util.Iterator;
import java.util.ArrayList;

import users.UserAccount;

public class Course {
	
	private String prefix;
	private Integer numb;
	private String name;
	private Integer capacity;
	private ArrayList<UserAccount> students;
	private UserAccount instructor;
	
	private static String module; 
	
	public Course(String prefix, Integer numb, String name, Integer capacity) {
		this.prefix = prefix;
		this.numb = numb;
		this.name = name;
		this.capacity = capacity;
		this.students = new ArrayList<>();
		module = prefix + " " + numb + ":\t"; 
	}
	
	public void addStudent(UserAccount student) {
		
		// ensure course not filled past capacity.
		if (students.size() >= capacity) {
			System.out.println(module+ "Course full; cancelling operation." );
			return;
		}
		students.add(student);
	}
	
	public ArrayList<UserAccount> getStudents(){
		return students;
	}
	
	public void changeInstructor(UserAccount instructor) {
		this.instructor = instructor;
	}
	
	public UserAccount getInstructor() {
		return instructor;
	}
	
	public void displayDetails(Boolean fullDetails) {
		String string = getDetails(fullDetails);
		System.out.println(string);
	}
	
	public String getDetails(Boolean fullDetails) {
		
		String string = getQuickInfo();
		if (fullDetails) {
			string = string
			+ "\n\tCourse Capacity: \t" + students.size() + "/" + capacity
			+ "\n\tInstructor: \t" + instructor
			+ "\n\tStudents: \n";
			
			Iterator<UserAccount> studentIterator = students.iterator();
			while (studentIterator.hasNext()) {
				UserAccount student = studentIterator.next();
				string+= "\t" + student.getAccountDetails(false) + "/n";
			}
		}
		
		string += "\n";
		
		return string;
		
	}
	
	protected String getQuickInfo() {
		String string = ""
				+ "Course Name:\t" + name
				+ "\n\tCourse Prefix: \t" + prefix 
				+ "\n\tCourse Number:\t" + numb
				;
				
		return string;
	};
	
}
