
package TimsShop.Models.ItemModels;

import java.util.ArrayList;


public class Toy extends Item
{
    
    private String description;
   
    public Toy(long id, String name, float price, long categoryId, int stockCount, ArrayList<Long> suppliers, String description, String dateJoined, String storeLocation)
    {
        super(id, name, price, categoryId, stockCount, suppliers, dateJoined, storeLocation);
        this.description = description;
    }
    
    public Toy(long id, String name, float price, long categoryId, int stockCount, String description, String dateJoined, String storeLocation)
    {
        super(id, name, price, categoryId, stockCount, dateJoined, storeLocation);
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
         
}
