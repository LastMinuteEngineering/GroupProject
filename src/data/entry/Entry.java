package data.entry;

import common.Types.*;

public abstract class Entry {
	
	protected String id;
	protected String title;
	protected EntryStatus status;
	protected String creation;
	protected Content[] content;

//
//  Abstract functions
//
	public abstract void displayDetails(Boolean fullDetails);
	public abstract void promptForDetails();
	
//
//  Getter functions
//
	public String getId() {
		return id;
	};
	
//	
//	Protected printing variables & functions
// 
	protected String main_divider = "-".repeat(50);
	protected String section_divider = "-".repeat(25);
	protected String getBaseInformation() {
		
		String string = main_divider
		+ "\n" + title
		+ "\n" + section_divider
		+ "\nID:\t" + id
		+ "\nStatus:\t" + status.description
		+ "\nCreation:\t" + creation
		+ "\n" + section_divider
		;
		
		for (int i=0; i < content.length; i++) {
			Content content = this.content[i];
			
			string = string
			+ "\n" + content.label
			+ "\n" + content.input;
		}
		
		string = string + "\n" + section_divider;
		
		
		return string;
	};
	
	protected String getQuickInfo() {
		String string = main_divider
				+ "\n" + title
				+ "\nID:\t" + id
				+ "\nStatus:\t" + status.description
				+ "\nCreation:\t" + creation
				+ "\n" + main_divider
				;
				
		return string;
	};
}
