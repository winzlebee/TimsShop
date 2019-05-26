
package TimsShop.Controllers;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.UserModels.Employee;
import javafx.collections.ObservableList;

/**************************************************************************
 * The LoginController class defines the initial login validation logic
 * while also providing a light-weight initialization of the  Application
 **************************************************************************/
public class LoginController
{    
  
    private ShopDataStorage m_storage;
    
    public LoginController(ShopDataStorage storage)
    {
        m_storage = storage;
    } 

  /************************************************************
      Checks against valid login credentials based on the 
      user entered value
     * @param staffId - user entered value
     * @return - whether a succesful staff login was entered
     *************************************************************/
    public boolean checkLogin(String staffId)
    { // TODO: check against pool of loaded staff-logins
        try {
            int pin = Integer.parseInt(staffId);

            if (m_storage.getPins().contains(pin)) {
                processLogin();
                return true;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return false;
    }
    
    public void addPin(int pin) {
        m_storage.addPin(pin);
    }
    
    /************************************************************
     * Processes login and launches the main application
     * processLogin() also determines a windows close callback 
     * for the main application
     *************************************************************/
    private void processLogin() 
    {   //Display Main
        ApplicationController.getInstance().display(Views.MAIN);
        //Close Login
        ApplicationController.getInstance().closeView(Views.LOGIN);
        //Set on Close Listener
        System.out.println("Hello");
        ViewLoader.getInstance().getStage(Views.MAIN).setOnCloseRequest( evt ->
        {
            ApplicationController.getInstance().closeApplication();
        });
    }
}
