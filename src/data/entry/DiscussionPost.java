package data.entry;

import java.util.ArrayList;
import java.util.Iterator;

import data.course.Course;
import data.course.CourseManager;
import common.types.Content;
import common.types.CustomEnums.*;

public class DiscussionPost extends Entry {
	
	private ArrayList<Entry> replies;
	private Course course;
	private Integer likes;	
	private Content description;
	private CourseManager cm;
	private EntryFactory factory;
	
	public DiscussionPost(String id, String creation) {
		super(id, creation);
		
		this.status = EntryStatus.Open;
		this.likes = 0;
		
		this.description = new Content("Discussion Description", "");
		this.replies = new ArrayList<>();
		this.cm = CourseManager.getInstance();
		this.factory = EntryFactory.getInstance();
		
		content.add(description);
	}
	
	

	@Override
	public void promptForDetails() {
		// title of discussion.
		title = modifyVariable(this.title, "discussion title");
		
		// status of discussion; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "discussion status", new String[] {
			EntryStatus.Open.description,
			EntryStatus.Closed.description
		}));
		
		// associate course to discussion.
		do {
			String coursePrefix = "";
			String courseNumb = "";
			coursePrefix = modifyVariable(coursePrefix, "course prefix");
			courseNumb = modifyVariable(courseNumb, "course number");
			
			course = cm.getCourse(coursePrefix, courseNumb);
			
		}while (course == null);
		
		// prompt user to modify all content contained in the Entry.
		modifyContent();
		
		// prompt user for new Content.
		addNewContent();
	}

	@Override
	public String getDetails(Boolean fullDetails) {
		String string = getQuickInfo();
		if (fullDetails) {
				string = string
				+ "\tCourse: \n\t" + course.getDetails(false)
				+ getContent()
				+ "\tLikes: \t" + likes
				+ "\n\tReplies:\n" + getReplyDetails(true) ;
		}else {
			// get show likes and replies.
			string = string 
			+ "\tLikes: \t" + likes
			+ "\n\tReplies:\t" + replies.size() ;
		}
				
		return string;
	}
	
	public void like() {
		likes++;
	}
	
	public void addReply() {
		Entry reply = factory.createEntry(EntryType.Reply, this);
		reply.promptForDetails();
		replies.add(reply);
	}
	
	
	// Utility functions
	private String getReplyDetails(Boolean fullDetails) {
		
		String string = ""; 
		Iterator<Entry> replyIterator = replies.iterator();
		
		// Handle case: no replies in list.
		if (!replyIterator.hasNext()) {
			return "\t" + "-".repeat(10)
					+ "\n\tNone";
		}
				
		while(replyIterator.hasNext()) {
			Entry reply = replyIterator.next();
			string = string
			+"\t" + "-".repeat(10)
			+ "\n\t" + reply.getDetails(fullDetails);
		}
		
		return string;
	}
	


}
