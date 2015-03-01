package rec;

import javax.swing.JFrame;

import CustomClass.User;
import CustomClass.Word;
import DataBase.DataBaseWord;

public class Gifac extends JFrame {
	
	private static final long serialVersionUID = 1170020159907047430L;
	Word WordFull;

	public Gifac(String text, User userNow) {
		
		camDataSource dataSource = new camDataSource();
		dataSource.setMainSource();
		dataSource.makeDataSourceCloneable();
		dataSource.startProcessing();
		DataBaseWord dw = new DataBaseWord();
		try {
			WordFull = dw.getIdByWords(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RecordNew rn = new RecordNew(dataSource, userNow, WordFull);
		rn.setSize(1280, 720);
		rn.setLocationRelativeTo(null);
	    
		rn.setVisible(true);
	}

}