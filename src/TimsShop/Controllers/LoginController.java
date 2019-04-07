
package TimsShop.Controllers;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.UserModels.Employee;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements AbstractController
{

 
    /******************************************************************
    * Helper class to initialize the singleton Service in a thread-safe way and
    * to keep the initialization ordering clear between the two services. 
    *****************************************************************/
    private static class ControllerInstance
    {
        public static LoginController INSTANCE = createControllerInstance();
        private ControllerInstance(){} // This class is not meant to be instantiated
        public static LoginController createControllerInstance()
        {
            return new LoginController();
        }
    }
     /***************************CONTROLLER lIST*********************************/
    private static MainViewController mainViewController = MainViewController.getInstance();
    
    private Stage currentStage;
    private Stage nextStage;
    private ShopDataStorage shopData; 
    private ObservableList<Employee> employees; //static data? or loaded on application controller
    
    
    public static LoginController getInstance()
    {
        return ControllerInstance.INSTANCE;
    }
    
    private LoginController()
    {}
    
    @Override
    public void setCurrentStage(Stage stage)
    {
        this.currentStage = stage;
    }
    public void makeLoginRequest() throws IOException
    {
         /*********************************
             TODO:
              * Report staff member logged in 
            **********************************/
            closeView();
            openNext();
            passControl(mainViewController);
    }
    public boolean checkLogin(String loginPin) throws Exception
    { 
   
        //TEST  LOGIN - REMOVE LATER
        if(loginPin.equals("1234")){makeLoginRequest();return true;} 
        
        /*  UNCOMMENT WHEN DATA IS LOADED IN
            //Check for each employee loaded from data storage
            if(!employees.isEmpty() && employees != null)
            {
                for(Employee e: employees)
                {   
                    if(e.checkLogin(loginPin))
                    {
                        return true;
                    }
                }  
            }
            */
       
        return false;
    }
    
    @Override
    public void closeView()
    {
        //Close login stage
        currentStage.close();
    }
    
    @Override
    public void openNext() throws IOException
    {
       //Load  MainView Stage FXML
       nextStage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/TimsShop/FXML/MainView.fxml"));
       Scene scene = new Scene(root);
       nextStage.setScene(scene);
       nextStage.show();
    
    }

    @Override
    public void passControl(AbstractController nextController) 
    {
        nextController.setCurrentStage(nextStage);
    }
    
    
    
    @Override
    public void setNextSource(Parent nextStage) {}
  
}
