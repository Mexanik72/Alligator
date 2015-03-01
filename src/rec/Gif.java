package rec;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gif extends JFrame {
	
	private static final long serialVersionUID = 1170020159907047430L;

	public Gif() {
		initComponents();
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		JPanel centerPanel = new JPanel();
		JLabel wait = new JLabel("Пожалуйста, подождите...");
		
		setTitle("Please, wait");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		URL url = ChooseWord.class.getResource("219.GIF");
	    Icon icon = new ImageIcon(url);
	    JLabel label = new JLabel(icon);
	    centerPanel.add(wait);
	    centerPanel.add(label);
	    add(centerPanel, java.awt.BorderLayout.CENTER);
	    setVisible(true);
	    
	    while(!Thread.currentThread().isInterrupted()) {}
	    this.dispose();
	}
}
