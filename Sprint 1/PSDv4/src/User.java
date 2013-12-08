
public abstract class User {
	
	private String firstname, surname;
	private String userId;
	private String password;
	private UserType userType;
	
	public User(String firstname, String surname, String userId, String password, UserType userType) {
		super();
		this.firstname = firstname;
		this.surname = surname;
		this.userId = userId;
		this.password = password;
		this.userType = userType;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}
	
	public UserType getUserType() {
		return userType;
	}

	public void toDisplay(){
		System.out.println(firstname +" " + surname + "("+userId+") " + userType);
	}
	
	public abstract void myMenu();
	
}
