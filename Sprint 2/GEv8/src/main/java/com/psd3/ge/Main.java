package com.psd3.ge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Main {

	public static boolean login = false;
	public static String userType = "";
	public static String name = "";
	public static String username = "";
	public static String delimiter = "/--/--/";
	
	public static Hashtable<String, ArrayList<Session>> mySession = new Hashtable<String, ArrayList<Session>>();
	public static Hashtable<String, User> listOfUser = new Hashtable<String, User>();
	
	public static void saveMySessionData (){
		try {
			PrintWriter out = new PrintWriter("mySession.db");
			for (Map.Entry<String, ArrayList<Session>> entry : mySession.entrySet()){
				for (Session s : entry.getValue())
				out.write(s.toSave());
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadMySessionData(){
		if (!new File("mySession.db").isFile()){
			System.out.println("Initialize New Datebase - mySession");
			createNewMySession();
		}
		mySession.clear();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("mySession.db"));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] tempA = sCurrentLine.split(delimiter);
				if (mySession.get(tempA[0])==null){				
					mySession.put(tempA[0], new ArrayList<Session>());					
				}
				mySession.get(tempA[0]).add(new Session(tempA[0], tempA[1], tempA[2], tempA[3], tempA[4], tempA[5], Integer.parseInt(tempA[6]), Integer.parseInt(tempA[7]), tempA[8], Integer.parseInt(tempA[9]), Integer.parseInt(tempA[10])));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void createNewMySession(){
		try {
			PrintWriter out = new PrintWriter("mySession.db");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}

	///////////////////////////////////////////////////////////////////////////////////
	
	public static void saveMyAccount (){
		try {
			PrintWriter out = new PrintWriter("myAccount.db");
			for (Map.Entry<String, User> entry : listOfUser.entrySet()){
				out.write(entry.getValue().toSave());
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadMyAccount(){
		if (!new File("myAccount.db").isFile()){
			genAccount();
			System.out.println("Initialize New Datebase - myAccount");
			saveMyAccount();
		}
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("myAccount.db"));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] tempA = sCurrentLine.split(delimiter);
				listOfUser.put(tempA[0],new User(tempA[0], tempA[1], tempA[2], tempA[3]));
				for (int i = 4; i < tempA.length; i++){
					listOfUser.get(tempA[0]).getMyAttendSession().add(tempA[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void createNewMyAccount(){
		try {
			PrintWriter out = new PrintWriter("myAccount.db");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public static void genAccount(){
		listOfUser.clear();
		listOfUser.put("admin", new User("admin", "admin", "admin", "Administrator"));
		listOfUser.put("larry", new User("larry", "larry", "user", "Larry Tan"));
		listOfUser.put("jack", new User("jack", "jack", "user", "Jack"));
		listOfUser.put("shijie", new User("shijie", "shijie", "user", "Shi Jie"));
		listOfUser.put("zhiyong", new User("zhiyong", "zhiyong", "user", "Zhi Yong"));
	}	
}
