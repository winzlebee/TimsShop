
package TimsShop.Models.ItemModels;

import java.util.ArrayList;


public class Toy extends Item
{
    
    private String description;
   
    public Toy(long id, String name, float price, long categoryId, int stockCount, ArrayList<Long> suppliers, String description)
    {
        super(id, name, price, categoryId, stockCount, suppliers);
        this.description = description;
        System.out.println("HERE Stock COunt " + this.getStockCount());
    }
    
    public String getDescription() {
        return this.description;
    }
         
}
