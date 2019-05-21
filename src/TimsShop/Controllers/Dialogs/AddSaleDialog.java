/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Controllers.Dialogs;

import TimsShop.Models.DataModels.ShopDataStorage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 *
 * @author win
 */
public class AddSaleDialog implements Initializable, Dialog {
    
    @FXML
    private TableView saleItemsTable;
    
    @FXML
    private ComboBox customerDropDown;
    
    private ShopDataStorage dataStorage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Add methods on initializeof AddSaleDialog
    }
    
    @Override
    public void setStorage(ShopDataStorage storage) {
        this.dataStorage = storage;
        
        // Initialize necessary fields from storage
        customerDropDown.setItems(dataStorage.getCustomers()); 
    }
    
}
