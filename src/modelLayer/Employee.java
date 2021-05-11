package modelLayer;

/* This class holds the information on the Employees
 * in the system. 
 * 
 */
public class Employee {
	private long CPR;
	private String name;
	private String address;
	private int phoneNumber;
	private String email;
	private String position; //such as manager, store clerk, student aid etc. 

	public Employee(long CPR, String name, String address, int phoneNumber, String email, String position) {
		this.CPR = CPR;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.position = position;
	}

	public long getCPR() {
		return CPR;
	}

	public void setCPR(long cPR) {
		CPR = cPR;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
