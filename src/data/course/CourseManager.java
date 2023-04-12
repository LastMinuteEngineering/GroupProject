package data.course;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.HashMap;

public class CourseManager {
	
	private static CourseManager instance;
	
	protected HashMap<String, HashMap<Integer, Course> > courses;
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
	
	public void addCourse(String prefix, Integer numb, String name, Integer capacity) {
		
		// Determine if course already exists.
		if (getCourse(prefix, numb) != null) {
			
			System.out.println(module+ "Course already exists; cancelling operation." );
			return;
		}
		
		HashMap<Integer, Course> subjectCourses = courses.getOrDefault(prefix, null);
	
		// create course.
		Course course = new Course(prefix, numb, name, capacity);
		if (subjectCourses == null) {
			// create subject course map
			subjectCourses = new HashMap<Integer, Course>();
			courses.put(prefix, subjectCourses);			
		}
		
		// add course.
		subjectCourses.put(numb, course);
		
		
	}
	
	public Course getCourse(String prefix, Integer numb) {
		
		// Determine if course exists.
		HashMap<Integer, Course> subjectCourses = courses.getOrDefault(prefix, null);
		if ((subjectCourses == null) || (subjectCourses.getOrDefault(numb, null) == null) ) {
			System.out.println(module+ "Course does not exist." );
			return null;
		}
			
		return subjectCourses.get(numb);
	}
	
	public void displayAvailableCourses() {
		
		// iterate over all available subject prefixes.
		Iterator<Entry<String, HashMap<Integer, Course>>> allCourseIterator = courses.entrySet().iterator();
		
		while (allCourseIterator.hasNext()) {
			HashMap<Integer, Course> subjectCourses = allCourseIterator.next().getValue(); 
			
			// iterate over all courses in subject prefix.
			Iterator<Entry<Integer, Course>> courseIterator = subjectCourses.entrySet().iterator();
	
			while (courseIterator.hasNext()) {
				
				Course course = courseIterator.next().getValue();
				course.displayDetails(false);
			}
			
		}
		
	};
	
	
	
	
}
