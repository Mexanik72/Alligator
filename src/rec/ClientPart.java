package rec;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CustomClass.Movie;
import CustomClass.User;
import CustomClass.Word;

import java.net.*;
import java.awt.Dimension;
import java.awt.Point;
import java.io.*;

public class ClientPart {
	
	private ArrayList<String> Categories;
	private CustomClass.Categories categ;

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
			outD.writeByte(3);
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

			outD.writeByte(0);

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

	public ArrayList<String> getCategories() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeByte(4);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Categories = (ArrayList<String>) in.readObject();
        in.close();
        return Categories;
	}

	public CustomClass.Categories getIdByCategories(String str) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeByte(5);
		outD.writeUTF(str);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        categ = (CustomClass.Categories) in.readObject();
        in.close();
        return categ;
	}

	public void setImg(int id, String img) throws IOException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeByte(6);
		outD.writeInt(id);
		outD.writeUTF(img);
	}

	public UserAttributes authentication(String user, String pass) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		outD = new DataOutputStream(socket.getOutputStream());
		outD.writeByte(7);
		outD.writeUTF(user);
		outD.writeUTF(pass);
		
		boolean flagWrongUser = dis.readBoolean();
		boolean flagWrongPassword = dis.readBoolean();
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		User User = (CustomClass.User) in.readObject();
		in.close();
		
		UserAttributes ua = new UserAttributes (User, flagWrongUser, flagWrongPassword);
		return ua;
	}
}