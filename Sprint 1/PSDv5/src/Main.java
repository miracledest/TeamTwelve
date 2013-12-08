import java.io.BufferedReader;
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
		myMain.genData();
		while(!myMain.displayLogin()){
		}
		myMain.userOption(myUser.get(loginID).getUserType(), loginID);
	}
	
	public boolean displayLogin(){
		String username, password;
		System.out.println("--- Login ---");		
		System.out.println("Username: ");
		username = in.nextLine().trim();
		System.out.println("Password: ");
		password = in.nextLine();
		
		if (!username.equals("") && !password.equals("")){		
			if (myUser.get(username) != null){
				if (myUser.get(username).getPassword().equals(password)){
					System.out.print("Welcome! ");
					myUser.get(username).toDisplay();
					System.out.println();
					loginID = username;					
					return true;
				}
			}
		}
		System.out.println("Invalid Login!");
		return false;
	}
	
	private void genData(){
		
		moduleList.put("PSD3", new Modules(new Grade(100), "PSD3"));
		
		attOption.put(1, "present");
		attOption.put(2, "absent");
		attOption.put(3, "mv");
		
		
		myUser.put("1000001", new Student("Mary","Watson","1000001","1000001", UserType.STUDENT));
		myUser.put("1000002", new Student("John","Smith","1000002","1000002", UserType.STUDENT));
		myUser.put("1000003", new Student("May","Clare","1000003","1000003", UserType.STUDENT));
		myUser.put("1000004", new Student("Wilson","Chai","1000004","1000004", UserType.STUDENT));
		myUser.put("1000005", new Student("Nelson","Koh","1000005","1000005", UserType.STUDENT));
		myUser.put("1000006", new Admin("Larry","Tan","1000006","1000006", UserType.ADMIN));
		myUser.put("1000007", new Tutor("Larry","Tan","1000007","1000007", UserType.TUTOR));
		
		
		for(Entry<String, User> u : myUser.entrySet()) {
			if (u.getValue().getUserType() == UserType.STUDENT){
				for (Entry<String, Modules> m : moduleList.entrySet()){
					((Student)u.getValue()).addModules(m.getKey(), new Modules(new Grade(100), m.getKey()));
					for (int i = 1; i < 6; i++){						
						((Student)u.getValue()).addAssignment(m.getKey(), "LAB"+i, new Assignment(new Grade(100), "Laboratory "+i));
						moduleList.get("PSD3").addAssignment("LAB"+i, new Assignment(new Grade(100), "Laboratory "+i));
						assignmentList.put("LAB"+i, new Assignment(new Grade(100), "Laboratory "+i));
					}
				}				
			}
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
					this.listModule();
					System.out.println("Please select a module: ");
					i = this.getUserInput();
					if (tempOption.get(i)!= null){
						System.out.println("Exporting selected module......");
						Modules m = ((Modules)moduleList.get(tempOption.get(i)));
						ArrayList<String> csvLine = new ArrayList<String>();
						String header = "First Name,Surname,ID";
						for (Entry<String, Assignment> a : m.getMyAssignment().entrySet()){
							header += ","+a.getValue().getAssignName();
						}
						csvLine.add(header+"\n");
						for (Entry<String, User> u : myUser.entrySet()){
							if (u.getValue().getUserType() == UserType.STUDENT){
								if (((Student)u.getValue()).getMyModules().get(m.getModuleName())!=null){
									String s = u.getValue().getFirstname()+","+u.getValue().getSurname()+","+u.getValue().getUserId();
									for (Entry<String, Assignment> a : ((Student)u.getValue()).getMyModules().get(m.getModuleName()).getMyAssignment().entrySet()){
										s += ","+a.getValue().getAttendance();
									}
									csvLine.add(s+"\n");
								}
							}
						}
						this.exportCSV(m.getModuleName(), csvLine);
					}
					tempOption.clear();
					break;
				case 2:
					this.listStudent();
					System.out.println("Please select a student: ");
					i = this.getUserInput();
					
					if (tempOption.get(i)!= null){
						System.out.println("Exporting select student......");
						Student s = ((Student)myUser.get(tempOption.get(i)));
						ArrayList<String> csvLine = new ArrayList<String>();
						csvLine.add("First Name:,"+s.getFirstname()+"\nSurname:,"+s.getSurname()+"\nID:,"+s.getUserId()+"\nTYPE:,"+s.getUserType()+"\n\n");
						for (Entry<String, Modules> m : s.getMyModules().entrySet()){
							csvLine.add("Module:,"+m.getValue().getModuleName()+",GRADE:,"+m.getValue().getModuleGrade().getGrade()+"\n");
							for (Entry<String, Assignment> a : m.getValue().getMyAssignment().entrySet()){
								csvLine.add("Assignment:,"+a.getValue().getAssignName()+",GRADE:,"+a.getValue().getAssignGrade().getGrade()+",ATTENDANCE:,"+a.getValue().getAttendance()+"\n");
							}
						}
						this.exportCSV(s.getUserId(), csvLine);
					}
					
					tempOption.clear();
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
					this.listAssignment();
					System.out.println("Please select a assignment: ");
					i = this.getUserInput();					
					if (tempOption.get(i)!= null){
						System.out.println("Attendance for "+assignmentList.get(tempOption.get(i)).getAssignName()+"\n------------------------------");
						System.out.println("Attendance option: 1. present 2. absent 3. mv\n");
						for (Entry<String, User> u : myUser.entrySet()){
							if (u.getValue().getUserType() == UserType.STUDENT){
								for (Entry<String, Modules> m : ((Student)u.getValue()).getMyModules().entrySet()){
									for (Entry<String, Assignment> a : m.getValue().getMyAssignment().entrySet()){
										if (a.getValue().getAssignName().equals(assignmentList.get(tempOption.get(i)).getAssignName())){
											System.out.println("Mark attendance for "+((Student)u.getValue()).getFirstname()+" "+((Student)u.getValue()).getSurname()+": ");
											i = -1;
											while(attOption.get(i)==null){
												i = getUserInput();
											}
											a.getValue().setAttendance(attOption.get(i));
											//System.out.println(((Student)u.getValue()).getFirstname()+" "+((Student)u.getValue()).getSurname()+" is marked as "+attOption.get(i)+"\n");
										}
									}
								}
							}
						}
					}
					break;
				case 2:
					this.listAssignment();
					System.out.println("Please select a assignment: ");
					i = this.getUserInput();					
					if (tempOption.get(i)!= null){
						System.out.println("Attendance for "+assignmentList.get(tempOption.get(i)).getAssignName()+"\n------------------------------");
						for (Entry<String, User> u : myUser.entrySet()){
							if (u.getValue().getUserType() == UserType.STUDENT){
								for (Entry<String, Modules> m : ((Student)u.getValue()).getMyModules().entrySet()){
									for (Entry<String, Assignment> a : m.getValue().getMyAssignment().entrySet()){
										if (a.getValue().getAssignName().equals(assignmentList.get(tempOption.get(i)).getAssignName())){
											System.out.println("("+a.getValue().getAttendance()+") "+((Student)u.getValue()).getFirstname()+" "+((Student)u.getValue()).getSurname());
										}
									}
								}
							}
						}
						System.out.println("------------------------------\n");
					}
					break;
				case 3:
					this.listModule();
					System.out.println("Please select a module: ");
					i = this.getUserInput();
					if (tempOption.get(i)!= null){
						System.out.println("Importing selected module......");
						Modules m = ((Modules)moduleList.get(tempOption.get(i)));
						String csvFile=m.getModuleName()+".csv";
						BufferedReader br = null;
						String line = "";
						String split = ",";
						try {
							 
							br = new BufferedReader(new FileReader(csvFile));
							while ((line = br.readLine()) != null) {
					 
							        // use comma as separator
								String[] stud = line.split(split);
								int count = 3;
								for(Entry<String, User> u : myUser.entrySet()) {
									//System.out.println(u.getValue().getUserId());
									if (u.getValue().getUserId().equals(stud[2])){
										for (Entry<String, Assignment> a : ((Student)u.getValue()).getMyModules().get(m.getModuleName()).getMyAssignment().entrySet()){
											if (stud[count].equals("0") || stud[count].equals("1") ){
												a.getValue().setAttendance(stud[count]);
											}
											count ++;
										}
									}
								}
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (br != null) {
								try {
									br.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
					tempOption.clear();
					break;
				case 4:
					this.listModule();
					System.out.println("Please select a module: ");
					i = this.getUserInput();
					if (tempOption.get(i)!= null){
						System.out.println("Exporting selected module......");
						Modules m = ((Modules)moduleList.get(tempOption.get(i)));
						ArrayList<String> csvLine = new ArrayList<String>();
						String header = "First Name,Surname,ID";
						for (Entry<String, Assignment> a : m.getMyAssignment().entrySet()){
							header += ","+a.getValue().getAssignName();
						}
						csvLine.add(header+"\n");
						for (Entry<String, User> u : myUser.entrySet()){
							if (u.getValue().getUserType() == UserType.STUDENT){
								if (((Student)u.getValue()).getMyModules().get(m.getModuleName())!=null){
									String s = u.getValue().getFirstname()+","+u.getValue().getSurname()+","+u.getValue().getUserId();
									for (Entry<String, Assignment> a : ((Student)u.getValue()).getMyModules().get(m.getModuleName()).getMyAssignment().entrySet()){
										s += ","+a.getValue().getAttendance();
									}
									csvLine.add(s+"\n");
								}
							}
						}
						this.exportCSV(m.getModuleName(), csvLine);
					}
					tempOption.clear();
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
	
	private void exportCSV(String filename, ArrayList<String> line){
		try
		{
		    FileWriter writer = new FileWriter(filename+".csv");
	 
		    for (String s : line){
		    	writer.append(s);
		    }
	 
		    writer.flush();
		    writer.close();
		    System.out.println("Exporting Completed.\n");
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		}
	}
}
