package rec;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.media.ControllerAdapter;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import server.Categories;
import server.Movie;
import server.User;
import LookAndFeel.HintTextField;
import LookAndFeel.RatePanel;

public class PlayVideo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5485026095222917165L;

	private Player player;
	private Component center;
	private Component south;
	private User userNow;
	private Categories categor;
	private Movie movieNow;
	private int rate;

	public PlayVideo(User user, Categories categor) throws ClassNotFoundException, IOException {
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

		movieNow = chooseVideo(categor.getId());
		ClientPart cl = new ClientPart();
		if (movieNow != null) {
			movieNow = cl.getVideo(movieNow);
			File file = new File(movieNow.getName());
			try {
				load(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					ClientPart cl = new ClientPart();
					boolean guess = false;
					try {
						guess = cl.getKeyWords(movieNow, rate, crocoText.getText().toLowerCase());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
					if (guess) {
						Dimension d = new Dimension();
						Point p;
						p = getLocationOnScreen();
						d.setSize(700, 700);
						clos();
						jop();
						new PlayOrCreate(userNow, d, null);
					} else {
						System.out.println("bad");
					}
				}
			}
		});
	}

	public void jop() {
		RatePanel rap = new RatePanel();
		Object[] inputs = {};
		JOptionPane.showOptionDialog(null, rap, "Rate",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, inputs, "");
		this.rate = rap.getRate();
		
	}

	public void clos() {
		this.dispose();
	}

	public Movie chooseVideo(int categor) throws ClassNotFoundException, IOException {
		List<Movie> movies = new ArrayList<Movie>();
		
		ClientPart cl = new ClientPart();
		movies = cl.getMovieByCategor(categor);

		if (movies.size() == 0) {
			// Object[] inputs = { "Выбрать другое", "Записать свое" };
			int res = JOptionPane
					.showOptionDialog(
							null,
							"В данной категории нет видео, вы можете выбрать видео из другой категории или записать свое",
							"Sorry", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE, null, null, "");
			if (res == JOptionPane.OK_OPTION) {
				System.out.println("pfirk");
				new ChooseCategory(userNow, null, null, false);
				clos();
			}
			if (res == JOptionPane.CANCEL_OPTION) {
				System.out.println("we");
				new ChooseCategory(userNow, null, null, true);
				clos();
			}
			clos();
		} else {

			Movie mov = movies.get((int) (Math.random() * movies.size()));
			System.out.println(mov.getName());
			return mov;
		}
		return null;
	}
}
