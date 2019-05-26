public class Employee
{
	private String firstName;
	private String lastName;
    private String email;
    private final long userID;
	private int loginPin;
    private long employeeNum;
    
    public Employee(long uID, String firstName, String lastName, String email, int loginPin, long employeeNum)
    {
        this.userID = uID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.loginPin = loginPin;
        this.employeeNum = employeeNum;
    }
	
	/****************GETTERS**********************/
	public String getFirstName() 
    {
        return firstName;
    }

    public long getUserID()
    {
        return userID;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public String getEmail() 
    {
        return email;
    }
       
    public long getENumber()
    {
        return employeeNum;
    }

	
	/****************SETTERS**********************/
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    @Override
    public String toString() 
    {
        return firstName+","+lastName+","+email+",";
    }
	
	public boolean checkLogin(int loginPin) 
    {
       return loginPin == this.loginPin;
    }
	
}

