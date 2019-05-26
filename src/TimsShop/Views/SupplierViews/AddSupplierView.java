
package TimsShop.Views.SupplierViews;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.CallBackEvt;
import TimsShop.Controllers.StockController.StockController;
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
    
    private StockController stockController;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       stockController = ApplicationController.getInstance().getStockController();
    }

    @FXML
    private void addHandler(MouseEvent event)
    {
        stockController.addSupplier(nameField.getText(), addressField.getText(),
                contactField.getText(), Long.parseLong(phoneField.getText()));
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
