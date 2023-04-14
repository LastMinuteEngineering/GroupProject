package users;

import java.util.ArrayList;
import java.util.Iterator;

import data.course.CourseManager;
import data.course.Course;

public class Student extends UserAccount {

	private Double gpa;
	private UserAccount advisor;
	private ArrayList<Course> courses;
	private CourseManager cm;
	
	// overloaded constructors with or without middle name.
	public Student(String firstName, String middleName, String lastName,
			String id, Integer age, String phoneNumber) {
		super(firstName, middleName, lastName, id, age, phoneNumber);
		
		this.gpa = 0.00;
		
		this.courses = new ArrayList<>();
		this.cm = CourseManager.getInstance();
		
	}
	
	public Student(String firstName, String lastName,
			String id, Integer age, String phoneNumber) {
		this(firstName,"",lastName, id, age, phoneNumber);
	}

	
	@Override
	public String getAccountDetails(Boolean fullDetails) {
		String string = fullDetails ? getBaseInfo(): getQuickInfo();
		
		if (fullDetails) {
			string = string
			+ "\tGPA:\t" + getGpa()		
			+ "\n\tAdvisor:\t\n" + (advisor == null ? 
					"\t" + "-".repeat(10)
					+ "\n\tNone\n": 
					"\t" + "-".repeat(10) + 
					"\n\t" + advisor.getAccountDetails(false))
			+ "\tTaking Courses:"
			+ "\n" + getCourseLoadSummary(courses.iterator(), false);
		}
		
		return string;
	}

	
	public UserAccount getAdvisor() {
		return advisor != null ? advisor : null;
	}
	
	public void changeAdvisor(UserAccount advisor) {
		// ensure passed advisor is not the one already set.
		if (this.advisor == advisor) {
			System.out.println(firstName + " " + lastName + ":\t\"This person is already my advisor.\"" );
			return;
		}
		this.advisor = advisor;
	}
	
	public void addCourse(String prefix, String numb) {
		
		Course course = cm.getCourse(prefix, numb);
		
		// ensure course exits
		if (course == null) {
			return;
		}
		
		// ensure instructor not already teaching course;
		if (courses.contains(course)) {
			System.out.println(firstName + " " + lastName + ":\t\"I'm already taking this course.\"" );
			return;
		}
		
		// add student to course and add course to course list.
		course.addStudent(this);
		courses.add(course);
		
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
	
	public void displayCourses() {
		String string = firstName + " " + lastName + " Taking Courses:"
				+ "\n" + getCourseLoadSummary(courses.iterator(), false);
		
		System.out.println(string);
	}
	
//	
//	Utility functions
//	
	
	// attach grade to course as well.
	@Override
	protected String getCourseLoadSummary(Iterator<Course> courseIterator, Boolean fullDetails) {
		
		// Handle case: no courses in list.
		if (!courseIterator.hasNext()) {
			return "\tNone";
		}
		
		String string = "";
				
		// cycle through passed courses.
		while(courseIterator.hasNext()) {
			Course course = courseIterator.next();
			
			// append user details at specified level.
			string = string
				+ "\t" + "-".repeat(10)
				+ "\n\t" + course.getDetails(fullDetails)
				+ "\tGrade:\t" + course.getStudentGrade(this, true)
				+ "\n";
		}
		
		return string;
				
	}
	
	private Double getGpa() {
		Iterator<Course> courseIterator = courses.iterator();
		
		// Handle case: no courses in list.
		if (!courseIterator.hasNext()) {
			return 0.00;
		}
		
		Integer coursesTaken = courses.size();
		Integer qualityPoints = 0;
		
		// cycle through courses and add get grade/quality points each.
		while(courseIterator.hasNext()) {
			Course course = courseIterator.next();
			
			// assume all courses worth 3 credits.
			switch (course.getStudentGrade(this, true)) {
			
			case "A" : 	qualityPoints += 12;
						break;
			
			case "B" : 	qualityPoints += 8;
						break;
						
			case "C" : 	qualityPoints += 4;
			break;
			
			default: 	qualityPoints += 0;
						break;
			}
			
		}
		
		return (double) (qualityPoints / (coursesTaken*3));
		
	}
	
	
}
