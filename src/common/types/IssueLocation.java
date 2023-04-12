package common.types;

public class IssueLocation {
	
	public String street;
	public String zip;
	public String building;
	public String room;
	public String other;
	
	public IssueLocation(String street, String zip, String building, String room, String other) {
		this.street = street;
		this.zip = zip;
		this.building = building;
		this.room = room;
		this.other = other;
	}

}
