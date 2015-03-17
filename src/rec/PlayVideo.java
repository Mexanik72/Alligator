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
import java.util.Calendar;
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
	private List<String> Key_words;
	private int attempt;

	public PlayVideo(User user, Movie mov, Categories categor)
			throws ClassNotFoundException, IOException {
		this.movieNow = mov;
		this.categor = categor;
		// if (movieChooser == null)
		// movieChooser = new JFileChooser();
		// movieChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		// movieChooser.addChoosableFileFilter(new MOVFilter());
		// movieChooser.setAcceptAllFileFilterUsed(false);
		// movieChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.attempt = 3;
		this.userNow = user;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("PLAYVIDEO");
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

		// movieNow = chooseVideo(categor.getId());
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
				// Component cpc = player.getControlPanelComponent();
				// if (cpc != null) {
				// contentPane.add(cpc, BorderLayout.SOUTH);
				// south = cpc;
				// } else {
				// if (south != null) {
				// contentPane.remove(south);
				// contentPane.validate();
				// }
				// }
				pack();
				setTitle("Воспроизведение");
			}
		};
		player.addControllerListener(listener);
		player.start();
		final JTextField crocoText = new HintTextField(
				"Какое слово изображает этот человек?   " + "Категория: "
						+ categor.getName());
		contentPane.add(crocoText, BorderLayout.NORTH);

		crocoText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					ClientPart cl = new ClientPart();
					try {
						Key_words = cl.getKeyWords(movieNow.getWord());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for (int i = 0; i < Key_words.size(); i++) {
						if (Key_words.get(i).equals(
								crocoText.getText().toLowerCase())) {
							Dimension d = new Dimension();
							Point p;
							p = getLocationOnScreen();
							d.setSize(700, 700);
							clos();
							java.util.Date someDate = Calendar.getInstance()
									.getTime();
							java.sql.Date sqlDate = new java.sql.Date(someDate
									.getTime());
							jop();
							ClientPart cl1 = new ClientPart();
							try {
								cl1.addRateToMovie(movieNow, sqlDate, rate);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							new PlayOrCreate(userNow, d, null);
						} else {
							crocoText.setText("");
							attempt--;
							if (attempt > 0) {
								Object[] inputs = { "Попробовать ещё",
										"Выйти в главное меню" };
								int res = JOptionPane.showOptionDialog(null,
										"К сожалению, вы не угадали, попробуйте снова. Осталось попыток: "
												+ attempt, "Неверно",
										JOptionPane.OK_OPTION,
										JOptionPane.INFORMATION_MESSAGE, null,
										inputs, "");
								if (res == JOptionPane.YES_OPTION) {

								}
								if (res == JOptionPane.NO_OPTION) {
									Dimension d = new Dimension();
									d.setSize(700, 700);
									clos();
									new PlayOrCreate(userNow, d, null);
								}
							} else {
								Object[] inputs = { "Выйти в главное меню" };
								int res = JOptionPane
										.showOptionDialog(
												null,
												"К сожалению, вы не угадали, и у вас не осталось попыток. Попробуйте заново",
												"Неверно",
												JOptionPane.OK_OPTION,
												JOptionPane.INFORMATION_MESSAGE,
												null, inputs, "");
								if (res == JOptionPane.YES_OPTION) {
									Dimension d = new Dimension();
									d.setSize(700, 700);
									clos();
									new PlayOrCreate(userNow, d, null);
								}
								if (res == JOptionPane.DEFAULT_OPTION) {
									Dimension d = new Dimension();
									d.setSize(700, 700);
									clos();
									new PlayOrCreate(userNow, d, null);
								}
							}
						}
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

}
