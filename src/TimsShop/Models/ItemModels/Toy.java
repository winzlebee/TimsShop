
package TimsShop.Models.ItemModels;


public class Toy extends Item
{
    
    private String description;
   
    public Toy(long id, String name, float price, Category category, String description)
    {
        super(id,name,price,category);
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
         
}
