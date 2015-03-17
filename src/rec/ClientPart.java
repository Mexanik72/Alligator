package rec;

import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.io.*;

import server.Movie;
import server.Score;
import server.User;
import server.Word;
import server.Categories;

public class ClientPart {

	private ArrayList<String> Categories;

	private Socket socket;
	private int port = 2154;
	private String addres = "127.0.0.1";
	private Movie movieNow;

	private DataOutputStream outD = null;
	private InetAddress ipAddress = null;
	private InputStream in = null;
	private DataInputStream dis = null;

	public ClientPart() {
		connect();
	}

	ClientPart(File selectedFile) {
		connect();
		sendVideo(selectedFile);
	}

	private void connect() {
		try {
			ipAddress = InetAddress.getByName(addres);
			socket = new Socket(ipAddress, port);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dis = new DataInputStream(in);
	}

	private void sendVideo(File selectedFile) {
		try {
			outD = new DataOutputStream(socket.getOutputStream());
			outD.writeInt(3);
			outD.writeLong(selectedFile.length());// отсылаем размер файла
			outD.writeUTF(selectedFile.getName());// отсылаем имя файла

			in = new FileInputStream(selectedFile);
			byte[] buffer = new byte[64 * 1024];
			int count;

			while ((count = in.read(buffer)) != -1) {
				outD.write(buffer, 0, count);
			}
			outD.flush();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getUserIcon(int userId) {
		// TODO Auto-generated method stub

	}

	public void getButtonIcon(String buttonText) {
		// TODO Auto-generated method stub

	}

	public Movie getVideo(Movie movie) {
		// TODO Auto-generated method stub
		this.movieNow = movie;
		try {
			outD = new DataOutputStream(socket.getOutputStream());
			outD.writeInt(0);
			outD.writeUTF(movieNow.getName());
			long fileSize = dis.readLong(); // получаем размер файла

			byte[] buffer = new byte[64 * 1024];
			FileOutputStream outF = new FileOutputStream(movieNow.getName());
			int count, total = 0;

			while ((count = dis.read(buffer)) != -1) {
				total += count;
				outF.write(buffer, 0, count);
				if (total == fileSize) {
					break;
				}
			}
			outF.flush();
			outF.close();

			socket.close();

			return movieNow;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getCategories() throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(4);
		System.out.println("4");
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		Categories = (ArrayList<String>) in.readObject();
		in.close();
		return Categories;
	}

	public Categories getIdByCategories(String str) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		Categories categ;
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(5);
		outD.writeUTF(str);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		categ = (server.Categories) in.readObject();
		in.close();
		return categ;
	}

	public void setImg(int id, String img) throws IOException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(6);
		outD.writeInt(id);
		outD.writeUTF(img);
	}

	public UserAttributes authentication(String user, String pass)
			throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(7);
		outD.writeUTF(user);
		outD.writeUTF(pass);

		boolean flagWrongUser = dis.readBoolean();
		boolean flagWrongPassword = dis.readBoolean();
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		User User = (server.User) in.readObject();
		in.close();

		UserAttributes ua = new UserAttributes(User, flagWrongUser,
				flagWrongPassword);
		return ua;
	}

	public void addUser(User user) throws IOException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(8);
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		out.writeObject(user);
		out.close();
	}

	public List<String> getKeyWords(int movie) throws IOException,
			ClassNotFoundException {
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(9);
		outD.writeInt(movie);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		return (List<String>) in.readObject();
	}

	public void addRateToMovie(Movie movieNow, Date sqlDate, int rate)
			throws IOException {
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(17);
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		out.writeObject(movieNow);
		out.writeObject(sqlDate);
		outD.writeInt(rate);
	}

	public List<Movie> getMovieByCategor(int categor) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Movie> movies;
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(10);
		outD.writeInt(categor);

		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		movies = (List<Movie>) in.readObject();
		in.close();

		return movies;
	}

	public List<Integer> getMoviesIds() throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Integer> ListIdMovies;
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(11);

		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		ListIdMovies = (List<Integer>) in.readObject();
		in.close();

		return ListIdMovies;
	}

	public void addScoreAndMovie(User userNow, String string, Word wordNow)
			throws IOException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(12);
		outD.writeUTF(string);
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		out.writeObject(userNow);
		out.writeObject(wordNow);
		out.close();
	}

	public ArrayList<String> getWordsWhereC(int categoryNow)
			throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<String> words;
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(13);
		outD.writeInt(categoryNow);

		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		words = (ArrayList<String>) in.readObject();
		in.close();

		return words;
	}

	public Word getIdByWords(String word) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		Word wordNow = null;
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(14);
		outD.writeUTF(word);

		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		wordNow = (Word) in.readObject();
		in.close();
		return wordNow;
	}

	public int getScoreByUser(int user) throws IOException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(15);
		outD.writeInt(user);
		int scoreInt = dis.readInt();
		return scoreInt;
	}

	public List<Score> getFiveTopUsers() throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Score> listScores;
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeInt(16);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		listScores = (List<Score>) in.readObject();
		in.close();
		return listScores;
	}
}