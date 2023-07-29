package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class UserDetails extends JFrame {

	private JPanel contentPane;
	private String username,password;
	private JTextField textUsername;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textContactNumber;
	private JTextField textAnswer;
	private JPasswordField textPassword;
	private boolean showPasswordf = false;
	private JButton btnSave;
	private JDateChooser textDateOfBirth;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxQuestions;
	private JTextField textFieldDob;
	private JTextField textFieldQuestion;
	private String fName, lName, contactNumber, securityQuestion, answer;
	private Date dateOfBirth;
	private JLabel lblContactError;
	private JLabel lblNotPassword;
	private JLabel lblNotUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDetails frame = new UserDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserDetails() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("User Details");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(566, 11, 281, 126);
		panel_2.add(lblNewLabel_1_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(258, 142, 1112, 576);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textUsername = new JTextField();
		textUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNotUsername.setText("You cannot edit your username.");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblNotUsername.setText("");
			}
		});
		textUsername.setEditable(false);
		textUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textUsername.setBounds(429, 143, 320, 27);
		panel.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username             :");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUsername.setBounds(272, 143, 151, 27);
		panel.add(lblUsername);
		
		textPassword = new JPasswordField();
		textPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNotPassword.setText("You cannot edit your password.");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblNotPassword.setText("");
			}
		});
		textPassword.setEditable(false);
		textPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textPassword.setBounds(429, 399, 295, 26);
		panel.add(textPassword);
		
	      try {
	         File file = new File("user.txt");
	         FileInputStream fos = null;
	         DataInputStream dos = null;
	         
	         try {
				fos = new FileInputStream(file);
				dos = new DataInputStream(fos);
				
				username = dos.readUTF();
				password = dos.readUTF();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fos.close();
					dos.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		textUsername.setText(username);
		textPassword.setText(password);
		
		JButton btnHidePassword = new JButton("");
		btnHidePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPasswordf) {
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
					textPassword.setEchoChar('•');
				}
				else if(!showPasswordf) {
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("")));
					btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/hidePassword.png")));
					textPassword.setEchoChar((char)0);
				}
				showPasswordf = !showPasswordf;
			}
		});
		btnHidePassword.setBorder(new LineBorder(new Color(119, 136, 153)));
		btnHidePassword.setBackground(new Color(255, 255, 255));
		btnHidePassword.setIcon(new ImageIcon(SignUpPage.class.getResource("/com/images/showPassword.png")));
		btnHidePassword.setBounds(723, 399, 26, 24);
		panel.add(btnHidePassword);
		
		
		textFirstName = new JTextField();
		textFirstName.setEditable(false);
		textFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFirstName.setBounds(429, 49, 320, 27);
		panel.add(textFirstName);
		textFirstName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name           :");
		lblFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFirstName.setBounds(272, 49, 151, 27);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name            :");
		lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblLastName.setBounds(272, 92, 151, 27);
		panel.add(lblLastName);
		
		textLastName = new JTextField();
		textLastName.setEditable(false);
		textLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textLastName.setColumns(10);
		textLastName.setBounds(429, 94, 320, 27);
		panel.add(textLastName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth        :");
		lblDateOfBirth.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDateOfBirth.setBounds(272, 193, 151, 27);
		panel.add(lblDateOfBirth);
		
		JLabel lblContactNumber = new JLabel("Contact Number   :");
		lblContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblContactNumber.setBounds(272, 248, 151, 27);
		panel.add(lblContactNumber);
		
		textContactNumber = new JTextField();
		textContactNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				checkContactNumber();
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				checkContactNumber();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				checkContactNumber();
			}

		});
		textContactNumber.setEditable(false);
		textContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textContactNumber.setColumns(10);
		textContactNumber.setBounds(429, 247, 320, 27);
		panel.add(textContactNumber);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question :");
		lblSecurityQuestion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSecurityQuestion.setBounds(272, 299, 151, 27);
		panel.add(lblSecurityQuestion);
		
		lblContactError = new JLabel("");
		lblContactError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblContactError.setForeground(new Color(255, 0, 0));
		lblContactError.setBounds(751, 248, 302, 27);
		panel.add(lblContactError);
		
		textAnswer = new JTextField();
		textAnswer.setEditable(false);
		textAnswer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textAnswer.setColumns(10);
		textAnswer.setBounds(429, 349, 320, 27);
		panel.add(textAnswer);
		
		JLabel lblAnswer = new JLabel("Answer                :");
		lblAnswer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAnswer.setBounds(272, 350, 151, 27);
		panel.add(lblAnswer);
		
		textDateOfBirth = new JDateChooser();
		textDateOfBirth.setVisible(false);
		textDateOfBirth.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textDateOfBirth.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textDateOfBirth.setDateFormatString("dd-MM-yyyy");
		textDateOfBirth.setBackground(new Color(250, 250, 210));
		textDateOfBirth.setBounds(429, 193, 320, 27);
		panel.add(textDateOfBirth);
		
		comboBoxQuestions = new JComboBox();
		comboBoxQuestions.setEditable(true);
		comboBoxQuestions.setVisible(false);
		comboBoxQuestions.setModel(new DefaultComboBoxModel(new String[] {"", "Your favorite movie?", "Your favorite food?", "Your favourite restaurant?", "What is your favorite sport?", "What is the first name of your favorite uncle?", "What is your oldest cousin's name?", "Mother's maiden name?", "What is the first name of your favorite aunt?", "Where did you spend your childhood summers?", "What is your skin color?", "What was the last name of your favorite teacher?", "What was the last name of your childhood friend?", "What was your favorite food as a child?", "What was the last name of your first boss?", "Where did you meet your spouse?", "What is the name of your first shcool?", "What is the name of the hospital you were born?", "What is your main frequent flier number?", "What was the model of your first car?", "What was the name of your favorite childhood pet?"}));
		comboBoxQuestions.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBoxQuestions.setBounds(429, 300, 320, 27);
		panel.add(comboBoxQuestions);
		
		JLabel lblPassword = new JLabel("Password             :");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPassword.setBounds(272, 398, 151, 27);
		panel.add(lblPassword);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validation()) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure, you want to update your details?", "Update confirmation", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_NO_OPTION)
						updateDetails();
					textFirstName.setEditable(false);
					textLastName.setEditable(false);
					textContactNumber.setEditable(false);
					textAnswer.setEditable(false);
					comboBoxQuestions.setVisible(false);
					textDateOfBirth.setVisible(false);
					btnSave.setVisible(false);
					
					textFieldDob.setVisible(true);
					textFieldQuestion.setVisible(true);
					lblNotUsername.setVisible(false);
					lblNotPassword.setVisible(false);
				}
			}
		});
		btnSave.setVisible(false);
		btnSave.setBackground(new Color(169, 169, 169));
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSave.setBounds(386, 469, 136, 37);
		panel.add(btnSave);
		
		textFieldDob = new JTextField();
		textFieldDob.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldDob.setEditable(false);
		textFieldDob.setColumns(10);
		textFieldDob.setBounds(429, 192, 320, 27);
		panel.add(textFieldDob);
		
		textFieldQuestion = new JTextField();
		textFieldQuestion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldQuestion.setEditable(false);
		textFieldQuestion.setColumns(10);
		textFieldQuestion.setBounds(429, 299, 320, 27);
		panel.add(textFieldQuestion);
		
		lblNotUsername = new JLabel("");
		lblNotUsername.setVisible(false);
		lblNotUsername.setForeground(Color.RED);
		lblNotUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNotUsername.setBounds(751, 143, 302, 27);
		panel.add(lblNotUsername);
		
		lblNotPassword = new JLabel("");
		lblNotPassword.setVisible(false);
		lblNotPassword.setForeground(Color.RED);
		lblNotPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNotPassword.setBounds(751, 399, 302, 27);
		panel.add(lblNotPassword);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 142, 260, 574);
		contentPane.add(panel_1);
		
		JButton btnEditBook = new JButton("Exit");
		btnEditBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to exit?", "Exit",
						JOptionPane.YES_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Bye!");
					System.exit(0);
				}
			}
		});
		btnEditBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnEditBook.setBackground(new Color(169, 169, 169));
		btnEditBook.setBounds(32, 363, 189, 67);
		panel_1.add(btnEditBook);
		
		JButton btnChangeDetails_1 = new JButton("Edit Details");
		btnChangeDetails_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFirstName.setEditable(true);
				textLastName.setEditable(true);
				textContactNumber.setEditable(true);
				textAnswer.setEditable(true);
				comboBoxQuestions.setVisible(true);
				textDateOfBirth.setVisible(true);
				btnSave.setVisible(true);
				
				textFieldDob.setVisible(false);
				textFieldQuestion.setVisible(false);
				lblNotUsername.setVisible(true);
				lblNotPassword.setVisible(true);
			}
		});
		btnChangeDetails_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnChangeDetails_1.setBackground(new Color(169, 169, 169));
		btnChangeDetails_1.setBounds(32, 149, 189, 67);
		panel_1.add(btnChangeDetails_1);
		
		JButton btnViewBooks = new JButton("Logout");
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to logout?","Logout",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					LoginPage login=new LoginPage();
					login.setVisible(true);
					dispose();
				}
			}
		});
		btnViewBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnViewBooks.setBackground(new Color(169, 169, 169));
		btnViewBooks.setBounds(32, 257, 189, 67);
		panel_1.add(btnViewBooks);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage frame = new HomePage();
				frame.setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));
		btnHome.setBounds(32, 42, 189, 67);
		panel_1.add(btnHome);
		
		getDetails();
	}

	// Fetching details from database
	public void getDetails() {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root","Himanshu@15");
			String sql = "select * from signup where username = ? and password = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				textFirstName.setText(rs.getString("firstname"));
				textLastName.setText(rs.getString("lastname"));
				textFieldDob.setText((rs.getString("dateOfBirth")));
				textDateOfBirth.setDate(formatter.parse(rs.getString("dateOfBirth")));
				textContactNumber.setText(rs.getString("contactNo"));
				textFieldQuestion.setText(rs.getString("securityquestion"));
				comboBoxQuestions.setSelectedItem(rs.getString("securityquestion"));
				textAnswer.setText(rs.getString("answer"));
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// validating the input
	boolean validation() {
		fName = textFirstName.getText().trim();
		lName = textLastName.getText().trim();
		dateOfBirth = textDateOfBirth.getDate();
		contactNumber = textContactNumber.getText().trim();
		securityQuestion = (String) comboBoxQuestions.getSelectedItem();
		answer = textAnswer.getText().trim();
		
		if (fName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the first name.");
			return false;
		}

		if (lName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the last name.");
			return false;
		}
		
		if (dateOfBirth == null) {
			JOptionPane.showMessageDialog(null, "Please enter the date of birth.");
			return false;
		}
		
		if (contactNumber.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the contact number.");
			return false;
		}
		
		if(isValidDob(dateOfBirth)) {
			JOptionPane.showMessageDialog(null, "Please enter the valid date of birth.");
			return false;
		}
		
		if(!isValidNumber(contactNumber)) {
			JOptionPane.showMessageDialog(null, "Please enter valid contact number.");
			return false;
		}
		
		if (securityQuestion.equals("")) {
			JOptionPane.showMessageDialog(null, "Please select the security question.");
			return false;
		}
		
		if (answer.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the answer for the security question.");
			return false;
		}
		
		return true;
	}
	
	// Contact number validation
		public  void checkContactNumber() {
			contactNumber = textContactNumber.getText();
			if(isValidNumber(contactNumber)) {
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
		
		private void updateDetails() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dob=formatter.format(dateOfBirth);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem","root","Himanshu@15");
				String sql="update signup set firstName = ?, lastName = ?, dateOfBirth = ?, contactNo = ?, securityquestion = ?, answer = ? where username = ? and password = ?";
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, fName);
				stmt.setString(2, lName);
				stmt.setString(3, dob);
				stmt.setString(4, contactNumber);
				stmt.setString(5, securityQuestion);
				stmt.setString(6, answer);
				stmt.setString(7, username);
				stmt.setString(8, password);

				int f = stmt.executeUpdate();
				if(f > 0) 
					JOptionPane.showMessageDialog(null, "Record updated successfully");
				else
					JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be updated.");
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}
