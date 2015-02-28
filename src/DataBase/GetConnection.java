package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
	public static Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://localhost/alig";
		return DriverManager.getConnection(url, "postgres", "toor123");
	}
}
