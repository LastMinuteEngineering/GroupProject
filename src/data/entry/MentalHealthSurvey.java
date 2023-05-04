package data.entry;

import java.util.Iterator;

import common.types.Content;
import common.types.CustomEnums.*;

public class MentalHealthSurvey extends Entry {
	
	private Content description;
	private Integer responses;
	
	public MentalHealthSurvey(String id, String creation) {
		super(id, creation);
		
		this.status = EntryStatus.Open;
		this.responses = 0;
		
		this.description = new Content("Survey Description", "");
		
		this.content.add(description);
		
	}

	@Override
	public void promptForDetails() {
		// title of request.
		title = modifyVariable(this.title, "Survey Title");
		
		// status of request; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "Survey Status", new String[] {
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
		
		// display basic survey info and description;
		Content required = contentIterator.next();
		System.out.println(
				getQuickInfo()
				+ "\t" + required.label
				+ "\n\t" + required.input);
		
		
		// ask questions and save responses.
		while(contentIterator.hasNext()) {
			Content content = contentIterator.next();
			
			// question
			System.out.println(content.label);
			
			// get response without showing previous responses.
			content.input = content.input +"\n\t"+ modifyVariable("", "Response");
		}
		
	}
	
//	
//	Utility functions
//	
	// overridden functions are to ensure only questions are changed.
	@Override
	protected void modifyContent() {
		Iterator<Content> contentIterator = content.iterator();
		
		// first member of content is required and instantiated in Entry constructor. 
		// User Cannot change label.
		Content required = contentIterator.next();
		required.input = modifyVariable(required.input, required.label);
		
		// handle remaining normally.
		while(contentIterator.hasNext()) {
			Content other = contentIterator.next();
			other.label = modifyVariable(required.label, "Question");
		}	
	}
	
	@Override
	protected void addNewContent() {
		// prompt user for more content.
		while(addContent()) {
			// make new content, get label and input.
			Content newContent = new Content("","");
			newContent.label = modifyVariable(newContent.label, "Question");
			content.add(newContent);
		}
		
	}
	
	@Override
	protected String getContent() {
		String string = "";
		
		
		Iterator<Content> contentIterator = content.iterator();
		
		Content description = contentIterator.next();
		
		string = string
				+ "\t" + description.label
				+ "\n\t\t" + description.input
				+ "\n\tQuestions & Responses"
				+ "\n";
		
		while(contentIterator.hasNext()) {
			Content content = contentIterator.next();
			String question = content.label;
			String responses = content.input;
		
			string = string
			+ "\t" + question
			+ "\t" + responses
			+ "\n";
		}
		
		return string;
	}
	
	
	


}
