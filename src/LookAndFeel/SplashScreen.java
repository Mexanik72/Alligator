package LookAndFeel;

//SplashScreen.java
//�������� �������� ��� ����������
import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
	public SplashScreen() {
		super();
		// ������ � ��������� ���� �� ������
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth()/2-75;
		double height = screenSize.getHeight()/2-75;
		setLocation((int) width, (int) height);
		setSize(150, 150);
		// ������� �������� �����
		
		try {
			Robot robot = new Robot();
			
			capture = robot.createScreenCapture(new Rectangle((int) width, (int) height, 500,
					400));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// ��������� ���������-��������
		getContentPane().add(new Splash());
		// ������� ���� �� �����
		setVisible(true);
		// ����������� ������ �� ��������� ���������� �������
		try {
			
		} catch (Exception e) {
		}
		
	}

	// ����������� ��� �����������
	private Image capture;
	private Image splash = getToolkit().getImage("src/Images/all128.png");

	// ��������� ������ ��������
	class Splash extends JComponent {
		public void paintComponent(Graphics g) {
			// ������ ����� ������
			g.drawImage(capture, 0, 0, this);
			// � ������ ��� - ������� � ���������
			g.drawImage(splash, 0, 0, this);
		}
	}
}
