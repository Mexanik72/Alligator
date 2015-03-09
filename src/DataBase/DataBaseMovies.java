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
				+ "(pathtomovie, owner, name, word, date) " + "values (?, ?, ?, ?, ?)");
		// �������� �������� ���������� �������

		st.setString(1, movie.getpathtomovie());
		st.setInt(2, movie.getOwner());
		st.setString(3, movie.getName());
		st.setInt(4, movie.getWord());
		st.setDate(5, movie.getDate());

		// ���������� �������
		st.executeUpdate();

		con.close();
	}
	
	public String getPathByWord (int wordId) throws Exception {
		Connection con = GetConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT name FROM movies WHERE word = ?");
		st.setInt(1, wordId);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			String path = rs.getString(1);
			return path;
		}
		
		rs.close();
		con.close();
		return null;		
	}
}
