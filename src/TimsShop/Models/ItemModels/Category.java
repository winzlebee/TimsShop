
package TimsShop.Models.ItemModels;

import java.io.Serializable;
import java.util.ArrayList;

/****************************************************
 * definitions/implementations of a Category.
   A category has a composition relationship with an Item.
    
    * Question: is having a class for category 
        redundant memory usage? would it be better 
          to use an Enum?
     *Question: should a category support further
      specialised category types (generalization/inheritance)
****************************************************/

public class Category implements Serializable
{
    private final long id;
    private final String name;
    private ArrayList<String> tags;
    
    public Category(long id, String name, ArrayList<String> tags) {
       this.id = id;
       this.name = name;
       this.tags = tags;
    }
    
    public long getID()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
    
    public ArrayList<String> getTags() {
        return tags;
    }
}
