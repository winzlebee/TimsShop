
package TimsShop.Models.UserModels;

import java.io.Serializable;


public class Supplier implements Serializable 
{
    private String businessName, address, contactPerson;
    private long phoneNum, supplierId;

    public Supplier(long supplierId, String businessName, String address, String contactPerson, long phoneNum)
    {
        this.supplierId = supplierId;
        this.businessName = businessName;
        this.address = address;
        this.contactPerson = contactPerson;
        this.phoneNum = phoneNum;
    }
    /************************GETTERS***************************/
    ///////////////////////////////////////////////////////////
    public String getBusinessName()
    {
        return businessName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getContactPerson()
    {
        return contactPerson;
    }

    public long getPhoneNum()
    {
        return phoneNum;
    }

    public long getSupplierId()
    {
        return supplierId;
    }
    
    /************************SETTERS***************************/
    ///////////////////////////////////////////////////////////
    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setContactPerson(String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public void setPhoneNum(long phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString()
    {
        return businessName;
    }
    
    
    
    
}
