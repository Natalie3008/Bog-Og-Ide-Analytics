package modelLayer;

public class Employee 
{
	private int CPR;
	private String name;
	private String address;
	private int phoneNumber;
	private String position;

	
	public Employee (int CPR, String name, String address, int phoneNumber, String position)
	{
		this.CPR = CPR;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.position = position;
	}

	
	public int getCPR() 
	{
		return CPR;
	}
	
	public void setCPR(int cPR) 
	{
		CPR = cPR;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public int getPhoneNumber() 
	{
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	
	public String getPosition() 
	{
		return position;
	}
	
	public void setPosition(String position) 
	{
		this.position = position;
	}

}
