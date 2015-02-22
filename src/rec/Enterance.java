package rec;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import CustomClass.User;
import DataBase.DataBaseUsers;
import LookAndFeel.MyButtonUI;

public class Enterance extends javax.swing.JFrame {

	/**
	 * 
	 */
	Registry reg = null;
	private static final long serialVersionUID = 1L;

	// private camDataSource dataSource;

	public Enterance() {
		initComponents();
	}

	private void initComponents() {
		centerPanel = new javax.swing.JPanel();
		userName = new javax.swing.JTextField();
		password = new javax.swing.JPasswordField();
		submit = new javax.swing.JButton();
		registr = new javax.swing.JButton();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Alligator:Registr");

		centerPanel.setLayout(null);

		getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);
		userName.setBounds(40, 40, 200, 40);
		password.setBounds(40, 120, 200, 40);
		submit.setBounds(60, 180, 160, 40);
		submit.setText("Submit");
		submit.setUI(new MyButtonUI(submit));
		centerPanel.add(userName);
		centerPanel.add(password);
		submit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitActionPerformed(evt);
			}
		});
		centerPanel.add(submit);
		registr.setBounds(260, 180, 160, 40);
		registr.setText("Registr");
		registr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registrActionPerformed(evt);
			}
		});
		centerPanel.add(registr);
		pack();
//		try {
//			// меняем
//			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			//
//			// попробуй еще так
//			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//			// или так
//			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			//
//			// обновляем дерево компонентов
//			SwingUtilities.updateComponentTreeUI(this);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JTextField userName;
	private javax.swing.JPasswordField password;
	private javax.swing.JButton submit;
	private javax.swing.JButton registr;

	private void submitActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		char s[] = password.getPassword();
		String user = userName.getText();
		String pass = new String(s);
		if (!pass.equals("")) {
			if (!user.equals("")) {
				List<String> usersNames = null;
				User User = new User();
				DataBaseUsers db = new DataBaseUsers();
				try {
					usersNames = db.getUsersNames();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean FlagWrongUser = false;
				for (int i = 0; i < usersNames.size(); i++) {
					if (usersNames.get(i).equals(user)) {
						try {
							User = db.getUserByUsername(usersNames.get(i));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (User.getPassword().equals(pass)) {
							FlagWrongUser = true;
							PlayOrCreate poc = new PlayOrCreate(User);
							poc.setSize(720, 720);
							poc.setLocationRelativeTo(null);
							poc.setVisible(true);
							this.dispose();
						} else {
							JOptionPane
									.showMessageDialog(rootPane,
											"Вы ввели неверный пароль, повторите пожалуйста ввод");
						}
					}
				}
				if (FlagWrongUser == false) {
					JOptionPane
							.showMessageDialog(rootPane,
									"Вы ввели неверный username, повторите пожалуйста ввод");
				}
			} else {
				JOptionPane
						.showMessageDialog(rootPane,
								"Вы ввели неверный username1, повторите пожалуйста ввод");
			}
		} else
			JOptionPane.showMessageDialog(rootPane,
					"Вы ввели неверный пароль, повторите пожалуйста ввод");
	}

	private void registrActionPerformed(java.awt.event.ActionEvent evt) {
		reg = new Registry();
		reg.setVisible(true);
		reg.setSize(500, 320);
		reg.setLocationRelativeTo(null);
	}

	public void setVisReg() {
		reg.setVisible(false);
		JOptionPane.showMessageDialog(rootPane, "false");
	}
}