package data.entry;

import common.types.Content;
import common.types.CustomEnums.*;

public class Reply extends Entry {
	
	private Entry parent;
	private Integer likes;	
	private Content reply;
	
	public Reply(String id, String creation, Entry parent) {
		this.id = id;
		this.creation = creation;
		this.parent = parent;
		
		this.title = "";
		this.status = EntryStatus.Unreviewed;
		this.likes = 0;
		
		this.reply = new Content("Relpy Content", "");
		
		content.add(reply);
	};

	@Override
	public void promptForDetails() {
		// title of reply.
		title = modifyVariable(this.title, "discussion title");
		
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
				+ getContent()
				+ "\tLikes: \t" + likes
				+ "\n";
		}
				
		return string;
	}
	
	public void accept() {
		status = EntryStatus.Accepted;
	}
	
	public void refuse() {
		status = EntryStatus.Refused;
	}
	
	public void like() {
		likes++;
	}
	
	public Entry getParent() {
		return parent;
	}

}
