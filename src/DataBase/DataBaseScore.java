package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CustomClass.Movie;
import CustomClass.Score;
import CustomClass.User;

public class DataBaseScore {

	public void addScore(Score score) throws Exception {
		// Получение соединения с БД
		Connection con = GetConnection.getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Insert into score"
				+ "(username, date, rate) " + "values ( ?, ?, ?)");
		// Указание значений параметров запроса

		st.setInt(1, score.getUser());
		st.setDate(2, score.getDate());
		st.setInt(3, score.getRate());

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
	
	public int getScoreByUser(int user) throws Exception {
		int score = 0;
		Connection con = GetConnection.getConnection();

		PreparedStatement st = con.prepareStatement("Select rate "
				+ "From score " + "Where username = ?");
		st.setInt(1, user);

		ResultSet rs = st.executeQuery();
		
		
		while (rs.next()) {
			score = rs.getInt(1);
		}
		rs.close();
		con.close();
		return score;
	}
}
