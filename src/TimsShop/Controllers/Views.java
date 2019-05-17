/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Controllers;

public enum Views
{
    LOGIN("/TimsShop/Views/EmployeeLoginView.fxml", "LOGIN"),
    MAIN("/TimsShop/Views/MainView.fxml", "MAIN"),
    CUSTOMER("/TimsShop/Views/CustomerDialog.fxml","CUSTOMER"),
    ADD_CUSTOMER("/TimsShop/Views/AddCustomerDialog.fxml","ADD_CUSTOMER"),
    MODIFY_CUSTOMER("/TimsShop/Views/ModifyCustomerDialog.fxml","MODIFY_CUSTOMER"),
    ADD_TOY("/TimsShop/Views/InsertToyDialog.fxml","ADD_TOY"),
    ADD_CATEGORY("/TimsShop/Views/AddCategoryDialog.fxml","ADD_CATEGORY ");
    
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
