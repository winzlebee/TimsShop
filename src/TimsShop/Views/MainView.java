
package TimsShop.Views;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Views.Dialogs.AddToyDialog;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
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
    private Button insertButton;
    @FXML
    private Button logoutButton;
    
    // Data storage for the application
    //ShopDataStorage storage;
    
    /**********************************************************************
    Function: called to initialize a controller after its 
              root element has been completely processed.
    Arguments:  @param url locator for FXML document.
                @param rb bundles the locally configured language settings
    ************************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       // TODO: Load list of toys from serialization
    } 
    
        
    @FXML
    private void insertHandler(MouseEvent evt) throws IOException {
        // Function to launch the dialog to insert certain items into the database
        FXMLLoader toyDialogLoader = new FXMLLoader(getClass().getResource("/TimsShop/FXML/InsertToyDialog.fxml"));
        Parent toyDialogRoot = toyDialogLoader.load();
        AddToyDialog dialog = toyDialogLoader.<AddToyDialog>getController();
        
        // Once we implement an observablelist for the toys we can use this.
        //dialog.setAppMainObservableList(tvObservableList);
        
        Scene dialogScene = new Scene(toyDialogRoot);
        Stage dialogStage = new Stage();
        
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
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
