package com.LibraryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField textStudentFirstName;
	private JTextField textStudentLastName;
	private JTextField textStudentDepartment;
	private JTextField textStudentContactNumber;
	private JTextField textStudentBooksIssued;
	private JTextField textBookName;
	private JTextField textBookQuantity;
	private JTextField textIssueDate;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxStudentId;
	private JButton btnClear_2;
	private JButton btnIssue;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxBookId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	@SuppressWarnings("rawtypes")
	public ReturnBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 0, 1370, 142);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Return Book");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel_1_3.setBounds(563, 11, 306, 126);
		panel_2.add(lblNewLabel_1_3);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 141, 260, 574);
		contentPane.add(panel);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHome.setBackground(new Color(169, 169, 169));
		btnHome.setBounds(32, 48, 189, 67);
		panel.add(btnHome);
		
		JButton btnSelectStudent = new JButton("Select Student");
		btnSelectStudent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSelectStudent.setBackground(new Color(169, 169, 169));
		btnSelectStudent.setBounds(32, 176, 189, 67);
		panel.add(btnSelectStudent);
		
		JButton btnSelectBook = new JButton("Select Book");
		btnSelectBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSelectBook.setBackground(new Color(169, 169, 169));
		btnSelectBook.setBounds(32, 292, 189, 67);
		panel.add(btnSelectBook);
		
		JButton btnClear = new JButton("Clear All");
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnClear.setBackground(new Color(169, 169, 169));
		btnClear.setBounds(32, 426, 189, 67);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(258, 141, 1112, 574);
		contentPane.add(panel_1);
		
		JLabel lblStudentBooksIssued = new JLabel("Books Issued       :");
		lblStudentBooksIssued.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentBooksIssued.setBounds(537, 153, 158, 25);
		panel_1.add(lblStudentBooksIssued);
		
		JLabel lblStudentId = new JLabel("Student Id     :");
		lblStudentId.setForeground(Color.BLACK);
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentId.setBounds(58, 47, 126, 24);
		panel_1.add(lblStudentId);
		
		JLabel lblStudentFirstName = new JLabel("First Name   :");
		lblStudentFirstName.setForeground(Color.BLACK);
		lblStudentFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentFirstName.setBounds(58, 101, 133, 24);
		panel_1.add(lblStudentFirstName);
		
		JLabel lblStudentDepartment = new JLabel("Department          :");
		lblStudentDepartment.setForeground(Color.BLACK);
		lblStudentDepartment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentDepartment.setBounds(537, 46, 158, 24);
		panel_1.add(lblStudentDepartment);
		
		JLabel lblStudentContactNumber = new JLabel("Contact Number  :");
		lblStudentContactNumber.setForeground(Color.BLACK);
		lblStudentContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentContactNumber.setBounds(537, 100, 158, 24);
		panel_1.add(lblStudentContactNumber);
		
		JLabel lblStudentLastName = new JLabel("Last Name    :");
		lblStudentLastName.setForeground(Color.BLACK);
		lblStudentLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentLastName.setBounds(58, 154, 133, 24);
		panel_1.add(lblStudentLastName);
		
		textStudentFirstName = new JTextField();
		textStudentFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentFirstName.setColumns(10);
		textStudentFirstName.setBounds(194, 100, 282, 25);
		panel_1.add(textStudentFirstName);
		
		textStudentLastName = new JTextField();
		textStudentLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentLastName.setColumns(10);
		textStudentLastName.setBounds(194, 153, 282, 25);
		panel_1.add(textStudentLastName);
		
		textStudentDepartment = new JTextField();
		textStudentDepartment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentDepartment.setColumns(10);
		textStudentDepartment.setBounds(705, 45, 331, 25);
		panel_1.add(textStudentDepartment);
		
		btnClear_2 = new JButton("Clear");
		btnClear_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnClear_2.setBackground(new Color(169, 169, 169));
		btnClear_2.setBounds(276, 433, 133, 46);
		panel_1.add(btnClear_2);
		
		btnIssue = new JButton("Issue");
		btnIssue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnIssue.setBackground(new Color(169, 169, 169));
		btnIssue.setBounds(737, 433, 133, 46);
		panel_1.add(btnIssue);
		
		JLabel lblEditionMsg = new JLabel("");
		lblEditionMsg.setBounds(718, 306, 113, 24);
		panel_1.add(lblEditionMsg);
		
		textStudentContactNumber = new JTextField();
		textStudentContactNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentContactNumber.setColumns(10);
		textStudentContactNumber.setBounds(705, 99, 331, 25);
		panel_1.add(textStudentContactNumber);
		
		textStudentBooksIssued = new JTextField();
		textStudentBooksIssued.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStudentBooksIssued.setColumns(10);
		textStudentBooksIssued.setBounds(705, 152, 331, 25);
		panel_1.add(textStudentBooksIssued);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 225, 1028, 25);
		panel_1.add(separator);
		
		JLabel lblBookId = new JLabel("Book Id        :");
		lblBookId.setForeground(Color.BLACK);
		lblBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookId.setBounds(58, 277, 126, 24);
		panel_1.add(lblBookId);
		
		JLabel lblBookName = new JLabel("Book Name  :");
		lblBookName.setForeground(Color.BLACK);
		lblBookName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookName.setBounds(58, 331, 133, 24);
		panel_1.add(lblBookName);
		
		textBookName = new JTextField();
		textBookName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textBookName.setColumns(10);
		textBookName.setBounds(194, 330, 282, 25);
		panel_1.add(textBookName);
		
		JLabel lblBookQuantity = new JLabel("Book Quantity     :");
		lblBookQuantity.setForeground(Color.BLACK);
		lblBookQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBookQuantity.setBounds(537, 276, 158, 24);
		panel_1.add(lblBookQuantity);
		
		textBookQuantity = new JTextField();
		textBookQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textBookQuantity.setColumns(10);
		textBookQuantity.setBounds(705, 275, 331, 25);
		panel_1.add(textBookQuantity);
		
		JLabel lblIssueDate = new JLabel("Issue Date           :");
		lblIssueDate.setForeground(Color.BLACK);
		lblIssueDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblIssueDate.setBounds(537, 330, 158, 24);
		panel_1.add(lblIssueDate);
		
		textIssueDate = new JTextField();
		textIssueDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textIssueDate.setColumns(10);
		textIssueDate.setBounds(705, 329, 331, 25);
		panel_1.add(textIssueDate);
		
		comboBoxStudentId = new JComboBox();
		comboBoxStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxStudentId.setBounds(194, 49, 282, 24);
		panel_1.add(comboBoxStudentId);
		
		comboBoxBookId = new JComboBox();
		comboBoxBookId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxBookId.setBounds(194, 276, 282, 24);
		panel_1.add(comboBoxBookId);
	}
}
