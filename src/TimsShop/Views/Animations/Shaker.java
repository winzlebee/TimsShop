
package TimsShop.Views.Animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/*************** ********************
 * Causes shake animation on erroneous inputs
*************** *********************/
public class Shaker
{
    private TranslateTransition translateTransition;
    
    public Shaker(Node node)
    {
        translateTransition = new TranslateTransition(Duration.millis(75),node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(3);      
        translateTransition.setAutoReverse(true);
    }
    
    public void shake()
    {
        translateTransition.playFromStart();
    }
}
