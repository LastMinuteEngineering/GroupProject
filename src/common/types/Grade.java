package common.types;

public class Grade {

	private Integer numberGrade;
	private String letterGrade;
	
	public Grade(Integer numberGrade) {
		changeGrade(numberGrade);
	}
	
	private String convertToLetter() {
		String letterGrade;
		
		// Return single letter grade based on N. American grading system.
		if (numberGrade >= 90) {
			letterGrade = "A";
		}else if((numberGrade >= 80) && (numberGrade < 90)) {
			letterGrade = "B";
		}else if((numberGrade >= 70) && (numberGrade < 80)) {
			letterGrade = "C";
		}else if((numberGrade >= 60) && (numberGrade < 70)) {
			letterGrade = "D";
		}else {
			letterGrade = "F";
		}
		return letterGrade;
	}
	
//	
//	Getters
//	
	public String getLetterGrade() {
		return letterGrade;
	}
	
	public Integer getNumberGrade() {
		return numberGrade;
	}
	
//	
//	Setters
//	
	
	public void changeGrade(Integer numberGrade) {
		this.numberGrade = numberGrade;
		this.letterGrade = convertToLetter();
	}
}
