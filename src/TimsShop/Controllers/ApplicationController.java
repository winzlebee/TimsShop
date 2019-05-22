
package TimsShop.Controllers;

/**************************************************************************************
The ApplicationController class is responsible for the Application scoped functions
 such as Starting, Closing The application.
 
 *The ApplicationController also behaves as an intermediary structure 
  between the ViewLoader the the respective Views 
   of the System
 ***********************************************************************************/
public class ApplicationController
{
       
     /*********************CLASS FIELDS************************/
    /////////////////////////////////////////////////////////
    private DBQueryProcessor queryProcessor;
    private LoginController loginController;
   
    private ApplicationController()
    {
        queryProcessor = new DBQueryProcessor();
        loginController = new LoginController(queryProcessor.getStorage().getEmployees());
        
    }
     /*************SINGLETON INSTANTIATION***************/
    /////////////////////////////////////////////////////////
    private static ApplicationController INSTANCE; 
    public static ApplicationController getInstance()
    {
        return INSTANCE == null ? INSTANCE = new ApplicationController(): INSTANCE;
    }
    
    /**********************APPLICATION METHODS****************************/
    /////////////////////////////////////////////////////////////////////
    //VIEW HANDLERS
    /********************************************************************
     * Write the current state of observable lists to persistent storage
     * and closes all open views
     *****************************************************************/
    public void closeApplication()
    {
        queryProcessor.writeToStorage();
        ViewLoader.getInstance().notifyAllToClose();
    }
    
    /********************************************************************
    * Starts the application via displaying the LoginView to the user
    *****************************************************************/
    public void startApplication()
    {
        ViewLoader.getInstance().load(Views.LOGIN);
        ViewLoader.getInstance().show(Views.LOGIN);
    }
    /********************************************************************
    * Calls the ViewLoader to display a desired View.
     * @param view the view wished to be displayed
     * NOTE: In order to be able to set the storage of a View,
       the View MUST first implement the StorageSettable interface and
       ALL Abstract methods. (namely  setStorage)
    *****************************************************************/
    public void display(Views view)
    {
        ViewLoader.getInstance().load(view);
        ViewLoader.getInstance().show(view);
        ((StorageSettable)ViewLoader.getInstance().getController(view)).setStorage(queryProcessor.getStorage());  
    }
    
    
    public void closeView(Views view)
    {
         ViewLoader.getInstance().close(view);
    }
    
    
    //CONTROLLER HANDLERS
    /************************************************************
    * Allow for public access for login controller to the login view
    * @return  the active login controller
    ************************************************************/
    public LoginController getLoginController()
    {
        return loginController;
    }
}


    
   
