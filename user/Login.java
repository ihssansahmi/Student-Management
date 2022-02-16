package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import controller.BaseD;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Login {

	private JFrame frmLogin;
	private JTextField username;
	private JPasswordField password;
	private JButton login;
	private JLabel error;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 511, 479);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 29));
		lblNewLabel.setBounds(205, 30, 92, 83);
		frmLogin.getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(205, 134, 193, 33);
		frmLogin.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(92, 129, 72, 39);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(92, 193, 72, 39);
		frmLogin.getContentPane().add(lblNewLabel_1_1);
		
		password = new JPasswordField();
		password.setBounds(205, 199, 193, 35);
		frmLogin.getContentPane().add(password);
		
		JButton login = new JButton("Sign in");
		login.setFont(new Font("Tahoma", Font.PLAIN, 16));

		login.setBounds(205, 296, 92, 33);
		frmLogin.getContentPane().add(login);
		
		JTextPane ee = new JTextPane();
		ee.setEditable(false);
		ee.setVisible(false);
		ee.setBackground(SystemColor.controlHighlight);
		ee.setForeground(new Color(128, 0, 0));
		ee.setText("username or password not valid");
		ee.setBounds(169, 268, 199, 20);
		frmLogin.getContentPane().add(ee);
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Username =  username.getText();
				String Password = password.getText();
				try {
					BaseD bD = new BaseD();
					Connection connection = bD.connection();
					Boolean rsBoolean = bD.selectUser(connection,Username,Password);
					ee.setVisible(false);
					if(rsBoolean) {
						frmLogin.setVisible(false);
						Manage ppp = new Manage();
						
					}else {
						ee.setVisible(true);
						
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("Error on verifying login functionnality");
				}
			}
		});

	}
}
