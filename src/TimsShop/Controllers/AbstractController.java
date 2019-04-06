
package TimsShop.Controllers;

import java.io.IOException;


public interface AbstractController 
{
    public void closeView() ;
    public void openNext() throws IOException;
}
