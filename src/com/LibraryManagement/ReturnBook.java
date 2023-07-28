package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ReturnBook extends JFrame {

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
	private boolean flag;
	private JTextField textSearchInTable;
	private JLabel lblSearchInTable;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		comboBoxStudentId.removeAllItems();
		comboBoxStudentId.addItem("");
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String set = "SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));";
			String sql = "select * from issuedbooks group by studentid;";
			PreparedStatement stmt = con.prepareStatement(set);
			stmt.executeUpdate();
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			String[] columnNames = {"Student Id", "First Name", "Last Name", "Department", "Contact Number"};
			model.setColumnIdentifiers(columnNames);
			for(int i=0; i < columnNames.length; i++) {
				tableStudent.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			tableStudent.getColumnModel().getColumn(3).setPreferredWidth(300);
			String[] row = new String[5];
			while(rs.next()) {
				comboBoxStudentId.addItem(rs.getString(1));
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				model.addRow(row);
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be fetched.");
			e.printStackTrace();
		}
	}
	
	// inserting book details into table and comboBoxBookId
	@SuppressWarnings("unchecked")
	void getBookDataFromDatabase() {
		model = (DefaultTableModel) tableStudent.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		comboBoxBookId.removeAllItems();
		comboBoxBookId.addItem("");
		
		String studentId = (String) comboBoxStudentId.getSelectedItem();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from issuedbooks where studentid = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			String[] columnNames = {"Book Id", "Book Name", "Issued Date"};
			model.setColumnIdentifiers(columnNames);
			for(int i=0; i < columnNames.length; i++) {
				tableStudent.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			String[] row = new String[3];

			while (rs.next()) {
				comboBoxBookId.addItem(rs.getString(6));
				row[0] = rs.getString(6);
				row[1] = rs.getString(7);
				row[2] = rs.getString(8);
				model.addRow(row);
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be fetched.");
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
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be fetched.");
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
				String sql = "select * from booklist where b_id = ?;";
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

		//updating issuedbooks for return book
		void removeDetails() {
			String studentId = (String) comboBoxStudentId.getItemAt(comboBoxStudentId.getSelectedIndex());
			String bookId = (String) comboBoxBookId.getItemAt(comboBoxBookId.getSelectedIndex());
			String issueDate = textIssueDate.getText();
			try {
				Date d = new SimpleDateFormat("dd/MM/yyyy").parse(issueDate);
				issueDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();
			String returnDate = dtf.format(now);
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem",
						"root", "Himanshu@15");
				String sql = "delete from issuedbooks where studentid = ? and bookid = ? ;";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, studentId);
				stmt.setString(2, bookId);

				int f = stmt.executeUpdate();
				
				sql = "update viewhistory set returndate = ? where s_id = ? and bookid = ? and issueddate = ? ;";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, returnDate);
				stmt.setString(2, studentId);
				stmt.setString(3, bookId);
				stmt.setString(4, issueDate);
				
				int f1 = stmt.executeUpdate();
				
				if (f > 0 && f1 > 0) {
					updateDetails();
					JOptionPane.showMessageDialog(this, "Record updated successfully");
				}
				else
					JOptionPane.showMessageDialog(this, "An error occurred!\nRecord could not be updated.");
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Updating issued books quantity in database
		boolean updateDetails() {
			studentIssuedBooks--;
			bookIssuedBooks--;
			bookQuantity++;
			String studentId = (String) comboBoxStudentId.getItemAt(comboBoxStudentId.getSelectedIndex());
			String bookId = (String) comboBoxBookId.getItemAt(comboBoxBookId.getSelectedIndex());
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
						"Himanshu@15");
				String studentQuery = "update student set s_issued = ? where s_id = ? ;";
				String bookQuery = "update booklist set b_quantity = ?, b_issued = ? where b_id = ? ;";
				PreparedStatement studentStmt = con.prepareStatement(studentQuery);
				PreparedStatement bookStmt = con.prepareStatement(bookQuery);

				studentStmt.setInt(1, studentIssuedBooks);
				studentStmt.setString(2, studentId);

				bookStmt.setInt(1, bookQuantity);
				bookStmt.setInt(2, bookIssuedBooks);
				bookStmt.setString(3, bookId);

				int f = studentStmt.executeUpdate();
				int f1 = bookStmt.executeUpdate();
				if (f > 0 && f1 > 0) {
					studentStmt.close();
					bookStmt.close();
					con.close();
					return true;
				} else
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
		textSearchInTable.setVisible(f);
		lblSearchInTable.setVisible(f);
		
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
	
	//search records in table
	private void searchTable(String query) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		tableStudent.setRowSorter(trs);
		
		trs.setRowFilter(RowFilter.regexFilter(query));
	}

	/**
	 * Create the frame.
	 */
	
	@SuppressWarnings("rawtypes")
	public ReturnBook() {
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
		
		btnIssue = new JButton("Return Book");
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
							int result = JOptionPane.showConfirmDialog(null, "return the Book?", "Return book confirmation", JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.YES_NO_OPTION)
								removeDetails();
						
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
				getBookDataFromDatabase();
				getBookDetails();
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
		scrollPaneStudent.setBounds(40, 47, 1028, 481);
		scrollPaneStudent.setVisible(false);
		scrollPaneStudent.setForeground(new Color(0, 0, 0));
		scrollPaneStudent.setBackground(new Color(255, 255, 255));
		scrollPaneStudent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel_1.add(scrollPaneStudent);
		
		tableStudent = new JTable(){
	         public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	             Component comp = super.prepareRenderer(renderer, row, column);
	             Color alternateColor = new Color(200, 201, 210);
	             Color whiteColor = Color.WHITE;
	             if(!comp.getBackground().equals(getSelectionBackground())) {
	                Color c = (row % 2 == 0 ? alternateColor : whiteColor);
	                comp.setBackground(c);
	                c = null;
	             }
	             return comp;
	          }
	       };
		tableStudent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPaneStudent.setViewportView(tableStudent);
		tableStudent.setRowHeight(30);
		tableStudent.setAutoCreateRowSorter(true);
		
		JTableHeader tableHeader = tableStudent.getTableHeader();
		tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		btnCloseTable = new JButton("");
		btnCloseTable.setBounds(1023, 11, 45, 34);
		btnCloseTable.setIcon(new ImageIcon(ReturnBook.class.getResource("/com/images/XMark.png")));
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
		btnSelectRecordFromTable.setBounds(979, 11, 45, 34);
		btnSelectRecordFromTable.setVisible(false);
		btnSelectRecordFromTable.setIcon(new ImageIcon(ReturnBook.class.getResource("/com/images/checkmark.png")));
		btnSelectRecordFromTable.setForeground(new Color(0, 255, 0));
		btnSelectRecordFromTable.setBackground(new Color(255, 255, 255));
		btnSelectRecordFromTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_1.add(btnSelectRecordFromTable);
		
		textSearchInTable = new JTextField();
		textSearchInTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchTable(textSearchInTable.getText());
			}
		});
		textSearchInTable.setVisible(false);
		textSearchInTable.setBounds(750, 11, 229, 34);
		panel_1.add(textSearchInTable);
		textSearchInTable.setColumns(10);
		
		lblSearchInTable = new JLabel("");
		lblSearchInTable.setVisible(false);
		lblSearchInTable.setBackground(new Color(255, 255, 255));
		lblSearchInTable.setIcon(new ImageIcon(ReturnBook.class.getResource("/com/images/search.png")));
		lblSearchInTable.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSearchInTable.setBounds(712, 11, 39, 34);
		panel_1.add(lblSearchInTable);
		btnSelectRecordFromTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisibleTable(false);
				if(flag)
					comboBoxStudentId.setSelectedIndex(tableStudent.getSelectedRow()+1);
				else
					comboBoxBookId.setSelectedIndex(tableStudent.getSelectedRow()+1);
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
		
		JLabel lblNewLabel_1_3 = new JLabel("Return Book");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(570, 11, 276, 126);
		panel_2.add(lblNewLabel_1_3);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 141, 260, 574);
		contentPane.add(panel);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));
		btnHome.setBounds(32, 48, 189, 67);
		panel.add(btnHome);
		
		JButton btnSelectStudent = new JButton("Select Student");
		btnSelectStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStudentDataFromDatabase();
				setVisibleTable(true);
				flag = true;
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
				setVisibleTable(true);
				flag = false;
				
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
