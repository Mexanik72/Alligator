package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public List<Movie> getMovieByCategor (int categor) {
		List<Movie> mov = new ArrayList<Movie>();
		Connection con = GetConnection.getConnection();
		PreparedStatement st;
		try {
			st = con.prepareStatement("Select movies.id, movies.owner, movies.name, movies.word from movies inner join words on movies.word = words.id and words.categor = ?");
		
		st.setInt(1, categor);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			mov.add( new Movie(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
		}
		
		rs.close();
		con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void addRatetoMovie(Movie movie, Date date, int rate) throws Exception {
		Connection con = GetConnection.getConnection();

		PreparedStatement st = con.prepareStatement("Insert into rate_of_video"
				+ "(user_owner, movie, date, rate) " + "values ( ?, ?, ?, ?)");
		
		st.setInt(1, movie.getOwner());
		st.setInt(2, movie.getId());
		st.setDate(3, date);
		st.setInt(4, rate);

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
}
