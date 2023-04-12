package common.types;

public class IssueLocation {
	
	public String street;
	public String city;
	public String state;
	public String zip;
	public String building;
	public String room;
	public String other;
	
	public IssueLocation(String street, String city, String state, String zip, String building, String room, String other) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.building = building;
		this.room = room;
		this.other = other;
	}
	
	public String toString() {
		// returns string of format : "street, city, state, zip
		//							   building, room
		//                             other"
		String string = ""
				+ street + ", " + city+ ", " + state+ ", " + zip
				+"\n\t" + building + ", " + room
				+"\n\t" + other;
		
		return string;
	}
	

}
