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

import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ViewHistory extends JFrame {

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
						tglbtnIssuedBooks.setBounds(211, 11, 159, 47);
						panel_1.add(tglbtnIssuedBooks);
						tglbtnIssuedBooks.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tglbtnAvailableBooks.setSelected(false);
								if (tglbtnIssuedBooks.isSelected()) {
									getIssuedBookDataFromDatabase();
								} else {
									getDataFromDatabase();
								}
							}
						});
						tglbtnIssuedBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
						tglbtnIssuedBooks.setBackground(new Color(169, 169, 169));
						
								tglbtnAvailableBooks = new JToggleButton("Returned Books");
								tglbtnAvailableBooks.setBounds(391, 11, 165, 47);
								panel_1.add(tglbtnAvailableBooks);
								tglbtnAvailableBooks.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										tglbtnIssuedBooks.setSelected(false);
										if (tglbtnAvailableBooks.isSelected()) {
											getreturnBookDataFromDatabase();
										} else {
											getDataFromDatabase();
										}
									}
								});
								tglbtnAvailableBooks.setBackground(new Color(169, 169, 169));
								tglbtnAvailableBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
								
										JButton btnNewButton = new JButton("");
										btnNewButton.setBounds(1114, 11, 35, 38);
										panel_1.add(btnNewButton);
										btnNewButton.setBorder(null);
										btnNewButton.setBackground(new Color(255, 255, 255));
										btnNewButton.setIcon(new ImageIcon(ViewBook.class.getResource("/com/images/search.png")));
										
												textSearchInTable = new JTextField("Search...");
												textSearchInTable.setBounds(1149, 12, 190, 36);
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

			String[] columnNames = { "Studetn Id", "First Name", "Last Name", "Department", "Contact No.", "Books Id","Book Name",
					"Issue Date", "Return Date" };
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

			String[] columnNames = { "Studetn Id", "First Name", "Last Name", "Department", "Contact No.", "Books Id","Book Name",
					"Issue Date", "Return Date" };
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

			String[] columnNames = { "Studetn Id", "First Name", "Last Name", "Department", "Contact No.", "Books Id","Book Name",
					"Issue Date", "Return Date" };
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

	// search records in table

	private void searchTable(String query) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter(query));
	}

}
