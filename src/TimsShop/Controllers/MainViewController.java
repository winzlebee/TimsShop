
package TimsShop.Controllers;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.UserModels.Employee;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainViewController  implements AbstractController
{

  
     /******************************************************************
    * Helper class to initialize the singleton Service in a thread-safe way and
    * to keep the initialization ordering clear between the two services. 
    *****************************************************************/
    private static class ControllerInstance
    {
        public static MainViewController INSTANCE = createControllerInstance();
        private ControllerInstance(){} // This class is not meant to be instantiated
        public static MainViewController createControllerInstance()
        {
            return new MainViewController();
        }
    }
    
    public static MainViewController getInstance()
    {
        return ControllerInstance.INSTANCE;
    }
        
    private Stage currentStage;
    private Stage nextStage;
    private ShopDataStorage shopData; 
    private ObservableList<Employee> employees; //static data? or loaded on application controller
    private Parent source; 
    
    
    //Each controller has a listing of other controllers that may need to be invoked
    
    /***************************CONTROLLER lIST*********************************/
    private static LoginController loginController = LoginController.getInstance();
    
    
    public void logOut() throws IOException
    {
        /*********************************
         TODO:
          * Report staff member logged in 
        **********************************/
        closeView(); //
        setNextSource((Parent)FXMLLoader.load(getClass().getResource("/TimsShop/FXML/EmployeeLoginView.fxml")));
        openNext();
        passControl(loginController);
    }
    
    public void insertToy() throws IOException
    {
        setNextSource((Parent)FXMLLoader.load(getClass().getResource("/TimsShop/FXML/InsertToyDialog.fxml")));
        openNext();
    }
    
    @Override
    public void closeView() 
    {
        currentStage.close();
    }

    @Override
    public void openNext() throws IOException 
    {
        //Load  MainView Stage FXML
        nextStage = new Stage(); //  depends multiple branches here
        Scene scene = new Scene(source);
        nextStage.setScene(scene);
        nextStage.show();
    }
    
    //Sets the file path for next
    @Override
    public void setNextSource(Parent source)
    { 
        this.source = source;
    }
    
    //sets the current stage
    @Override
    public void setCurrentStage(Stage stage) 
    {
       this.currentStage = stage;
    }

    @Override
    public void passControl(AbstractController controller) 
    {     
        controller.setCurrentStage(nextStage);
    }
    
    
}