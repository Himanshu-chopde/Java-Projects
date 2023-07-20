package com.LibraryManagement;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JTextField txtBookAuthor;
	private JTextField txtBookPublisher;
	private JLabel lblEditionMsg;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEdition, comboBoxQuantity;
	String bookId, bookName, bookAuthor, bookPublisher, edition, quantity, addedDate;
	int bookEdition = 0,bookQuantity;
	
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

	//validating the inputs
	boolean validation() {
		bookId = txtBookId.getText();
		bookName = txtBookName.getText();
		bookAuthor = txtBookAuthor.getText();
		bookPublisher = txtBookPublisher.getText();
		edition = (String)comboBoxEdition.getItemAt(comboBoxEdition.getSelectedIndex());
		quantity = (String)comboBoxQuantity.getItemAt(comboBoxQuantity.getSelectedIndex());
		
		if(bookId.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book id");
			return false;
		}
		
		if(checkBookId()) {
			JOptionPane.showMessageDialog(null, "This book id already exist\nPlease try another");
			return false;
		}
		
		if(bookName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book name");
			return false;
		}
		
		if(bookAuthor.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book author name");
			return false;
		}
		
		if(bookPublisher.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the book publisher");
			return false;
		}
		
		try {
			
		} catch (Exception e) {
			bookEdition = Integer.parseInt(edition);
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
			String sql = "select * from booklist where id=?";
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
	
	//inserting book details into the database
	
	void insertBookDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem","root","Himanshu@15");
			String sql="insert into booklist values (?,?,?,?,?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, bookId);
			stmt.setString(2, bookName);
			stmt.setString(3, bookAuthor);
			stmt.setString(4, bookPublisher);
			stmt.setInt(5, bookEdition);
			stmt.setInt(6, bookQuantity);
			stmt.setString(7, addedDate);

			int f = stmt.executeUpdate();
			if(f > 0) 
				JOptionPane.showMessageDialog(this, "Record inserted successfully");
			else
				JOptionPane.showMessageDialog(this, "An error occurred!\nRecord could not be inserted.");
			con.close();
		} catch (Exception e) {
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
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 142, 260, 574);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel btnPanelHome = new JPanel();
		
		btnPanelHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				btnPanelHome.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				btnPanelHome.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				btnPanelHome.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		
		btnPanelHome.setLayout(null);
		btnPanelHome.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnPanelHome.setBackground(new Color(169, 169, 169));
		btnPanelHome.setBounds(32, 37, 189, 70);
		panel.add(btnPanelHome);
		
		JLabel lblNewLabel = new JLabel("Home");
		lblNewLabel.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/home_small.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 11, 189, 55);
		btnPanelHome.add(lblNewLabel);
		
		JPanel btnPanelAddBook = new JPanel();
		
		btnPanelAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				btnPanelAddBook.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				btnPanelAddBook.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				btnPanelAddBook.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Color clr = new Color(105,105,105);
				btnPanelAddBook.setBackground(clr);
			}
		});
		
		btnPanelAddBook.setLayout(null);
		btnPanelAddBook.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnPanelAddBook.setBackground(new Color(169, 169, 169));
		btnPanelAddBook.setBounds(32, 140, 189, 70);
		panel.add(btnPanelAddBook);
		
		JLabel lblNewLabel_1 = new JLabel("Edit Book");
		lblNewLabel_1.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/edit.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(0, 11, 189, 48);
		btnPanelAddBook.add(lblNewLabel_1);
		
		JPanel btnPanelExit = new JPanel();
		
		btnPanelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				btnPanelExit.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				btnPanelExit.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				btnPanelExit.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int result;
				result = JOptionPane.showConfirmDialog(null, "Are you sure, You want to exit?","Exit",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					//JOptionPane.showMessageDialog(this, "Bye Bye!");
					System.exit(0);
				}
			}
		});
		
		btnPanelExit.setLayout(null);
		btnPanelExit.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnPanelExit.setBackground(new Color(169, 169, 169));
		btnPanelExit.setBounds(32, 464, 189, 70);
		panel.add(btnPanelExit);
		
		JLabel lblNewLabel_2 = new JLabel("Exit");
		lblNewLabel_2.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/exit.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(0, 11, 189, 48);
		btnPanelExit.add(lblNewLabel_2);
		
		JPanel btnPanelBack = new JPanel();
		
		btnPanelBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				btnPanelBack.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				btnPanelBack.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				btnPanelBack.setBackground(clr);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		
		btnPanelBack.setLayout(null);
		btnPanelBack.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnPanelBack.setBackground(new Color(169, 169, 169));
		btnPanelBack.setBounds(32, 249, 189, 70);
		panel.add(btnPanelBack);
		
		JLabel lblNewLabel_3 = new JLabel("Back");
		lblNewLabel_3.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/back1.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(0, 11, 189, 55);
		btnPanelBack.add(lblNewLabel_3);
		
		JPanel btnPanelSignout = new JPanel();
		
		btnPanelSignout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Color clr = new Color(105,105,105);
				btnPanelSignout.setBackground(clr);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Color clr = new Color(169,169,169);
				btnPanelSignout.setBackground(clr);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Color clr = new Color(220,220,220);
				btnPanelSignout.setBackground(clr);
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
		
		btnPanelSignout.setLayout(null);
		btnPanelSignout.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnPanelSignout.setBackground(new Color(169, 169, 169));
		btnPanelSignout.setBounds(32, 355, 189, 70);
		panel.add(btnPanelSignout);
		
		JLabel lblNewLabel_4 = new JLabel("Sign out");
		lblNewLabel_4.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/signout.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(0, 11, 189, 55);
		btnPanelSignout.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(259, 142, 1111, 574);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Book Id           :");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(287, 79, 133, 25);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name     :");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(287, 138, 133, 24);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Author             :");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(287, 194, 133, 24);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_4 = new JLabel("Edition            :");
		lblNewLabel_1_4.setForeground(Color.BLACK);
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(287, 306, 133, 24);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Quantity          :");
		lblNewLabel_1_5.setForeground(Color.BLACK);
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(287, 362, 133, 24);
		panel_1.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Publisher         :");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(287, 252, 133, 24);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtBookId = new JTextField();
		txtBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookId.setBounds(424, 79, 282, 25);
		panel_1.add(txtBookId);
		txtBookId.setColumns(10);
		
		txtBookName = new JTextField();
		txtBookName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookName.setColumns(10);
		txtBookName.setBounds(424, 138, 282, 25);
		panel_1.add(txtBookName);
		
		txtBookAuthor = new JTextField();
		txtBookAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookAuthor.setColumns(10);
		txtBookAuthor.setBounds(424, 194, 282, 25);
		panel_1.add(txtBookAuthor);
		
		txtBookPublisher = new JTextField();
		txtBookPublisher.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBookPublisher.setColumns(10);
		txtBookPublisher.setBounds(424, 252, 282, 25);
		panel_1.add(txtBookPublisher);
		
		comboBoxEdition = new JComboBox();
		comboBoxEdition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEditionMsg.setText("Optional");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblEditionMsg.setText("");
			}
			
		});
		comboBoxEdition.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"}));
		comboBoxEdition.setBackground(new Color(255, 255, 255));
		comboBoxEdition.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxEdition.setBounds(424, 306, 282, 24);
		panel_1.add(comboBoxEdition);
		
		comboBoxQuantity = new JComboBox();
		comboBoxQuantity.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200"}));
		comboBoxQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxQuantity.setBackground(Color.WHITE);
		comboBoxQuantity.setBounds(424, 365, 282, 24);
		panel_1.add(comboBoxQuantity);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBookId.setText("");
				txtBookName.setText("");
				txtBookAuthor.setText("");
				txtBookPublisher.setText("");
				comboBoxEdition.setSelectedIndex(0);
				comboBoxQuantity.setSelectedIndex(0);
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(169, 169, 169));
		btnNewButton.setBounds(277, 462, 133, 46);
		panel_1.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(AddBook.class.getResource("/com/images/save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validation()) {
					insertBookDetails();
				}
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave.setBackground(new Color(169, 169, 169));
		btnSave.setBounds(663, 462, 133, 46);
		panel_1.add(btnSave);
		
		lblEditionMsg = new JLabel("");
		lblEditionMsg.setBounds(718, 306, 113, 24);
		panel_1.add(lblEditionMsg);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Add Book");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(579, 11, 253, 126);
		panel_2.add(lblNewLabel_1_3);
	}
}
