
package TimsShop.Controllers.CustomerControllers;

import TimsShop.Controllers.CallBackEvt;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.UserModels.Customer;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class CustomerController
{
    private ObservableList<Customer> customers;
    private ObservableList<Category> categories;
    private ShopDataStorage storage;
    private Customer selectedCustomer;
    
    private CallBackEvt refreshEvent;
   
    public CustomerController(ShopDataStorage storage)
    {
        this.customers = storage.getCustomers();
        this.categories = storage.getCategories();
        this.storage = storage;
    }
    
    /*****************STORAGE PROCEDURES***********************/
    ////////////////////////////////////////////////////////////
    /***********************************************
     * @param c the customer requested to be deleted
     * @return whether that delete was successfully made
     *************************************************/
    public boolean requestDelete(Customer c)
    {
        return customers.remove(c);
    }
    
    public void addCustomer(String fName, String lName, String email, long phone, String date, float credit, boolean isMember, ArrayList<Long> interests)
    {
        storage.addCustomer(fName, lName, email, phone, date, credit, isMember, interests);
    }
    
    /*********************************************************
     * User data to be updated
     * @param fName
     * @param lName
     * @param email
     * @param phoneNum
     * @return whether a not-null customer has been selected
     * NOTE: A customer is ONLY ever null if the ModifyCustomer 
        view is opened with unintended  system behaviour 
     **************************************************************************/
    public boolean updateCustomer(String fName, String lName, String email, String phoneNum)
    {
        if(selectedCustomer != null)
        {
            selectedCustomer.setFirstName(fName);
            selectedCustomer.setLastName(lName);
            selectedCustomer.setEmail(email);
            selectedCustomer.setPhoneNum(Long.parseLong(phoneNum));   
            refreshEvent.callBack();
            return true;
        }
        return false;        
    }
    
    /*****************GETTERS***********************/
    ///////////////////////////////////////////////////
    public ObservableList<Customer> getCustomers()
    {
        return customers;
    }
    
    public ObservableList<Category> getCategories()
    {
        return categories;
    }
    
    public Customer getCustomer()
    {
        return selectedCustomer;
    }
    
    public Customer getCustomerById(long id)
    {
        return customers.filtered((c) -> c.getUserID() == id).get(0);
    }
    
    /*****************SETTERS***********************/
    ///////////////////////////////////////////////////
     public void setCustomerForModification(Customer c)
     {
        System.out.println("CUSTOMER INFO IN CONTROLLER");
         System.out.println(c.toString());
         this.selectedCustomer = c;
     }
     
     public void setRefreshCallBack(CallBackEvt evt)
     {
         this.refreshEvent = evt;
     }
     
    /*****************VALIDATORS***********************/
    ///////////////////////////////////////////////////
    public boolean isValidForm(String fName, String lName, String email, String phoneNum)
    {   //First name field
        if(fName.isEmpty() || fName.equals("".trim()))
        {
             return false;
        }
        //last name field
        if(lName.isEmpty() || fName.equals("".trim()))
        {
            return false;
        }
        //Email field
        if(email.isEmpty() || fName.equals("".trim()))
        {
            return false;
        }
        //Phone number field
        if(phoneNum.isEmpty()|| fName.equals("".trim()))
        {
            return false;
        }
        return true;
    }
    
    
}
