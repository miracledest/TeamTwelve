import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Modules {

	private HashMap<String, Assignment> myAssignment;
	private ArrayList<Session> mySession;
	private Grade moduleGrade;
	private String moduleName;
	private ArrayList<Tutor> moduleTutor;
	
	public Modules(Grade moduleGrade, String moduleName) {
		super();
		this.myAssignment = new HashMap<String, Assignment>();
		this.mySession = new ArrayList<Session>();
		this.moduleGrade = moduleGrade;
		this.moduleName = moduleName;
	}

	
	
	public HashMap<String, Assignment> getMyAssignment() {
		return myAssignment;
	}

	public void addAssignment(String assignName, Assignment assign){
		this.myAssignment.put(assignName, assign);
	}
	
	public ArrayList<Session> getMySession() {
		return mySession;
	}
	
	public void addSession (Session session){
		this.mySession.add(session);
	}

	public Grade getModuleGrade() {
		return moduleGrade;
	}

	public void setModuleGrade(Grade moduleGrade) {
		this.moduleGrade = moduleGrade;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public ArrayList<Tutor> getModuleTutor() {
		return moduleTutor;
	}

	public void addTutor (Tutor tutor){
		this.moduleTutor.add(tutor);
	}
	
	public void toDisplay(boolean withExtra){
		if (withExtra){
			System.out.println("Module: " + moduleName + " (GR: "+moduleGrade.getGrade()+")");
			for(Entry<String, Assignment> a : myAssignment.entrySet()) {
				a.getValue().toDisplay(withExtra);
			}
		}else{
			System.out.println("Module: " + moduleName);	
			for(Entry<String, Assignment> a : myAssignment.entrySet()) {
				a.getValue().toDisplay(withExtra);
			}
		}
	}
	
	
}
