
package TimsShop.Controllers;

import TimsShop.Models.UserModels.Employee;
import javafx.collections.ObservableList;

/**************************************************************************
 * The LoginController class defines the initial login validation logic
 * while also providing a light-weight initialization of the  Application
 **************************************************************************/
public class LoginController
{    
  
    private ObservableList<Employee> employeeList;
    public LoginController(ObservableList<Employee> employees)
    {
        this.employeeList = employees;
    } 

  /************************************************************
      Checks against valid login credentials based on the 
      user entered value
     * @param staffId - user entered value
     * @return - whether a succesful staff login was entered
     *************************************************************/
    public boolean checkLogin(String staffId) 
    { // TODO: check against pool of loaded staff-logins
        if(staffId.equals("1234")) //<----------TEST LOGIN
        {
            processLogin();
            return true;
        }
        return false;
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
        ViewLoader.getInstance().getStage(Views.MAIN).setOnCloseRequest( evt ->
        {
            ApplicationController.getInstance().closeApplication();
        });
    }
}
