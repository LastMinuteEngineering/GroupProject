package data.entry;

import java.util.ArrayList;

import common.types.CustomEnums.*;
import common.types.IssueLocation;
import common.types.Content;

public class HealthIssueReport extends Entry {
	
	private IssueLocation issueLocation;
	private Content description;
	private Urgency urgency;
	
	public HealthIssueReport(String id, String title, EntryStatus status, String creation) {
		this.id = id;
		content = new ArrayList<>();
		
	}

	@Override
	public void promptForDetails() {
		
		

	}

	@Override
	public String getDetails(Boolean fullDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
