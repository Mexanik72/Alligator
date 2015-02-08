package rec;

import java.awt.GridLayout;

import javax.swing.JFrame;

import CustomClass.User;

public class PlayOrCreate extends JFrame{
	
	private User userNow;
	
	PlayOrCreate(User user) {
		this.userNow = user;
		initComponents();
	}

	private void initComponents() {
		centerPanel = new javax.swing.JPanel();
		play = new javax.swing.JButton("Play");
		create = new javax.swing.JButton("Create");
		
		play.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playActionPerformed(evt);
			}
		});
		
		create.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createActionPerformed(evt);
			}
		});
		
		setTitle("PlayOrCreate");

		centerPanel.setLayout(new GridLayout(2,1));

		getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);
		
		centerPanel.add(play);
		centerPanel.add(create);
	}

	private void playActionPerformed(java.awt.event.ActionEvent evt) {
		PlayVideo pv = new PlayVideo(userNow);
		this.dispose();
	}
	
	private void createActionPerformed(java.awt.event.ActionEvent evt) {
		ChooseWord cw = new ChooseWord(userNow);
		cw.setSize(720, 720);
		cw.setLocationRelativeTo(null);
		cw.setVisible(true);
		this.dispose();
	}
	
	private javax.swing.JPanel centerPanel;
	private javax.swing.JButton play;
	private javax.swing.JButton create;
}
