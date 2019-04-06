
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
 
    private Stage currentStage;
    private Stage nextStage;
    private ShopDataStorage shopData;
    private ObservableList<Employee> employees;
    
    public LoginController(Stage currentStage)
    {
        this.currentStage  = currentStage; 
        this.employees     = shopData.getEmployees();
    }
    
    public void makeLoginRequest() throws IOException
    {
         /*********************************
             TODO:
              * Report staff member logged in 
            **********************************/
            openNext();
            closeView();
    }
    public boolean checkLogin(int loginPin) throws IOException
    { 
        //Check for each employee loaded from data storage
        if(!employees.isEmpty())
        {
            for(Employee e: employees)
            {   
                if(e.checkLogin(loginPin))
                {
                    makeLoginRequest();
                    return true;
                }
            }  
        }
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

  
}
