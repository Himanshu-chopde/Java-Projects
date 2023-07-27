package com.LibraryManagement;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class ViewBook extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JToggleButton tglbtnIssuedBooks;
	private JToggleButton tglbtnAvailableBooks;
	private JTextField textSearchInTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBook frame = new ViewBook();
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
	public ViewBook() {
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
		panel_1.setBounds(258, 142, 1112, 574);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("View Books");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(579, 11, 253, 126);
		panel_2.add(lblNewLabel_1_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setBounds(31, 31, 1051, 502);
		panel_1.add(scrollPane);
		
		table = new JTable(){
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
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
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
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));
		btnHome.setBounds(32, 126, 189, 67);
		panel.add(btnHome);
		
		tglbtnAvailableBooks = new JToggleButton("Available Books");
		tglbtnAvailableBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnIssuedBooks.setSelected(false);
				if(tglbtnAvailableBooks.isSelected()) {
					getAvailableBookDataFromDatabase();
				}
				else {
					getBookDataFromDatabase();
				}
			}
		});
		tglbtnAvailableBooks.setBounds(32, 253, 189, 66);
		panel.add(tglbtnAvailableBooks);
		tglbtnAvailableBooks.setBackground(new Color(169, 169, 169));
		tglbtnAvailableBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tglbtnIssuedBooks = new JToggleButton("Issued Books");
		tglbtnIssuedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnAvailableBooks.setSelected(false);
				if(tglbtnIssuedBooks.isSelected()){
					getIssuedBookDataFromDatabase();
				}
				else {
					getBookDataFromDatabase();
				}
			}
		});
		tglbtnIssuedBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tglbtnIssuedBooks.setBackground(new Color(169, 169, 169));
		tglbtnIssuedBooks.setBounds(32, 376, 189, 66);
		panel.add(tglbtnIssuedBooks);
		
		textSearchInTable = new JTextField("Search...");
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
		textSearchInTable.setBounds(67, 34, 154, 36);
		panel.add(textSearchInTable);
		textSearchInTable.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(ViewBook.class.getResource("/com/images/search.png")));
		btnNewButton.setBounds(32, 33, 35, 38);
		panel.add(btnNewButton);
		
		getBookDataFromDatabase();
	}

	//inserting book details into table
	void getBookDataFromDatabase() {
		model = (DefaultTableModel) table.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select *, DATE_FORMAT(b_addedDate,'%d/%m/%Y') as issueddate from booklist;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Book Id", "Book Name", "Author", "Publisher", "Quantity", "Books Issued", "Add Date" };
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
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

//inserting Available book details into table
	void getAvailableBookDataFromDatabase() {
		model = (DefaultTableModel) table.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
					"Himanshu@15");
			String sql = "select *, DATE_FORMAT(b_addedDate,'%d/%m/%Y') as issueddate from booklist where b_quantity > 0;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Book Id", "Book Name", "Author", "Publisher", "Quantity", "Books Issued", "Add Date" };
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
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

//inserting issued book details into table
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
			String sql = "select *, DATE_FORMAT(b_addedDate,'%d/%m/%Y') as issueddate from booklist where b_issued > 0;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String[] columnNames = { "Book Id", "Book Name", "Author", "Publisher", "Quantity", "Books Issued", "Add Date" };
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
			}

			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred!\nRecord could not be inserted.");
			e.printStackTrace();
		}
	}

//search records in table
	
	 private void searchTable(String query) { 
		 TableRowSorter<DefaultTableModel>
		 trs = new TableRowSorter<DefaultTableModel>(model); table.setRowSorter(trs);
	 
		 trs.setRowFilter(RowFilter.regexFilter(query)); 
	 }
}
