package rec;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CustomClass.Word;
import DataBase.DataBaseMovies;
import DataBase.DataBaseWord;

import java.net.*;
import java.io.*;

public class ClientPart {

	Socket socket;
	File selectFile;
	int port = 2154;
	String addres = "127.0.0.1";
	
	DataOutputStream outD = null;
	InetAddress ipAddress = null;
	InputStream in = null;
	DataInputStream dis = null;

	ClientPart() {
		connect();
	}

	ClientPart(File selectedFile) {
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

	public String getVideo(int category) {
		// TODO Auto-generated method stub		
		ArrayList<Integer> words = new ArrayList<Integer>();
		DataBaseWord dbw = new DataBaseWord();
		DataBaseMovies dbm = new DataBaseMovies();
		try {
			words = (ArrayList<Integer>) dbw.getIdByCategories(category);
			String path = dbm
					.getPathByWord(words.get((int) (Math.random() * words
							.size())));
			
			outD = new DataOutputStream(socket.getOutputStream());
			outD.writeByte(0);
			outD.writeUTF(path);
			
			long fileSize = dis.readLong(); // получаем размер файла
			
			System.out.println(fileSize);
			byte[] buffer = new byte[64 * 1024];
			FileOutputStream outF = new FileOutputStream(path);
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
			
			return path;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}