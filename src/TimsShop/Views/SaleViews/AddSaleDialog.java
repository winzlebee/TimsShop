/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Views.SaleViews;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.CustomerControllers.CustomerController;
import TimsShop.Controllers.StockController.StockController;
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
public class AddSaleDialog implements Initializable {
    
    @FXML
    private TableView saleItemsTable;
    
    @FXML
    private ComboBox customerDropDown;
    
    private StockController m_stockControl;
    private CustomerController m_customerControl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Add methods on initializeof AddSaleDialog
        
        initFields();
    }
    
    public void initFields() {
        this.m_stockControl = ApplicationController.getInstance().getStockController();
        this.m_customerControl = ApplicationController.getInstance().getCustomerController();
        
        // Initialize necessary fields from storage
        customerDropDown.setItems(m_customerControl.getCustomers());
    }
    
}
