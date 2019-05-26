
package TimsShop.Views.CustomerViews;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.CustomerControllers.CustomerController;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    
   
    
    private ArrayList<Long> interestList;
    
    private static CustomerController controller; 
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Set the appropraite controller for the CustomerView
        initController();
        interestList = new ArrayList<>();
    }
    
    private void initController()
    {
        controller = ApplicationController.getInstance().getCustomerController();
        interestPicker.setItems(controller.getCategories());
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
        //Request Controller to validate form data
        if(controller.isValidForm(fName.getText(), lName.getText(), email.getText(), phoneNum.getText()))
        { 
            DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime currentDate = LocalDateTime.now();
            
            //Requests Controller to process customer input data
            controller.addCustomer(fName.getText(), lName.getText(), email.getText(),
                Long.parseLong(phoneNum.getText()), date.format(currentDate), 
                0, false, interestList);
        }
        else
        {
            errMsgLabel.setText("Please Fill In All Fields");
        }
    }
}
