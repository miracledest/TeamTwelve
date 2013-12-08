package com.psd3.ge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

	public static boolean login = false;
	public static String userType = "";
	public static String userName = "";
	public static String delimiter = "/--/--/";
	
	public static ArrayList<Session> mySession = new ArrayList<Session>();
	
	public static void saveData (){
		try {
			PrintWriter out = new PrintWriter("mySession.db");
			for (Session s : mySession){
				out.write(s.toSave());
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadData(){
		if (!new File("mySession.db").isFile()){
			System.out.println("Initialize New Datebase.");
			clearData();
		}
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("mySession.db"));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] tempA = sCurrentLine.split(delimiter);
				mySession.add(new Session(tempA[0], tempA[1], tempA[2], tempA[3], tempA[4], tempA[5], Integer.parseInt(tempA[6]), Integer.parseInt(tempA[7]), tempA[8], Integer.parseInt(tempA[9])));
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
	
	public static void clearData(){
		try {
			PrintWriter out = new PrintWriter("mySession.db");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}

}
