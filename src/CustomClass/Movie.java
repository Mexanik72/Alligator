package CustomClass;

import java.sql.Date;

public class Movie {
	private int id;
	private int owner;
	private String name;
	private int word;
	private int category;

	public Movie(int id, int owner, String name, int word, int category) {
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.word = word;
		this.category = category;
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
	
	public int getWord() {
		return word;
	}

	public void setWord(int word) {
		this.word = word;
	}
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
