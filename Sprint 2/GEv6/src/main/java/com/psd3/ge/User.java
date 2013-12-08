package com.psd3.ge;

import java.util.ArrayList;

public class User {
	
	String username, password, userType, name;
	ArrayList<String> myAttendSession;

	public User(String username, String password, String userType, String name) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.name = name;
		this.myAttendSession = new ArrayList<String>();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUserType() {
		return userType;
	}

	public String getName() {
		return name;
	}
	
	public String toSave(){
		
		String saveStr = "";
		
		saveStr += username+Main.delimiter;
		saveStr += password+Main.delimiter;
		saveStr += userType+Main.delimiter;
		if (myAttendSession.size() == 0){
			saveStr += name+"\n";
		}else{
			saveStr += name+Main.delimiter;
			for (String s : myAttendSession){
				saveStr += s+Main.delimiter;
			}
			saveStr += "\n";
		}
		
		return saveStr;
	}

	public ArrayList<String> getMyAttendSession() {
		return myAttendSession;
	}
	
	
}
