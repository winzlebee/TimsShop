
package TimsShop.Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MainView implements  Initializable  
{
        
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Button orderingButton;
    @FXML
    private Button browseButton;
    @FXML
    private Button logoutButton;
    

    
    /**********************************************************************
    Function: called to initialize a controller after its 
              root element has been completely processed.
    Arguments:  @param url locator for FXML document.
                @param rb bundles the locally configured language settings
    ************************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    } 

    @FXML
    private void logoutHandler(MouseEvent event) throws IOException
    {
             /*********************************
                TODO:
                 * Report staff member logged in 
                 * Close login stage- DONE
            **********************************/
            //Close login stage
            Stage currentStage = (Stage)logoutButton.getScene().getWindow();
            currentStage.close();
           
            //Load Main Stage FXML
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/TimsShop/FXML/EmployeeLoginView.fxml"));
            Scene scene = new Scene(root);
            loginStage.setScene(scene);
            loginStage.show();
    }
    
}
