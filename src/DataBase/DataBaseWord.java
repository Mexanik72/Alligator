package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CustomClass.Word;

public class DataBaseWord {
	private Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://localhost/alig";
		return DriverManager.getConnection(url, "postgres", "toor123");
	}

	public List<Integer> getWordsIds() throws Exception {
		List<Integer> wordsIds = new ArrayList<Integer>();
		// Получение соединения с БД
		Connection con = getConnection();

		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
				"Select id From words");
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			wordsIds.add(rs.getInt(1));
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return wordsIds;
	}
	
	public List<String> getWords() throws Exception {
		List<String> Words = new ArrayList<String>();
		// Получение соединения с БД
		Connection con = getConnection();

		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
				"Select word From words");
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			Words.add(rs.getString(1));
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return Words;
	}
	
	public List<Word> getWordById(int id) throws Exception {
		List<Word> words = new ArrayList<Word>();
		// Получение соединения с БД
		Connection con = getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Select word, rate "
				+ "From words " + "Where id = ?");
		// Указание значений параметров запроса
		st.setInt(1, id);

		// Выполнение запроса
		ResultSet rs = st.executeQuery();

		Word word = null;
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем результаты,
			// формируем новый объект Product
			// и помещаем его в коллекцию
			word = new Word(id, rs.getString(1), rs.getInt(2));
			words.add(word);
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return words;
	}
	
	public Word getIdByWords(String word) throws Exception {
		Word words = new Word();
		// Получение соединения с БД
		Connection con = getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Select id, rate "
				+ "From words " + "Where word = ?");
		// Указание значений параметров запроса
		st.setString(1, word);

		// Выполнение запроса
		ResultSet rs = st.executeQuery();

		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем результаты,
			// формируем новый объект Product
			// и помещаем его в коллекцию
			words = new Word(rs.getInt(2), word, rs.getInt(2));
			
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return words;
	}
	
	public void addWord(Word word) throws Exception {
		// Получение соединения с БД
		Connection con = getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Insert into words"
				+ "(word, rate) " + "values (?, ?)");
		// Указание значений параметров запроса
		
		st.setString(1, word.getWord());
		st.setInt(2, word.getRate());

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
}
