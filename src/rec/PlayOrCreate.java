package rec;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JFrame;

import CustomClass.User;
import LookAndFeel.ContentPanel;
import LookAndFeel.MyButtonUI;
import LookAndFeel.SimpleMenu;

public class PlayOrCreate extends JFrame {

	private User userNow;
	private Dimension d;
	private Point p;

	PlayOrCreate(User user, Dimension d, Point p) {
		this.userNow = user;
		this.d = d;
		this.p = p;
		initComponents();
	}

	private void initComponents() {
		centerPanel = new javax.swing.JPanel();
		northPanel = new javax.swing.JPanel();
		play = new javax.swing.JButton("Play");
		create = new javax.swing.JButton("Create");

		// setSize(720, 720);
		setSize(d);
		if (p == null) {
			setLocationRelativeTo(null);
		} else {
			setLocation(p);
		}
		
		setVisible(true);

		ContentPanel pa = new ContentPanel();
		pa.setLayout(new BorderLayout());
		northPanel.setLayout(new BorderLayout());
		SimpleMenu sm = new SimpleMenu(userNow);
		northPanel.add(sm, java.awt.BorderLayout.EAST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		play.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playActionPerformed();
			}
		});

		MyButtonUI.setupButtonUI(play, 0, 3);

		create.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createActionPerformed();
			}
		});

		MyButtonUI.setupButtonUI(create, 0, 3);

		setTitle("PlayOrCreate");

		centerPanel.setLayout(new GridLayout(2, 1, 0, 100));
		northPanel.setOpaque(false);
		centerPanel.setOpaque(false);
		pa.add(northPanel, java.awt.BorderLayout.NORTH);
		pa.add(centerPanel, java.awt.BorderLayout.CENTER);
		add(pa);
		centerPanel.add(play);
		centerPanel.add(create);
	}

	private void playActionPerformed() {
		Dimension d;
		Point p;
		p = getLocationOnScreen();
		getLocationOnScreen();
		d = getSize();
		new ChooseCategory(userNow, d, p, false);
		this.dispose();
	}

	private void createActionPerformed() {

		Dimension d;
		Point p;
		p = getLocationOnScreen();
		getLocationOnScreen();
		d = getSize();
		new ChooseCategory(userNow, d, p, true);

		this.dispose();
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JButton play;
	private javax.swing.JButton create;
}
