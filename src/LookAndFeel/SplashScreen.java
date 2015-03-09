package LookAndFeel;

//SplashScreen.java
//Создание заставки для приложения
import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
	public SplashScreen() {
		super();
		// размер и положение окна на экране
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth()/2-75;
		double height = screenSize.getHeight()/2-75;
		setLocation((int) width, (int) height);
		setSize(150, 150);
		// снимаем экранную копию
		
		try {
			Robot robot = new Robot();
			
			capture = robot.createScreenCapture(new Rectangle((int) width, (int) height, 500,
					400));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// добавляем компонент-заставку
		getContentPane().add(new Splash());
		// выводим окно на экран
		setVisible(true);
		// заканчиваем работу по истечении некоторого времени
		try {
			
		} catch (Exception e) {
		}
		
	}

	// необходимые нам изображения
	private Image capture;
	private Image splash = getToolkit().getImage("src/Images/all128.png");

	// компонент рисует заставку
	class Splash extends JComponent {
		public void paintComponent(Graphics g) {
			// рисуем копию экрана
			g.drawImage(capture, 0, 0, this);
			// и поверх нее - рисунок с заставкой
			g.drawImage(splash, 0, 0, this);
		}
	}
}
