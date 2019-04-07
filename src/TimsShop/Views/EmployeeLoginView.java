/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Views;

import TimsShop.Controllers.LoginController;
import TimsShop.Views.Animations.Shaker;

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
    
    private static LoginController loginController = LoginController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
     
    }
    
  

    @FXML
    private void loginHandler(MouseEvent event) throws Exception
    {   //Request login access from controller
        if(!loginController.checkLogin((pinField.getText())))
        {    //Shake effect (because why not)
            shaker = new Shaker(pinField);
            shaker.shake();
            pinField.setStyle("-fx-border-color:red;-fx-;");
        }
            
    }
    
  
    
}
