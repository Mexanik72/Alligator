package rec;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LookAndFeel.ContentPanel;
import LookAndFeel.MyButtonUI;
import LookAndFeel.RoundButton;
import CustomClass.User;
import CustomClass.Word;
import DataBase.DataBaseWord;
import LookAndFeel.SimpleMenu;

public class ChooseCategory extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User userNow;
	private ArrayList<String> Categories;
	private Word WordFull;
	private Word wordNow;
	public DataBaseWord dw;
	private CustomClass.Categories categ;
	private Dimension d;
	private Point p;
	private boolean boo;

	public ChooseCategory(User user, Dimension d, Point p, boolean boo) {
		this.userNow = user;
		this.d = d;
		this.p = p;
		this.boo = boo;
		DataBaseWord dw = new DataBaseWord();
		try {
			Categories = (ArrayList<String>) dw.getCategories();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponents();
	}

	private void initComponents() {

		centerPanel = new JPanel();
		northPanel = new JPanel();
		southPanel = new JPanel();
		hiLabel = new JLabel();

		if (d != null)
			setSize(d);
		else
			setSize(720, 720);
		if (p != null)
			setLocation(p);
		else
			setLocationRelativeTo(null);
		setVisible(true);

		centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		centerPanel.setLayout(new GridLayout(10, 1, 10, 10));

		for (int i = 0; i < 8; i++) {
			final JButton butt = new JButton(Categories.get(i));
			butt.setIcon(new ImageIcon("src/Images/forWords/"
					+ Categories.get(i) + ".png"));

			centerPanel.add(butt);
			butt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					buttonActionPerformed(butt.getText());
				}
			});
			MyButtonUI.setupButtonUI(butt, 0, 3);
		}

		JButton RoundButton = new RoundButton();
		RoundButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BackActionPerformed();
			}
		});

		ContentPanel pa = new ContentPanel();
		pa.setLayout(new BorderLayout());
		pa.add(centerPanel, java.awt.BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		northPanel.setLayout(new java.awt.BorderLayout());
		northPanel.setOpaque(false);
		hiLabel.setText("Hi, " + userNow.getName()
				+ ", please, choose 1 of the buttons");
		northPanel.add(RoundButton, java.awt.BorderLayout.WEST);
		SimpleMenu sm = new SimpleMenu(userNow);
		northPanel.add(sm, java.awt.BorderLayout.EAST);
		pa.add(northPanel, java.awt.BorderLayout.NORTH);
		southPanel.setOpaque(false);
		southPanel.setLayout(new java.awt.BorderLayout());
		pa.add(southPanel, java.awt.BorderLayout.SOUTH);

		setTitle("ChooseCategories");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(pa);
	}

	void BackActionPerformed() {
		Dimension d;
		Point p;
		p = getLocationOnScreen();
		d = getSize();
		new PlayOrCreate(userNow, d, p);
		this.dispose();
	}

	void buttonActionPerformed(String str) {
		if (boo) {
			try {
				DataBaseWord dw = new DataBaseWord();
				categ = new CustomClass.Categories();
				categ = dw.getIdByCategories(str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dimension d;
			Point p;
			p = getLocationOnScreen();
			d = getSize();
			new ChooseWord(userNow, categ, d, p);
			this.dispose();
		} else {
			this.dispose();
			DataBaseWord dw = new DataBaseWord();
			categ = new CustomClass.Categories();
			try {
				categ = dw.getIdByCategories(str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			new PlayVideo(userNow, categ);
			
		}
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JPanel southPanel;
	private javax.swing.JLabel hiLabel;

}
