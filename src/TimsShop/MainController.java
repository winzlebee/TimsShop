
package TimsShop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/****************************************************************
This class serves as controller between the views of  Tims Toy shop.
NOte: Any @FXML annotations denote XML loaded components/listeners
* To edit the GUI element, install scene builder @
********************************************************************/
public class MainController extends Application implements Initializable 
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
     /**********************************************************************
     Function: loads XML document and instantiates the main Stage (view) 
     Arguments: the initial frame of the program
     Result: Sets the frame to show.
      ************************************************************************/
    @Override
    public void start(Stage primaryStage) throws Exception 
    {   //Load xml document
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }  
}
