package rec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CustomClass.Score;
import CustomClass.User;
import DataBase.DataBaseScore;
import DataBase.DataBaseUsers;
import LookAndFeel.ContentPanel;

public class RatesTabel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User userNow;

	public RatesTabel(User user) {
		this.userNow = user;
		initComponents();
	}

	/**
	 * 
	 */
	private void initComponents() {

		DataBaseScore dbs = new DataBaseScore();

		int scoreInt = 0;
		List<Score> listScores = new ArrayList<Score>();
		Score score = new Score();

		try {
			scoreInt = dbs.getScoreByUser(userNow.getId());
			listScores = dbs.getFiveTopUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Font font    = new Font("TimesRoman", Font.BOLD,    16); 
		JLabel lbl = new JLabel("Количество ваших очков: ");
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