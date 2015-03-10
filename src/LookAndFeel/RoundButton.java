package LookAndFeel;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RoundButton() {
        
        Dimension size = getPreferredSize();
        size.width = size.height = 24;
        setPreferredSize(size);
        setContentAreaFilled(false);
    }
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
            Image min = new ImageIcon("src/Images/левоЅ.png")
			.getImage();
            g.drawImage(min, 0, 0, null);
        } else {
        	Image min = new ImageIcon("src/Images/лево.png")
			.getImage();
        	g.drawImage(min, 2, 2, null);
        }
//        g.fillOval(0, 0, getSize().width - 1,
//                getSize().height - 1);

        super.paintComponent(g);
    }
   protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        g.drawOval(0, 0, getSize().width - 1,
//                getSize().height - 1);
    }

//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(0, 0,
//                    getWidth(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
}
