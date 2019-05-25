package TimsShop.Controllers.StockController;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.UserModels.Supplier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.ObservableList;


public class StockController
{
    /*********************CLASS FIELDS************************/
    /////////////////////////////////////////////////////////
    private ObservableList<Toy> toys;
    private ObservableList<Category> categories;
    private ObservableList<Supplier> suppliers;
    private ShopDataStorage storage;
    
    public StockController(ShopDataStorage storage)
    {
        this.storage = storage;
        toys = storage.getToys();
        suppliers = storage.getSuppliers();
        categories = storage.getCategories();
        
    }
    
    
    /******************STORAGE PROCEDURES**********************************/
    ///////////////////////////////////////////////////////////////////////
    public void makeInsertionRequest(String name, float price, String description, 
            Object categoryId, int count, ArrayList<Long> suppliers, String location)
    {
        try{
        
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDateTime currentDate = LocalDateTime.now();
        
        
        storage.insertToy(name, price, description, ((Category)categoryId).getID(), count, suppliers, date.format(currentDate), location);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**************************
     * Validate that key form  entries are mode
     * @param name - the toy name, entered from form
     * @param id the Category ID, entered from form
     * @return 
     *****************************************/
    public boolean isValidForm(String name, Object id)
    {
        try {
            if (name.isEmpty() || name.equals("".trim())) 
            {
                return false;
            }

            if (((Category)id).getID() == 0)
            {
                return false;
            }
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    
    /*********************GETTERS************************/
    /////////////////////////////////////////////////////////
    
    public boolean categoriesIsEmpty()
    {
        return categories.isEmpty();
    }
    public ObservableList<Category> getCategories()
    {
        return categories;
    }
    
    public ObservableList<Supplier> getSuppliers()
    {
        return suppliers;
    }
    
    public ObservableList<Toy> getToys()
    {
        return toys;
    }
    
    public String getCategiryName(long id)
    {
        return (storage.getCategoryById(id)).getName();
    }
    
    public String getFormattedSuppliers(Toy t)
    {
        return storage.getSupplierString(t);
    }
    
    
     
    
    
}
