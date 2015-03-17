package rec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import server.Movie;
import server.User;
import LookAndFeel.ContentPanel;
import LookAndFeel.MyButtonUI;
import LookAndFeel.RoundButton;
import LookAndFeel.SimpleMenu;

public class ChooseCategory extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User userNow;
	private ArrayList<String> Categories;
	private server.Categories categ;
	private Dimension d;
	private Point p;
	private boolean boo;
	private Movie movieNow;

	public ChooseCategory(User user, Dimension d, Point p, boolean boo)
			throws ClassNotFoundException, IOException {
		this.userNow = user;
		this.d = d;
		this.p = p;
		this.boo = boo;
		ClientPart cl = new ClientPart();
		Categories = cl.getCategories();
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

		for (int i = 0; i < 10; i++) {
			final JButton butt = new JButton(Categories.get(i));
			butt.setIcon(new ImageIcon("src/Images/forWords/"
					+ Categories.get(i) + ".png"));

			centerPanel.add(butt);
			butt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					try {
						buttonActionPerformed(butt.getText());
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		hiLabel.setText("  " + userNow.getName()
				+ ", пожалуйста, выберите одну из категорий");
		hiLabel.setForeground(Color.WHITE);
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(RoundButton, java.awt.BorderLayout.WEST);
		p.add(hiLabel, java.awt.BorderLayout.EAST);
		p.setOpaque(false);
		
		northPanel.add(p, java.awt.BorderLayout.WEST);
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

	void buttonActionPerformed(String str) throws ClassNotFoundException,
			IOException {
		ClientPart cl = new ClientPart();
		try {
			categ = cl.getIdByCategories(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (boo) {
			Dimension d;
			Point p;
			p = getLocationOnScreen();
			d = getSize();
			new ChooseWord(userNow, categ, d, p);
			this.dispose();
		} else {
			this.dispose();
			movieNow = chooseVideo(categ.getId());
			
			if (movieNow != null) {
				new PlayVideo(userNow, movieNow);
			}
		}
	}

	public Movie chooseVideo(int categor) throws ClassNotFoundException,
			IOException {
		List<Movie> movies = new ArrayList<Movie>();

		ClientPart cl = new ClientPart();
		movies = cl.getMovieByCategor(categor);

		if (movies.size() == 0) {
			 Object[] inputs = { "¬ыбрать другое", "«аписать свое" };
			int res = JOptionPane
					.showOptionDialog(
							null,
							"¬ данной категории нет видео, вы можете выбрать видео из другой категории или записать свое",
							"Sorry", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE, null, null, "");
			if (res == JOptionPane.OK_OPTION) {
				System.out.println("pfirk");
				new ChooseCategory(userNow, null, null, false);
			}
			if (res == JOptionPane.CANCEL_OPTION) {
				System.out.println("we");
				new ChooseCategory(userNow, null, null, true);
			}
		} else {

			Movie mov = movies.get((int) (Math.random() * movies.size()));
			System.out.println(mov.getName());
			return mov;
		}
		return null;
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JPanel southPanel;
	private javax.swing.JLabel hiLabel;

}
