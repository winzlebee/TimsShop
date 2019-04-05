
package TimsShop.Models.ItemModels;
/****************************************************
 * definitions/implementations of a Category.
   A category has a composition relationship with an Item.
    
    * Question: is having a class for category 
        redundant memory usage? would it be better 
          to use an Enum?
     *Question: should a category support further
      specialised category types (generalization/inheritance)
****************************************************/

public class Category 
{
 /***************************
     *  Could do as class? 
  ****************************/   
}

enum CategoryEnum
{
   APPLIANCES(00001,"Appliance"),
   BOAT(00002,"Boat"),
   TRAIN(00003,"Train"),
   CAR(00004,"Car"),
   AIRPLANE(0005,"Airplane"),
   MISCELANEOUS(0006,"Misc");

   private long id;
   private String name;
   
   private CategoryEnum(long id, String name)
   {
       this.id = id;
       this.name = name;
   }   
   
   public long getID()
   {
       return id;
   }
   
   public String getName()
   {
       return name;
   }
}
