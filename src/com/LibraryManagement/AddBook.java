package com.LibraryManagement;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JTextField txtBookAuthor;
	private JTextField txtBookPublisher;
	private JLabel lblEditionMsg;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxQuantity;
	String bookId, bookName, bookAuthor, bookPublisher, quantity, addedDate;
	int bookQuantity;
	private JLabel lblBookId;
	private JLabel lblBookName;
	private JLabel lblAuthor;
	private JLabel lblPublisher;
	private JLabel lblQuantity;
	private JLabel lblHeading;
	private JButton btnClear;
	private JButton btnSave;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxBookId;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnSearch;
	private JTextField textSearchInTable;
	private DefaultTableModel model;
	private JButton btnPrint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// validating the inputs
	boolean validation() {
		bookId = txtBookId.getText();
		bookName = txtBookName.getText();
		bookAuthor = txtBookAuthor.getText();
		bookPublisher = txtBookPublisher.getText();
		quantity = (String) comboBoxQuantity.getItemAt(comboBoxQuantity.getSelectedIndex());

		if (btnSave.getText().equals("Add") && bookId.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book id");
			return false;
		}

		if (btnSave.getText().equals("Add") && checkBookId()) {
			JOptionPane.showMessageDialog(null, "This book id already exist\nPlease try another");
			return false;
		}

		if (bookName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book name");
			return false;
		}

		if (bookAuthor.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book author name");
			return false;
		}

		if (bookPublisher.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book publisher");
			return false;
		}

		try {
			bookQuantity = Integer.parseInt(quantity);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please select the Quantity", "Message", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		addedDate = dtf.format(now);

		return true;
	}

	boolean checkBookId() {
		bookId = txtBookId.getText();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from booklist where b_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, bookId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				con.close();
				return true;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Inserting book details into the database

	void insertBookDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "insert into booklist values (?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, bookId);
			stmt.setString(2, bookName);
			stmt.setString(3, bookAuthor);
			stmt.setString(4, bookPublisher);
			stmt.setInt(5, bookQuantity);
			stmt.setInt(6, 0);
			stmt.setString(7, addedDate);

			int f = stmt.executeUpdate();
			if (f > 0)
				JOptionPane.showMessageDialog(this, "Record inserted successfully");
			else
				JOptionPane.showMessageDialog(this, "An error occurred!\nRecord could not be inserted.");
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Updating book details into the database

	void updateBookDetails() {
		bookId = (String) comboBoxBookId.getSelectedItem();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "update booklist set b_name = ?, b_author = ?, b_publisher = ?, b_quantity = ? where b_id = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, bookName);
			stmt.setString(2, bookAuthor);
			stmt.setString(3, bookPublisher);
			stmt.setInt(4, bookQuantity);
			stmt.setString(5, bookId);

			int f = stmt.executeUpdate();

			System.out.println("f=" + f + " " + bookName + " " + bookAuthor + " " + bookPublisher + " " + bookQuantity
					+ " " + bookId);
			if (f > 0)
				JOptionPane.showMessageDialog(null, "Record updated successfully");
			else
				JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be updated.");
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Deleting book details into the database

	void deleteBookDetails() {
		bookId = (String) comboBoxBookId.getSelectedItem();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "delete from booklist where b_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, bookId);

			int f = stmt.executeUpdate();
			if (f > 0)
				JOptionPane.showMessageDialog(null, "Record deleted successfully");
			else
				JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be deleted.");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	// View Books
	// inserting book details into table and combo box
	void getBookDataFromDatabase() {
		model = (DefaultTableModel) table.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);

		comboBoxBookId.removeAllItems();
		comboBoxBookId.addItem("");

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select *, DATE_FORMAT(b_addedDate,'%d/%m/%Y') as issueddate from booklist;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Book Id", "Book Name", "Author", "Publisher", "Quantity", "Books Issued",
					"Add Date" };
			model.setColumnIdentifiers(columnNames);
			for (int i = 0; i < columnNames.length; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			String[] row = new String[7];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(8);
				model.addRow(row);

				if (rs.getString(6).equals("0")) {
					comboBoxBookId.addItem(rs.getString(1));
				}

			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

	// search records in table

	private void searchTable(String query) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}

	// getting book details from database
	public void getBookDetails() {
		String studentId = (String) comboBoxBookId.getItemAt(comboBoxBookId.getSelectedIndex());
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select * from booklist where b_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				txtBookName.setText(rs.getString("b_name"));
				txtBookAuthor.setText(rs.getString("b_Author"));
				txtBookPublisher.setText(rs.getString("b_publisher"));
				comboBoxQuantity.setSelectedItem(rs.getString("b_quantity"));
			}

			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 142, 260, 574);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnEditBook = new JButton("Edit Book");
		btnEditBook.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/edit-property-24.png")));
		btnEditBook.setBorder(new MatteBorder(1, 1, 5, 5, (Color) new Color(102, 111, 255)));
		btnEditBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBookDataFromDatabase();

				lblHeading.setText("Edit Book");

				comboBoxBookId.setVisible(true);
				lblBookId.setVisible(true);

				table.setVisible(false);
				btnPrint.setVisible(false);
				scrollPane.setVisible(false);
				textSearchInTable.setVisible(false);
				btnSearch.setVisible(false);

				lblBookName.setVisible(false);
				lblAuthor.setVisible(false);
				lblPublisher.setVisible(false);
				lblQuantity.setVisible(false);

				txtBookId.setVisible(false);
				txtBookName.setVisible(false);
				txtBookAuthor.setVisible(false);
				txtBookPublisher.setVisible(false);
				comboBoxQuantity.setVisible(false);

				btnSave.setText("Edit");
				btnSave.setVisible(true);
				btnClear.setVisible(true);

			}
		});
		btnEditBook.setBounds(32, 363, 189, 67);
		panel.add(btnEditBook);
		btnEditBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnEditBook.setBackground(new Color(230, 230, 250));

		JButton btnRemoveBook = new JButton("Remove Book");
		btnRemoveBook.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/delete-property-24.png")));
		btnRemoveBook.setBorder(new MatteBorder(1, 1, 5, 5, (Color) new Color(102, 111, 255)));
		btnRemoveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBookDataFromDatabase();

				lblHeading.setText("Remove Book");

				comboBoxBookId.setVisible(true);
				lblBookId.setVisible(true);

				table.setVisible(false);
				btnPrint.setVisible(false);
				scrollPane.setVisible(false);
				textSearchInTable.setVisible(false);
				btnSearch.setVisible(false);

				lblBookName.setVisible(false);
				lblAuthor.setVisible(false);
				lblPublisher.setVisible(false);
				lblQuantity.setVisible(false);

				txtBookId.setVisible(false);
				txtBookName.setVisible(false);
				txtBookAuthor.setVisible(false);
				txtBookPublisher.setVisible(false);
				comboBoxQuantity.setVisible(false);

				btnSave.setText("Remove");
				btnSave.setVisible(true);
				btnClear.setVisible(true);

			}
		});
		btnRemoveBook.setBounds(32, 469, 189, 67);
		panel.add(btnRemoveBook);
		btnRemoveBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRemoveBook.setBackground(new Color(230, 230, 250));

		JButton btnAdd = new JButton("Add Book");
		btnAdd.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/plus-5-24.png")));
		btnAdd.setBorder(new MatteBorder(1, 1, 5, 5, (Color) new Color(102, 111, 255)));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblHeading.setText("Add Book");

				comboBoxBookId.setVisible(false);

				table.setVisible(false);
				btnPrint.setVisible(false);
				scrollPane.setVisible(false);
				textSearchInTable.setVisible(false);
				btnSearch.setVisible(false);

				lblBookId.setVisible(true);
				lblBookName.setVisible(true);
				lblAuthor.setVisible(true);
				lblPublisher.setVisible(true);
				lblQuantity.setVisible(true);

				txtBookId.setVisible(true);
				txtBookName.setVisible(true);
				txtBookAuthor.setVisible(true);
				txtBookPublisher.setVisible(true);
				comboBoxQuantity.setVisible(true);

				txtBookId.setText("");
				txtBookName.setText("");
				txtBookAuthor.setText("");
				txtBookPublisher.setText("");
				comboBoxQuantity.setSelectedIndex(0);

				btnSave.setText("Add");
				btnSave.setVisible(true);
				btnClear.setVisible(true);

			}
		});
		btnAdd.setBounds(32, 149, 189, 67);
		panel.add(btnAdd);
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBackground(new Color(230, 230, 250));

		JButton btnViewBooks = new JButton("View Books");
		btnViewBooks.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/books-24.png")));
		btnViewBooks.setBorder(new MatteBorder(1, 1, 5, 5, (Color) new Color(102, 111, 255)));
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblHeading.setText("View Books");
				table.setVisible(true);
				scrollPane.setVisible(true);
				textSearchInTable.setVisible(true);
				btnSearch.setVisible(true);
				btnPrint.setVisible(true);

				getBookDataFromDatabase();

				comboBoxBookId.setVisible(false);
				lblBookId.setVisible(false);

				lblBookName.setVisible(false);
				lblAuthor.setVisible(false);
				lblPublisher.setVisible(false);
				lblQuantity.setVisible(false);

				txtBookId.setVisible(false);
				txtBookName.setVisible(false);
				txtBookAuthor.setVisible(false);
				txtBookPublisher.setVisible(false);
				comboBoxQuantity.setVisible(false);

				btnSave.setText("Remove");
				btnSave.setVisible(false);
				btnClear.setVisible(false);
			}
		});
		btnViewBooks.setBounds(32, 257, 189, 67);
		panel.add(btnViewBooks);
		btnViewBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnViewBooks.setBackground(new Color(230, 230, 250));

		JButton btnHome = new JButton("Home");
		btnHome.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/house-24.png")));
		btnHome.setForeground(new Color(0, 0, 0));
		btnHome.setBorder(new MatteBorder(1, 1, 5, 5, (Color) new Color(102, 111, 255)));
		btnHome.setBounds(32, 42, 189, 67);
		panel.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage home = new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(230, 230, 250));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(259, 142, 1111, 574);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblBookId = new JLabel("Book Id           :");
		lblBookId.setVisible(false);
		lblBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookId.setBounds(287, 79, 133, 25);
		panel_1.add(lblBookId);

		lblBookName = new JLabel("Book Name     :");
		lblBookName.setVisible(false);
		lblBookName.setForeground(Color.BLACK);
		lblBookName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookName.setBounds(287, 138, 133, 24);
		panel_1.add(lblBookName);

		lblAuthor = new JLabel("Author             :");
		lblAuthor.setVisible(false);
		lblAuthor.setForeground(Color.BLACK);
		lblAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAuthor.setBounds(287, 194, 133, 24);
		panel_1.add(lblAuthor);

		lblQuantity = new JLabel("Quantity          :");
		lblQuantity.setVisible(false);
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblQuantity.setBounds(287, 306, 133, 24);
		panel_1.add(lblQuantity);

		lblPublisher = new JLabel("Publisher         :");
		lblPublisher.setVisible(false);
		lblPublisher.setForeground(Color.BLACK);
		lblPublisher.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPublisher.setBounds(287, 252, 133, 24);
		panel_1.add(lblPublisher);

		txtBookId = new JTextField();
		txtBookId.setVisible(false);
		txtBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookId.setBounds(424, 79, 282, 25);
		panel_1.add(txtBookId);
		txtBookId.setColumns(10);

		txtBookName = new JTextField();
		txtBookName.setVisible(false);
		txtBookName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookName.setColumns(10);
		txtBookName.setBounds(424, 138, 282, 25);
		panel_1.add(txtBookName);

		txtBookAuthor = new JTextField();
		txtBookAuthor.setVisible(false);
		txtBookAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookAuthor.setColumns(10);
		txtBookAuthor.setBounds(424, 194, 282, 25);
		panel_1.add(txtBookAuthor);

		txtBookPublisher = new JTextField();
		txtBookPublisher.setVisible(false);
		txtBookPublisher.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookPublisher.setColumns(10);
		txtBookPublisher.setBounds(424, 252, 282, 25);
		panel_1.add(txtBookPublisher);

		comboBoxQuantity = new JComboBox();
		comboBoxQuantity.setVisible(false);
		comboBoxQuantity.setModel(new DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
				"43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
				"60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76",
				"77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93",
				"94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108",
				"109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123",
				"124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138",
				"139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153",
				"154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168",
				"169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183",
				"184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198",
				"199", "200" }));
		comboBoxQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxQuantity.setBackground(Color.WHITE);
		comboBoxQuantity.setBounds(424, 306, 282, 24);
		panel_1.add(comboBoxQuantity);

		btnClear = new JButton("Clear");
		btnClear.setBorder(new MatteBorder(1, 1, 3, 3, (Color) new Color(0, 0, 255)));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setVisible(false);
		btnClear.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/cancelSmall.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBookId.setText("");
				txtBookName.setText("");
				txtBookAuthor.setText("");
				txtBookPublisher.setText("");
				comboBoxQuantity.setSelectedIndex(0);
				comboBoxBookId.setSelectedIndex(0);

			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnClear.setBackground(new Color(51, 153, 204));
		btnClear.setBounds(277, 462, 146, 46);
		panel_1.add(btnClear);

		btnSave = new JButton("Save");
		btnSave.setBorder(new MatteBorder(1, 1, 3, 3, (Color) new Color(0, 0, 255)));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setVisible(false);
		btnSave.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnSave.getText().equals("Add")) {
					if (validation()) {
						int result = JOptionPane.showConfirmDialog(null, "Do you want to add the Book?",
								"Add book confirmation", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_NO_OPTION) {
							insertBookDetails();
							txtBookId.setText("");
							txtBookName.setText("");
							txtBookAuthor.setText("");
							txtBookPublisher.setText("");
							comboBoxQuantity.setSelectedIndex(0);
						}
					}
				}
				if (btnSave.getText().equals("Edit")) {
					if (validation()) {
						int result = JOptionPane.showConfirmDialog(null, "Do you want to edit the Book?",
								"Update book confirmation", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_NO_OPTION)
							updateBookDetails();
					}

				}
				if (btnSave.getText().equals("Remove")) {
					if (validation()) {
						int result = JOptionPane.showConfirmDialog(null, "Do you want to remove the Book?",
								"remove book confirmation", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_NO_OPTION)
							deleteBookDetails();
					}
				}
				getBookDataFromDatabase();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave.setBackground(new Color(51, 153, 204));
		btnSave.setBounds(663, 462, 146, 46);
		panel_1.add(btnSave);

		lblEditionMsg = new JLabel("");
		lblEditionMsg.setBounds(718, 306, 113, 24);
		panel_1.add(lblEditionMsg);

		comboBoxBookId = new JComboBox();
		comboBoxBookId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxBookId.getSelectedIndex() > 0) {
					lblBookName.setVisible(true);
					lblAuthor.setVisible(true);
					lblPublisher.setVisible(true);
					lblQuantity.setVisible(true);

					txtBookName.setVisible(true);
					txtBookAuthor.setVisible(true);
					txtBookPublisher.setVisible(true);
					comboBoxQuantity.setVisible(true);

					btnSave.setVisible(true);
					btnClear.setVisible(true);

					getBookDetails();
				} else {
					lblBookName.setVisible(false);
					lblAuthor.setVisible(false);
					lblPublisher.setVisible(false);
					lblQuantity.setVisible(false);

					txtBookName.setVisible(false);
					txtBookAuthor.setVisible(false);
					txtBookPublisher.setVisible(false);
					comboBoxQuantity.setVisible(false);

					btnSave.setVisible(false);
					btnClear.setVisible(false);
				}
			}
		});
		comboBoxBookId.setVisible(false);
		comboBoxBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxBookId.setBounds(424, 79, 282, 25);
		panel_1.add(comboBoxBookId);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(34, 58, 1046, 487);
		panel_1.add(scrollPane);

		table = new JTable() {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				Color alternateColor = new Color(200, 201, 210);
				Color whiteColor = Color.WHITE;
				if (!comp.getBackground().equals(getSelectionBackground())) {
					Color c = (row % 2 == 0 ? alternateColor : whiteColor);
					comp.setBackground(c);
					c = null;
				}
				return comp;
			}
		};
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		table.setVisible(false);
		scrollPane.setViewportView(table);

		textSearchInTable = new JTextField("Search...");
		textSearchInTable.setVisible(false);
		textSearchInTable.setBounds(767, 22, 215, 36);
		panel_1.add(textSearchInTable);
		textSearchInTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textSearchInTable.setText("Search...");
			}

			@Override
			public void focusGained(FocusEvent e) {
				textSearchInTable.setText("");
			}
		});

		textSearchInTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchTable(textSearchInTable.getText());
			}
		});
		textSearchInTable.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textSearchInTable.setColumns(10);

		btnSearch = new JButton("");
		btnSearch.setVisible(false);
		btnSearch.setBounds(732, 21, 35, 38);
		panel_1.add(btnSearch);
		btnSearch.setBorder(null);
		btnSearch.setBackground(new Color(255, 255, 255));
		btnSearch.setIcon(new ImageIcon(ViewBook.class.getResource("/com/images/search.png")));

		btnPrint = new JButton("Print");
		btnPrint.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/printer-.png")));
		btnPrint.setForeground(new Color(255, 255, 255));
		btnPrint.setBorder(new MatteBorder(1, 1, 3, 3, (Color) new Color(0, 0, 255)));
		btnPrint.setVisible(false);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Book Details");
				MessageFormat footer = new MessageFormat("page {0,number,Integer}");

				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnPrint.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPrint.setBackground(new Color(51, 153, 204));
		btnPrint.setBounds(992, 22, 88, 36);
		panel_1.add(btnPrint);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(70, 130, 180));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);

		lblHeading = new JLabel("Book");
		lblHeading.setBackground(new Color(70, 130, 180));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblHeading.setBounds(0, 0, 1370, 142);
		panel_2.add(lblHeading);

		getBookDataFromDatabase();

	}
}
