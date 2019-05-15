
package TimsShop.Models.UserModels;

import java.io.Serializable;


public abstract class User implements Serializable
{
    
    protected String firstName;
    protected String lastName;
    protected String email;
    protected final long userID;


    
    public User(long uID, String firstName, String lastName, String email) 
    {
        this.userID = uID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
    
    /***********************************************
        * Staff login may just involve a staff pin
        * Customer login may need username/password
    ************************************************/
    public boolean checkLogin(int loginPin)
    {
        return false;
    }

    
    
    
   
}
