
package TimsShop.Models.UserModels;


public class Employee extends User
{
    private int loginPin;
    private long employeeNum;
    
    public Employee(String firstName, String lastName, String email, int loginPin, long employeeNum)
    {
        super(firstName, lastName, email);
        this.loginPin = loginPin;
        this.employeeNum = employeeNum;
    }
       
    public long getENumber()
    {
        return employeeNum;
    }

    @Override
    public boolean checkLogin(int loginPin) 
    {
       return loginPin == this.loginPin;
    }
    
}
