package data.entry;

import common.types.CustomEnums.*;
import common.types.IssueLocation;
import common.types.Content;

public class HealthIssueReport extends Entry {
	
	private IssueLocation issueLocation;
	private Content description;
	private Urgency urgency;
	
	public HealthIssueReport(String id, String creation) {
		this.id = id;
		this.creation = creation;
		
		description = new Content("description", "");
		this.issueLocation = new IssueLocation("","","","","","","");
		this.urgency = Urgency.Medium;
		
	}

	@Override
	public void promptForDetails() {
		
		// title of issue
		title = modifyVariable(this.title, "title");
		
		// status of issue; converts string back to enum.
		status = EntryStatus.toEnum(modifyVariable(status.description, "staus", new String[] {
			EntryStatus.Open.description,
			EntryStatus.InProgress.description,
			EntryStatus.Completed.description,
			EntryStatus.Closed.description
		}));
		
		// description of issue.
		description.input = modifyVariable(description.input, description.label);
		content.add(description);
		
		// location of issue
		issueLocation.street = modifyVariable(issueLocation.street, "street");
		issueLocation.zip = modifyVariable(issueLocation.zip, "zipcode");
		issueLocation.building = modifyVariable(issueLocation.building, "building");
		issueLocation.room = modifyVariable(issueLocation.room, "room number");
		issueLocation.other = modifyVariable(issueLocation.other, "extra details");
		
		// urgency of the issue; converts string back to enum.
		urgency = Urgency.toEnum(modifyVariable(urgency.description, "urgency", new String[] {
				Urgency.Low.description,
				Urgency.Medium.description,
				Urgency.High.description,
				Urgency.Critical.description
		}));
		
		
	}

	@Override
	public String getDetails(Boolean fullDetails) {
		
		String string = getBaseInfo();
		if (fullDetails) {
				string = string
				+ "\n\tIssue Location: \n\t" + issueLocation.toString()
				+ "\n\tUrgency: \t" + urgency.description
				+ "\n" + getContent();
			}
				
		return string;
	}

}
