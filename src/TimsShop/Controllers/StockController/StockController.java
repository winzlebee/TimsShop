package TimsShop.Controllers.StockController;

import TimsShop.Controllers.CallBackEvt;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Item;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.RelationModels.Sale;
import TimsShop.Models.UserModels.Customer;
import TimsShop.Models.UserModels.Supplier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import javafx.collections.ObservableList;


public class StockController
{
    /*********************CLASS FIELDS************************/
    /////////////////////////////////////////////////////////
    private ObservableList<Toy> toys;
    private ObservableList<Category> categories;
    private ObservableList<Supplier> suppliers;
    private ObservableList<Sale> sales;
    
    private CallBackEvt toyCallBack;
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
    
    public void insertCategory(String name, ArrayList<String> tags)
    {
        storage.addCategory(name, tags);
    }
    
    public void makeSale(Customer cust, Map<Item, Integer> quantities)
    {
        // Remove each of the item quantities where necessary
        for (Item i : quantities.keySet()) {
            int quantity = quantities.get(i);
            i.removeStock(quantity);
        }
        
        storage.insertSale(quantities, cust);
        
        toyCallBack.callBack();
    }
    
    public void setRefreshCallback(CallBackEvt evt)
    {
        toyCallBack = evt;
    }
    
    
    /**************************
     * Validate that key form  entries are mode
     * @param name - the toy name, entered from form
     * @param id the Category ID, entered from form
     * @return 
     *****************************************/
    public boolean isValidForm(String name, Category cat)
    {
        try {
            if (name.isEmpty() || name.equals("".trim())) 
            {
                return false;
            }

            if (cat == null)
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
    
    public ObservableList<Sale> getSales()
    {
        return sales;
    }
    
    public String getCategoryName(long id)
    {
        return (storage.getCategoryById(id)).getName();
    }
    
    public String getFormattedSuppliers(Toy t)
    {
        return storage.getSupplierString(t);
    }
    
    
     
    
    
}
