package rmi.formgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rmi.server.RmiService;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpassword;
	private static Login frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 250, 553, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(285, 101, 85, 30);
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(285, 206, 67, 22);
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1);

		//display an error message when wrong credentials entered
		JLabel errormsg = new JLabel("Invalid Credentials. Try Again.");
		errormsg.setBounds(320, 375, 185, 22);
		errormsg.setForeground(Color.RED);
		errormsg.setFont(new Font("Calibri", Font.PLAIN, 13));
		contentPane.add(errormsg);
		errormsg.setVisible(false);

		txtusername = new JTextField();
		txtusername.setBounds(285, 131, 220, 43);
		txtusername.setForeground(Color.DARK_GRAY);
		txtusername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contentPane.add(txtusername);
		txtusername.setColumns(10);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(285, 230, 220, 38);
		txtpassword.setForeground(Color.DARK_GRAY);
		txtpassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contentPane.add(txtpassword);
		txtpassword.setColumns(10);

		JButton btnLogin = new JButton("Sign In");
		btnLogin.setBounds(357, 313, 102, 38);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(Color.BLUE);
				btnLogin.setForeground(Color.WHITE);
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean f = false;
				String username = txtusername.getText();
				String password = txtpassword.getText();

				RmiService service;
				//String result = null;
				try {
					// find the remote service
					service = (RmiService) Naming.lookup("rmi://localhost:1099/FireAlarmSensor");
					// invoke the remote method
					f = service.ValidateLogin(username, password);
					

				} catch (MalformedURLException | RemoteException | NotBoundException ex) {
					ex.printStackTrace();
				}
				System.out.println(f); // write the result on the console

				
				if (f == true) {


					Main main = new Main(true); //open the main interface 
					main.main(null);
				} else {
					errormsg.setVisible(true);
				}
				

			}
		});
		contentPane.add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 267, 431);
		panel.setBackground(Color.BLUE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(44, 66, 190, 188);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/img/blue.png")));
		panel.add(lblNewLabel_2);
		
		JLabel label = new JLabel("ADMIN");
		label.setBounds(81, 265, 115, 43);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Calibri", Font.PLAIN, 30));
		panel.add(label);
	}
}
