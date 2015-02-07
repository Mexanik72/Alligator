package rec;

import java.awt.GridLayout;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CustomClass.User;
import CustomClass.Word;
import DataBase.DataBaseWord;

public class ChooseWord extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User userNow;
	private List<String> Words;
	private Word WordFull;
	public DataBaseWord dw;

	public ChooseWord(User user) {
		this.userNow = user;
		DataBaseWord dw = new DataBaseWord();
		try {
			Words = dw.getWords();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponents();

	}

	private void initComponents() {
		centerPanel = new JPanel();
		northPanel = new JPanel();
		hiLabel = new JLabel();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();

		centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		centerPanel.setLayout(new GridLayout(2, 2, 50, 50));

		button1.setSize(100, 40);
		button1.setText(Words.get(random()));
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button1ActionPerformed(evt);
			}
		});
		centerPanel.add(button1);

		button2.setSize(100, 40);
		button2.setText(Words.get((int) (Math.random() * 4)));
		button2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button2ActionPerformed(evt);
			}
		});
		centerPanel.add(button2);

		button3.setSize(100, 40);
		button3.setText(Words.get((int) (Math.random() * 4)));
		button3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button3ActionPerformed(evt);
			}
		});
		centerPanel.add(button3);

		button4.setSize(100, 40);
		button4.setText(Words.get((int) (Math.random() * 4)));
		button4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button4ActionPerformed(evt);
			}
		});
		centerPanel.add(button4);

		add(centerPanel, java.awt.BorderLayout.CENTER);

		northPanel.setLayout(new java.awt.BorderLayout());

		hiLabel.setText("Hi " + userNow.getName()
				+ " please, choose 1 of the buttons");
		northPanel.add(hiLabel);
		add(northPanel, java.awt.BorderLayout.NORTH);

		setTitle("ChooseWord");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private int random() {
		return (int) (Math.random() * Words.size() - 1);
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JButton button1;
	private javax.swing.JButton button2;
	private javax.swing.JButton button3;
	private javax.swing.JButton button4;
	private javax.swing.JLabel hiLabel;

	private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < Words.size(); i++) {
			if (Words.get(i).equals(button1.getText())) {
				camDataSource dataSource = new camDataSource(null);
				dataSource.setMainSource();
				dataSource.makeDataSourceCloneable();
				dataSource.startProcessing();
				dw = new DataBaseWord();
				try {
					WordFull = dw.getIdByWords(Words.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RecordNew rn = new RecordNew(dataSource, userNow, WordFull);
				rn.setSize(1280, 720);
				rn.setLocationRelativeTo(null);
				rn.setVisible(true);
				this.dispose();
			}
		}
	}

	private void button2ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < Words.size(); i++) {
			if (Words.get(i).equals(button2.getText())) {
				camDataSource dataSource = new camDataSource(null);
				dataSource.setMainSource();
				dataSource.makeDataSourceCloneable();
				dataSource.startProcessing();
				dw = new DataBaseWord();
				try {
					WordFull = dw.getIdByWords(Words.get(i).toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RecordNew rn = new RecordNew(dataSource, userNow, WordFull);
				rn.setSize(1280, 720);
				rn.setLocationRelativeTo(null);
				rn.setVisible(true);
				this.dispose();
			}
		}
	}

	private void button3ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < Words.size(); i++) {
			if (Words.get(i).equals(button3.getText())) {
				camDataSource dataSource = new camDataSource(null);
				dataSource.setMainSource();
				dataSource.makeDataSourceCloneable();
				dataSource.startProcessing();
				dw = new DataBaseWord();
				try {
					WordFull = dw.getIdByWords(Words.get(i).toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RecordNew rn = new RecordNew(dataSource, userNow, WordFull);
				rn.setSize(1280, 720);
				rn.setLocationRelativeTo(null);
				rn.setVisible(true);
				this.dispose();
			}
		}
	}

	private void button4ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < Words.size(); i++) {
			if (Words.get(i).equals(button4.getText())) {
				camDataSource dataSource = new camDataSource(null);
				dataSource.setMainSource();
				dataSource.makeDataSourceCloneable();
				dataSource.startProcessing();
				dw = new DataBaseWord();
				try {
					WordFull = dw.getIdByWords(Words.get(i).toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RecordNew rn = new RecordNew(dataSource, userNow, WordFull);
				rn.setSize(1280, 720);
				rn.setLocationRelativeTo(null);
				rn.setVisible(true);
				this.dispose();
			}
		}
	}

}
