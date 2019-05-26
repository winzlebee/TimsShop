/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Controllers;

public enum Views
{
    LOGIN("/TimsShop/FXML/EmployeeLoginView.fxml", "LOGIN"),
    MAIN("/TimsShop/FXML/MainView.fxml", "MAIN"),
    CUSTOMER("/TimsShop/FXML/CustomerDialog.fxml","CUSTOMER"),
    ADD_CUSTOMER("/TimsShop/FXML/AddCustomerDialog.fxml","ADD_CUSTOMER"),
    MODIFY_CUSTOMER("/TimsShop/FXML/ModifyCustomerDialog.fxml","MODIFY_CUSTOMER"),
    ADD_TOY("/TimsShop/FXML/InsertToyDialog.fxml","ADD_TOY"),
    ADD_CATEGORY("/TimsShop/FXML/AddCategoryDialog.fxml","ADD_CATEGORY "),
    SUPPLIER("/TimsShop/FXML/SupplierView.fxml", "SUPPLIER"),
    ADD_SUPPLIER("/TimsShop/FXML/AddSupplierView.fxml", "ADD_SUPPLIER"),
    ADD_SALE("/TimsShop/FXML/MakeSaleView.fxml", "MAKE_SALE"),
    STOCKTAKE("/TimsShop/FXML/ModifyToyDialog.fxml", "STOCKTAKE"),
    ADD_PIN("/TimsShop/FXML/AddPinDialog.fxml", "ADD_PIN");
  
    private final String path;
    private final String key;
    private Views(String path, String key)
    {
        this.path = path;
        this.key = key;
    }

    public String getPath()
    {
        return path;
    }
    public String getKey()
    {
        return key;
    }
    
}
