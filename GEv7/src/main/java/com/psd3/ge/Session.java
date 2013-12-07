package com.psd3.ge;

public class Session{

	String name, date, time, freq2, compulsory, venue, lect_name;
	int duration, freq1, max_attendance, current_attendance;
	public Session(String name, String date, String time, String freq2,
			String compulsory, String venue, int duration, int freq1,
			String lect_name, int max_attendance, int current_attendance) {
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
		this.current_attendance = current_attendance;
	}
	
	public String toDisplay(){
		return "<div class=\"session\"><table>"+genObjString()+"</table></div><br><br>";
	}
	
	public String toSave(){
		
		String saveStr = "";
		
		saveStr += name+Main.delimiter;
		saveStr += date+Main.delimiter;
		saveStr += time+Main.delimiter;
		saveStr += freq2+Main.delimiter;
		saveStr += compulsory+Main.delimiter;
		saveStr += venue+Main.delimiter;
		saveStr += duration+Main.delimiter;
		saveStr += freq1+Main.delimiter;
		saveStr += lect_name+Main.delimiter;
		saveStr += max_attendance+Main.delimiter;
		saveStr += ""+current_attendance+"\n";
		
		return saveStr;
	}
	
	public String genObjString(){
		String s = "";
		s += "<tr><td colspan=\"2\">Session: "+name+"</td></tr>";
		s += "<tr><td>Date: "+date+"</td>";
		s += "<td>Time: "+time+"</td></tr>";
		s += "<tr><td>Duration: "+duration+"</td>";
		s += "<td>Lecture: "+lect_name+"</td></tr>";
		s += "<tr><td>Venue: "+venue+"</td>";
		s += "<td>Compulsory: "+compulsory+"</td></tr>";
		return s;
	}
	
	public String toSignUp(){
		String s = "";
		s+="Session: "+name+" ";
		s+="Time: "+time+" ";
		s+="Venue: "+venue+" ";
		s+="Lecturer: "+lect_name+" ";
		s+="Compulsory: "+compulsory;
		s+=" ("+current_attendance+"/"+max_attendance+")";
		return s;		
	}
	
	public String toConflict(){
		String s = "Overlap ";
		s+=name+" - ";
		s+=date+" ";
		s+=time+" ";
		s+=duration+"min(s) ";
		s+=venue+"\\n\\n";
		return s;		
	}
	
	public String getDate(){
		return date;
	}

	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}

	public String getFreq2() {
		return freq2;
	}

	public String getCompulsory() {
		return compulsory;
	}

	public String getVenue() {
		return venue;
	}

	public String getLect_name() {
		return lect_name;
	}

	public int getDuration() {
		return duration;
	}

	public int getFreq1() {
		return freq1;
	}

	public int getMax_attendance() {
		return max_attendance;
	}
	
	public boolean isFull(){
		return current_attendance == max_attendance;
	}
	
	public void addAttendance(){
		current_attendance++;
	}	
}
