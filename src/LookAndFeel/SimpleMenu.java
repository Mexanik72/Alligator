package LookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.*;

import rec.Enterance;
import CustomClass.User;
import DataBase.DataBaseUsers;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

public class SimpleMenu extends JApplet {

	private JMenu menu;

	private JMenuItem item1, item2, item3, item4;

	private User userNow;

	private class MenuItemListener

	implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem jmi =

			(JMenuItem) e.getSource();

			if (jmi.equals(item4))

				System.exit(0);

			if (jmi.equals(item1)) {
				
			}
			
			if (jmi.equals(item3)) {
				
				Enterance frame = new Enterance();
				frame = new Enterance();
				frame.setSize(500, 320);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			} else

				showStatus("My Simple Menu");

		}
	}

	public SimpleMenu(User user) {
		this.userNow = user;
		init();
	}

	public void init() {

		JMenuBar menubar = new JMenuBar();

		setJMenuBar(menubar);
		// menubar.setOpaque(false);

		menu = new JMenu(userNow.getUsername());
		// menu.setIcon(defaultIcon);
		Image img, averimg;
		if (userNow.getImg() != null) {
			img = new ImageIcon("src/Images/forUsers/" + userNow.getImg())
					.getImage();
		} else {
			img = new ImageIcon("src/Images/forUsers/default.png").getImage();
		}

		CropImageFilter crp = new CropImageFilter(50, 50, 30, 30);

		// в) увеличиваем изображение в два раза с усреднением

		AreaAveragingScaleFilter asf = new AreaAveragingScaleFilter(16, 16);

		averimg = createImage(new FilteredImageSource(img.getSource(), asf));
		menu.setIcon(new ImageIcon(averimg));
		item1 = new JMenuItem("Таблица рекордов");

		item2 = new JMenuItem("Загрузить аватар");

		item3 = new JMenuItem("Сменить пользователя");

		item4 = new JMenuItem("Выход");

		item1.addActionListener(

		new MenuItemListener());

		item2.addActionListener(

		new MenuItemListener());

		item3.addActionListener(

		new MenuItemListener());

		item2.addActionListener((new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadImageMouseClicked();
			}
		}));

		menu.add(item1);

		menu.add(item2);

		menu.add(item3);

		menu.add(item4);

		menubar.add(menu);

	}

	private void loadImageMouseClicked() {
		String fileToLoad = null;
		BufferedImage imgToLoad = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select File To Load");
		int returnVal = chooser.showDialog(new JDialog(), "Select file");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileToLoad = chooser.getSelectedFile().toString();
		}
		// Graphics2D graph = (Graphics2D) drawingPanel.getGraphics();
		try {
			imgToLoad = ImageIO.read(new File(fileToLoad));
		} catch (IOException excp) {
			excp.printStackTrace();
		}
		// graph.drawImage(imgToLoad, null, 150,50);
		String img = userNow.getUsername() + ".png";
		File f = new File("src/Images/forUsers/" + img);
		userNow.setImg(img);
		DataBaseUsers db = new DataBaseUsers();
		try {
			db.setImg(userNow.getId(), img);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		menu.setIcon(new ImageIcon("src/Images/forUsers/" + userNow.getImg()));
		String format = "PNG";
		try {
			ImageIO.write(imgToLoad, format, f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
