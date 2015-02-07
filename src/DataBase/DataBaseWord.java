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
		// ��������� ���������� � ��
		Connection con = getConnection();

		// ���������� SQL-�������
		ResultSet rs = con.createStatement().executeQuery(
				"Select id From words");
		// ����������� ���������� �������
		while (rs.next()) {
			// �� ������ ������ ������� ��������
			// ��������� � �������� � ���������
			wordsIds.add(rs.getInt(1));
		}
		// ��������� ������� � ���������� � ��
		rs.close();
		con.close();
		return wordsIds;
	}
	
	public List<String> getWords() throws Exception {
		List<String> Words = new ArrayList<String>();
		// ��������� ���������� � ��
		Connection con = getConnection();

		// ���������� SQL-�������
		ResultSet rs = con.createStatement().executeQuery(
				"Select word From words");
		// ����������� ���������� �������
		while (rs.next()) {
			// �� ������ ������ ������� ��������
			// ��������� � �������� � ���������
			Words.add(rs.getString(1));
		}
		// ��������� ������� � ���������� � ��
		rs.close();
		con.close();
		return Words;
	}
	
	public List<Word> getWordById(int id) throws Exception {
		List<Word> words = new ArrayList<Word>();
		// ��������� ���������� � ��
		Connection con = getConnection();

		// ���������� SQL-�������
		PreparedStatement st = con.prepareStatement("Select word, rate "
				+ "From words " + "Where id = ?");
		// �������� �������� ���������� �������
		st.setInt(1, id);

		// ���������� �������
		ResultSet rs = st.executeQuery();

		Word word = null;
		// ����������� ���������� �������
		while (rs.next()) {
			// �� ������ ������ ������� �������� ����������,
			// ��������� ����� ������ Product
			// � �������� ��� � ���������
			word = new Word(id, rs.getString(1), rs.getInt(2));
			words.add(word);
		}
		// ��������� ������� � ���������� � ��
		rs.close();
		con.close();
		return words;
	}
	
	public Word getIdByWords(String word) throws Exception {
		Word words = new Word();
		// ��������� ���������� � ��
		Connection con = getConnection();

		// ���������� SQL-�������
		PreparedStatement st = con.prepareStatement("Select id, rate "
				+ "From words " + "Where word = ?");
		// �������� �������� ���������� �������
		st.setString(1, word);

		// ���������� �������
		ResultSet rs = st.executeQuery();

		// ����������� ���������� �������
		while (rs.next()) {
			// �� ������ ������ ������� �������� ����������,
			// ��������� ����� ������ Product
			// � �������� ��� � ���������
			words = new Word(rs.getInt(2), word, rs.getInt(2));
			
		}
		// ��������� ������� � ���������� � ��
		rs.close();
		con.close();
		return words;
	}
	
	public void addWord(Word word) throws Exception {
		// ��������� ���������� � ��
		Connection con = getConnection();

		// ���������� SQL-�������
		PreparedStatement st = con.prepareStatement("Insert into words"
				+ "(word, rate) " + "values (?, ?)");
		// �������� �������� ���������� �������
		
		st.setString(1, word.getWord());
		st.setInt(2, word.getRate());

		// ���������� �������
		st.executeUpdate();

		con.close();
	}
}
