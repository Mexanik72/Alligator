package rec;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ImgPanel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imgFile;

	ImgPanel(String imgFile){
		this.imgFile = imgFile;
	}
	
	public void changePicture(String imgFile) {
		this.imgFile = imgFile;
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		try {
			Image img = ImageIO.read(new File(imgFile));
			g.drawImage(img, 5, 5, null);
		} catch (IOException ex) {
			Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}
