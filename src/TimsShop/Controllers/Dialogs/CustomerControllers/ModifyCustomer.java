
package TimsShop.Controllers.Dialogs.CustomerControllers;

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
    
    private Customer customer;
    private CallBackEvt tableRefreshCallBack;
    
 
    
    public void setCustomer(Customer customer, CallBackEvt callBack)
    {
        this.customer = customer;
        tableRefreshCallBack = callBack;
        setFields();
    }
    
    private void setFields()
    {
        fName.setText(customer.getFirstName());
        lName.setText(customer.getLastName());
        email.setText(customer.getEmail());
        phoneNum.setText(customer.getPhoneNum()+"");
    }

    @FXML
    private void saveHandler(MouseEvent event)
    {
        customer.setFirstName(fName.getText());
        customer.setLastName(lName.getText());
        customer.setEmail(email.getText());
        customer.setPhoneNum(Long.parseLong(phoneNum.getText()));
        tableRefreshCallBack.callBack();
    }
    @FXML
    private void addInterestHandler(MouseEvent event){}
    @Override
    public void initialize(URL location, ResourceBundle resources){}
    
}
