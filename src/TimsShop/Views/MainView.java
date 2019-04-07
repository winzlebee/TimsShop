
package TimsShop.Views;


import TimsShop.Controllers.MainViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


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
    
    private static MainViewController mainViewController = MainViewController.getInstance();

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
       // AddToyDialog dialog = toyDialogLoader.<AddToyDialog>getController();
      // Once we implement an observablelist for the toys we can use this.
      //dialog.setAppMainObservableList(tvObservableList);
        mainViewController.insertToy();
 
    }

    @FXML
    private void logoutHandler(MouseEvent event) throws IOException
    {     
         mainViewController.logOut();     
    }
    
}
