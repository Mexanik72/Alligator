package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CustomClass.Categories;
import CustomClass.Word;

public class DataBaseWord {

	public List<Integer> getWordsIds() throws Exception {
		List<Integer> wordsIds = new ArrayList<Integer>();
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

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
		Connection con = GetConnection.getConnection();

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

	public List<String> getWordsWhereC(int category) throws Exception {
		List<String> Words = new ArrayList<String>();
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		PreparedStatement st = con.prepareStatement(
				"Select word " + "From words " + "Where categor = ?");
		st.setInt(1, category);
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			Words.add(rs.getString(1));
		}
		st.close();
		rs.close();
		con.close();
		return Words;
	}
	
	public int getWordById(int id) throws Exception {
		int rate = 0;
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		PreparedStatement st = con.prepareStatement(
				"Select  rate " + "From words " + "Where id = ?");
		st.setInt(1, id);
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			rate = rs.getInt(1);
		}
		st.close();
		rs.close();
		con.close();
		return rate;
	}

	public List<String> getCategories() throws Exception {
		List<String> Categories = new ArrayList<String>();
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
				"Select name From categories");
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			Categories.add(rs.getString(1));
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return Categories;
	}

	public Categories getIdByCategories(String name) throws Exception {
		Categories categ = new Categories();
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Select id "
				+ "From categories " + "Where name = ?");
		// Указание значений параметров запроса
		st.setString(1, name);

		// Выполнение запроса
		ResultSet rs = st.executeQuery();

		// Перечисляем результаты выборки
		while (rs.next()) {
			categ = new Categories(rs.getInt(1), name);

		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return categ;
	}

	public Word getIdByWords(String word) throws Exception {
		Word words = new Word();
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con
				.prepareStatement("Select id, rate, description, img, categor "
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
			words = new Word(rs.getInt(1), word, rs.getInt(2), rs.getString(3),
					rs.getString(4), rs.getInt(5));

		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return words;
	}

	public void addWord(Word word) throws Exception {
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Insert into words"
				+ "(word, rate, description, img) " + "values (?, ?, ?, ?)");
		// Указание значений параметров запроса

		st.setString(1, word.getWord());
		st.setInt(2, word.getRate());
		st.setString(3, word.getDescription());
		st.setString(4, word.getImg());

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
	
	public List<Integer> getIdByCategories (int category) throws Exception {
		List<Integer> words = new ArrayList<Integer>();
		Connection con = GetConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT id FROM words WHERE categor = ?");
		st.setInt(1, category);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			words.add(rs.getInt(1));
		}
		
		rs.close();
		con.close();
		return words;
	}

	public int getCount() throws Exception {

		Connection con = GetConnection.getConnection();
		PreparedStatement st = con
				.prepareStatement("SELECT COUNT(*) FROM words;");
		ResultSet rs = st.executeQuery();

		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}

		rs.close();
		con.close();

		return count;
	}
}
