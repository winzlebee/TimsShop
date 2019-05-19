
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
    private ArrayList<Long> suppliers;
    
    private long categoryId;
    
    public Item(long id, String name, float price, long categoryId, int stockCount, ArrayList<Long> suppliers)
    {
        this.name = name;
        this.id = id;
        this.price=price;
        this.categoryId = categoryId;
        this.suppliers = new ArrayList<>();
        this.stockCount = stockCount;
        this.suppliers = suppliers;
 
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

    public ArrayList<Long> getSuppliers()
    {
        return suppliers;
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
    
    
        
    @Override
    public String toString() {
        return id+","+name+","+price+",";
    }
    
}
