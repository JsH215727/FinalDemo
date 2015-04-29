import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author jh215727
 */
class MyWinGG extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
}