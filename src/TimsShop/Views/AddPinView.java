/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Views;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.Views;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author win
 */
public class AddPinView implements Initializable{
    
    @FXML
    private TextField codeInput;
    
    @FXML
    private Label warnLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void onCancel(MouseEvent evt) {
        ApplicationController.getInstance().closeView(Views.ADD_PIN);
    }
    
    @FXML
    private void onAdd(MouseEvent evt) {
        if (!codeInput.getText().matches("[0-9]{4}")) {
            warnLabel.setText("Enter a 4 digit pin.");
            return;
        }
        
        try {
            ApplicationController.getInstance().getLoginController().addPin(Integer.parseInt(codeInput.getText()));
        } catch (NumberFormatException ex) {
            return;
        }
        
        ApplicationController.getInstance().closeView(Views.ADD_PIN);
    }
    
}
