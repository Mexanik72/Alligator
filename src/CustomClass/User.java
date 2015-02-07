package CustomClass;

public class User {

	private int id;
	private String name;
	private String username;
	private String password;
	private int score;

	public User(int id, String name, String username, String password, int score) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.score = score;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
