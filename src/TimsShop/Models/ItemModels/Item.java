
package TimsShop.Models.ItemModels;

import TimsShop.Models.UserModels.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
/****************************************************
 * This class represents the superclass of the item
    model set. 
   
    * Question: Should the amount of items in stock
       be represented by a int or duplicate objects?
    * Question: Would there be any need to instantiate
       an Item object? or would making it abstract be
        appropriate?
****************************************************/
public abstract class Item implements Serializable
{
    private final long id;
    private String name;
    private float price;
    private int stockCount;
    private String dateStocked;
    private String storeLocation;
    private ArrayList<Long> suppliers;
    
    private long categoryId;
    
    public Item(long id, String name, float price, long categoryId, int stockCount, ArrayList<Long> suppliers, String dateStocked, String storeLocation)
    {
        this.name = name;
        this.id = id;
        this.price = price;
        this.categoryId = categoryId;
        this.stockCount = stockCount;
        this.dateStocked = dateStocked;
        this.storeLocation = storeLocation;
        this.suppliers = new ArrayList<>(suppliers);
     
    }
    
    public Item(long id, String name, float price, long categoryId, int stockCount, String dateStocked, String storeLocation)
    {
        this.name = name;
        this.id = id;
        this.price = price;
        this.categoryId = categoryId;
        this.stockCount = stockCount;
        this.suppliers = new ArrayList<>();
        this.dateStocked = dateStocked;
        this.storeLocation = storeLocation;
 
    }

   /********GETTERS**********/
    public String getName() 
    {
        return name;
    }

    public float getPrice() 
    {
        return price;
    }

    public long getId() 
    {
        return id;
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public int getStockCount()
    {
        return stockCount;
    }

    public ArrayList<Long> getSupliers()
    {
        return suppliers;
    }

    public String getDateStocked()
    {
        return dateStocked;
    }

    public String getStoreLocation()
    {
        return storeLocation;
    }
    


    /********SETTERS**********/
    public void setPrice(float price) 
    {
        this.price = price;
    }
    public void setName(String name)
    {    
        this.name = name;
    }
    public void setCategory(long categoryId)
    {
        this.categoryId = categoryId;
    }
    
    public void addSupplier(Long s)
    {
        suppliers.add(s);
    }
    public void removeSupplier(Long s)
    {
        suppliers.remove(s);
    }

    public void setStockCount(int stockCount)
    {
        this.stockCount = stockCount;
    }
    
    public void removeStock(int amount)
    {
        if (stockCount < amount) return;
        
        this.stockCount -= amount;
    }

    public void setDateStocked(String dateStocked)
    {
        this.dateStocked = dateStocked;
    }

    public void setStoreLocation(String storeLocation)
    {
        this.storeLocation = storeLocation;
    }
    
    
    
    
        
    @Override
    public String toString() {
        return id+","+name+","+price+",";
    }
    
}
