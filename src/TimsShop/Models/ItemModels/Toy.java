
package TimsShop.Models.ItemModels;


public class Toy extends Item
{
    
    private String description;
   
    public Toy(long id, String name, float price, long categoryId, String description)
    {
        super(id,name,price,categoryId);
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
         
}
