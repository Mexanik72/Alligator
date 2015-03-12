package rec;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.media.ControllerAdapter;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import CustomClass.Categories;
import CustomClass.Movie;
import CustomClass.User;
import DataBase.DataBaseMovies;
import LookAndFeel.HintTextField;

public class PlayVideo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5485026095222917165L;

	private Player player;
	private Component center;
	private Component south;
	private User userNow;
	// private JFileChooser movieChooser;
	private JButton button;
	private Categories categor;
	private Movie movieNow;
	private List<String> Key_words;

	public PlayVideo(User user, Categories categor) {
		this.categor = categor;
		// if (movieChooser == null)
		// movieChooser = new JFileChooser();
		// movieChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		// movieChooser.addChoosableFileFilter(new MOVFilter());
		// movieChooser.setAcceptAllFileFilterUsed(false);
		// movieChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		this.userNow = user;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// button = new JButton("Select File");
		// ActionListener listener = new ActionListener() {
		// public void actionPerformed(ActionEvent event) {
		//
		// int status = movieChooser.showOpenDialog(PlayVideo.this);
		// if (status == JFileChooser.APPROVE_OPTION) {
		// File file = movieChooser.getSelectedFile();
		// if (!file.getName().endsWith(".mov")
		// && !file.getName().endsWith(".MOV"))
		// file = new File(file.toString() + ".mov");
		// try {
		// load(file);
		// } catch (Exception e) {
		// System.err.println("Try again: " + e);
		// }
		// }
		// }
		// };
		// button.addActionListener(listener);
		// getContentPane().add(button, BorderLayout.NORTH);
		// int category = 2;
		ClientPart cl = new ClientPart();
		movieNow = cl.getVideo(categor.getId());
		File file = new File(movieNow.getName());
		try {
			load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pack();
		show();
	}

	public void load(final File file) throws Exception {
		URL url = file.toURL();
		final Container contentPane = getContentPane();
		if (player != null) {
			player.stop();
		}
		player = Manager.createPlayer(url);
		ControllerListener listener = new ControllerAdapter() {
			public void realizeComplete(RealizeCompleteEvent event) {
				Component vc = player.getVisualComponent();
				if (vc != null) {
					contentPane.add(vc, BorderLayout.CENTER);
					vc.requestFocus();
					center = vc;
				} else {
					if (center != null) {
						contentPane.remove(center);
						contentPane.validate();
					}
				}
				Component cpc = player.getControlPanelComponent();
				if (cpc != null) {
					contentPane.add(cpc, BorderLayout.SOUTH);
					south = cpc;
				} else {
					if (south != null) {
						contentPane.remove(south);
						contentPane.validate();
					}
				}
				pack();
				setTitle(file.getName());
			}
		};
		player.addControllerListener(listener);
		player.start();
		final JTextField crocoText = new HintTextField(
				"Какое слово изображает этот человек?" + "Категория: "
						+ categor.getName());
		contentPane.add(crocoText, BorderLayout.NORTH);

		crocoText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					DataBaseMovies dbm = new DataBaseMovies();
					try {
						Key_words = dbm.getKeyWords(movieNow.getWord());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i=0;i<Key_words.size();i++){
						if (Key_words.get(i).equals(crocoText.getText().toLowerCase())) {
							System.out.println("norma");
						}
						else {
							System.out.println("bad");
						}
					}
				}
			}
		});
	}
}
