package rec;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import CustomClass.Categories;
import CustomClass.User;
import CustomClass.Word;
import DataBase.DataBaseWord;
import LookAndFeel.ContentPanel;
import LookAndFeel.MyButtonUI;
import LookAndFeel.RoundButton;
import LookAndFeel.SimpleMenu;
import LookAndFeel.SplashScreen;

public class ChooseWord extends javax.swing.JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private User userNow;
	private Categories categoryNow;
	private ArrayList<String> Words;
	private Word WordFull;
	private Word wordNow;
	public DataBaseWord dw;
	private String Star32 = "src/Images/forWords/Star32.png";
	private String Star322 = "src/Images/forWords/Star322.png";
	int i = 0, j = 0;
	private Dimension d;
	private Point p;

	public ChooseWord(User user, Categories category, Dimension d, Point p) {
		this.userNow = user;
		this.categoryNow = category;
		this.d = d;
		this.p = p;
		DataBaseWord dw = new DataBaseWord();
		try {
			Words = (ArrayList<String>) dw.getWordsWhereC(categoryNow.getId());
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
		centerPanel.setLayout(new GridLayout(2, 2, 10, 10));

		defineB(button1);
		centerPanel.add(button1);

		defineB(button2);
		centerPanel.add(button2);

		defineB(button3);
		centerPanel.add(button3);

		defineB(button4);
		centerPanel.add(button4);
		
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

		setTitle("ChooseWord");
		//setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		d.setSize(d.getWidth()+200, d.getHeight());
		setSize(d);
		setLocation(p);
		setVisible(true);
		add(pa);
	}
	
	void BackActionPerformed() {
		Dimension d;
		Point p;
		p = getLocationOnScreen();
		d = getSize();
		new ChooseCategory(userNow, d, p, true);
		this.dispose();
	}

	private int random() {
		return (int) (Math.random() * Words.size());
	}

	private void defineB(final JButton button) {
		Image img, averimg;
		ImageObserver io;
		button.setSize(100, 40);
		button.setLayout(null);
		i = random();
		rate(button, i);

		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon("src/Images/forWords/"+ categoryNow.getName() + "/" + wordNow.getImg()));
		
		final String str = wordNow.getWord();
		
		button.setText("<html><body><h1 align='center'>" + str
				+ "</h1><br><p aligin='center'>" + wordNow.getDescription()
				+ "</p></body></html>");

		button.setVerticalAlignment(SwingConstants.CENTER);
		// button1.setHorizontalAlignment(SwingConstants.RIGHT);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.TOP);
		button.setMargin(new Insets(10, 10, 10, 10));
		button.setIconTextGap(20);
		Words.remove(i);

		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonActionPerformed(str);
			}
		});
		MyButtonUI.setupButtonUI(button, 0, 3);
	}

	private void rate(JButton button1, int in) {
		button1.setLayout(new FlowLayout());
		int i = 0, j = 0;
		DataBaseWord dw = new DataBaseWord();
		try {
			wordNow = dw.getIdByWords(Words.get(in));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		i = wordNow.getRate();
		JLabel[] labels = new JLabel[11];
		int x = 80, y = 180, width = 32, height = 32;
		while (i != 0) {
			if (i >= 2) {
				labels[j] = new JLabel();
				labels[j].setBounds(x, y, width, height);
				x = x + 32;
				labels[j].setIcon(new ImageIcon(Star32));
				button1.add(labels[j]);
				i = i - 2;
				j++;
			}
			if (i == 1) {
				labels[j] = new JLabel();
				labels[j].setBounds(x, y, width, height);
				x = x + 32;
				labels[j].setIcon(new ImageIcon(Star322));
				button1.add(labels[j]);
				i--;
				j++;
			}
		}
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JPanel southPanel;
	private javax.swing.JButton button1;
	private javax.swing.JButton button2;
	private javax.swing.JButton button3;
	private javax.swing.JButton button4;
	private javax.swing.JLabel hiLabel;

	private void buttonActionPerformed(String text) {

		this.dispose();

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

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new SplashScreen();
	}
	
}
