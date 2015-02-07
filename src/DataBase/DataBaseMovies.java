package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CustomClass.Movie;

public class DataBaseMovies {
	private Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://localhost/alig";
		return DriverManager.getConnection(url, "postgres", "toor123");
	}
	
	public List<Integer> getMoviesIds() throws Exception {
		List<Integer> moviesIds = new ArrayList<Integer>();
		// Получение соединения с БД
		Connection con = getConnection();

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
		Connection con = getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Insert into movies"
				+ "(pathtomovie, owner, name) " + "values (?, ?, ?)");
		// Указание значений параметров запроса
		
		st.setString(1, movie.getpathtomovie());
		st.setInt(2, movie.getOwner());
		st.setString(3, movie.getName());

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
}
