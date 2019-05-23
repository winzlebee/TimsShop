
package TimsShop.Controllers.Dialogs.SupplierControllers;

import TimsShop.Controllers.CallBackEvt;
import TimsShop.Models.DataModels.ShopDataStorage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class AddSupplierView implements Initializable
{

    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField contactField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button addButton;
    
    
    private CallBackEvt evnt;
    private ShopDataStorage storage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       
    }
    
    public void setStorage(ShopDataStorage storage, CallBackEvt e)
    {
        this.storage = storage;
        evnt = e;
    }

    @FXML
    private void addHandler(MouseEvent event)
    {
        storage.addSupplier(nameField.getText(), addressField.getText(),
                contactField.getText(), Long.parseLong(phoneField.getText()));
        evnt.callBack();
    }

    @FXML
    private void clearHandler(MouseEvent event)
    {
        nameField.setText("");
        addressField.setText("");
        contactField.setText("");
        phoneField.setText("");
    }
    
}
