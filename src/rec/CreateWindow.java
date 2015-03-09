package rec;

import javax.swing.JFrame;

import LookAndFeel.SplashScreen;

public class CreateWindow {

	Enterance frame = null;
	public CreateWindow() {
		frame = new Enterance();
		frame.setSize(500, 320);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		CreateWindow cw = new CreateWindow();

	}

}
