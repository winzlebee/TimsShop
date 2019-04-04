
package TimsShop.Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class MainView implements  Initializable  
{
        
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        label.setText("Hello World!");
    }
    
    
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
    
}
