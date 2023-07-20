package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Component;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	JLabel lblError;
	String username, password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	LoginPage lp = this;

	void userVarification(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from signup where username=? and password=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "Login successful");
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
					this.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Wrong username or password");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setForeground(new Color(192, 192, 192));
		setBackground(new Color(192, 192, 192));
		setTitle("Library Management Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null); // center
		setResizable(false);

		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));

		panel.setLayout(null);
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(289, 0, 125, 58);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 50));
		panel.add(lblLogin);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Enter Username      :");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(139, 79, 164, 24);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Enter Password      :");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(139, 129, 164, 24);
		panel_1.add(lblNewLabel_1_1);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtUsername.setColumns(10);
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(313, 79, 215, 24);
		panel_1.add(txtUsername);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtPassword.setBounds(313, 129, 215, 24);
		panel_1.add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtUsername.getText();
				password = String.valueOf(txtPassword.getPassword());
				if (username.trim().equals("") || password.trim().equals("")) {
					lblError.setText("Please Enter Username and Password");
				} else {
					userVarification(username, password);
				}
			}
		});
		btnLogin.setIcon(new ImageIcon(LoginPage.class.getResource("/com/images/login.png")));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setBounds(102, 253, 131, 33);
		panel_1.add(btnLogin);

		JButton btnSignup = new JButton("Signup");
		btnSignup.setIcon(new ImageIcon(LoginPage.class.getResource("/com/images/signup.png")));
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage signUp = new SignUpPage();
				signUp.frmLibraryManagementLogin.setVisible(true);
				dispose();
			}
		});
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSignup.setBackground(Color.GRAY);
		btnSignup.setBounds(297, 253, 131, 33);
		panel_1.add(btnSignup);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to exit?","Exit",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(lp, "Bye Bye!");
					System.exit(0);
				}
			}
		});
		btnExit.setIcon(new ImageIcon(LoginPage.class.getResource("/com/images/exit.png")));
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(488, 253, 131, 33);
		panel_1.add(btnExit);

		lblError = new JLabel("");
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(192, 175, 389, 19);
		panel_1.add(lblError);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(71).addComponent(panel_1,
						GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginPage.class.getResource("/com/images/admin.png")));
		lblNewLabel.setBounds(170, 0, 64, 72);
		panel.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
	}
}
