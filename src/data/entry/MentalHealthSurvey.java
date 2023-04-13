package data.entry;

import java.util.ArrayList;
import java.util.Iterator;

import common.types.Content;
import common.types.CustomEnums.*;

public class MentalHealthSurvey extends Entry {
	
	private Content description;
	private Integer responses;
	private ArrayList<Content> modifiedContent;
	
	public MentalHealthSurvey(String id, String creation) {
		super(id, creation);
		
		this.status = EntryStatus.Open;
		this.responses = 0;
		
		this.description = new Content("Survey Description", "");
		this.modifiedContent = new ArrayList<>();
		
		
		saveContent(description);
		
	}

	@Override
	public void promptForDetails() {
		// title of request.
		title = modifyVariable(this.title, "request title");
		
		// status of request; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "request status", new String[] {
			EntryStatus.Open.description,
			EntryStatus.Closed.description,
		}));
		
		// prompt user to modify questions.
		modifyContent();
				
		// prompt user for new questions.
		addNewContent();
	}

	@Override
	public String getDetails(Boolean fullDetails) {
		String string = getQuickInfo();
		if (fullDetails) {
				string = string
				+ "\t# Responses: \t" + responses
				+ "\n" + getContent();
			}
				
		return string;
	}
	
	public void respond() {
		// increment response counter.
		responses++;
		
		Iterator<Content> contentIterator = content.iterator();
		Iterator<Content> modifiedContentIterator = modifiedContent.iterator();
		
		// display basic survey info and description;
		Content required = contentIterator.next();
		System.out.println(
				getQuickInfo()
				+ "\t" + required.label
				+ "\n\t" + required.input);
		// update modified iterator to match original.
		modifiedContentIterator.next();
		
		
		// ask questions and save responses to modified content.
		while(contentIterator.hasNext()) {
			Content original = contentIterator.next();
			Content modified = modifiedContentIterator.next();
			
			// question
			System.out.println(original.label);
			
			// get response without showing previous responses.
			modified.input = modified.input +"\n"+ modifyVariable(original.input, "Response");
		}
		
	}
	
//	
//	Utility functions
//	
	// overridden functions are to ensure original and modified remain separate.
	@Override
	protected void modifyContent() {
		Iterator<Content> contentIterator = content.iterator();
		Iterator<Content> modifiedContentIterator = modifiedContent.iterator();
		
		// first member of content is required and instantiated in Entry constructor. 
		// User Cannot change label.
		Content required = contentIterator.next();
		required.input = modifyVariable(required.input, required.label);
		
		// change first modified content object to match original.
		modifiedContentIterator.next().input = required.input;
		
		// handle remaining normally.
		while(contentIterator.hasNext()) {
			Content other = contentIterator.next();
			other.label = modifyVariable(required.label, "Question");
			
			// modified content should contain the same elements as original content
			// in the same index positions. Ensure modified labels remain consistent.
			modifiedContentIterator.next().label = other.label;
		}	
	}
	
	@Override
	protected void addNewContent() {
		// prompt user for more content.
		while(addContent()) {
			// make new content, get label and input.
			Content newContent = new Content("","");
			newContent.label = modifyVariable(newContent.label, "Question");
			saveContent(newContent);
		}
		
	}
	
	@Override
	protected String getContent() {
		String string = "";
		
		
		Iterator<Content> contentIterator = content.iterator();
		Iterator<Content> modifiedContentIterator = modifiedContent.iterator();
		
		Content description = contentIterator.next();
		
		string = string
				+ "\t" + description.label
				+ "\n\t\t" + description.input
				+ "\n\tQuestions & Responses"
				+ "\n";
		
		// update modified iterator to match original.
		modifiedContentIterator.next();
		
		while(contentIterator.hasNext()) {
			String question = contentIterator.next().label;
			String responses = modifiedContentIterator.next().input;
		
			string = string
			+ "\t" + question
			+ "\n\t\t" + responses
			+ "\n";
		}
		
		return string;
	}
	
	private void saveContent(Content content) {
		// create shallow copy of Content.
		modifiedContent.add(new Content(content.label, content.input));
		this.content.add(content);
	}
	
	
	


}
