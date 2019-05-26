/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Views.ToyViews;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.StockController.StockController;
import TimsShop.Controllers.Views;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author win
 */
public class StocktakeDialog implements Initializable {
    
    @FXML
    private TextField itemName;
    
    @FXML
    private Spinner<Integer> quantitySpin;
    
    StockController stockControl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stockControl = ApplicationController.getInstance().getStockController();
        
        itemName.setText(stockControl.getSelectedToy().getName());
        
        quantitySpin.getValueFactory().setValue(stockControl.getSelectedToy().getStockCount());
    }
    
    @FXML
    private void onRemove(MouseEvent evt)
    {
        // Remove the currently selected toy
        stockControl.removeToy(stockControl.getSelectedToy());
        ApplicationController.getInstance().closeView(Views.STOCKTAKE);
    }
    
    @FXML
    private void onConfirm(MouseEvent evt)
    {
        stockControl.setToyQuantity(stockControl.getSelectedToy(), quantitySpin.getValue());
        
        ApplicationController.getInstance().closeView(Views.STOCKTAKE);
    }
    
    @FXML
    private void onCancel(MouseEvent evt)
    {
        ApplicationController.getInstance().closeView(Views.STOCKTAKE);
    }
    
}
