/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Views.SaleViews;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.CustomerControllers.CustomerController;
import TimsShop.Controllers.StockController.StockController;
import TimsShop.Controllers.ViewLoader;
import TimsShop.Controllers.Views;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Item;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.UserModels.Customer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author win
 */
public class AddSaleDialog implements Initializable {
    
    private class ItemAddition {
        Item selectedItem;
        int selectedQuantity;
        
        public ItemAddition(Item t, int quantity) {
            selectedItem = t;
            selectedQuantity = quantity;
        }
        
        public Item getItem() {
            return selectedItem;
        }
        
        public int getQuantity() {
            return selectedQuantity;
        }
    }
    
    @FXML
    private TableView<ItemAddition> saleItemsTable;
    
    @FXML
    private TableColumn<ItemAddition, String> itemCol;
    
    @FXML
    private TableColumn<ItemAddition, Integer> quantityCol;
    
    @FXML
    private ComboBox customerDropDown;
    
    @FXML
    private ComboBox itemDropDown;
    
    @FXML
    private Spinner<Integer> quantitySpinBox;
    
    @FXML
    private Button addRowButton;
    
    @FXML
    private Button delRowButton;
    
    /**  WARNING LABELS **/
    @FXML
    private Label itemWarn;
    @FXML
    private Label custWarnLabel;
    
    private StockController m_stockControl;
    private CustomerController m_customerControl;

    private ObservableList<ItemAddition> m_tempItems;
    
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
        itemDropDown.setItems(m_stockControl.getToys());
        
        // Create the table and initialize it for adding
        m_tempItems = FXCollections.observableArrayList();
        
        saleItemsTable.setItems(m_tempItems);
        
        itemCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getItem().getName()));
        quantityCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getQuantity()));
    }
    
    @FXML
    public void addRowHandler(MouseEvent evt) {
        // Add the row to the internal data table
        
        Item selectedItem = (Item) itemDropDown.getSelectionModel().getSelectedItem();
        
        if (selectedItem == null) {
            // Show warning message and return
            itemWarn.setText("Please select an item.");
            return;
        }
        
        int quantity = quantitySpinBox.getValue();
        
        if (quantity == 0) {
            itemWarn.setText("Please enter a quantity above zero.");
            return;
        }
        
        if (quantity > selectedItem.getStockCount()) {
            itemWarn.setText("Not enough in stock of that item!");
            return;
        }
        
        m_tempItems.add(new ItemAddition(selectedItem, quantity));
        itemWarn.setText("");
    }
    
    @FXML
    public void removeRowHandler(MouseEvent evt) {
        // Remove the selected row in the sale records table
        
        if (saleItemsTable.getSelectionModel().getSelectedItem() == null) return;
        
        m_tempItems.remove(saleItemsTable.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    public void closeHandler(MouseEvent evt) {
        ViewLoader.getInstance().close(Views.ADD_SALE);
    }
    
    @FXML
    public void addSaleCloseHandler(MouseEvent evt) {
        
        // Copy the items into a HashMap compatible with the sale view
        Customer selectedCust = (Customer) customerDropDown.getSelectionModel().getSelectedItem();
        if (selectedCust == null) {
            custWarnLabel.setText("Please select a customer.");
            return;
        }
        
        if (m_tempItems.isEmpty()) {
            custWarnLabel.setText("Please add items to the sale.");
            return;
        }
        
        Map<Item, Integer> quantities = new HashMap<Item, Integer>();
        for (ItemAddition item : m_tempItems) {
            quantities.put(item.getItem(), item.getQuantity());
        }
        
        m_stockControl.makeSale(selectedCust, quantities);
        
        System.out.println("Sale Added.");
        
        ViewLoader.getInstance().close(Views.ADD_SALE);
    }
    
}
