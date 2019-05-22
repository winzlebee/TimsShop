
package TimsShop.Views;

import TimsShop.Controllers.Animations.Shaker;
import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

/*****************************************************
 * The main view for Employee Login -
 *  Prompts Employees to Enter login pin 
 ***************************************************/
public class EmployeeLoginView implements Initializable
{
    @FXML
    private PasswordField pinField;
    @FXML
    private Button lognButton;
    
    private Shaker shaker;
    
    private static ApplicationController appController = ApplicationController.getInstance();

    @FXML
    private void loginHandler(MouseEvent event) 
    {
        if(!appController.getLoginController().checkLogin(pinField.getText()))
        {   //Shake effect (because why not)
            shaker = new Shaker(pinField);
            shaker.shake();
            pinField.setStyle("-fx-border-color:red;-fx-;");
        }
    }
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
   
}
