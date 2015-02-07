package rec;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import CustomClass.User;
import DataBase.DataBaseUsers;

public class Registry extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Registry() {
		initComponents();
	}

	private void initComponents() {

		
		centerPanel = new javax.swing.JPanel();
		userName = new javax.swing.JTextField();
		password = new javax.swing.JPasswordField();
		submit = new javax.swing.JButton();
		label = new javax.swing.JLabel();
		name = new javax.swing.JLabel();
		nameField = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Alligator");

		centerPanel.setLayout(null);

		getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);
		label.setBounds(0, 0, 200, 20);
		label.setText("Registry");
		centerPanel.add(label);

		userName.setBounds(40, 40, 200, 40);
		centerPanel.add(userName);

		password.setBounds(40, 120, 200, 40);
		centerPanel.add(password);

		name.setBounds(0, 180, 200, 40);
		name.setText("Name:");
		centerPanel.add(name);

		nameField.setBounds(40, 180, 200, 40);
		centerPanel.add(nameField);

		submit.setBounds(60, 220, 160, 40);
		submit.setText("Submit");
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
