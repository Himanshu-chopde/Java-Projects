package com.LibraryManagement;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class SignUpPage extends JPanel{

	JFrame frmLibraryManagementLogin;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JTextField txtContactNumber;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JDateChooser txtDateOfBirth;
	private JLabel lblPasswordErrorMsg;
	private JLabel lblContactError;
	private JLabel lblCheckUsername;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxQuestions;
	boolean showPasswordf = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage window = new SignUpPage();
					window.frmLibraryManagementLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	private String fName, lName, username, contactNo, password, confPassword, question, answer;
	private Date dateOfBirth;
	private JTextField txtAnswer;
	public SignUpPage() {
		initialize();
	}

	// getting input for signup
	boolean validation() {

		fName = txtFirstName.getText().trim();
		lName = txtLastName.getText().trim();
		username = txtUsername.getText().trim();
		password = String.valueOf(txtPassword.getPassword()).trim();
		confPassword = String.valueOf(txtConfirmPassword.getPassword()).trim();
		dateOfBirth = txtDateOfBirth.getDate();
		contactNo = txtContactNumber.getText().trim();
		question = (String) comboBoxQuestions.getSelectedItem();
		answer = txtAnswer.getText().trim();
		
		if (fName.equals("")) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the first name.");
			return false;
		}

		if (lName.equals("")) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the last name.");
			return false;
		}

		if (username.equals("")) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the username.");
			return false;
		}

		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the password.");
			return false;
		}

		if (new String(confPassword).isEmpty()) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please confirm the password.");
			return false;
		}

		if (dateOfBirth == null) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the date of birth.");
			return false;
		}
		
		if (contactNo.equals("")) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the contact number.");
			return false;
		}
		
		if(!checkUsername()) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Username already exist\nPlease try with another username.");
			return false;
		}
		
		if(!isValidPassword(password)) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter valid password.");
			return false;
		}
		
		if (!password.equals(confPassword)) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Password don't match.");
			return false;
		}
		
		if(isValidDob(dateOfBirth)) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the valid date of birth.");
			return false;
		}
		
		if(!isValidNumber(contactNo)) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter valid contact number.");
			return false;
		}
		
		if (question.equals("")) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please select the security question.");
			return false;
		}
		
		if (answer.equals("")) {
			JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Please enter the answer for the security question.");
			return false;
		}
		
		return true;
	}
		
	// Password Validation
	public void checkPassword() {
		password = String.valueOf(txtPassword.getPassword());
		if(isValidPassword(password)) {
			lblPasswordErrorMsg.setText("");
		}
		else {
			lblPasswordErrorMsg.setText("<html>● Must have at least one lowercase character.<br>● Must have at least one uppercase character.<br>● Must have at least one numeric character.<br>● Must have at least one special symbol [@#$%^&*+=].<br>● Password length should be between 8 and 20</html>");
		}
		
	}
	
	public boolean isValidPassword(String pwd) {
		Pattern p = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
		Matcher m = p.matcher(pwd);
		return (m.matches());
	}

	// Contact number validation
	public  void checkContactNumber() {
		contactNo = txtContactNumber.getText();
		if(isValidNumber(contactNo)) {
			lblContactError.setText(" ");
		}
		else {
			lblContactError.setText("Contact Number should be of 10 digit.");
		}
	}
	
	public boolean isValidNumber(String number) {
		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(number);
		return (m.matches());
	}
	
	//date validation
	public boolean isValidDob(Date d) {
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		 Date currDate = new Date();
		 if(formatter.format(currDate).compareTo(formatter.format(d)) > 0) {
			 return false;
		 }
		 return true;
	}
	//checking duplicate User Names
	
	boolean checkUsername() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem","root","Himanshu@15");
			String sql="select * from signup where username=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				con.close();
				return false;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Inserting sign up details in database
	void insertDetails() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dob=formatter.format(dateOfBirth);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem","root","Himanshu@15");
			String sql="insert into signup(firstName,lastName,username,password,dateOfBirth,contactNo,securityquestion,answer) values (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, fName);
			stmt.setString(2, lName);
			stmt.setString(3, username);
			stmt.setString(4, password);
			stmt.setString(5, dob);
			stmt.setString(6, contactNo);
			stmt.setString(7, question);
			stmt.setString(8, answer);

			int f = stmt.executeUpdate();
			if(f > 0) 
				JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Record inserted successfully");
			else
				JOptionPane.showMessageDialog(frmLibraryManagementLogin, "An error occurred!\nRecord could not be inserted.");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmLibraryManagementLogin = new JFrame();
		frmLibraryManagementLogin.setLocationRelativeTo(null);
		frmLibraryManagementLogin.setTitle("Library Management Sign Up");
		frmLibraryManagementLogin.setForeground(new Color(205, 133, 63));
		frmLibraryManagementLogin.setBounds(100, 100, 730, 545);
		frmLibraryManagementLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibraryManagementLogin.setResizable(false);
		

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frmLibraryManagementLogin.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frmLibraryManagementLogin.getHeight()) / 2);
		frmLibraryManagementLogin.setLocation(x, y);

		JPanel panel = new JPanel();
		panel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panel.setBackground(new Color(70, 130, 180));
		
		JLabel lblNewLabel = new JLabel("Sign up");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setLayout(null);

		//frmLibraryManagementLogin.add(panel,BorderLayout.);
		
		JLabel lblNewLabel_1 = new JLabel("First Name             :");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(30, 36, 164, 24);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Last Name              :");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(30, 71, 164, 24);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Username               :");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(30, 106, 164, 24);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Password               :");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(30, 141, 164, 24);
		panel_1.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Confirm Password :");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(30, 176, 164, 24);
		panel_1.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Date of Birth          :");
		lblNewLabel_1_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(30, 211, 164, 24);
		panel_1.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Contact Number     :");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_6.setBounds(30, 246, 164, 24);
		panel_1.add(lblNewLabel_1_6);

		txtFirstName = new JTextField();
		txtFirstName.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 149, 237), new Color(100, 149, 237), null, new Color(100, 149, 237)));
		txtFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtFirstName.setBackground(new Color(255, 255, 255));
		txtFirstName.setBounds(195, 36, 215, 24);
		panel_1.add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 149, 237), new Color(100, 149, 237), null, new Color(100, 149, 237)));
		txtLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtLastName.setBackground(new Color(255, 255, 255));
		txtLastName.setColumns(10);
		txtLastName.setBounds(195, 71, 215, 24);
		panel_1.add(txtLastName);

		txtUsername = new JTextField();
		txtUsername.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 149, 237), new Color(100, 149, 237), null, new Color(100, 149, 237)));
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCheckUsername.setText("Username must be unique");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCheckUsername.setText(" ");
			}
		});
		txtUsername.addKeyListener(new KeyAdapter() {
		});
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setColumns(10);
		txtUsername.setBounds(195, 106, 215, 24);
		panel_1.add(txtUsername);

		txtContactNumber = new JTextField();
		txtContactNumber.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 149, 237), new Color(100, 149, 237), null, new Color(100, 149, 237)));
		txtContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtContactNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				checkContactNumber();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				checkContactNumber();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				checkContactNumber();
			}
		});
		txtContactNumber.setBackground(new Color(255, 255, 255));
		txtContactNumber.setColumns(10);
		txtContactNumber.setBounds(195, 246, 215, 24);
		panel_1.add(txtContactNumber);

		txtPassword = new JPasswordField();
		txtPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 149, 237), new Color(100, 149, 237), null, new Color(100, 149, 237)));
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		txtPassword.addKeyListener(new KeyAdapter() {
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
		txtPassword.setBackground(new Color(255, 255, 255));
		txtPassword.setBounds(195, 141, 192, 24);
		panel_1.add(txtPassword);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 149, 237), new Color(100, 149, 237), null, new Color(100, 149, 237)));
		txtConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtConfirmPassword.setBackground(new Color(255, 255, 255));
		txtConfirmPassword.setBounds(195, 176, 215, 24);
		panel_1.add(txtConfirmPassword);

		txtDateOfBirth = new JDateChooser();
		txtDateOfBirth.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 149, 237), new Color(100, 149, 237), new Color(100, 149, 237), new Color(100, 149, 237)));
		txtDateOfBirth.getCalendarButton().setBackground(new Color(255, 255, 255));
		txtDateOfBirth.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDateOfBirth.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtDateOfBirth.setDateFormatString("dd-MM-yyyy");
		txtDateOfBirth.setBackground(new Color(255, 255, 255));
		txtDateOfBirth.setBounds(195, 211, 215, 24);
		panel_1.add(txtDateOfBirth);
		JButton btnSignUp = new JButton("Signup");
		btnSignUp.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/signup.png")));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validation()) {
					insertDetails();
				}
			}
		});
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBackground(new Color(70, 130, 180));
		btnSignUp.setBounds(280, 385, 130, 32);
		panel_1.add(btnSignUp);

		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/login.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login =new LoginPage();
				login.setVisible(true);
				frmLibraryManagementLogin.dispose();
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLogin.setBackground(new Color(70, 130, 180));
		btnLogin.setBounds(541, 67, 130, 32);
		panel_1.add(btnLogin);

		lblPasswordErrorMsg = new JLabel();
		lblPasswordErrorMsg.setVerticalTextPosition(SwingConstants.TOP);
		lblPasswordErrorMsg.setVerticalAlignment(SwingConstants.TOP);
		lblPasswordErrorMsg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPasswordErrorMsg.setForeground(new Color(255, 0, 0));
		lblPasswordErrorMsg.setBounds(412, 141, 302, 106);
		panel_1.add(lblPasswordErrorMsg);
		
		
		
		lblContactError = new JLabel("");
		lblContactError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblContactError.setForeground(new Color(255, 0, 0));
		lblContactError.setBounds(412, 246, 302, 24);
		panel_1.add(lblContactError);
		
		lblCheckUsername = new JLabel("");
		lblCheckUsername.setForeground(new Color(255, 0, 0));
		lblCheckUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCheckUsername.setBounds(412, 107, 302, 24);
		panel_1.add(lblCheckUsername);
		
		JLabel lblNewLabel_1_7 = new JLabel("Click here to login");
		lblNewLabel_1_7.setForeground(Color.BLACK);
		lblNewLabel_1_7.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_7.setBounds(531, 36, 148, 24);
		panel_1.add(lblNewLabel_1_7);
		GroupLayout groupLayout = new GroupLayout(frmLibraryManagementLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(429, Short.MAX_VALUE))
		);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to exit?","Exit",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(frmLibraryManagementLogin, "Bye!");
					System.exit(0);
				}
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExit.setBackground(new Color(70, 130, 180));
		btnExit.setBounds(541, 385, 131, 33);
		panel_1.add(btnExit);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Security Question   :");
		lblNewLabel_1_6_1.setForeground(Color.BLACK);
		lblNewLabel_1_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_6_1.setBounds(30, 281, 164, 24);
		panel_1.add(lblNewLabel_1_6_1);
		
		JLabel lblNewLabel_1_6_2 = new JLabel("Answer                  :");
		lblNewLabel_1_6_2.setForeground(Color.BLACK);
		lblNewLabel_1_6_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_6_2.setBounds(30, 316, 164, 24);
		panel_1.add(lblNewLabel_1_6_2);
		
		txtAnswer = new JTextField();
		txtAnswer.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(100, 149, 237), new Color(100, 149, 237), null, new Color(100, 149, 237)));
		txtAnswer.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtAnswer.setColumns(10);
		txtAnswer.setBackground(new Color(255, 255, 255));
		txtAnswer.setBounds(195, 319, 320, 24);
		panel_1.add(txtAnswer);
		
		comboBoxQuestions = new JComboBox();
		comboBoxQuestions.setBorder(null);
		comboBoxQuestions.setBackground(new Color(255, 255, 255));
		comboBoxQuestions.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBoxQuestions.setModel(new DefaultComboBoxModel(new String[] {"", "Your favorite movie?", "Your favorite food?", "Your favourite restaurant?", "What is your favorite sport?", "What is the first name of your favorite uncle?", "What is your oldest cousin's name?", "Mother's maiden name?", "What is the first name of your favorite aunt?", "Where did you spend your childhood summers?", "What is your skin color?", "What was the last name of your favorite teacher?", "What was the last name of your childhood friend?", "What was your favorite food as a child?", "What was the last name of your first boss?", "Where did you meet your spouse?", "What is the name of your first shcool?", "What is the name of the hospital you were born?", "What is your main frequent flier number?", "What was the model of your first car?", "What was the name of your favorite childhood pet?"}));
		comboBoxQuestions.setBounds(195, 281, 215, 25);
		panel_1.add(comboBoxQuestions);
		comboBoxQuestions.setSize(320, comboBoxQuestions.getPreferredSize().height);
		
		JButton btnHidePassword = new JButton("");
		btnHidePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPasswordf) {
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
					txtPassword.setEchoChar('•');
				}
				else if(!showPasswordf) {
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/hidePassword.png")));
					txtPassword.setEchoChar((char)0);
				}
				showPasswordf = !showPasswordf;
			}
		});
		btnHidePassword.setBorder(new LineBorder(new Color(119, 136, 153)));
		btnHidePassword.setBackground(new Color(255, 255, 255));
		btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
		btnHidePassword.setBounds(384, 141, 26, 24);
		panel_1.add(btnHidePassword);
		
		
		frmLibraryManagementLogin.getContentPane().setLayout(groupLayout);
	}
}
