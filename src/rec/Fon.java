package rec;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
 
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
 
public class Fon extends JPanel {
    Image min;
    
    public Fon(){
        min = new ImageIcon("src/Images/texture.jpg").getImage();
    }
    
    public void paintComponent(Graphics g){
         Graphics2D g2d=(Graphics2D)g;
         g2d.drawImage(min,0,0,null);
         }
    
    
}