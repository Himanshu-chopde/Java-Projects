package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxQuestions;
	private JPasswordField txtResetPassword, txtConfirmPassword;
	JLabel lblError;
String username, password, question, answer,DBQuestion,DBAnswer, username_1,password_1;

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
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JButton btnSignup;
	private JButton btnExit;
	private JButton btnForgotPassword;
	private JTextField txtAnswer;
	private JLabel lblSecurityQuestion;
	private JLabel lblAnswer;
	private JLabel lblEnterSecurityQuestion;
	private JLabel lblResetPassword;
	private JLabel lblConfirmPassword;
	private JTextField txtUsername_1;
	private JLabel lblUsername_1;
	private String confPassword;
	private JLabel lblPasswordErrorMsg;
	private boolean showPasswordf = true,showPassword_1 = false;
	private JButton btnHidePassword_1, btnHidePassword;
	// Checking data in database
	boolean userVarification(String username) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from signup where username=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				DBQuestion = rs.getString("securityquestion");
				DBAnswer = rs.getString("answer");
				password_1 = rs.getString("password");
				stmt.close();
				con.close();
				return true;
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Password Validation
		public void checkPassword() {
			password = String.valueOf(txtResetPassword.getPassword());
			if(isValidPassword(password)) {
				lblPasswordErrorMsg.setText("");
			}
			else {
				lblPasswordErrorMsg.setText("<html>● Must have at least one lowercase, At least one uppercase character, At least one numeric character, At least one special symbol [@#$%^&*+=].<br>● Password length should be between 8 and 20</html>");
			}
			
		}
		
		public boolean isValidPassword(String pwd) {
			Pattern p = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
			Matcher m = p.matcher(pwd);
			return (m.matches());
		}
		
		private boolean inputValidation() {
			username_1 = txtUsername_1.getText();
			question = (String) comboBoxQuestions.getSelectedItem();
			answer = txtAnswer.getText().trim();
			
			if (username_1.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter username.");
				return false;
			}
			
			if(!userVarification(username_1)) {
				JOptionPane.showMessageDialog(null, "Wrong username!\nPlease enter correct username.");
				return false;
			}
			
			if (question.equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the security question.");
				return false;
			}
			
			if (answer.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter the answer for the security question.");
				return false;
			}
			if(DBQuestion.equals(question) && DBAnswer.equals(answer)) {
				return true;
			}
			JOptionPane.showMessageDialog(null, "Question or Answer don't match!\nPlease try again.");
			return false;
		}
		
		boolean passwordValidation() {
			password = String.valueOf(txtResetPassword.getPassword()).trim();
			confPassword = String.valueOf(txtConfirmPassword.getPassword()).trim();
			
			if (password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter the new password.");
				return false;
			}

			if (new String(confPassword).isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please confirm the password.");
				return false;
			}
			
			if(!isValidPassword(password)) {
				JOptionPane.showMessageDialog(null, "Please enter valid password.");
				return false;
			}
			
			if (!password.equals(confPassword)) {
				JOptionPane.showMessageDialog(null, "Password don't match.");
				return false;
			}
			
			return true;
		}
		
		// Update Password in database
		boolean updatePassword() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
						"Himanshu@15");
				String sql = "update signup set password = ? where username = ?;";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, password);
				stmt.setString(2, username_1);
				
				int rs = stmt.executeUpdate();
				
				if (rs > 0) {
					JOptionPane.showMessageDialog(null, "Password changed successfully.");
					stmt.close();
					con.close();
					return true;
				}
				else {
					JOptionPane.showMessageDialog(null, "An error occurred.\nPassword Can't be changed");
				}
				
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		panel.setBackground(new Color(70, 130, 180));

		panel.setLayout(null);
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(46, 0, 613, 72);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 50));
		panel.add(lblLogin);

		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(230, 230, 250));
		loginPanel.setLayout(null);

		lblUsername = new JLabel("Enter Username      :");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUsername.setBounds(139, 79, 164, 24);
		loginPanel.add(lblUsername);

		lblPassword = new JLabel("Enter Password      :");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPassword.setBounds(139, 129, 164, 24);
		loginPanel.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, new Color(30, 144, 255)));
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtUsername.setColumns(10);
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(313, 79, 240, 24);
		loginPanel.add(txtUsername);

		txtPassword = new JPasswordField();
		txtPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, new Color(30, 144, 255)));
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtPassword.setBounds(313, 130, 215, 24);
		loginPanel.add(txtPassword);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					username = txtUsername.getText();
					password = String.valueOf(txtPassword.getPassword());
					if (username.trim().equals("") || password.trim().equals("")) {
						lblError.setText("Please Enter Username and Password");
					} else {
						if(userVarification(username) && password_1.equals(password))  {
							JOptionPane.showMessageDialog(null, "Login successful");
							
							try {
						         File file = new File("user.txt");
						         file.createNewFile();
						         new FileOutputStream("user.txt").close();
						         FileOutputStream fos = null;
						         DataOutputStream dos = null;
						         //System.out.println("File: " + file);
						         try {
									fos = new FileOutputStream(file);
									dos = new DataOutputStream(fos);
									
									dos.writeUTF(username);
									dos.writeUTF(password);
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								finally {
									try {
										fos.close();
										dos.close();

									} catch (Exception e2) {
										e2.printStackTrace();
									}
								}
						      } catch(Exception e3) {
						         e3.printStackTrace();
						      }
							
							try {
								HomePage frame = new HomePage();
								frame.setVisible(true);
								dispose();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Wrong username or password");
						} {
							
						}
					}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLogin.setBackground(new Color(70, 130, 180));
		btnLogin.setBounds(297, 253, 131, 33);
		loginPanel.add(btnLogin);

		btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnSignup.getText().equals("Signup")) {
				SignUpPage signUp = new SignUpPage();
				signUp.frmLibraryManagementLogin.setVisible(true);
				dispose();
				}
				else if (btnSignup.getText().equals("Back")) {
					lblUsername.setVisible(true);
					lblPassword.setVisible(true);
					txtUsername.setVisible(true);
					txtPassword.setVisible(true);
					btnForgotPassword.setVisible(true);
					btnLogin.setVisible(true);
					
					lblPasswordErrorMsg.setText("");
					lblEnterSecurityQuestion.setVisible(false);
					lblResetPassword.setVisible(false);
					btnHidePassword_1.setVisible(false);
					btnHidePassword.setVisible(true);
					lblConfirmPassword.setVisible(false);
					txtResetPassword.setVisible(false);
					txtConfirmPassword.setVisible(false);
					txtResetPassword.setText("");
					txtConfirmPassword.setText("");
					lblUsername_1.setVisible(false);
					txtUsername_1.setVisible(false);
					txtUsername_1.setText("");
					lblSecurityQuestion.setVisible(false);
					lblAnswer.setVisible(false);
					comboBoxQuestions.setVisible(false);
					comboBoxQuestions.setSelectedIndex(0);
					txtAnswer.setVisible(false);
					txtAnswer.setText("");
					btnSignup.setText("Signup");
					btnExit.setText("Exit");
					lblLogin.setText("Login");
				}
			}
		});
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSignup.setBackground(new Color(70, 130, 180));
		btnSignup.setBounds(123, 253, 131, 33);
		loginPanel.add(btnSignup);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnExit.getText().equals("Exit")) {
					int result;
					result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to exit?", "Exit",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(lp, "Bye!");
						System.exit(0);
					}
				}
				else if(btnExit.getText().equals("OK")) {
					if(inputValidation())
					{
						lblEnterSecurityQuestion.setVisible(true);
						lblEnterSecurityQuestion.setText("Please enter new password");
						lblUsername_1.setVisible(false);
						lblSecurityQuestion.setVisible(false);
						lblAnswer.setVisible(false);
						txtUsername_1.setVisible(false);
						comboBoxQuestions.setVisible(false);
						txtAnswer.setVisible(false);
						showPasswordf = false;
						
						lblResetPassword.setVisible(true);
						lblConfirmPassword.setVisible(true);
						txtResetPassword.setVisible(true);
						txtConfirmPassword.setVisible(true);
						btnHidePassword_1.setVisible(true);
						btnHidePassword.setVisible(true);
						
						if(passwordValidation()) {
							if(updatePassword()) {
								lblUsername.setVisible(true);
								lblPassword.setVisible(true);
								txtUsername.setVisible(true);
								txtPassword.setVisible(true);
								btnForgotPassword.setVisible(true);
								btnLogin.setVisible(true);
								
								lblPasswordErrorMsg.setText("");
								lblEnterSecurityQuestion.setVisible(false);
								lblResetPassword.setVisible(false);
								lblConfirmPassword.setVisible(false);
								txtResetPassword.setVisible(false);
								txtConfirmPassword.setVisible(false);
								txtResetPassword.setText("");
								btnHidePassword_1.setVisible(false);
								btnHidePassword.setVisible(true);
								txtConfirmPassword.setText("");
								lblUsername_1.setVisible(false);
								txtUsername_1.setVisible(false);
								txtUsername_1.setText("");
								lblSecurityQuestion.setVisible(false);
								lblAnswer.setVisible(false);
								comboBoxQuestions.setVisible(false);
								comboBoxQuestions.setSelectedIndex(0);
								txtAnswer.setVisible(false);
								txtAnswer.setText("");
								btnSignup.setText("Signup");
								lblLogin.setText("Login");
								btnExit.setText("Exit");
							}
						}
						
					}
				}
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExit.setBackground(new Color(70, 130, 180));
		btnExit.setBounds(488, 253, 131, 33);
		loginPanel.add(btnExit);

		lblError = new JLabel("");
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(192, 175, 389, 19);
		loginPanel.add(lblError);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(loginPanel, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(71).addComponent(loginPanel,
						GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		
		btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setForeground(new Color(248, 248, 255));
		btnForgotPassword.setBackground(new Color(70, 130, 180));
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPasswordf = false;
				lblUsername.setVisible(false);
				lblPassword.setVisible(false);
				txtUsername.setVisible(false);
				txtPassword.setVisible(false);
				btnForgotPassword.setVisible(false);
				txtResetPassword.setVisible(false);
				txtConfirmPassword.setVisible(false);
				lblResetPassword.setVisible(false);
				lblConfirmPassword.setVisible(false);
				btnLogin.setVisible(false);
				lblLogin.setText("Forgot Pasword");
				btnHidePassword.setVisible(false);
				btnHidePassword_1.setVisible(false);
				
				lblEnterSecurityQuestion.setVisible(true);
				lblEnterSecurityQuestion.setText("Please enter security question and answer to reset the password");
				lblUsername_1.setVisible(true);
				lblSecurityQuestion.setVisible(true);
				lblAnswer.setVisible(true);
				txtUsername_1.setVisible(true);
				comboBoxQuestions.setVisible(true);
				txtAnswer.setVisible(true);
				
				btnSignup.setText("Back");
				btnExit.setText("OK");
			}
		});
		btnForgotPassword.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		btnForgotPassword.setBounds(297, 205, 131, 23);
		loginPanel.add(btnForgotPassword);
		contentPane.setLayout(gl_contentPane);
		
		comboBoxQuestions = new JComboBox();
		comboBoxQuestions.setVisible(false);
		comboBoxQuestions.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBoxQuestions.setModel(new DefaultComboBoxModel(new String[] {"", "Your favorite movie?", "Your favorite food?", "Your favourite restaurant?", "What is your favorite sport?", "What is the first name of your favorite uncle?", "What is your oldest cousin's name?", "Mother's maiden name?", "What is the first name of your favorite aunt?", "Where did you spend your childhood summers?", "What is your skin color?", "What was the last name of your favorite teacher?", "What was the last name of your childhood friend?", "What was your favorite food as a child?", "What was the last name of your first boss?", "Where did you meet your spouse?", "What is the name of your first shcool?", "What is the name of the hospital you were born?", "What is your main frequent flier number?", "What was the model of your first car?", "What was the name of your favorite childhood pet?"}));
		comboBoxQuestions.setBounds(263, 130, 215, 25);
		loginPanel.add(comboBoxQuestions);
		comboBoxQuestions.setSize(320, comboBoxQuestions.getPreferredSize().height);
		
		lblSecurityQuestion = new JLabel("Security Question   :");
		lblSecurityQuestion.setVisible(false);
		lblSecurityQuestion.setForeground(Color.BLACK);
		lblSecurityQuestion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSecurityQuestion.setBounds(100, 129, 164, 24);
		loginPanel.add(lblSecurityQuestion);
		
		lblAnswer = new JLabel("Answer                  :");
		lblAnswer.setVisible(false);
		lblAnswer.setForeground(Color.BLACK);
		lblAnswer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAnswer.setBounds(100, 180, 164, 24);
		loginPanel.add(lblAnswer);
		
		lblEnterSecurityQuestion = new JLabel("");
		lblEnterSecurityQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterSecurityQuestion.setVisible(false);
		lblEnterSecurityQuestion.setForeground(Color.BLACK);
		lblEnterSecurityQuestion.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEnterSecurityQuestion.setBounds(106, 26, 489, 24);
		loginPanel.add(lblEnterSecurityQuestion);
		
		txtResetPassword = new JPasswordField();
		txtResetPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, new Color(30, 144, 255)));
		txtResetPassword.setVisible(false);
		txtResetPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		txtResetPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				checkPassword();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				checkPassword();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				checkPassword();
			}
		});
		txtResetPassword.setBackground(new Color(255, 255, 255));
		txtResetPassword.setBounds(313, 79, 215, 24);
		loginPanel.add(txtResetPassword);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, new Color(30, 144, 255)));
		txtConfirmPassword.setVisible(false);
		txtConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtConfirmPassword.setBackground(new Color(255, 255, 255));
		txtConfirmPassword.setBounds(313, 129, 215, 24);
		loginPanel.add(txtConfirmPassword);
		
		txtAnswer = new JTextField();
		txtAnswer.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, new Color(30, 144, 255)));
		txtAnswer.setVisible(false);
		txtAnswer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtAnswer.setColumns(10);
		txtAnswer.setBackground(Color.WHITE);
		txtAnswer.setBounds(263, 180, 322, 24);
		loginPanel.add(txtAnswer);
		
		lblResetPassword = new JLabel("New Password       :");
		lblResetPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				checkPassword();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				checkPassword();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				checkPassword();
			}
		});
		lblResetPassword.setVisible(false);
		lblResetPassword.setForeground(Color.BLACK);
		lblResetPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblResetPassword.setBounds(139, 79, 164, 24);
		loginPanel.add(lblResetPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password  :");
		lblConfirmPassword.setVisible(false);
		lblConfirmPassword.setForeground(Color.BLACK);
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(139, 129, 164, 24);
		loginPanel.add(lblConfirmPassword);
		
		txtUsername_1 = new JTextField();
		txtUsername_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, new Color(30, 144, 255)));
		txtUsername_1.setVisible(false);
		txtUsername_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtUsername_1.setColumns(10);
		txtUsername_1.setBackground(Color.WHITE);
		txtUsername_1.setBounds(263, 79, 322, 24);
		loginPanel.add(txtUsername_1);
		
		lblUsername_1 = new JLabel("Enter Username      :");
		lblUsername_1.setVisible(false);
		lblUsername_1.setForeground(Color.BLACK);
		lblUsername_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUsername_1.setBounds(100, 79, 164, 24);
		loginPanel.add(lblUsername_1);
		
		lblPasswordErrorMsg = new JLabel("");
		lblPasswordErrorMsg.setForeground(Color.RED);
		lblPasswordErrorMsg.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPasswordErrorMsg.setBounds(123, 164, 496, 81);
		loginPanel.add(lblPasswordErrorMsg);
		
		btnHidePassword = new JButton("");
		btnHidePassword.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(showPasswordf == false) {
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
					txtPassword.setEchoChar('•');
					txtConfirmPassword.setEchoChar('•');
				}
				else if(showPasswordf == true) {
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/hidePassword.png")));
					txtPassword.setEchoChar((char)0);
					txtConfirmPassword.setEchoChar((char)0);
				}
				showPasswordf = !showPasswordf;
			}
		});
		btnHidePassword.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), new Color(30, 144, 255), new Color(30, 144, 255)));
		btnHidePassword.setBackground(new Color(255, 255, 255));
		btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
		btnHidePassword.setBounds(527, 130, 26, 23);
		loginPanel.add(btnHidePassword);
		
		
		btnHidePassword_1 = new JButton("");
		btnHidePassword_1.setVisible(false);
		btnHidePassword_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHidePassword_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(showPassword_1) {
					btnHidePassword_1.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
					txtResetPassword.setEchoChar('•');
				}
				else if(!showPassword_1) {
					btnHidePassword_1.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/hidePassword.png")));
					txtResetPassword.setEchoChar((char)0);
				}
				showPassword_1 = !showPassword_1;
			}
		});
		btnHidePassword_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), new Color(30, 144, 255), new Color(30, 144, 255)));
		btnHidePassword_1.setBackground(new Color(255, 255, 255));
		btnHidePassword_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
		btnHidePassword_1.setBounds(527, 79, 26, 24);
		loginPanel.add(btnHidePassword_1);
	}
}
