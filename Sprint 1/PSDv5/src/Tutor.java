import java.util.*;


public class Tutor extends User {

	public Tutor(String firstname, String surname, String userId,
			String password, UserType userType) {
		super(firstname, surname, userId, password, userType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void myMenu() {
		System.out.println("----- Tutor Main Menu -----");
		System.out.println("1. Mark Attendance");
		System.out.println("2. View Attendance");
		System.out.println("3. Import all student attendance records for a single course");
		System.out.println("4. Export all student attendance records for a single course");
		System.out.println("0. Exit\n---------------------------\nChoose an option:");
	}
 
	
	
}
