package CustomClass;

public class Movie {
	private int id;
	private String pathtomovie;
	private int owner;
	private String name;

	public Movie(int id, String pathtomovie, int owner, String name) {
		this.id = id;
		this.pathtomovie = pathtomovie;
		this.owner = owner;
		this.name = name;
	}

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getpathtomovie() {
		return pathtomovie;
	}

	public void setpathtomovie(String pathtomovie) {
		this.pathtomovie = pathtomovie;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
