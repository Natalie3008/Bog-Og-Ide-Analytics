package modelLayer;

public class Supplier {

	private int CVR;
	private String name;
	private String contactPerson;
	private String address;
	private String phoneNumber;
	private String email;
	private String productCategory;

	public Supplier(int CVR, String name, String contactPerson, String address, String phoneNumber, String email,
			String productCategory) {
		this.CVR = CVR;
		this.name = name;
		this.contactPerson = contactPerson;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getCVR() {
		return CVR;
	}

	public void setCVR(int cVR) {
		CVR = cVR;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

}
