package com.LibraryManagement;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		
		JPanel panel_2_5 = new JPanel();
		
		panel_2_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_5.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_5.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_5.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_5.setBackground(clr);
			}
		});

		panel_2_5.setLayout(null);
		panel_2_5.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_5.setBackground(new Color(169, 169, 169));
		panel_2_5.setBounds(899, 221, 189, 113);
		panel_1.add(panel_2_5);
		
		JLabel lblViewHistory = new JLabel("View History");
		lblViewHistory.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/text-book-opened-from-top-view.png")));
		lblViewHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewHistory.setForeground(Color.WHITE);
		lblViewHistory.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblViewHistory.setBounds(0, 11, 189, 91);
		panel_2_5.add(lblViewHistory);
		
		JPanel panel_2_6 = new JPanel();
		
		panel_2_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_6.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_6.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_6.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_6.setBackground(clr);
			}
		});

		panel_2_6.setLayout(null);
		panel_2_6.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_6.setBackground(new Color(169, 169, 169));
		panel_2_6.setBounds(277, 407, 189, 113);
		panel_1.add(panel_2_6);
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/about.png")));
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAbout.setBounds(0, 11, 189, 91);
		panel_2_6.add(lblAbout);
		
		JPanel panel_2_7 = new JPanel();
		
		panel_2_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_7.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_7.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_7.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_7.setBackground(clr);
			}
		});

		panel_2_7.setLayout(null);
		panel_2_7.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_7.setBackground(new Color(169, 169, 169));
		panel_2_7.setBounds(592, 407, 189, 113);
		panel_1.add(panel_2_7);
		
		JLabel lblDeleteAccount = new JLabel("Delete Account");
		lblDeleteAccount.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/delete.png")));
		lblDeleteAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteAccount.setForeground(Color.WHITE);
		lblDeleteAccount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDeleteAccount.setBounds(0, 11, 189, 91);
		panel_2_7.add(lblDeleteAccount);
		
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
		
		JButton btnIssueBook_1 = new JButton("Add Books");
		btnIssueBook_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook frame = new AddBook();
				frame.setVisible(true);
				dispose();
			}
		});
		btnIssueBook_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnIssueBook_1.setBackground(new Color(169, 169, 169));
		btnIssueBook_1.setBounds(277, 34, 189, 113);
		panel_1.add(btnIssueBook_1);
		
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
		btnViewBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnViewBook.setBackground(new Color(169, 169, 169));
		btnViewBook.setBounds(277, 221, 189, 113);
		panel_1.add(btnViewBook);
		
		JButton btnReturnBooks = new JButton("Student");
		btnReturnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student frame = new Student();
				frame.setVisible(true);
				dispose();
			}
		});
		btnReturnBooks.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReturnBooks.setBackground(new Color(169, 169, 169));
		btnReturnBooks.setBounds(592, 221, 189, 113);
		panel_1.add(btnReturnBooks);
		contentPane.add(panel);
	}
}
