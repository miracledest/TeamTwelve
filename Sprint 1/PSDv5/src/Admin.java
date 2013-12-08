
public class Admin extends User {

	public Admin(String firstname, String surname, String userId,
			String password, UserType userType) {
		super(firstname, surname, userId, password, userType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void myMenu() {
		System.out.println("----- Admin Main Menu -----");
		System.out.println("1. Export all student attendance records for a single course");
		System.out.println("2. Export all recorded information for a single student");
		System.out.println("0. Exit\n---------------------------\nChoose an option:");
	}
	
	
	
}
