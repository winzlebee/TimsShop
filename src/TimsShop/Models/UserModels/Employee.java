
package TimsShop.Models.UserModels;


public class Employee extends User
{
    private String loginPin, employeeNum;
    
    public Employee(String firstName, String lastName, String email, 
            String loginPin, String employeeNumber)
    {
        super(firstName, lastName, email);
        this.loginPin = loginPin;
        this.employeeNum = employeeNum;
    }
       
    public String getENumber()
    {
        return employeeNum;
    }

    @Override
    public boolean checkLogin(String loginPin) 
    {
       return loginPin == this.loginPin;
    }
    
}
