import java.util.*;
import java.util.Map.Entry;


public class Student extends User {

	private HashMap<String, Modules> myModules;

	public Student(String firstname, String surname, String userId,	String password, UserType userType) {
		super(firstname, surname, userId, password, userType);
		this.myModules = new HashMap<String, Modules>();
	}	
	
	public void addAssignment(String modulesName, String assignName, Assignment a){
		myModules.get(modulesName).addAssignment(assignName, a);
	}
	
	public void toDisplay(){
		super.toDisplay();
		
	}
	
	public void addModules(String modulesName, Modules m){
		myModules.put(modulesName, m);
	}
	
	
	
	public HashMap<String, Modules> getMyModules() {
		return myModules;
	}

	public void toDisplayWithModules(boolean withExtra){
		//this.toDisplay();
		for(Entry<String, Modules> m : myModules.entrySet()) {
			m.getValue().toDisplay(withExtra);
		}
	}
	@Override
	public void myMenu(){
		
	}	
}
