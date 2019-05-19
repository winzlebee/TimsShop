package TimsShop.Models.UserModels;

import java.util.ArrayList;




public class Customer extends User
{
    private String dateOfJoining;
    private long phoneNum;
    float storeCredit;
    boolean isMember;
    private ArrayList<String> interests;


    public Customer(long uID ,String firstName, String lastName, String email, long phoneNum, String dateOfJoining,  float storeCredit, boolean isMember, ArrayList<String> interests)
    {
        super(uID,firstName, lastName, email);
        this.phoneNum = phoneNum;
        this.storeCredit = storeCredit;
        this.isMember = isMember;
        this.dateOfJoining = dateOfJoining;
        this.interests = new ArrayList<>(interests);
    }
    
    /************************GETTERS************************/
    public long getPhoneNum()
    {
        return phoneNum;
    }

    public String getDateOfJoining()
    {
        return dateOfJoining;
    }

    public float getStoreCredit()
    {
        return storeCredit;
    }

    public boolean isMember()
    {
        return isMember;
    }
    
    public ArrayList<String> getInterests()
    {
        return interests;
    }
    
    public String interestsToString()
    {
        String str="";
        if(interests != null)
        {
           
            for(String s: interests)
            {
                str += s +", ";
            }
        }
     
        return str;
    }
 
    /******************SETTERS********************************/

    public void setDateOfJoining(String dateOfJoining)
    {
        this.dateOfJoining = dateOfJoining;
    }

    public void setStoreCredit(float storeCredit)
    {
        this.storeCredit = storeCredit;
    }

    public void setIsMember(boolean isMember)
    {
        this.isMember = isMember;
    }
    
    
    
/*
    private void addInterest(Stri c)
    {
        interests.add(c);
    }
    
    private boolean removeInterest(Long cID)
    {
       return interests.removeIf(category -> category == cID);
           
    }
*/

    public void setPhoneNum(long phoneNum)
    {
        this.phoneNum = phoneNum;
    }
    

    
    
  
    
    
    
    
}
