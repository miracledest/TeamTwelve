package com.psd3.ge;

public class Session {

	String name, date, time, freq2, compulsory, venue, lect_name;
	int duration, freq1, max_attendance;
	public Session(String name, String date, String time, String freq2,
			String compulsory, String venue, int duration, int freq1,
			String lect_name, int max_attendance) {
		super();
		this.name = name;
		this.date = date;
		this.time = time;
		this.freq2 = freq2;
		this.compulsory = compulsory;
		this.venue = venue;
		this.duration = duration;
		this.freq1 = freq1;
		this.lect_name = lect_name;
		this.max_attendance = max_attendance;
	}
	
	public String toDisplay(){
		return "<li>"+genObjString()+"</li>";
	}
	
	public String genObjString(){
		String s = "";
		s += "Session: "+name+"<br>";
		s += " Date: "+date+"<br>";
		s += " Time: "+time+"<br>";
		s += " Duration: "+duration+"<br>";
		s += " Max Attendance: "+max_attendance+"<br>";
		s += " Venue: "+venue;
		return s;
	}
	
	
}
