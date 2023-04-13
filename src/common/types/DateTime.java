package common.types;

public class DateTime {
	public String date;
	public String time;
	
	public DateTime(String date, String time) {
		this.date = date;
		this.time = time;
	}
	
	public String toString() {
		// returns string of format : "date @ time"
		String string = ""
				+ date + " @ " + time;
		return string;
	}
}
