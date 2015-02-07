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
		// ��������� ���������� � ��
		Connection con = getConnection();

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
		Connection con = getConnection();

		// ���������� SQL-�������
		PreparedStatement st = con.prepareStatement("Insert into movies"
				+ "(pathtomovie, owner, name) " + "values (?, ?, ?)");
		// �������� �������� ���������� �������
		
		st.setString(1, movie.getpathtomovie());
		st.setInt(2, movie.getOwner());
		st.setString(3, movie.getName());

		// ���������� �������
		st.executeUpdate();

		con.close();
	}
}
