package CustomClass;

public class Word {

	private int id;
	private String word;
	private int rate;

	public Word(int id, String word, int rate) {
		this.id = id;
		this.word = word;
		this.rate = rate;
	}

	public Word() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
