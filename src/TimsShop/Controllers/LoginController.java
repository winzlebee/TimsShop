/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Controllers;

import TimsShop.Controllers.Animations.Shaker;
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
public class LoginController implements Initializable
{

    @FXML
    private PasswordField pinField;
    @FXML
    private Button lognButton;
    
    private Shaker shaker; 

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
       
    }
    
    private boolean checkLogin()
    { // TODO: check against pool of loaded staff-logins
      return pinField.getText().equals("1234");
    }

    @FXML
    private void loginHandler(MouseEvent event) throws IOException
    {
        if(checkLogin())
        {   
            processLogin(pinField.getText());  
        }
        else
        {   //Shake effect (because why not)
            shaker = new Shaker(pinField);
            shaker.shake();
            pinField.setStyle("-fx-border-color:red;-fx-;");
        }
       
    }
    
    private void processLogin(String staffId) throws IOException
    {   //Handle view loading, showing and closing of Login view
        ViewLoader.getInstance().load(Views.MAIN);
        ViewLoader.getInstance().show(Views.MAIN);
        ViewLoader.getInstance().close(Views.LOGIN);
        //Set on Close Listener
        MainViewController controller = ViewLoader.getInstance().getController(Views.MAIN);
        ViewLoader.getInstance().getStage(Views.MAIN).setOnCloseRequest( evt -> controller.onClose()); 
    }
    
    
    //AddToyDialog dialog = toyDialogLoader.<AddToyDialog>getController();
    
}
