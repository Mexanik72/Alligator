package rec;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import CustomClass.Score;
import CustomClass.User;
import DataBase.DataBaseUsers;
import LookAndFeel.CustomDialog;
import LookAndFeel.MyButtonUI;

public class Enterance extends javax.swing.JFrame {

	/**
	 * 
	 */
	Registry reg = null;
	private static final long serialVersionUID = 1L;
	private String all128 = "src/Images/all128.png";
	// private camDataSource dataSource;
	Font font = new Font("Verdana", Font.BOLD, 24);

	public Enterance() {
		initComponents();		
	}

	private void initComponents() {
		// centerPanel = new javax.swing.JPanel();
		userName = new javax.swing.JTextField();
		password = new javax.swing.JPasswordField();
		submit = new javax.swing.JButton();
		registr = new javax.swing.JButton();
		jPanel1 = new JLabel();
		labelForUser = new JLabel();
		labelForPass = new JLabel();
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Alligator:Enterance");

		setContentPane(new Fon("src/Images/texture.jpg"));
		Container centerPanel = getContentPane();

		centerPanel.setLayout(null);
		jPanel1.setIcon(new ImageIcon(all128));
		// jPanel1.getIcon();
		// getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
		labelForUser.setLabelFor(userName);
		labelForUser.setText("Логин");
		labelForUser.setBounds(40, 10, 200, 25);
		labelForUser.setFont(font);
		labelForUser.setForeground(new Color(79, 68, 3));

		labelForPass.setText("Пароль");
		labelForPass.setBounds(40, 90, 200, 25);
		labelForPass.setLabelFor(password);
		labelForPass.setFont(font);
		labelForPass.setForeground(new Color(79, 68, 3));

		userName.setBounds(40, 40, 200, 40);
		password.setBounds(40, 120, 200, 40);
		jPanel1.setBounds(280, -20, 200, 240);
		jPanel1.setVisible(true);
		submit.setBounds(60, 180, 160, 40);
		submit.setText("Войти");
		// submit.setUI(new MyButtonUI(submit));
		MyButtonUI.setupButtonUI(submit, 0, 1);
		MyButtonUI.setupButtonUI(registr, 0, 1);
		centerPanel.add(labelForUser);
		centerPanel.add(labelForPass);
		centerPanel.add(userName);
		centerPanel.add(password);
		centerPanel.add(jPanel1);
		submit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitActionPerformed(evt);
			}
		});
		centerPanel.add(submit);
		registr.setBounds(260, 180, 160, 40);
		registr.setText("Регистрация");
		registr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registrActionPerformed(evt);
			}
		});
		centerPanel.add(registr);
		
		// getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);
		pack();
		// try {
		// // меняем
		// //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// //
		// // попробуй еще так
		// //
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		// // или так
		// //
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		// //
		// // обновляем дерево компонентов
		// SwingUtilities.updateComponentTreeUI(this);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	// private javax.swing.JPanel centerPanel;
	private javax.swing.JTextField userName;
	private javax.swing.JPasswordField password;
	private javax.swing.JButton submit;
	private javax.swing.JButton registr;
	private JLabel jPanel1;
	private JLabel labelForUser;
	private JLabel labelForPass;

	private void submitActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		userName.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		password.setBorder(BorderFactory.createLineBorder(Color.WHITE));
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
							Dimension d = new Dimension();
							Point p;
							p = getLocationOnScreen();
							d.setSize(700, 700);
							new PlayOrCreate(User, d, null);
							
							this.dispose();
						} else {
							// JOptionPane
							// .showMessageDialog(rootPane,
							// "Вы ввели неверный пароль, повторите пожалуйста ввод");
							CustomDialog.showTooltipWindow(password, 3, null);
							password.setBorder(BorderFactory
									.createLineBorder(Color.RED));
						}
					}
				}
				if (FlagWrongUser == false) {
					// JOptionPane
					// .showMessageDialog(rootPane,
					// "Вы ввели неверный username, повторите пожалуйста ввод");
					CustomDialog.showTooltipWindow(userName, 1, null);

					userName.setBorder(BorderFactory
							.createLineBorder(Color.RED));
				}
			} else {
				// JOptionPane
				// .showMessageDialog(rootPane,
				// "Вы ввели неверный username1, повторите пожалуйста ввод");
				CustomDialog.showTooltipWindow(userName, 1, null);
				userName.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
		} else
			// JOptionPane.showMessageDialog(rootPane,
			// "Вы ввели неверный пароль, повторите пожалуйста ввод");
			CustomDialog.showTooltipWindow(password, 3, null);
		password.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void registrActionPerformed(java.awt.event.ActionEvent evt) {
		userName.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		password.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		reg = new Registry();
		reg.setVisible(true);
		reg.setSize(500, 360);
		reg.setLocationRelativeTo(null);
	}

	public void setVisReg() {
		reg.setVisible(false);
	}

}