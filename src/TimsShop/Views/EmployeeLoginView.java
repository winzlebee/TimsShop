/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Views;

import TimsShop.Controllers.LoginController;
import TimsShop.Views.Animations.Shaker;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    
    private LoginController loginController;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
       //Create the views primary controller
       loginController = new LoginController((Stage)pinField.getScene().getWindow());
    }
    
  

    @FXML
    private void loginHandler(MouseEvent event) throws IOException
    {   //Request login access from controller
        if(this.loginController.checkLogin(Integer.parseInt(pinField.getText())))
        {   
            
        }
        else
        {   //Shake effect (because why not)
            shaker = new Shaker(pinField);
            shaker.shake();
            pinField.setStyle("-fx-border-color:red;-fx-;");
        }
       
    }
    
  
    
}
