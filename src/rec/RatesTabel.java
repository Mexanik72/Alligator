package rec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



import server.Score;
import server.User;
import LookAndFeel.ContentPanel;

public class RatesTabel extends JFrame {

	private static final long serialVersionUID = 1L;
	User userNow;

	public RatesTabel(User user) {
		this.userNow = user;
		try {
			initComponents();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException 
	 * @throws ClassNotFoundException 
*
*/
	private void initComponents() throws IOException, ClassNotFoundException {
		ClientPart cl = new ClientPart();
		List<Score> listScores;
		int scoreInt = cl.getScoreByUser(userNow.getId());
		ClientPart cl1 = new ClientPart();
		listScores = cl1.getFiveTopUsers();
		Score score = new Score();
		
		Font font = new Font("TimesRoman", Font.BOLD, 16);
		JLabel lbl = new JLabel("blabla: ");

		lbl.setForeground(new Color(255, 181, 130));
		lbl.setFont(font);
		JLabel ownScore = new JLabel("" + scoreInt);
		ownScore.setForeground(new Color(255, 181, 130));
		ownScore.setFont(font);

		northPanel.setLayout(new FlowLayout());
		centerPanel.setLayout(new GridLayout(listScores.size()+1, 2));
		
		JLabel lbq = new JLabel("");
		centerPanel.add(lbq);
		JLabel lbw = new JLabel("Пользователь: ");
		centerPanel.add(lbw);
		lbw.setForeground( Color.white);
		
		lbw.setFont(font);
		JLabel lbe = new JLabel("Очки: ");
		lbe.setForeground(Color.white);
		lbe.setFont(font);
		centerPanel.add(lbe);
		
		for (int i = 0; i < listScores.size(); i++) {
			JLabel lb = new JLabel();
			lb.setText(i+1 + ". ");

			centerPanel.add(lb);
			lb.setForeground(Color.white);
			JLabel lbu = new JLabel();
			lbu.setText("" + listScores.get(i).getUserStr());
			lbu.setForeground(Color.white);
			centerPanel.add(lbu);
			JLabel lbr = new JLabel();
			lbr.setText("" + listScores.get(i).getRate());
			lbr.setForeground(Color.white);
			centerPanel.add(lbr);
		}
		
		setTitle("Таблица рейтингов");
		
		ContentPanel pa = new ContentPanel();
		pa.setLayout(new BorderLayout());
		
		northPanel.add(lbl);
		northPanel.add(ownScore);
		pa.add(northPanel, BorderLayout.NORTH);
		pa.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		northPanel.setOpaque(false);
		setSize(400, 200);
		setLocationRelativeTo(null);
		setVisible(true);
		add(pa);
	}

	JPanel northPanel = new JPanel();
	JPanel centerPanel = new JPanel();

}
