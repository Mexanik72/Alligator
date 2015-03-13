package LookAndFeel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import rec.PlayVideo;

public class RatePanel extends JPanel {

	public int rate;

	public RatePanel() {
		super(new GridBagLayout());
		JPanel lblFN = new JPanel();
		JLabel lblF = new JLabel("ќцените: ");
		rate(lblFN, 5);
		add(lblF, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 5), 0, 0));
		add(lblFN, new GridBagConstraints(1, 0, 1, 1, 1, 0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

	}

	private String Star32 = "src/Images/forWords/Star32.png";
	private String Star32w = "src/Images/forWords/Star32w.png";
	int ij = 0;

	private void rate(JPanel lab, int in) {
		lab.setLayout(new FlowLayout());
		int i = 0;
		int j = 0;

		i = in;
		final JLabel[] labels = new JLabel[6];
		int x = 80, y = 180, width = 32, height = 32;
		while (i != 0) {
			labels[j] = new JLabel();
			labels[j].setBounds(x, y, width, height);
			x = x + 32;
			labels[j].setIcon(new ImageIcon(Star32w));
			lab.add(labels[j]);
			labels[j].addMouseListener(new MouseListener() {
				public void mouseEntered(MouseEvent arg0) {
					Component c = arg0.getComponent();
					chooselab(c);
					for (int fr = 0; fr <= ij; fr++) {
						labels[fr].setIcon(new ImageIcon(Star32));
					}
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					Component c = arg0.getComponent();
					chooselab(c);
					setRate(ij);
					c = getParent();
					clos();
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					for (int fd = 0; fd < 5; fd++) {
						labels[fd].setIcon(new ImageIcon(Star32w));
					}
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});

			i--;
			j++;
		}
	}

	void clos() {
		SwingUtilities.windowForComponent(this).dispose();
	}
	
	int chooselab(Component c) {
		Rectangle re = c.getBounds();
		if (re.getX() == 5) {
			ij = 0;
		}
		if (re.getX() == 42) {
			ij = 1;
		}
		if (re.getX() == 79) {
			ij = 2;
		}
		if (re.getX() == 116) {
			ij = 3;
		}
		if (re.getX() == 153) {
			ij = 4;
		}
		return ij;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}
}