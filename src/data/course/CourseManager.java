package data.course;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.HashMap;

public class CourseManager {
	
	private static CourseManager instance;
	
	protected HashMap<String, HashMap<String, Course> > courses;
	private String module = "CourseManager:\t";
	
	private CourseManager() {
		courses = new HashMap<>();
	}
	
	public static synchronized CourseManager getInstance() {
		
		if (instance == null) {
			instance = new CourseManager();
		}
		
		return instance;
	}
	
	public void addCourse(String prefix, String numb, String name, Integer capacity) {
		
		// Determine if course already exists.
		if (getCourse(prefix, numb) != null) {
			
			System.out.println(module+ "Course already exists; cancelling operation." );
			return;
		}
		
		HashMap<String, Course> subjectCourses = courses.getOrDefault(prefix, null);
	
		// create course.
		Course course = new Course(prefix, numb, name, capacity);
		if (subjectCourses == null) {
			// create subject course map
			subjectCourses = new HashMap<String, Course>();
			courses.put(prefix, subjectCourses);			
		}
		
		// add course.
		subjectCourses.put(numb, course);
		
		
	}
	
	public Course getCourse(String prefix, String numb) {
		
		// Determine if course exists.
		HashMap<String, Course> subjectCourses = courses.getOrDefault(prefix, null);
		if ((subjectCourses == null) || (subjectCourses.getOrDefault(numb, null) == null) ) {
			System.out.println(module+ "Course does not exist." );
			return null;
		}
			
		return subjectCourses.get(numb);
	}
	
	public void displayAvailableCourses() {
		
		// iterate over all available subject prefixes.
		Iterator<Entry<String, HashMap<String, Course>>> allCourseIterator = courses.entrySet().iterator();
		
		while (allCourseIterator.hasNext()) {
			HashMap<String, Course> subjectCourses = allCourseIterator.next().getValue(); 
			
			// iterate over all courses in subject prefix.
			Iterator<Entry<String, Course>> courseIterator = subjectCourses.entrySet().iterator();
	
			while (courseIterator.hasNext()) {
				
				Course course = courseIterator.next().getValue();
				course.displayDetails(false);
			}
			
		}
		
	};
	
	
	
	
}
