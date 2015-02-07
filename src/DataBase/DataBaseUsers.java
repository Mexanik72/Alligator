package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CustomClass.User;

public class DataBaseUsers {
	private Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://localhost/alig";
		return DriverManager.getConnection(url, "postgres", "toor123");
	}

	public List<Integer> getUsersIds() throws Exception {
		List<Integer> usersIds = new ArrayList<Integer>();
		// Получение соединения с БД
		Connection con = getConnection();

		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
				"Select id From users");
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			usersIds.add(rs.getInt(1));
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return usersIds;
	}
	
	public List<String> getUsersNames() throws Exception {
		List<String> usersNames = new ArrayList<String>();
		// Получение соединения с БД
		Connection con = getConnection();

		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
				"Select username From users");
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем
			// результат и помещаем в коллекцию
			usersNames.add(rs.getString(1));
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return usersNames;
	}
	
	public User getUserByUsername(String username) throws Exception {
		User users = new User();
		// Получение соединения с БД
		Connection con = getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Select id, name, password, score "
				+ "From users " + "Where username = ?");
		// Указание значений параметров запроса
		st.setString(1, username);

		// Выполнение запроса
		ResultSet rs = st.executeQuery();

		User user = null;
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем результаты,
			// формируем новый объект Product
			// и помещаем его в коллекцию
			users = new User(rs.getInt(1), rs.getString(2), username,
					rs.getString(3),rs.getInt(4));
			//users.add(user);
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return users;
	}
	
	public List<User> getUserById(int id) throws Exception {
		List<User> users = new ArrayList<User>();
		// Получение соединения с БД
		Connection con = getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Select name, username, password, score "
				+ "From users " + "Where id = ?");
		// Указание значений параметров запроса
		st.setInt(1, id);

		// Выполнение запроса
		ResultSet rs = st.executeQuery();

		User user = null;
		// Перечисляем результаты выборки
		while (rs.next()) {
			// Из каждой строки выборки выбираем результаты,
			// формируем новый объект Product
			// и помещаем его в коллекцию
			user = new User(id, rs.getString(1), rs.getString(2),
					rs.getString(3),rs.getInt(4));
			users.add(user);
		}
		// Закрываем выборку и соединение с БД
		rs.close();
		con.close();
		return users;
	}
	
	public void addUser(User user) throws Exception {
		// Получение соединения с БД
		Connection con = getConnection();

		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement("Insert into users"
				+ "(name, username, password) " + "values (?, ?, ?)");
		// Указание значений параметров запроса
		
		st.setString(1, user.getName());
		st.setString(2, user.getUsername());
		st.setString(3, user.getPassword());

		// Выполнение запроса
		st.executeUpdate();

		con.close();
	}
}
