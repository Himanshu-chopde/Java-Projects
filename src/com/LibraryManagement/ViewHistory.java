package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ViewHistory extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 142, 260, 574);
		contentPane.add(panel);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));
		btnHome.setBounds(32, 126, 189, 67);
		panel.add(btnHome);
		
		JToggleButton tglbtnIssuedBooks_1 = new JToggleButton("Returned Books");
		tglbtnIssuedBooks_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tglbtnIssuedBooks_1.setBackground(new Color(169, 169, 169));
		tglbtnIssuedBooks_1.setBounds(32, 380, 189, 66);
		panel.add(tglbtnIssuedBooks_1);
		
		JToggleButton tglbtnIssuedBooks = new JToggleButton("Issued Books");
		tglbtnIssuedBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tglbtnIssuedBooks.setBackground(new Color(169, 169, 169));
		tglbtnIssuedBooks.setBounds(32, 252, 189, 66);
		panel.add(tglbtnIssuedBooks);
		
		textField = new JTextField("Search...");
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(67, 34, 154, 36);
		panel.add(textField);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ViewHistory.class.getResource("/com/images/search.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(32, 33, 35, 38);
		panel.add(btnNewButton);
		
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
		lblNewLabel_1_3.setBounds(0, 0, 1370, 142);
		panel_2.add(lblNewLabel_1_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(258, 142, 1112, 574);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setBounds(31, 31, 1051, 502);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		
		
		
		
		
	}
}
