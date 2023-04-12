package data.entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import common.types.Content;
import common.types.CustomEnums.*;

public abstract class Entry {
	
	protected String id;
	protected String title;
	protected EntryStatus status;
	protected String creation;
	protected ArrayList<Content> content;
	
	protected Scanner input = new Scanner (System.in);

//
//  Abstract functions
//
	public abstract void promptForDetails();
	public abstract String getDetails(Boolean fullDetails);
	
//
//  Getter functions
//
	public String getId() {
		return id;
	};
	
//
//  Getter functions
//
	protected String modifyVariable(String toModify, String label) {
		
		//TODO
//		if (toModify.length() == 0) {
//			System.out.println(
//					"Stored " + label 
//					+ ":\n")
//		}
		
		return toModify;
	};
	
	
	
//	
//	Entry access functions.
// 
	public void displayDetails(Boolean fullDetails) {
		String string = getDetails(fullDetails);
		System.out.println(string);
	};
	
	protected String getBaseInformation() {
		
		String string = getQuickInfo()
		+ "\n\tCreation: \t" + creation;
		
		Iterator<Content> contentIterator = content.iterator();
		while(contentIterator.hasNext()) {
			Content content = contentIterator.next();
		
			string = string
			+ "\n\t" + content.label
			+ "\n\t\t" + content.input;
		}
		
		string = string + "\n";
		
		
		return string;
	};
	
	protected String getQuickInfo() {
		String string = ""
				+ "Title: \t" + title
				+ "\n\tID: \t" + id
				+ "\n\tStatus: \t" + status.description
				;
				
		return string;
	};
}
