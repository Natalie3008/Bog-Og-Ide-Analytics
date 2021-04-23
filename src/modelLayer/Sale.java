package modelLayer;

public class Sale 
{
	private int CPR;
	private String name;
	private String address;
	private int phoneNumber;
	private String emailAddress;
	private String position;
	
	public Sale(int CPR,String name,String address,int phoneNumber,String emailAddress,String position)
	{
		this.CPR = CPR;
		this.name= name;
		this.address= address;
		this.phoneNumber= phoneNumber;
		this.emailAddress= emailAddress;
		this.position= position;
	}

	public int getCPR() 
	{
		return CPR;
	}

	public void setCPR(int CPR) 
	{
		this.CPR = CPR;
	}
	public String getname() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	public String getaddress() 
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
	public String getEmailAddress() 
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) 
	{
		this.emailAddress = emailAddress;
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
