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
				+ "(owner, name, word, category) " + "values ( ?, ?, ?, ?)");
		// Указание значений параметров запроса

		st.setInt(1, movie.getOwner());
		st.setString(2, movie.getName());
		st.setInt(3, movie.getWord());
		st.setInt(4, movie.getCategory());

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
}
