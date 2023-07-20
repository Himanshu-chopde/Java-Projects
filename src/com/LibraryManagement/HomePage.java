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
	
	private HomePage hp = this;
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
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				AddBook frame = new AddBook();
				frame.setVisible(true);
				hp.dispose();
				
			}
		});
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(277, 34, 189, 113);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Book");
		lblNewLabel.setBounds(0, 11, 189, 91);
		panel_2.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/plus.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_1.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_1.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_1.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_1.setBackground(clr);
			}
		});
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_1.setBackground(new Color(169, 169, 169));
		panel_2_1.setBounds(592, 34, 189, 113);
		panel_1.add(panel_2_1);
		
		JLabel lblIssueBook = new JLabel("Issue Book");
		lblIssueBook.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/issue_book.png")));
		lblIssueBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblIssueBook.setForeground(Color.WHITE);
		lblIssueBook.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblIssueBook.setBounds(0, 11, 189, 91);
		panel_2_1.add(lblIssueBook);
		
		JPanel panel_2_2 = new JPanel();
		
		panel_2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_2.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_2.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_2.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_2.setBackground(clr);
			}
		});

		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_2.setBackground(new Color(169, 169, 169));
		panel_2_2.setBounds(899, 34, 189, 113);
		panel_1.add(panel_2_2);
		
		JLabel lblReturnBook = new JLabel("Return Book");
		lblReturnBook.setBounds(0, 11, 189, 91);
		panel_2_2.add(lblReturnBook);
		lblReturnBook.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/return_book.png")));
		lblReturnBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturnBook.setForeground(Color.WHITE);
		lblReturnBook.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		JPanel panel_2_3 = new JPanel();
		
		panel_2_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_3.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_3.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_3.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_3.setBackground(clr);
			}
		});
		
		panel_2_3.setLayout(null);
		panel_2_3.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_3.setBackground(new Color(169, 169, 169));
		panel_2_3.setBounds(277, 221, 189, 113);
		panel_1.add(panel_2_3);
		
		JLabel lblViewBooks = new JLabel("View Books");
		lblViewBooks.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/view_books.png")));
		lblViewBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewBooks.setForeground(Color.WHITE);
		lblViewBooks.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblViewBooks.setBounds(0, 11, 189, 91);
		panel_2_3.add(lblViewBooks);
		
		JPanel panel_2_4 = new JPanel();
		
		panel_2_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_4.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_4.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_4.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_4.setBackground(clr);
			}
		});

		panel_2_4.setLayout(null);
		panel_2_4.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_4.setBackground(new Color(169, 169, 169));
		panel_2_4.setBounds(592, 221, 189, 113);
		panel_1.add(panel_2_4);
		
		JLabel lblViewIssuedBooks = new JLabel("View Issued Books");
		lblViewIssuedBooks.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/view_issued_books.png")));
		lblViewIssuedBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewIssuedBooks.setForeground(Color.WHITE);
		lblViewIssuedBooks.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblViewIssuedBooks.setBounds(0, 11, 186, 91);
		panel_2_4.add(lblViewIssuedBooks);
		
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
		
		JPanel panel_2_8 = new JPanel();
		
		panel_2_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				panel_2_8.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				panel_2_8.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				panel_2_8.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int result;
				result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to logout?","Logout",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					LoginPage login=new LoginPage();
					login.setVisible(true);
					dispose();
				}
			}
		});

		panel_2_8.setLayout(null);
		panel_2_8.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_2_8.setBackground(new Color(169, 169, 169));
		panel_2_8.setBounds(899, 407, 189, 113);
		panel_1.add(panel_2_8);
		
		JLabel lblSignOut = new JLabel("Sign Out");
		lblSignOut.setIcon(new ImageIcon(HomePage.class.getResource("/com/images/signout.png")));
		lblSignOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignOut.setForeground(Color.WHITE);
		lblSignOut.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSignOut.setBounds(0, 11, 189, 91);
		panel_2_8.add(lblSignOut);
		contentPane.add(panel);
	}
}
