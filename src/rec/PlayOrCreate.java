package rec;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import CustomClass.User;
import LookAndFeel.SimpleMenu;

public class PlayOrCreate extends JFrame{
	
	private User userNow;
	
	PlayOrCreate(User user) {
		this.userNow = user;
		initComponents();
	}

	private void initComponents() {
		centerPanel = new javax.swing.JPanel();
		northPanel = new javax.swing.JPanel();
		play = new javax.swing.JButton("Play");
		create = new javax.swing.JButton("Create");
		
		northPanel.setLayout(new BorderLayout());
		SimpleMenu sm = new SimpleMenu(userNow);
		northPanel.add(sm, java.awt.BorderLayout.EAST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		play.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playActionPerformed();
			}
		});
		
		create.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createActionPerformed();
			}
		});
		
		setTitle("PlayOrCreate");

		centerPanel.setLayout(new GridLayout(2,1));

		getContentPane().add(northPanel, java.awt.BorderLayout.NORTH);
		getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);
		
		centerPanel.add(play);
		centerPanel.add(create);
	}

	private void playActionPerformed() {
		PlayVideo pv = new PlayVideo(userNow);
		this.dispose();
	}
	
	private void createActionPerformed() {
		ChooseCategory cw = new ChooseCategory(userNow);
		cw.setSize(720, 720);
		cw.setLocationRelativeTo(null);
		cw.setVisible(true);
		this.dispose();
	}
	
	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JButton play;
	private javax.swing.JButton create;
}
