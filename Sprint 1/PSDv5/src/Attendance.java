import java.util.ArrayList;


public class Attendance {
	
	private ArrayList<Student> studentList;

	public Attendance() {
		super();
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	public void addStudent(Student student){
		studentList.add(student);
	}

}
