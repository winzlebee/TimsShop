/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Controllers.Dialogs.CustomerControllers;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class AddCustomer implements Initializable
{

    @FXML
    private TextField lName;
    @FXML
    private TextField fName;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNum;
    @FXML
    private ComboBox interestPicker;
    @FXML
    private Button addButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label errMsgLabel;
    @FXML
    private TextField interestField;
    
   
    private ShopDataStorage dataStorage;
    private ArrayList<Long> interestList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        interestList = new ArrayList<>();
    }
    
    public void setStorage(ShopDataStorage storage)
    {
        this.dataStorage = storage;
        interestPicker.setItems(dataStorage.getCategories());
    }

    @FXML
    private void addInterestHandler(MouseEvent event)
    {
        if(interestPicker.getValue() != null)
        {
            interestField.appendText(interestPicker.getValue()+ ",");
            interestList.add(((Category) interestPicker.getValue()).getID());
        }
       
    }
    
    

    @FXML
    private void saveHandler(MouseEvent event)
    {
        if(isValidForm())
        { 
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime currentDate = LocalDateTime.now();
            
            //add Customer to storage
            dataStorage.addCustomer(fName.getText(), lName.getText(), email.getText(),
                    Long.parseLong(phoneNum.getText()), date.format(currentDate), 0, false, interestList);
        }
    }
    
  
    
    private boolean isValidForm()
    {
        
        if(fName.getText().isEmpty())
        {
             errMsgLabel.setText("Please enter a name.");
             return false;
        }
        
        if(lName.getText().isEmpty())
        {
            errMsgLabel.setText("Please enter a name.");
            return false;
        }
        
        if(email.getText().isEmpty())
        {
            errMsgLabel.setText("Please Enter a valid email");
            return false;
        }
        
        if(phoneNum.getText().isEmpty())
        {
            errMsgLabel.setText("Please Enter a valid Phone Number");
            return false;
        }
        
        return true;
    }
    
    
    
    
}
