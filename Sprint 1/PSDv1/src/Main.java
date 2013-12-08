import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class Main {
	
	private static ArrayList<String> readData = new ArrayList<String>();
	private static HashMap<String, Assignment> assignmentList = new HashMap<String, Assignment>();
	private static HashMap<String, Modules> moduleList = new HashMap<String, Modules>();
	private static HashMap<Integer, Object> tempOption = new HashMap<Integer, Object>();
	private static HashMap<Integer, String> attOption = new HashMap<Integer, String>();
	public static HashMap<String, User> myUser = new HashMap<String, User>();
	private static Scanner in = new Scanner(System.in);
	private static String loginID = "1000007";
	
	public static void main(String[] args) throws IOException {
		
		Main myMain = new  Main();
	}
	

	
	private void readCSV (String filename) throws FileNotFoundException{
		FileReader reader = new FileReader(filename);
		Scanner in = new Scanner(reader);
		while (in.hasNext()){
			readData.add(in.nextLine());
		}
	}
	
	
	
	private void listAssignment(){
		int i = 1;
		System.out.println("----- All Assignment -----");
		for (Entry<String, Assignment> a : assignmentList.entrySet()){
			tempOption.put(i, a.getKey());
			System.out.print(i+". ");
			a.getValue().toDisplay(false);
			i++;
		}
		System.out.println("-----------------------");
	}
	
	private void listModule(){
		int i = 1;
		System.out.println("----- All Modules -----");
		for (Entry<String, Modules> m : moduleList.entrySet()){
			tempOption.put(i, m.getKey());
			System.out.print(i+". ");
			m.getValue().toDisplay(false);
			i++;
		}
		System.out.println("-----------------------");
	}
	
	private void listStudent(){
		int i = 1;
		System.out.println("----- All Students -----");
		for (Entry<String, User> u : myUser.entrySet()){
			if (u.getValue().getUserType() == UserType.STUDENT){
				tempOption.put(i, u.getKey());
				System.out.print(i+". ");
				((Student)u.getValue()).toDisplay();
			}
			i++;
		}
		System.out.println("-----------------------");
	}
	
	private void userOption(UserType userType, String ID){
		if (userType == UserType.ADMIN){
			int i = -1;
			while (i!=0){
				myUser.get(ID).myMenu();
				i = this.getUserInput();
				switch (i) {
				case 1:
					
					break;
				case 2:
					
					break;

				default:
					break;
				}
			}
			in.close();
		}else if (userType == UserType.STUDENT){
			in.close();
		}else if (userType == UserType.TUTOR){
			int i = -1;
			while (i!=0){
				myUser.get(ID).myMenu();
				i = this.getUserInput();
				switch (i) {
				case 1:
					break;
				case 2:
					break;
				default:
					break;
				}
			}
			in.close();
		}
	}
	
	private int getUserInput(){
		String s = in.nextLine().trim();
		try{
			int i = Integer.parseInt(s);
			return i;
		}catch (NumberFormatException e){
			System.out.println("Invalid input.");
			return getUserInput();
		}
	}
}
