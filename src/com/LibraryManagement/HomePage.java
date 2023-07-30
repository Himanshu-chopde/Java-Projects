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
		panel.setBackground(new Color(70, 130, 180));
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
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setLayout(null);
		contentPane.add(panel_1);
		
		JButton btnIssueBook = new JButton("Issue Books");
		btnIssueBook.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/issue_book.png")));
		btnIssueBook.setForeground(new Color(255, 255, 255));
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
		btnIssueBook.setBackground(new Color(70, 130, 180));
		btnIssueBook.setBounds(592, 34, 189, 113);
		panel_1.add(btnIssueBook);
		
		JButton btnReturnBook = new JButton("Return Books");
		btnReturnBook.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/return_book.png")));
		btnReturnBook.setForeground(new Color(255, 255, 255));
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnBook frame = new ReturnBook();
				frame.setVisible(true);
				dispose();
			}
		});
		btnReturnBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReturnBook.setBackground(new Color(70, 130, 180));
		btnReturnBook.setBounds(899, 34, 189, 113);
		panel_1.add(btnReturnBook);
		
		JButton btnAddBook = new JButton("Add Books");
		btnAddBook.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/plus.png")));
		btnAddBook.setForeground(new Color(255, 255, 255));
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook frame = new AddBook();
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddBook.setBackground(new Color(70, 130, 180));
		btnAddBook.setBounds(277, 34, 189, 113);
		panel_1.add(btnAddBook);
		
		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/signout.png")));
		btnSignOut.setForeground(new Color(255, 255, 255));
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
		btnSignOut.setBackground(new Color(70, 130, 180));
		btnSignOut.setBounds(899, 407, 189, 113);
		panel_1.add(btnSignOut);
		
		JButton btnViewBook = new JButton("View Books");
		btnViewBook.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/view_books.png")));
		btnViewBook.setForeground(new Color(255, 255, 255));
		btnViewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBook frame = new ViewBook();
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnViewBook.setBackground(new Color(70, 130, 180));
		btnViewBook.setBounds(277, 221, 189, 113);
		panel_1.add(btnViewBook);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/student.png")));
		btnStudent.setForeground(new Color(255, 255, 255));
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student frame = new Student();
				frame.setVisible(true);
				dispose();
			}
		});
		btnStudent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnStudent.setBackground(new Color(70, 130, 180));
		btnStudent.setBounds(592, 221, 189, 113);
		panel_1.add(btnStudent);
		
		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/view_issued_books.png")));
		btnViewHistory.setForeground(new Color(255, 255, 255));
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewHistory frame = new ViewHistory();
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewHistory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnViewHistory.setBackground(new Color(70, 130, 180));
		btnViewHistory.setBounds(899, 221, 189, 113);
		panel_1.add(btnViewHistory);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/exit.png")));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Bye!");
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExit.setBackground(new Color(70, 130, 180));
		btnExit.setBounds(277, 407, 189, 113);
		panel_1.add(btnExit);
		
		JButton btnLibrarianDetails = new JButton("User Details");
		btnLibrarianDetails.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/view all record.png")));
		btnLibrarianDetails.setForeground(new Color(255, 255, 255));
		btnLibrarianDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDetails frame = new UserDetails();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLibrarianDetails.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLibrarianDetails.setBackground(new Color(70, 130, 180));
		btnLibrarianDetails.setBounds(592, 407, 189, 113);
		panel_1.add(btnLibrarianDetails);
		contentPane.add(panel);
	}
}
