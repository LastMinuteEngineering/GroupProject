package data.entry;

import java.util.Arrays;
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
	// avoid having to import ArrayList lib in every entry.
	protected ArrayList<Content> content = new ArrayList<>();
	
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
//	Entry access functions.
// 
	public void displayDetails(Boolean fullDetails) {
		String string = getDetails(fullDetails);
		System.out.println(string);
	};
	
	protected String getBaseInfo() {
		
		String string = getQuickInfo()
		+ "\n\tCreation: \t" + creation
		+ "\n";
		
		return string;
	};
	
	protected String getContent() {
		String string = "";
		
		Iterator<Content> contentIterator = content.iterator();
		while(contentIterator.hasNext()) {
			Content content = contentIterator.next();
		
			string = string
			+ "\t" + content.label
			+ "\n\t\t" + content.input
			+ "\n";
		}
		
		return string;
	}
	
	protected String getQuickInfo() {
		String string = ""
				+ "Title: \t" + title
				+ "\n\tID: \t" + id
				+ "\n\tStatus: \t" + status.description
				+ "\n";
				
		return string;
	};
	
//
//  Utility functions
//
	protected String modifyVariable(String toModify, String label) {
		
		// prompt user based on data; if toModify has data, display it and prompt user.
		if (toModify.length() == 0) {
			System.out.println(
					"Stored " + label.toLowerCase() + ":"
					+ "\n" + toModify
					+ "\nEnter " + label.toLowerCase() + " or leave blank and press enter to keep stored " + label.toLowerCase() + ":" );
		}else {
			System.out.println("Enter new " + label.toLowerCase() + ":");
		}
		
		String response = input.nextLine();
		
		// if user enters response, send it back for data modification, else return previous value.		
		if (response.length() != 0) { 
			return response;
		}
		
		return toModify;
	};
	
	// overloaded w/ values that require a certain value.
	protected String modifyVariable(String toModify, String label, String[] allowableValues) {
		
		// prompt user based on data; if toModify has data, display it and prompt user.
		if (toModify.length() == 0) {
			System.out.println(
					"Stored " + label.toLowerCase() + ":"
					+ "\n" + toModify
					+ "\nEnter new " + label.toLowerCase() + " (allowable values: " + listToString(allowableValues) + ")"
							+ " or leave blank and press enter to keep stored " + label.toLowerCase() + ":" );
		}else {
			System.out.println("Enter new " + label.toLowerCase() + " (allowable values: " + listToString(allowableValues) + ") :");
		}
		
		String response = input.nextLine();
		
		// if user enters response, send it back for data modification, else return previous value.		
		if (response.length() != 0) { 
			return response;
		}else {
			
			// Loop while user input does not match one of the expected vals.
			while(!stringContainsItemFromList(response, allowableValues)) {
				System.out.println( response + " is not a valid " + label.toLowerCase() + ". Please try again (allowable values: " + listToString(allowableValues) + ") :");
				response = input.nextLine();
			}
		}
		
		return toModify;
	};
	
	protected Boolean makeNewContent() {
		// prompt user for more content.
		System.out.println("Add more content? (y/n)");
		
		String response = input.nextLine();
		
		// loop while answer is not yes/no or similar.
		while(!stringContainsItemFromList(response, new String[] {"Y", "y", "Yes", "yes", "N", "n", "No", "no"})) {
			System.out.println( "Please enter yes, y, no, or n):");
			response = input.nextLine();
		}
		
		// return true if user replied y/yes.
		return stringContainsItemFromList(response, new String[] {"Y", "y", "Yes", "yes"});
	}
	
	private String listToString(String[] values){
		// return array vals as string : "val1, val2, ..., valN"
		return String.join(", ", values);
	}
	
	private boolean stringContainsItemFromList(String inputStr, String[] items) {
		// return true if inputStr matches one of the strings in items array.
	    return Arrays.stream(items).anyMatch(inputStr::contains);
	}
	
}


