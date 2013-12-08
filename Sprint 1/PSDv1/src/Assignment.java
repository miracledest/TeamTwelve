
public class Assignment {

	private Grade assignGrade;
	private String assignName;
	private String attendance;
	
	

	public Assignment(Grade assignGrade, String assignName) {
		super();
		this.assignGrade = assignGrade;
		this.assignName = assignName;
		this.attendance = "na";
	}

	public Grade getAssignGrade() {
		return assignGrade;
	}

	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}
	
	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	
	public void toDisplay(boolean forStudent){
		if (forStudent){
			System.out.println("\tAssignment: " + assignName + " (ATT: "+attendance+" GR: "+assignGrade.getGrade()+")");
		}else{
			System.out.println("\tAssignment: " + assignName);	
		}
		
	}
	
}
