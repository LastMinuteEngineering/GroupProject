package data.entry;

import common.types.Content;
import common.types.DateTime;
import common.types.Location;
import common.types.CustomEnums.*;

public class SocialEvent extends Entry {
	
	private Content description;
	private Location eventLocation;
	private DateTime eventDateTime;
	
	public SocialEvent(String id, String creation) {
		super(id, creation);
		
		this.status = EntryStatus.Upcoming;
		
		this.description = new Content("Event Description", "");
		this.eventLocation = new Location("","","","","","","");
		this.eventDateTime = new DateTime("","");
		
		content.add(description);
		
	}

	@Override
	public void promptForDetails() {
		
		// title of event.
		title = modifyVariable(this.title, "event title");
		
		// status of event; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "event status", new String[] {
			EntryStatus.Upcoming.description,
			EntryStatus.Passed.description
		}));
		
		
		// date & time of the event; suggested format not enforced.
		eventDateTime.date = modifyVariable(eventDateTime.date, "event date (mm-dd-yy)");
		eventDateTime.time = modifyVariable(eventDateTime.time, "event time (HH:MM AM/PM)");
		
		// location of event.
		eventLocation.street = modifyVariable(eventLocation.street, "street");
		eventLocation.zip = modifyVariable(eventLocation.zip, "zipcode");
		eventLocation.building = modifyVariable(eventLocation.building, "building");
		eventLocation.room = modifyVariable(eventLocation.room, "room number");
		eventLocation.other = modifyVariable(eventLocation.other, "extra details");
		
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
				+ "\tEvent Date and Time: \t" + eventDateTime.toString()
				+ "\n\tEvent Location: \n\t" + eventLocation.toString()
				+ "\n" + getContent();
			}
				
		return string;
	}

}
