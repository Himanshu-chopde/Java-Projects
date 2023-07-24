package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class Student extends JFrame {

	private JPanel contentPane;
	private JTextField textStudentId;
	private JTextField textStudentFirstName;
	private JTextField textStudentLastName;
	private JTextField textStudentContactNumber;
	private JDateChooser textStudentDateOfBirth;
	private JButton btnSave, btnClear;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxStudentDepartment;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxStudentId;
	JLabel lblStudentIdError, lblContactNumberError;

	String id, firstName, lastName, department, contactNumber;
	Date dateOfBirth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// getting input of student details
	boolean validation() {

		id = textStudentId.getText();
		if(btnSave.getText().equals("Update") || btnSave.getText().equals("Remove")) {
			id = (String) comboBoxStudentId.getItemAt(comboBoxStudentId.getSelectedIndex());
		}
		firstName = textStudentFirstName.getText();
		lastName = textStudentLastName.getText();
		department = (String) comboBoxStudentDepartment.getItemAt(comboBoxStudentDepartment.getSelectedIndex());
		dateOfBirth = textStudentDateOfBirth.getDate();
		contactNumber = textStudentContactNumber.getText();

		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the student id");
			return false;
		}

		if (!(btnSave.getText().equals("Update") || btnSave.getText().equals("Remove")) && (!checkStudentId())) {
			JOptionPane.showMessageDialog(null, "Student id already exists");
			return false;
		}

		if (firstName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the first name");
			return false;
		}

		if (lastName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the last name");
			return false;
		}

		if (department.equals("")) {
			JOptionPane.showMessageDialog(null, "Please select the department");
			return false;
		}

		if (contactNumber.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the contact number");
			return false;
		}

		if (!isValidNumber(contactNumber)) {
			JOptionPane.showMessageDialog(null, "Please enter valid contact number");
			return false;
		}

		if (dateOfBirth == null) {
			JOptionPane.showMessageDialog(null, "Please enter the date of birth");
			return false;
		}

		if (isValidDob(dateOfBirth)) {
			JOptionPane.showMessageDialog(null, "Please enter the valid date of birth");
			return false;
		}

		return true;
	}

	// Contact number validation
	public void checkContactNumber() {
		contactNumber = textStudentContactNumber.getText();
		if (isValidNumber(contactNumber)) {
			lblContactNumberError.setText(" ");
		} else {
			lblContactNumberError.setText("Contact Number should be of 10 digit.");
		}
	}

	public boolean isValidNumber(String number) {
		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(number);
		return (m.matches());
	}

	// date validation
	public boolean isValidDob(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date currDate = new Date();
		if (formatter.format(currDate).compareTo(formatter.format(d)) > 0) {
			return false;
		}
		return true;
	}

	// checking duplicate student Id

	boolean checkStudentId() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from student where s_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				con.close();
				return false;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// Inserting sign up details in database
	void insertDetails() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dob = formatter.format(dateOfBirth);
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "insert into student values (?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, department);
			stmt.setString(5, contactNumber);
			stmt.setString(6, dob);
			stmt.setInt(7, 0);

			int f = stmt.executeUpdate();
			if (f > 0)
				JOptionPane.showMessageDialog(null, "Record inserted successfully");
			else
				JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void comboBoxFillStudentId() {
		comboBoxStudentId.addItem("");
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select s_id from student";
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				comboBoxStudentId.addItem(rs.getString("s_id"));
			}
			
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}
	
	//getting student details from database
	public void getStudentDetails() {
		String studentId = (String) comboBoxStudentId.getItemAt(comboBoxStudentId.getSelectedIndex());
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from student where s_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				textStudentFirstName.setText(rs.getString("s_firstName"));
				textStudentLastName.setText(rs.getString("s_lastName"));
				comboBoxStudentDepartment.setSelectedItem(rs.getString("s_department"));
				textStudentContactNumber.setText(rs.getString("s_contactNumber"));
				textStudentDateOfBirth.setDate(rs.getDate("s_dateOfBirth"));
			}
			
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}
	
	//updating student details
	void updateDetails() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dob=formatter.format(dateOfBirth);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem","root","Himanshu@15");
			String sql="update student set s_firstName = ?, s_lastName = ?, s_department = ?, s_contactNumber = ?, s_dateOfBirth = ? where s_id = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, department);
			stmt.setString(4, contactNumber);
			stmt.setString(5, dob);
			stmt.setString(6, id);

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

	//delete student details
		void deleteDetails() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem","root","Himanshu@15");
				String sql = "select s_issued from student where s_id = ?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("s_issued") > 0) {
						con.close();
						JOptionPane.showMessageDialog(null, "Could not delete the record\nStudent has issued some books");
						return;
					}
				}
				
				sql="delete from student where s_id = ?";
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, id);

				int f = stmt.executeUpdate();
				if(f > 0) 
					JOptionPane.showMessageDialog(null, "Record deleted successfully");
				else
					JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be deleted.");
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(259, 142, 1111, 574);
		contentPane.add(panel_1);

		JLabel lblStudentId = new JLabel("Student Id               :");
		lblStudentId.setVisible(false);
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentId.setBounds(254, 79, 166, 25);
		panel_1.add(lblStudentId);

		JLabel lblStudentFirstName = new JLabel("First Name             :");
		lblStudentFirstName.setVisible(false);
		lblStudentFirstName.setForeground(Color.BLACK);
		lblStudentFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentFirstName.setBounds(254, 138, 166, 24);
		panel_1.add(lblStudentFirstName);

		JLabel lblStudentLastName = new JLabel("Last Name              :");
		lblStudentLastName.setVisible(false);
		lblStudentLastName.setForeground(Color.BLACK);
		lblStudentLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentLastName.setBounds(254, 194, 166, 24);
		panel_1.add(lblStudentLastName);

		JLabel lblStudentContactNumber = new JLabel("Contact Number     :");
		lblStudentContactNumber.setVisible(false);
		lblStudentContactNumber.setForeground(Color.BLACK);
		lblStudentContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentContactNumber.setBounds(254, 306, 166, 24);
		panel_1.add(lblStudentContactNumber);

		JLabel lblStudentDateOfBirth = new JLabel("Date Of Birth         :");
		lblStudentDateOfBirth.setVisible(false);
		lblStudentDateOfBirth.setForeground(Color.BLACK);
		lblStudentDateOfBirth.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentDateOfBirth.setBounds(254, 362, 166, 24);
		panel_1.add(lblStudentDateOfBirth);

		JLabel lblStudentDepartment = new JLabel("Department             :");
		lblStudentDepartment.setVisible(false);
		lblStudentDepartment.setForeground(Color.BLACK);
		lblStudentDepartment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentDepartment.setBounds(254, 252, 166, 24);
		panel_1.add(lblStudentDepartment);

		textStudentId = new JTextField();
		textStudentId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblStudentIdError.setText("Student Id must be unique");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblStudentIdError.setText("");
			}
		});
		textStudentId.setVisible(false);
		textStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentId.setColumns(10);
		textStudentId.setBounds(424, 79, 282, 25);
		panel_1.add(textStudentId);

		textStudentFirstName = new JTextField();
		textStudentFirstName.setVisible(false);
		textStudentFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentFirstName.setColumns(10);
		textStudentFirstName.setBounds(424, 138, 282, 25);
		panel_1.add(textStudentFirstName);

		textStudentLastName = new JTextField();
		textStudentLastName.setVisible(false);
		textStudentLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentLastName.setColumns(10);
		textStudentLastName.setBounds(424, 194, 282, 25);
		panel_1.add(textStudentLastName);

		comboBoxStudentId = new JComboBox();
		comboBoxStudentId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxStudentId.getSelectedIndex() > 0) {
					lblStudentFirstName.setVisible(true);
					lblStudentLastName.setVisible(true);
					lblStudentDepartment.setVisible(true);
					lblStudentContactNumber.setVisible(true);
					lblStudentDateOfBirth.setVisible(true);
					textStudentFirstName.setVisible(true);
					textStudentLastName.setVisible(true);
					comboBoxStudentDepartment.setVisible(true);
					textStudentContactNumber.setVisible(true);
					textStudentDateOfBirth.setVisible(true);
					btnClear.setVisible(true);
					btnSave.setVisible(true);
					
					getStudentDetails();
					
				}
				else {
					textStudentFirstName.setText("");
					textStudentLastName.setText("");
					comboBoxStudentDepartment.setSelectedIndex(0);
					textStudentContactNumber.setText("");
					textStudentDateOfBirth.setCalendar(null);
					lblStudentFirstName.setVisible(false);
					lblStudentLastName.setVisible(false);
					lblStudentDepartment.setVisible(false);
					lblStudentContactNumber.setVisible(false);
					lblStudentDateOfBirth.setVisible(false);
					textStudentFirstName.setVisible(false);
					textStudentLastName.setVisible(false);
					comboBoxStudentDepartment.setVisible(false);
					textStudentContactNumber.setVisible(false);
					textStudentDateOfBirth.setVisible(false);
					btnClear.setVisible(false);
					btnSave.setVisible(false);
				}
			}
		});
		comboBoxStudentId.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxStudentId.setVisible(false);
		comboBoxStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxStudentId.setBackground(Color.WHITE);
		comboBoxStudentId.setBounds(424, 79, 282, 24);
		panel_1.add(comboBoxStudentId);
		AutoCompleteDecorator.decorate(comboBoxStudentId);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textStudentId.setText("");
				textStudentFirstName.setText("");
				textStudentLastName.setText("");
				comboBoxStudentDepartment.setSelectedIndex(0);
				textStudentContactNumber.setText("");
				textStudentDateOfBirth.setCalendar(null);
				comboBoxStudentId.setSelectedIndex(0);
				
				if(btnSave.getText().equals("Add")) {
					lblStudentFirstName.setVisible(true);
					lblStudentLastName.setVisible(true);
					lblStudentDepartment.setVisible(true);
					lblStudentContactNumber.setVisible(true);
					lblStudentDateOfBirth.setVisible(true);
					textStudentId.setVisible(true);
					textStudentFirstName.setVisible(true);
					textStudentLastName.setVisible(true);
					comboBoxStudentDepartment.setVisible(true);
					textStudentContactNumber.setVisible(true);
					textStudentDateOfBirth.setVisible(true);
					btnClear.setVisible(true);
					btnSave.setVisible(true);
				}

				lblContactNumberError.setText(" ");
			}
		});
		btnClear.setVisible(false);
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnClear.setBackground(new Color(169, 169, 169));
		btnClear.setBounds(277, 462, 133, 46);
		panel_1.add(btnClear);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnSave.getText().equals("Add")) {
					if (validation()) {
						insertDetails();
					}
				}
				if (btnSave.getText().equals("Update")) {
					if (validation()) {
						updateDetails();
					}

				}
				if (btnSave.getText().equals("Remove")) {
					if (validation()) {
						deleteDetails();
					}
				}
			}
		});
		btnSave.setVisible(false);
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave.setBackground(new Color(169, 169, 169));
		btnSave.setBounds(663, 462, 133, 46);
		panel_1.add(btnSave);

		lblContactNumberError = new JLabel("");
		lblContactNumberError.setForeground(new Color(255, 0, 0));
		lblContactNumberError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblContactNumberError.setBounds(718, 306, 329, 24);
		panel_1.add(lblContactNumberError);

		textStudentContactNumber = new JTextField();
		textStudentContactNumber.addKeyListener(new KeyAdapter() {
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
		textStudentContactNumber.setVisible(false);
		textStudentContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentContactNumber.setColumns(10);
		textStudentContactNumber.setBounds(424, 310, 282, 25);
		panel_1.add(textStudentContactNumber);

		textStudentDateOfBirth = new JDateChooser();
		textStudentDateOfBirth.setVisible(false);
		textStudentDateOfBirth.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textStudentDateOfBirth.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textStudentDateOfBirth.setDateFormatString("dd-MM-yyyy");
		textStudentDateOfBirth.setBackground(new Color(250, 250, 210));
		textStudentDateOfBirth.setBounds(424, 362, 282, 24);
		panel_1.add(textStudentDateOfBirth);

		lblStudentIdError = new JLabel("");
		lblStudentIdError.setForeground(new Color(255, 0, 0));
		lblStudentIdError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblStudentIdError.setBounds(716, 79, 329, 24);
		panel_1.add(lblStudentIdError);

		comboBoxStudentDepartment = new JComboBox();
		comboBoxStudentDepartment.setVisible(false);
		comboBoxStudentDepartment.setModel(new DefaultComboBoxModel(
				new String[] { "", "Computer Science and Engineering", "Mechanical Engineering", "Civil Engineering",
						"Electrical Engineering", "Electronics and Communication Engineering", "Information Technology",
						"Chemical Engineering", "Aeronautical Engineering" }));
		comboBoxStudentDepartment.setBounds(424, 251, 282, 25);
		panel_1.add(comboBoxStudentDepartment);

		AutoCompleteDecorator.decorate(comboBoxStudentDepartment);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1_3 = new JLabel("Student");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(579, 11, 253, 126);
		panel_2.add(lblNewLabel_1_3);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 142, 260, 574);
		contentPane.add(panel);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		btnHome.setBounds(32, 48, 189, 67);
		panel.add(btnHome);
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxStudentId.setVisible(false);
				lblStudentId.setVisible(true);
				lblStudentFirstName.setVisible(true);
				lblStudentLastName.setVisible(true);
				lblStudentDepartment.setVisible(true);
				lblStudentContactNumber.setVisible(true);
				lblStudentDateOfBirth.setVisible(true);
				textStudentId.setVisible(true);
				textStudentFirstName.setVisible(true);
				textStudentLastName.setVisible(true);
				comboBoxStudentDepartment.setVisible(true);
				textStudentContactNumber.setVisible(true);
				textStudentDateOfBirth.setVisible(true);
				btnClear.setVisible(true);
				btnSave.setVisible(true);
				btnSave.setText("Add");
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBackground(new Color(169, 169, 169));
		btnAdd.setBounds(32, 176, 189, 67);
		panel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStudentId.setVisible(true);
				lblStudentFirstName.setVisible(false);
				lblStudentLastName.setVisible(false);
				lblStudentDepartment.setVisible(false);
				lblStudentContactNumber.setVisible(false);
				lblStudentDateOfBirth.setVisible(false);
				textStudentId.setText("");
				textStudentId.setVisible(false);
				textStudentFirstName.setVisible(false);
				textStudentLastName.setVisible(false);
				comboBoxStudentDepartment.setVisible(false);
				textStudentContactNumber.setVisible(false);
				textStudentDateOfBirth.setVisible(false);
				comboBoxStudentId.setVisible(true);
				btnClear.setVisible(false);
				btnSave.setVisible(false);
				btnSave.setText("Update");
				
				comboBoxStudentId.removeAllItems();
				comboBoxFillStudentId();
				
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBackground(new Color(169, 169, 169));
		btnUpdate.setBounds(32, 292, 189, 67);
		panel.add(btnUpdate);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStudentId.setVisible(true);
				lblStudentFirstName.setVisible(false);
				lblStudentLastName.setVisible(false);
				lblStudentDepartment.setVisible(false);
				lblStudentContactNumber.setVisible(false);
				lblStudentDateOfBirth.setVisible(false);
				textStudentId.setText("");
				textStudentId.setVisible(false);
				textStudentFirstName.setVisible(false);
				textStudentLastName.setVisible(false);
				comboBoxStudentDepartment.setVisible(false);
				textStudentContactNumber.setVisible(false);
				textStudentDateOfBirth.setVisible(false);
				comboBoxStudentId.setVisible(true);
				btnClear.setVisible(false);
				btnSave.setVisible(false);
				btnSave.setText("Remove");
				
				comboBoxStudentId.removeAllItems();
				comboBoxFillStudentId();
			}
		});
		btnRemove.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRemove.setBackground(new Color(169, 169, 169));
		btnRemove.setBounds(32, 426, 189, 67);
		panel.add(btnRemove);
	}
}
