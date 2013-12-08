import java.sql.Time;
import java.util.ArrayList;

public class Session {
	
	private Venue myVenue;
	private String type;
	private int duration;
	private Time startTime;
	private ArrayList<Integer> sessionDay;
	private Attendance myAttendance;
	
	public Session(Venue myVenue, String type) {
		super();
		this.myVenue = myVenue;
		this.type = type;
	}

	public Venue getMyVenue() {
		return myVenue;
	}

	public void setMyVenue(Venue myVenue) {
		this.myVenue = myVenue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public ArrayList<Integer> getSessionDay() {
		return sessionDay;
	}

	public void setSessionDay(ArrayList<Integer> sessionDay) {
		this.sessionDay = sessionDay;
	}

	public Attendance getMyAttendance() {
		return myAttendance;
	}

	public void setMyAttendance(Attendance myAttendance) {
		this.myAttendance = myAttendance;
	}
	
	
}
