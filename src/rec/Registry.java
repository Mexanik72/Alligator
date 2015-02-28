package rec;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import CustomClass.User;
import DataBase.DataBaseUsers;
import LookAndFeel.MyButtonUI;

public class Registry extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String all128 = "src/Images/all128.png";
	Font font = new Font("Verdana", Font.BOLD, 24);
	
	public Registry() {
		initComponents();
	}

	private void initComponents() {

		//centerPanel = new javax.swing.JPanel();
		userName = new javax.swing.JTextField();
		password = new javax.swing.JPasswordField();
		submit = new javax.swing.JButton();
		label = new javax.swing.JLabel();
		nameField = new javax.swing.JTextField();
		jPanel1 = new JLabel();
		labelforuser = new JLabel();
		labelforpass = new JLabel();
		labelforname = new JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Alligator:Registry");

		setContentPane(new Fon());
		Container centerPanel = getContentPane();
		
		centerPanel.setLayout(null);
		jPanel1.setIcon(new ImageIcon(all128));
		centerPanel.add(jPanel1);
		jPanel1.setBounds(280, -20, 200, 240);
		
		//getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);
		labelforuser.setLabelFor(userName);
		labelforuser.setBounds(40, 10, 200, 25);
		labelforuser.setText("Логин");
		labelforuser.setFont(font);
		labelforuser.setForeground(new Color(79,68,3));
		centerPanel.add(labelforuser);

		labelforuser.setLabelFor(password);
		labelforpass.setBounds(40, 90, 200, 25);
		labelforpass.setText("Пароль");
		labelforpass.setFont(font);
		labelforpass.setForeground(new Color(79,68,3));
		centerPanel.add(labelforpass);
		
		labelforuser.setLabelFor(nameField);
		labelforname.setBounds(40, 170, 200, 25);
		labelforname.setText("Имя");
		labelforname.setFont(font);
		labelforname.setForeground(new Color(79,68,3));
		centerPanel.add(labelforname);
		
		userName.setBounds(40, 40, 200, 40);
		centerPanel.add(userName);

		password.setBounds(40, 120, 200, 40);
		centerPanel.add(password);


		nameField.setBounds(40, 200, 200, 40);
		centerPanel.add(nameField);

		
		MyButtonUI.setupButtonUI(submit, 0);
		submit.setBounds(60, 260, 160, 40);
		submit.setText("Зарегестрироваться");
		submit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitActionPerformed(evt);
			}
		});
		centerPanel.add(submit);

		pack();
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JTextField userName;
	private javax.swing.JPasswordField password;
	private javax.swing.JButton submit;
	private javax.swing.JLabel label;
	private javax.swing.JLabel name;
	private javax.swing.JTextField nameField;
	private javax.swing.JLabel jPanel1;
	private javax.swing.JLabel labelforuser;
	private javax.swing.JLabel labelforpass;
	private javax.swing.JLabel labelforname;

	private void submitActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		char s[] = password.getPassword();
		String userNam = userName.getText();
		String pass = new String(s);
		if (!pass.equals("")) {
			if (!userNam.equals("")) {
				User user1 = new User();
				user1.setUsername(userName.getText());
				user1.setPassword(pass);
				user1.setName(nameField.getText());
				DataBaseUsers db = new DataBaseUsers();
				 try {
				 db.addUser(user1);
				 } catch (Exception e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 }
				this.dispose();
			} else {
				JOptionPane
						.showMessageDialog(rootPane,
								"Вы ввели неверный username, повторите пожалуйста ввод");
			}
		} else
			JOptionPane.showMessageDialog(rootPane,
					"Вы ввели неверный пароль, повторите пожалуйста ввод");
	}

}
