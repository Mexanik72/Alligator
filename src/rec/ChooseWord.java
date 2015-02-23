package rec;

import java.awt.GridLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CustomClass.User;
import CustomClass.Word;
import DataBase.DataBaseWord;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ChooseWord extends javax.swing.JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private User userNow;
	private ArrayList<String> Words;
	private Word WordFull;
	public DataBaseWord dw;

	public ChooseWord(User user) {
		this.userNow = user;
		DataBaseWord dw = new DataBaseWord();
		try {
			Words = (ArrayList<String>) dw.getWords();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponents();

	}

	public ChooseWord() {
		// TODO Auto-generated constructor stub
	}

	private void initComponents() {
		
		centerPanel = new JPanel();
		northPanel = new JPanel();
		southPanel = new JPanel();
		hiLabel = new JLabel();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();

		centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		centerPanel.setLayout(new GridLayout(2, 2, 50, 50));
 
		int i;
	
		button1.setSize(100, 40);
		button1.setText(Words.get(i = random()));
		Words.remove(i);
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String text = button1.getText();
				buttonActionPerformed(text);
			}
		});
		centerPanel.add(button1);

		
		button2.setSize(100, 40);
		button2.setText(Words.get(i = random()));
		Words.remove(i);
		button2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String text = button2.getText();
				buttonActionPerformed(text);
			}
		});
		centerPanel.add(button2);

		
		button3.setSize(100, 40);
		button3.setText(Words.get(i = random()));
		Words.remove(i);
		button3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String text = button3.getText();
				buttonActionPerformed(text);
			}
		});
		centerPanel.add(button3);

		
		button4.setSize(100, 40);
		button4.setText(Words.get(i = random()));
		Words.remove(i);
		button4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String text = button4.getText();
				buttonActionPerformed(text);
			}
		});
		centerPanel.add(button4);

		add(centerPanel, java.awt.BorderLayout.CENTER);

		northPanel.setLayout(new java.awt.BorderLayout());

		hiLabel.setText("Hi, " + userNow.getName()
				+ ", please, choose 1 of the buttons");
		northPanel.add(hiLabel);
		add(northPanel, java.awt.BorderLayout.NORTH);
		
		southPanel.setLayout(new java.awt.BorderLayout());
		add(southPanel, java.awt.BorderLayout.SOUTH);

		setTitle("ChooseWord");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private int random() {
		return (int) (Math.random() * Words.size());
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JPanel southPanel;
	private javax.swing.JButton button1;
	private javax.swing.JButton button2;
	private javax.swing.JButton button3;
	private javax.swing.JButton button4;
	private javax.swing.JLabel hiLabel;

	@SuppressWarnings("deprecation")
	private void buttonActionPerformed(String text) {
		
		Thread thread = new Thread(new ChooseWord());
		thread.start();
		
		camDataSource dataSource = new camDataSource();
		dataSource.setMainSource();
		dataSource.makeDataSourceCloneable();
		dataSource.startProcessing();
		dw = new DataBaseWord();
		try {
			WordFull = dw.getIdByWords(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RecordNew rn = new RecordNew(dataSource, userNow, WordFull);
		rn.setSize(1280, 720);
		rn.setLocationRelativeTo(null);
	    
		rn.setVisible(true);
		thread.interrupt();
				
		this.dispose();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("hello");
		new Gif();
	}
}
