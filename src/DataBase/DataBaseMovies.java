package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DataBase.GetConnection;
import CustomClass.Movie;

public class DataBaseMovies {

	public List<Integer> getMoviesIds() throws Exception {
		List<Integer> moviesIds = new ArrayList<Integer>();
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
				"Select id From movies");
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			moviesIds.add(rs.getInt(1));
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return moviesIds;
	}

	public void addMovie(Movie movie) throws Exception {
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Insert into movies"
				+ "(owner, name, word) " + "values ( ?, ?, ?)");
		// Указание значений параметров запроса

		st.setInt(1, movie.getOwner());
		st.setString(2, movie.getName());
		st.setInt(3, movie.getWord());

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
	
	public Movie getPathByWord (int wordId) throws Exception {
		Movie mov = new Movie();
		Connection con = GetConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT owner, name, word FROM movies WHERE word = ?");
		st.setInt(1, wordId);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			mov = new Movie(wordId, rs.getInt(1), rs.getString(2), rs.getInt(3));
		}
		
		rs.close();
		con.close();
		return mov;		
	}
	
	public List<String> getKeyWords(int word) throws Exception {
		List<String> moviesIds = new ArrayList<String>();
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		PreparedStatement st = con.prepareStatement("Select key From key_words Where word = ?");
		st.setInt(1, word);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			moviesIds.add(rs.getString(1));
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return moviesIds;
	}
}
