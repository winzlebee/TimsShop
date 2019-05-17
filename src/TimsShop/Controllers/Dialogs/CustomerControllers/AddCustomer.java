
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
   /******************************CLASS FIELDS******************************/
    ///////////////////////////////////////////////////////////////////////////
    
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
    
    /********************************************
     * sets the storage for the controller to use
     * @param storage the loaded shop data 
     ********************************************/
    public void setStorage(ShopDataStorage storage)
    {
        this.dataStorage = storage;
        interestPicker.setItems(dataStorage.getCategories());
    }
    /*************************EVENT LISTENERS*********************************/
    //////////////////////////////////////////////////////////////////////////
    /*************************************************
     * appends the interest string value to the text field
     * and adds the respective category id to the list
     * @param event  on click event
     *************************************************/
    @FXML
    private void addInterestHandler(MouseEvent event)
    {
        if(interestPicker.getValue() != null)
        {
            interestField.appendText(interestPicker.getValue()+ ",");
            interestList.add(((Category) interestPicker.getValue()).getID());
        }
    }
    /******************************************************
     * Checks  for a valid form, then adds new customer 
        to the persistent storage
     * @param event on click event
     *****************************************************/
    @FXML
    private void saveHandler(MouseEvent event)
    {
        if(isValidForm())
        { 
            DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime currentDate = LocalDateTime.now();
            
            //add Customer to storage
            dataStorage.addCustomer(fName.getText(), lName.getText(), email.getText(),
                    Long.parseLong(phoneNum.getText()), date.format(currentDate), 0, false, interestList);
        }
    }
    
    /*************************************************
     * @return checks all fields have values in them
     **************************************************/
    private boolean isValidForm()
    {   //First name field
        if(fName.getText().isEmpty())
        {
             errMsgLabel.setText("Please enter a name.");
             return false;
        }
        //last name field
        if(lName.getText().isEmpty())
        {
            errMsgLabel.setText("Please enter a name.");
            return false;
        }
        //Email field
        if(email.getText().isEmpty())
        {
            errMsgLabel.setText("Please Enter a valid email");
            return false;
        }
        //Phone number field
        if(phoneNum.getText().isEmpty())
        {
            errMsgLabel.setText("Please Enter a valid Phone Number");
            return false;
        }
        return true;
    }
}
