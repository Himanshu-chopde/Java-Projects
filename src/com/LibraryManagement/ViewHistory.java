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

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ViewHistory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JToggleButton tglbtnIssuedBooks;
	private JToggleButton tglbtnReturnedBooks;
	private JTextField textSearchInTable;
	private JToggleButton tglbtnSearchByDate;
	private JDateChooser txtDateFrom;
	private JDateChooser txtDateTo;
	private JButton btnDateTo;
	private Date fromDate, toDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHistory frame = new ViewHistory();
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
	public ViewHistory() {
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
		panel_1.setBounds(0, 142, 1370, 574);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1_3 = new JLabel("View History");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(566, 11, 281, 126);
		panel_2.add(lblNewLabel_1_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setBounds(31, 77, 1308, 464);
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

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(31, 11, 159, 47);
		panel_1.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage home = new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));

		tglbtnIssuedBooks = new JToggleButton("Issued Books");
		tglbtnIssuedBooks.setBounds(200, 11, 159, 47);
		panel_1.add(tglbtnIssuedBooks);
		tglbtnIssuedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnReturnedBooks.setSelected(false);
				tglbtnSearchByDate.setSelected(false);
				if (tglbtnIssuedBooks.isSelected()) {
					getIssuedBookDataFromDatabase();
				} else {
					getDataFromDatabase();
				}
			}
		});
		tglbtnIssuedBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tglbtnIssuedBooks.setBackground(new Color(169, 169, 169));

		tglbtnReturnedBooks = new JToggleButton("Returned Books");
		tglbtnReturnedBooks.setBounds(369, 11, 160, 47);
		panel_1.add(tglbtnReturnedBooks);
		tglbtnReturnedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnIssuedBooks.setSelected(false);
				tglbtnSearchByDate.setSelected(false);
				if (tglbtnReturnedBooks.isSelected()) {
					getreturnBookDataFromDatabase();
				} else {
					getDataFromDatabase();
				}
			}
		});
		tglbtnReturnedBooks.setBackground(new Color(169, 169, 169));
		tglbtnReturnedBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(1028, 16, 35, 38);
		panel_1.add(btnNewButton);
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(ViewBook.class.getResource("/com/images/search.png")));

		textSearchInTable = new JTextField("Search...");
		textSearchInTable.setBounds(1063, 17, 190, 36);
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

		txtDateFrom = new JDateChooser();
		txtDateFrom.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDateFrom.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtDateFrom.setDateFormatString("dd-MM-yyyy");
		txtDateFrom.setBackground(new Color(250, 250, 210));
		txtDateFrom.setBounds(752, 24, 120, 24);
		panel_1.add(txtDateFrom);

		txtDateTo = new JDateChooser();
		txtDateTo.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDateTo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtDateTo.setDateFormatString("dd-MM-yyyy");
		txtDateTo.setBackground(new Color(250, 250, 210));
		txtDateTo.setBounds(898, 24, 120, 24);
		panel_1.add(txtDateTo);

		tglbtnSearchByDate = new JToggleButton("Search By Date");
		tglbtnSearchByDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnReturnedBooks.setSelected(false);
				tglbtnIssuedBooks.setSelected(false);
				if(!dateValidation()) {
					tglbtnSearchByDate.setSelected(false);
				}
				else {
					getDataByDateFromDatabase();
				}
				if(!tglbtnSearchByDate.isSelected()) {
					getDataFromDatabase();
				}
			}
		});
		tglbtnSearchByDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tglbtnSearchByDate.setBackground(new Color(169, 169, 169));
		tglbtnSearchByDate.setBounds(539, 11, 160, 47);
		panel_1.add(tglbtnSearchByDate);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFrom.setBounds(713, 25, 35, 24);
		panel_1.add(lblFrom);

		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTo.setBounds(877, 25, 24, 24);
		panel_1.add(lblTo);

		btnDateTo = new JButton("Print");
		btnDateTo.setBackground(new Color(169, 169, 169));
		btnDateTo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDateTo.setBounds(1263, 11, 76, 47);
		panel_1.add(btnDateTo);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		getDataFromDatabase();
	}

	// inserting all details into table
	void getDataFromDatabase() {
		model = (DefaultTableModel) table.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select *, DATE_FORMAT(issueddate,'%d/%m/%Y') as issueddate, DATE_FORMAT(returndate,'%d/%m/%Y') as returndate from viewhistory;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Studetn Id", "First Name", "Last Name", "Department", "Contact No.", "Books Id",
					"Book Name", "Issue Date", "Return Date" };
			model.setColumnIdentifiers(columnNames);
			for (int i = 0; i < columnNames.length; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			table.getColumnModel().getColumn(3).setPreferredWidth(281);

			String[] row = new String[9];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(7);
				row[7] = rs.getString(10);
				row[8] = rs.getString(11);
				model.addRow(row);
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

	// inserting returned book details into table
	void getreturnBookDataFromDatabase() {
		model = (DefaultTableModel) table.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select *, DATE_FORMAT(issueddate,'%d/%m/%Y') as issueddate, DATE_FORMAT(returndate,'%d/%m/%Y') as returndate from viewhistory where returndate is not null;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Studetn Id", "First Name", "Last Name", "Department", "Contact No.", "Books Id",
					"Book Name", "Issue Date", "Return Date" };
			model.setColumnIdentifiers(columnNames);
			for (int i = 0; i < columnNames.length; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			table.getColumnModel().getColumn(3).setPreferredWidth(281);

			String[] row = new String[9];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(7);
				row[7] = rs.getString(10);
				row[8] = rs.getString(11);
				model.addRow(row);
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}

	}

	// inserting issued book details into table
	void getIssuedBookDataFromDatabase() {
		model = (DefaultTableModel) table.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select *, DATE_FORMAT(issueddate,'%d/%m/%Y') as issueddate, DATE_FORMAT(returndate,'%d/%m/%Y') as returndate from viewhistory where returndate is null;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Studetn Id", "First Name", "Last Name", "Department", "Contact No.", "Books Id",
					"Book Name", "Issue Date", "Return Date" };
			model.setColumnIdentifiers(columnNames);
			for (int i = 0; i < columnNames.length; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			table.getColumnModel().getColumn(3).setPreferredWidth(281);

			String[] row = new String[9];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(7);
				row[7] = rs.getString(10);
				row[8] = rs.getString(11);
				model.addRow(row);
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}

	}
	
	private void getDataByDateFromDatabase(){
		model = (DefaultTableModel) table.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String from = formater.format(fromDate);
		String to = formater.format(toDate);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select *, DATE_FORMAT(issueddate,'%d/%m/%Y') as issued_date, DATE_FORMAT(returndate,'%d/%m/%Y') as return_date from viewhistory where (issueddate between ? and ?) or (returndate between ? and ?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, from);
			stmt.setString(2, to);
			stmt.setString(3, from);
			stmt.setString(4, to);
			
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Studetn Id", "First Name", "Last Name", "Department", "Contact No.", "Books Id",
					"Book Name", "Issue Date", "Return Date" };
			model.setColumnIdentifiers(columnNames);
			for (int i = 0; i < columnNames.length; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			table.getColumnModel().getColumn(3).setPreferredWidth(281);

			String[] row = new String[9];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(7);
				row[7] = rs.getString(10);
				row[8] = rs.getString(11);
				model.addRow(row);
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}

	}
	
	// Validating the dates
	private boolean dateValidation() {
		fromDate = txtDateFrom.getDate();
		toDate = txtDateTo.getDate();
		if(fromDate == null || toDate == null) {
			JOptionPane.showMessageDialog(null,"Please enter the dates");
			return false;
		}
		return true;
	}

	// search records in table

	private void searchTable(String query) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}
}
