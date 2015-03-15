package rec;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import server.DataBaseUsers;
import CustomClass.User;
import LookAndFeel.CustomDialog;
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

		// centerPanel = new javax.swing.JPanel();
		userName = new javax.swing.JTextField();
		password = new javax.swing.JPasswordField();
		submit = new javax.swing.JButton();
		nameField = new javax.swing.JTextField();
		jPanel1 = new JLabel();
		labelForUser = new JLabel();
		labelForPass = new JLabel();
		labelForName = new JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Alligator:Registry");

		setContentPane(new Fon("src/Images/texture.jpg"));
		Container centerPanel = getContentPane();

		centerPanel.setLayout(null);
		jPanel1.setIcon(new ImageIcon(all128));
		centerPanel.add(jPanel1);
		jPanel1.setBounds(280, -20, 200, 240);

		// getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);
		labelForUser.setLabelFor(userName);
		labelForUser.setBounds(40, 10, 200, 25);
		labelForUser.setText("Логин");
		labelForUser.setFont(font);
		labelForUser.setForeground(new Color(79, 68, 3));
		centerPanel.add(labelForUser);

		labelForUser.setLabelFor(password);
		labelForPass.setBounds(40, 90, 200, 25);
		labelForPass.setText("Пароль");
		labelForPass.setFont(font);
		labelForPass.setForeground(new Color(79, 68, 3));
		centerPanel.add(labelForPass);

		labelForUser.setLabelFor(nameField);
		labelForName.setBounds(40, 170, 200, 25);
		labelForName.setText("Имя");
		labelForName.setFont(font);
		labelForName.setForeground(new Color(79, 68, 3));
		centerPanel.add(labelForName);

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

	// private javax.swing.JPanel centerPanel;
	private javax.swing.JTextField userName;
	private javax.swing.JPasswordField password;
	private javax.swing.JButton submit;
	private javax.swing.JTextField nameField;
	private javax.swing.JLabel jPanel1;
	private javax.swing.JLabel labelForUser;
	private javax.swing.JLabel labelForPass;
	private javax.swing.JLabel labelForName;

	private void submitActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		userName.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		password.setBorder(BorderFactory.createLineBorder(Color.WHITE));
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
				// JOptionPane
				// .showMessageDialog(rootPane,
				// "Вы ввели неверный username, повторите пожалуйста ввод");
				CustomDialog.showTooltipWindow(userName, 1, null);
				userName.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
		} else {
			// JOptionPane.showMessageDialog(rootPane,
			// "Вы ввели неверный пароль, повторите пожалуйста ввод");
			CustomDialog.showTooltipWindow(password, 3, null);
			password.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}

}
