package data.entry;

import common.types.Content;
import common.types.CustomEnums.*;

public class MentalHealthSupportRequest extends Entry {
	
	private Urgency requestUrgency;
	private Content details;
	
	public MentalHealthSupportRequest(String id, String creation) {
		this.id = id;
		this.creation = creation;
		this.title = "";
		
		this.status = EntryStatus.Open;
		this.requestUrgency = Urgency.High;	
		
		this.details = new Content("Request details", "");
		
		content.add(details);
	}

	@Override
	public void promptForDetails() {
		// title of request.
		title = modifyVariable(this.title, "request title");
		
		// status of request; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "request status", new String[] {
			EntryStatus.Open.description,
			EntryStatus.InProgress.description,
			EntryStatus.Completed.description,
			EntryStatus.Closed.description,
			EntryStatus.Unreviewed.description,
			EntryStatus.Accepted.description
		}));
		
		// urgency of the request; converts string back to enum.
		requestUrgency = Urgency.toEnum(modifyVariable(requestUrgency.description, "request urgency", new String[] {
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
				+ "\tRequest Urgency: \t" + requestUrgency.description
				+ "\n" + getContent();
			}
				
		return string;
	}

}
