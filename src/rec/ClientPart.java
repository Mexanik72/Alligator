package rec;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CustomClass.Movie;
import CustomClass.Word;
import DataBase.DataBaseMovies;
import DataBase.DataBaseWord;

import java.net.*;
import java.awt.Dimension;
import java.awt.Point;
import java.io.*;

public class ClientPart {

	Socket socket;
	File selectFile;
	int port = 2154;
	String addres = "127.0.0.1";
	Movie movieNow;

	DataOutputStream outD = null;
	InetAddress ipAddress = null;
	InputStream in = null;
	DataInputStream dis = null;

	ClientPart() {
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
}