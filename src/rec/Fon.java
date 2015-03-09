package rec;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
 


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
 
 
public class Fon extends JComponent {
    Image min;
    
    public Fon(String img){
        //min = new ImageIcon("src/Images/texture.jpg").getImage();
    	//min = new ImageIcon(img).getImage();
    	try {
			min = ImageIO.read(new File(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void paintComponent(Graphics g){
         Graphics2D g2d=(Graphics2D)g;
         g2d.drawImage(min,0,0,null);
         }
    
    
}