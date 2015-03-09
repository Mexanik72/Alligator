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
		// ��������� ���������� � ��
		Connection con = GetConnection.getConnection();

		// ���������� SQL-�������
		ResultSet rs = con.createStatement().executeQuery(
				"Select id From movies");
		// ����������� ���������� �������
		while (rs.next()) {
			// �� ������ ������ ������� ��������
			// ��������� � �������� � ���������
			moviesIds.add(rs.getInt(1));
		}
		// ��������� ������� � ���������� � ��
		rs.close();
		con.close();
		return moviesIds;
	}

	public void addMovie(Movie movie) throws Exception {
		// ��������� ���������� � ��
		Connection con = GetConnection.getConnection();

		// ���������� SQL-�������
		PreparedStatement st = con.prepareStatement("Insert into movies"
				+ "(owner, name, word, category) " + "values ( ?, ?, ?, ?)");
		// �������� �������� ���������� �������

		st.setInt(1, movie.getOwner());
		st.setString(2, movie.getName());
		st.setInt(3, movie.getWord());
		st.setInt(4, movie.getCategory());

		// ���������� �������
		st.executeUpdate();

		con.close();
	}
}
