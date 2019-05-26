
package TimsShop.Views.CustomerViews;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.CustomerControllers.CustomerController;
import TimsShop.Controllers.Views;
import TimsShop.Models.UserModels.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class ModifyCustomer implements Initializable
{
    /*********************CLASS FIELDS************************/
    /////////////////////////////////////////////////////////
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
    private TextField interestField;
    @FXML
    private Button addButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label errMsgLabel;
    
    
    private static CustomerController controller;
 
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initController();
    }
    private void initController()
    {
        controller = ApplicationController.getInstance().getCustomerController();
    }
    public void setFields()
    {
        Customer customer = controller.getCustomer();
        fName.setText(customer.getFirstName()); 
        lName.setText(customer.getLastName()); 
        email.setText(customer.getEmail()); 
        phoneNum.setText(customer.getPhoneNum()+""); 

    }
    
    @FXML
    private void saveHandler(MouseEvent event)
    {
        if(controller.updateCustomer(fName.getText(), lName.getText(), email.getText(), phoneNum.getText()))
        {
            //Display Succesful update and Close View
            ApplicationController.getInstance().closeView(Views.MODIFY_CUSTOMER);
        }
        else
        {
            //Display error Message
        }
    } 
    @FXML
    private void addInterestHandler(MouseEvent event){}


    
}
