package com.LibraryManagement;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("Library Management Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1370, 142);
		panel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel.setBackground(new Color(169, 169, 169));
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("  Home");
		lblNewLabel_1.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/home.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(562, 11, 253, 126);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 50));
		panel.add(lblNewLabel_1);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 140, 1370, 573);
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setLayout(null);
		contentPane.add(panel_1);
		
		JButton btnIssueBook = new JButton("Issue Books");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IssueBook frame = new IssueBook();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnIssueBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnIssueBook.setBackground(new Color(169, 169, 169));
		btnIssueBook.setBounds(592, 34, 189, 113);
		panel_1.add(btnIssueBook);
		
		JButton btnReturnBook = new JButton("Return Books");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnBook frame = new ReturnBook();
				frame.setVisible(true);
				dispose();
			}
		});
		btnReturnBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReturnBook.setBackground(new Color(169, 169, 169));
		btnReturnBook.setBounds(899, 34, 189, 113);
		panel_1.add(btnReturnBook);
		
		JButton btnAddBook = new JButton("Add Books");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook frame = new AddBook();
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddBook.setBackground(new Color(169, 169, 169));
		btnAddBook.setBounds(277, 34, 189, 113);
		panel_1.add(btnAddBook);
		
		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.addActionListener(new ActionListener() {
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
		btnSignOut.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSignOut.setBackground(new Color(169, 169, 169));
		btnSignOut.setBounds(899, 407, 189, 113);
		panel_1.add(btnSignOut);
		
		JButton btnViewBook = new JButton("View Books");
		btnViewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBook frame = new ViewBook();
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnViewBook.setBackground(new Color(169, 169, 169));
		btnViewBook.setBounds(277, 221, 189, 113);
		panel_1.add(btnViewBook);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student frame = new Student();
				frame.setVisible(true);
				dispose();
			}
		});
		btnStudent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnStudent.setBackground(new Color(169, 169, 169));
		btnStudent.setBounds(592, 221, 189, 113);
		panel_1.add(btnStudent);
		
		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewHistory frame = new ViewHistory();
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewHistory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnViewHistory.setBackground(new Color(169, 169, 169));
		btnViewHistory.setBounds(899, 221, 189, 113);
		panel_1.add(btnViewHistory);
		
		JButton btnLibrarianDetails = new JButton("User Details");
		btnLibrarianDetails.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLibrarianDetails.setBackground(new Color(169, 169, 169));
		btnLibrarianDetails.setBounds(277, 407, 189, 113);
		panel_1.add(btnLibrarianDetails);
		
		JButton btnLibrarianDetails_1 = new JButton("User Details");
		btnLibrarianDetails_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLibrarianDetails_1.setBackground(new Color(169, 169, 169));
		btnLibrarianDetails_1.setBounds(592, 407, 189, 113);
		panel_1.add(btnLibrarianDetails_1);
		contentPane.add(panel);
	}
}
