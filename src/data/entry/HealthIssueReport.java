package data.entry;

import java.util.Iterator;
import java.util.ArrayList;

import common.types.CustomEnums.*;
import common.types.Location;
import common.types.Content;


public class HealthIssueReport extends Entry {
	
	private Location issueLocation;
	private Content description;
	private Urgency urgency;
	private EntryFactory factory;
	private ArrayList<Entry> replies;
	
	public HealthIssueReport(String id, String creation) {
		super(id, creation);
		
		this.status = EntryStatus.Open;
		this.urgency = Urgency.Medium;
		
		this.description = new Content("Issue Description", "");
		this.issueLocation = new Location("","","","","","","");
		this.replies = new ArrayList<>();
		this.factory = EntryFactory.getInstance();
		
		content.add(description);
		
	}

	@Override
	public void promptForDetails() {
		
		// title of issue
		title = modifyVariable(this.title, "issue title");
		
		// status of issue; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "issue status", new String[] {
			EntryStatus.Open.description,
			EntryStatus.InProgress.description,
			EntryStatus.Completed.description,
			EntryStatus.Closed.description,
			EntryStatus.Unreviewed.description,
			EntryStatus.Accepted.description,
			EntryStatus.Refused.description
		}));
		
		// location of issue.
		issueLocation.street = modifyVariable(issueLocation.street, "street");
		issueLocation.zip = modifyVariable(issueLocation.zip, "zipcode");
		issueLocation.building = modifyVariable(issueLocation.building, "building");
		issueLocation.room = modifyVariable(issueLocation.room, "room number");
		issueLocation.other = modifyVariable(issueLocation.other, "extra details");
		
		// urgency of the issue; converts string back to enum.
		urgency = Urgency.toEnum(modifyVariable(urgency.description, "issue urgency", new String[] {
				Urgency.Low.description,
				Urgency.Medium.description,
				Urgency.High.description,
				Urgency.Critical.description
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
				+ "\tIssue Location: \n\t" + issueLocation.toString()
				+ "\n\tIssue Urgency: \t" + urgency.description
				+ "\n" + getContent()+ 
				"\tReplies:\n" + getReplyDetails(true) ;
		}else {
			// get replies in short form.
			string = string 
			+ "\n\tReplies:\n" + getReplyDetails(false) ;
		}
				
		return string;
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
		while(replyIterator.hasNext()) {
			Entry reply = replyIterator.next();
			string = string
			+ "\t" + reply.getDetails(fullDetails);
		}
		
		return string;
	}

}
