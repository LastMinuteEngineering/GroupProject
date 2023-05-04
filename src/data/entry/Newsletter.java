package data.entry;

import java.util.ArrayList;
import java.util.Iterator;

import common.types.Content;
import common.types.CustomEnums.EntryStatus;
import common.types.CustomEnums.EntryType;

public class Newsletter extends Entry {

	private ArrayList<Entry> replies;
	private Integer likes;	
	private Content description;
	private EntryFactory factory;
	
	public Newsletter(String id, String creation) {
		super(id, creation);
		
		this.status = EntryStatus.Unreviewed;
		this.likes = 0;
		
		this.description = new Content("Letter Description", "");
		this.replies = new ArrayList<>();
		this.factory = EntryFactory.getInstance();
		
		content.add(description);
	}
	
	

	@Override
	public void promptForDetails() {
		// title of letter.
		title = modifyVariable(this.title, "discussion title");
		
		// status of letter; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "letter status", new String[] {
			EntryStatus.Unreviewed.description,
			EntryStatus.Accepted.description,
			EntryStatus.Refused.description,
			EntryStatus.Posted.description,
		}));
		
		
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
				+ "\t" + getContent()
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
