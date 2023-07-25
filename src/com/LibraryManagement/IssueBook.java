package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class IssueBook extends JFrame {

	private JPanel contentPane;
	private JTextField textStudentFirstName;
	private JTextField textStudentLastName;
	private JTextField textStudentDepartment;
	private JTextField textStudentContactNumber;
	private JTextField textStudentBooksIssued;
	private JTextField textBookName;
	private JTextField textBookQuantity;
	private JTextField textIssueDate;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxStudentId;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxBookId;
	private JButton btnIssue;
	private JTable tableStudent;
	private JScrollPane scrollPaneStudent;
	private JLabel lblStudentId;
	private JLabel lblStudentFirstName;
	private JLabel lblStudentLastName;
	private JLabel lblStudentDepartment;
	private JLabel lblStudentContactNumber;
	private JLabel lblStudentBooksIssued;
	private JSeparator separator;
	private JLabel lblBookId;
	private JLabel lblBookName;
	private JLabel lblBookQuantity;
	private JLabel lblIssueDate;
	private JButton btnCloseTable;
	private JButton btnSelectRecordFromTable;
	private int studentIssuedBooks, bookIssuedBooks, bookQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//inserting details into the studentTable and comboBoxStudentId
	@SuppressWarnings("unchecked")
	void getStudentDataFromDatabase() {
		DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		comboBoxStudentId.removeAllItems();
		comboBoxStudentId.addItem("");
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from student";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = {"Student Id", "First Name", "Last Name", "Department", "Contact Number", "Books Issued"};
			for(int i=0; i < columnNames.length; i++) {
				model.setColumnIdentifiers(columnNames);
			}
			tableStudent.getColumnModel().getColumn(3).setPreferredWidth(300);
			String[] row = new String[6];
			while(rs.next()) {
				comboBoxStudentId.addItem(rs.getString(1));
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(7);
				model.addRow(row);
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}
	
	// inserting book details into comboBoxStudentId
	@SuppressWarnings("unchecked")
	void getBookDataFromDatabase() {
		comboBoxBookId.removeAllItems();
		comboBoxBookId.addItem("");
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from booklist";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				comboBoxBookId.addItem(rs.getString(1));
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

	// getting student details from database
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
			if (rs.next()) {
				textStudentFirstName.setText(rs.getString("s_firstName"));
				textStudentLastName.setText(rs.getString("s_lastName"));
				textStudentDepartment.setText(rs.getString("s_department"));
				textStudentContactNumber.setText(rs.getString("s_contactNumber"));
				textStudentBooksIssued.setText(rs.getString("s_issued"));
				studentIssuedBooks = rs.getInt("s_issued");
			}

			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}
				
	// getting book details from database
		public void getBookDetails() {
			String bookId = (String) comboBoxBookId.getItemAt(comboBoxBookId.getSelectedIndex());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			LocalDateTime now = LocalDateTime.now();
			String issueDate = dtf.format(now);
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
						"Himanshu@15");
				String sql = "select * from booklist where b_id = ? and b_quantity > 0";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, bookId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					textBookName.setText(rs.getString("b_name"));
					textBookQuantity.setText(rs.getString("b_quantity"));
					textIssueDate.setText(issueDate);
					bookIssuedBooks = rs.getInt("b_issued");
					bookQuantity = rs.getInt("b_quantity");
				}
				stmt.close();
				con.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be fetched from database.");
				e.printStackTrace();
			}
		}

		// inserting issued book details in database
		void insertDetails() {
			String studentId = (String) comboBoxStudentId.getItemAt(comboBoxStudentId.getSelectedIndex());
			String bookId = (String) comboBoxBookId.getItemAt(comboBoxBookId.getSelectedIndex());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now();
			String issueDate = dtf.format(now);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem",
						"root", "Himanshu@15");
				String sql = "insert into issuedbooks values (?,?,?,?,?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, studentId);
				stmt.setString(2, textStudentFirstName.getText());
				stmt.setString(3, textStudentLastName.getText());
				stmt.setString(4, textStudentDepartment.getText());
				stmt.setString(5, textStudentContactNumber.getText());
				stmt.setString(6, bookId);
				stmt.setString(7, textBookName.getText());
				stmt.setString(8, issueDate);

				int f = stmt.executeUpdate();
				if (f > 0) {
					updateDetails();
					JOptionPane.showMessageDialog(this, "Book issued");
				}
				else
					JOptionPane.showMessageDialog(this, "An error occurred!\nRecord could not be inserted.");
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Updating issued books quantity in database
		boolean updateDetails() {
			studentIssuedBooks++;
			bookIssuedBooks++;
			bookQuantity--;
			String studentId = (String) comboBoxStudentId.getItemAt(comboBoxStudentId.getSelectedIndex());
			String bookId = (String) comboBoxBookId.getItemAt(comboBoxBookId.getSelectedIndex());
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem","root","Himanshu@15");
				String studentQuery = "update student set s_issued = ? where s_id = ?";
				String bookQuery = "update booklist set b_quantity = ?, b_issued = ? where b_id = ?";
				PreparedStatement studentStmt=con.prepareStatement(studentQuery);
				PreparedStatement bookStmt=con.prepareStatement(bookQuery);
				
				studentStmt.setInt(1, studentIssuedBooks);
				studentStmt.setString(2, studentId);
				
				bookStmt.setInt(1, bookQuantity);
				bookStmt.setInt(2, bookIssuedBooks);
				bookStmt.setString(3, bookId);
				
				int f = studentStmt.executeUpdate();
				int f1 = bookStmt.executeUpdate();
				if(f > 0 && f1 > 0) {
					studentStmt.close();
					bookStmt.close();
					con.close();
					return true;
				}
				else
					JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be updated.");
				studentStmt.close();
				bookStmt.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	
	void setVisibleTable(boolean f) {
		tableStudent.setVisible(f);
		scrollPaneStudent.setVisible(f);
		btnCloseTable.setVisible(f);
		btnSelectRecordFromTable.setVisible(f);
		
		lblStudentId.setVisible(!f);
		lblStudentFirstName.setVisible(!f);
		lblStudentLastName.setVisible(!f);
		lblStudentDepartment.setVisible(!f);
		lblStudentContactNumber.setVisible(!f);
		lblStudentBooksIssued.setVisible(!f);
		separator.setVisible(!f);
		lblBookId.setVisible(!f);
		lblBookName.setVisible(!f);
		lblBookQuantity.setVisible(!f);
		lblIssueDate.setVisible(!f);
		
		comboBoxStudentId.setVisible(!f);
		textStudentFirstName.setVisible(!f);
		textStudentLastName.setVisible(!f);
		textStudentDepartment.setVisible(!f);
		textStudentContactNumber.setVisible(!f);
		textStudentBooksIssued.setVisible(!f);
		comboBoxBookId.setVisible(!f);
		textBookName.setVisible(!f);
		textBookQuantity.setVisible(!f);
		textIssueDate.setVisible(!f);
		
		btnIssue.setVisible(!f);
	}

	/**
	 * Create the frame.
	 */
	
	@SuppressWarnings("rawtypes")
	public IssueBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(258, 141, 1112, 574);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblStudentBooksIssued = new JLabel("Books Issued       :");
		lblStudentBooksIssued.setBounds(537, 153, 158, 25);
		lblStudentBooksIssued.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblStudentBooksIssued);
		
		lblStudentId = new JLabel("Student Id     :");
		lblStudentId.setBounds(58, 47, 126, 24);
		lblStudentId.setForeground(Color.BLACK);
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblStudentId);
		
		lblStudentFirstName = new JLabel("First Name   :");
		lblStudentFirstName.setBounds(58, 101, 133, 24);
		lblStudentFirstName.setForeground(Color.BLACK);
		lblStudentFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblStudentFirstName);
		
		lblStudentDepartment = new JLabel("Department          :");
		lblStudentDepartment.setBounds(537, 46, 158, 24);
		lblStudentDepartment.setForeground(Color.BLACK);
		lblStudentDepartment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblStudentDepartment);
		
		lblStudentContactNumber = new JLabel("Contact Number  :");
		lblStudentContactNumber.setBounds(537, 100, 158, 24);
		lblStudentContactNumber.setForeground(Color.BLACK);
		lblStudentContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblStudentContactNumber);
		
		lblStudentLastName = new JLabel("Last Name    :");
		lblStudentLastName.setBounds(58, 154, 133, 24);
		lblStudentLastName.setForeground(Color.BLACK);
		lblStudentLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblStudentLastName);
		
		textStudentFirstName = new JTextField();
		textStudentFirstName.setBounds(194, 100, 282, 25);
		textStudentFirstName.setEditable(false);
		textStudentFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentFirstName.setColumns(10);
		panel_1.add(textStudentFirstName);
		
		textStudentLastName = new JTextField();
		textStudentLastName.setBounds(194, 153, 282, 25);
		textStudentLastName.setEditable(false);
		textStudentLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentLastName.setColumns(10);
		panel_1.add(textStudentLastName);
		
		textStudentDepartment = new JTextField();
		textStudentDepartment.setBounds(705, 45, 331, 25);
		textStudentDepartment.setEditable(false);
		textStudentDepartment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentDepartment.setColumns(10);
		panel_1.add(textStudentDepartment);
		
		btnIssue = new JButton("Issue");
		btnIssue.setBounds(467, 436, 133, 46);
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxStudentId.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please enter student details");
				}
				else {
					if(comboBoxBookId.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter book details");
					}
					else {
						JOptionPane.showConfirmDialog(null, "Do you want to issue the Book?", "issue book confirmation", JOptionPane.YES_NO_OPTION);
						insertDetails();
					}
				}
			}
		});
		btnIssue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnIssue.setBackground(new Color(169, 169, 169));
		panel_1.add(btnIssue);
		
		JLabel lblEditionMsg = new JLabel("");
		lblEditionMsg.setBounds(718, 306, 113, 24);
		panel_1.add(lblEditionMsg);
		
		textStudentContactNumber = new JTextField();
		textStudentContactNumber.setBounds(705, 99, 331, 25);
		textStudentContactNumber.setEditable(false);
		textStudentContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentContactNumber.setColumns(10);
		panel_1.add(textStudentContactNumber);
		
		textStudentBooksIssued = new JTextField();
		textStudentBooksIssued.setBounds(705, 152, 331, 25);
		textStudentBooksIssued.setEditable(false);
		textStudentBooksIssued.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentBooksIssued.setColumns(10);
		panel_1.add(textStudentBooksIssued);
		
		separator = new JSeparator();
		separator.setBounds(40, 225, 1028, 25);
		panel_1.add(separator);
		
		lblBookId = new JLabel("Book Id        :");
		lblBookId.setBounds(58, 277, 126, 24);
		lblBookId.setForeground(Color.BLACK);
		lblBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblBookId);
		
		lblBookName = new JLabel("Book Name  :");
		lblBookName.setBounds(58, 331, 133, 24);
		lblBookName.setForeground(Color.BLACK);
		lblBookName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblBookName);
		
		textBookName = new JTextField();
		textBookName.setEditable(false);
		textBookName.setBounds(194, 330, 282, 25);
		textBookName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textBookName.setColumns(10);
		panel_1.add(textBookName);
		
		lblBookQuantity = new JLabel("Book Quantity     :");
		lblBookQuantity.setBounds(537, 276, 158, 24);
		lblBookQuantity.setForeground(Color.BLACK);
		lblBookQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblBookQuantity);
		
		textBookQuantity = new JTextField();
		textBookQuantity.setEditable(false);
		textBookQuantity.setBounds(705, 275, 331, 25);
		textBookQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textBookQuantity.setColumns(10);
		panel_1.add(textBookQuantity);
		
		lblIssueDate = new JLabel("Issue Date           :");
		lblIssueDate.setBounds(537, 330, 158, 24);
		lblIssueDate.setForeground(Color.BLACK);
		lblIssueDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblIssueDate);
		
		textIssueDate = new JTextField();
		textIssueDate.setEditable(false);
		textIssueDate.setBounds(705, 329, 331, 25);
		textIssueDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textIssueDate.setColumns(10);
		panel_1.add(textIssueDate);
		
		comboBoxStudentId = new JComboBox();
		comboBoxStudentId.setBounds(194, 49, 282, 24);
		comboBoxStudentId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxStudentId.getSelectedIndex() > 0) {
					getStudentDetails();
				}
				else {
					textStudentFirstName.setText("");
					textStudentLastName.setText("");
					textStudentDepartment.setText("");
					textStudentContactNumber.setText("");
					textStudentBooksIssued.setText("");
				}
			}
		});
		comboBoxStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(comboBoxStudentId);
		
		AutoCompleteDecorator.decorate(comboBoxStudentId);
		
		comboBoxBookId = new JComboBox();
		comboBoxBookId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxBookId.getSelectedIndex() > 0) {
					getBookDetails();
				}
				else {
					textBookName.setText("");
					textBookQuantity.setText("");
					textIssueDate.setText("");
				}
			}
		});
		comboBoxBookId.setBounds(194, 276, 282, 24);
		comboBoxBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(comboBoxBookId);
		
		AutoCompleteDecorator.decorate(comboBoxBookId);
		
		scrollPaneStudent = new JScrollPane();
		scrollPaneStudent.setBounds(40, 11, 1028, 471);
		scrollPaneStudent.setVisible(false);
		scrollPaneStudent.setForeground(new Color(0, 0, 0));
		scrollPaneStudent.setBackground(new Color(255, 255, 255));
		scrollPaneStudent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel_1.add(scrollPaneStudent);
		
		tableStudent = new JTable();
		tableStudent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPaneStudent.setViewportView(tableStudent);
		
		btnCloseTable = new JButton("");
		btnCloseTable.setBounds(510, 482, 45, 34);
		btnCloseTable.setIcon(new ImageIcon(IssueBook.class.getResource("/com/images/XMark.png")));
		btnCloseTable.setForeground(new Color(255, 0, 0));
		btnCloseTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisibleTable(false);
			}
		});
		btnCloseTable.setVisible(false);
		btnCloseTable.setBackground(new Color(255, 255, 255));
		btnCloseTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_1.add(btnCloseTable);
		
		btnSelectRecordFromTable = new JButton("");
		btnSelectRecordFromTable.setBounds(555, 482, 45, 34);
		btnSelectRecordFromTable.setVisible(false);
		btnSelectRecordFromTable.setIcon(new ImageIcon(IssueBook.class.getResource("/com/images/checkmark.png")));
		btnSelectRecordFromTable.setForeground(new Color(0, 255, 0));
		btnSelectRecordFromTable.setBackground(new Color(255, 255, 255));
		btnSelectRecordFromTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_1.add(btnSelectRecordFromTable);
		btnSelectRecordFromTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisibleTable(false);
				comboBoxStudentId.setSelectedIndex(tableStudent.getSelectedRow()+1);
			}
		});
		
		getStudentDataFromDatabase();
		getBookDataFromDatabase();
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Issue Book");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(579, 11, 253, 126);
		panel_2.add(lblNewLabel_1_3);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 141, 260, 574);
		contentPane.add(panel);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));
		btnHome.setBounds(32, 48, 189, 67);
		panel.add(btnHome);
		
		JButton btnSelectStudent = new JButton("Select Student");
		btnSelectStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStudentDataFromDatabase();
				setVisibleTable(true);
			}
		});
		btnSelectStudent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSelectStudent.setBackground(new Color(169, 169, 169));
		btnSelectStudent.setBounds(32, 176, 189, 67);
		panel.add(btnSelectStudent);
		
		JButton btnSelectBook = new JButton("Select Book");
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBookDataFromDatabase();
				
			}
		});
		btnSelectBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSelectBook.setBackground(new Color(169, 169, 169));
		btnSelectBook.setBounds(32, 292, 189, 67);
		panel.add(btnSelectBook);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxStudentId.setSelectedIndex(0);
				comboBoxBookId.setSelectedIndex(0);
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnClear.setBackground(new Color(169, 169, 169));
		btnClear.setBounds(32, 426, 189, 67);
		panel.add(btnClear);
	}
}