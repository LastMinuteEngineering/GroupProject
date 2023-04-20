package users;

import java.util.ArrayList;

import data.course.Course;
import data.course.CourseManager;

public class Instructor extends Advisor{
	
	private ArrayList<Course> courses;
	private CourseManager cm;
	
	// overloaded constructors with or without middle name.
	public Instructor(String firstName, String middleName, String lastName,
			String id, Integer age, String phoneNumber) {
		super(firstName, middleName, lastName, id, age, phoneNumber);
		
		this.courses = new ArrayList<>();
		this.cm = CourseManager.getInstance();
	}
	
	public Instructor(String firstName, String lastName,
			String id, Integer age, String phoneNumber) {
		this(firstName,"",lastName, id, age, phoneNumber);
	}
	

	@Override
	public String getAccountDetails(Boolean fullDetails) {
		String string = super.getAccountDetails(fullDetails);
				
		if (fullDetails) {
			string = string
			+ "\tTeaching Courses:"
			+ "\n" + getCourseLoadSummary(courses.iterator(), false);
		}
		
		return string;
	}
	
	
	public void addCourse(String prefix, String numb) {
		
		Course course = cm.getCourse(prefix, numb);
		
		// ensure course exits
		if (course == null) {
			return;
		}
		
		// ensure instructor not already teaching course;
		if (courses.contains(course)) {
			System.out.println(getName() + ":\t\"I'm already teaching this course.\"" );
			return;
		}
		
		// change instructor in course and add to course list.
		course.changeInstructor(this);
		courses.add(course);
		
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
	
	public void displayCourses() {
		String string = getName() + " Teaching Courses:"
				+ "\n" + getCourseLoadSummary(courses.iterator(), false);
		
		System.out.println(string);
	}
}
