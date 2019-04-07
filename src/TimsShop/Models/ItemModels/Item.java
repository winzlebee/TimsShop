
package TimsShop.Models.ItemModels;

import java.io.Serializable;
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
    
    private long categoryId;
    
    public Item(long id,String name,float price, long categoryId)
    {
        this.name = name;
        this.id = id;
        this.price=price;
        this.categoryId = categoryId;
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
        
    @Override
    public String toString() {
        return id+","+name+","+price+",";
    }
    
}
