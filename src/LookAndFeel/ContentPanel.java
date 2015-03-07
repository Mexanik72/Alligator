package LookAndFeel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	  Image bgimage = null;

	  public ContentPanel() {
	    MediaTracker mt = new MediaTracker(this);
	    bgimage = Toolkit.getDefaultToolkit().getImage("src/Images/green_background.png");
	    mt.addImage(bgimage, 0);
	    try {
	      mt.waitForAll();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }

	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int imwidth = bgimage.getWidth(null);
	    int imheight = bgimage.getHeight(null);
	    g.drawImage(bgimage, 1, 1, null);
	  }
	}
