package common.types;

public class ComplexContent {
	public String filename;
	public String format;
	public String headerData;
	public String payloadData;
	public String trailerData;
	
	public ComplexContent(String filename, String format, String headerData, String payloadData, String trailerData) {
		this.filename = filename;
		this.format = format;
		this.headerData = headerData;
		this.payloadData = payloadData;
		this.trailerData = trailerData;
	}
}
