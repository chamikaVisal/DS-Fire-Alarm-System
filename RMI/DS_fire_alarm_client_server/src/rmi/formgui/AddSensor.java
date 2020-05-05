package rmi.formgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import rmi.server.RmiService;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddSensor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtsensorid;
	private JTextField txtfloorno;
	private JTextField txtroomno;
	private boolean res;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddSensor dialog = new AddSensor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddSensor() {
		setBounds(250, 300, 424, 409);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLUE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sensor ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(33, 87, 93, 28);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Floor No");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(33, 166, 93, 28);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Room No");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(33, 244, 93, 20);
		contentPanel.add(lblNewLabel_2);

		txtsensorid = new JTextField();
		txtsensorid.setHorizontalAlignment(SwingConstants.CENTER);
		txtsensorid.setBackground(Color.LIGHT_GRAY);
		txtsensorid.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtsensorid.setBounds(165, 84, 216, 38);
		contentPanel.add(txtsensorid);
		txtsensorid.setColumns(10);

		txtfloorno = new JTextField();
		txtfloorno.setHorizontalAlignment(SwingConstants.CENTER);
		txtfloorno.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtfloorno.setBackground(Color.LIGHT_GRAY);
		txtfloorno.setBounds(165, 163, 216, 38);
		contentPanel.add(txtfloorno);
		txtfloorno.setColumns(10);

		txtroomno = new JTextField();
		txtroomno.setHorizontalAlignment(SwingConstants.CENTER);
		txtroomno.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtroomno.setBackground(Color.LIGHT_GRAY);
		txtroomno.setBounds(165, 237, 216, 38);
		contentPanel.add(txtroomno);
		txtroomno.setColumns(10);

		//display an error message if same id is entered
		JLabel lblerromsg = new JLabel("ID " + txtsensorid.getText() + " is already exist!");
		lblerromsg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblerromsg.setForeground(Color.RED);
		lblerromsg.setBounds(131, 301, 158, 19);
		lblerromsg.setVisible(false);
		contentPanel.add(lblerromsg);
		
		JLabel lblNewLabel_3 = new JLabel("Add Sensor");
		lblNewLabel_3.setBackground(Color.BLUE);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 27));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(122, 25, 177, 38);
		contentPanel.add(lblNewLabel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLUE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("ADD");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						okButton.setBackground(Color.LIGHT_GRAY);
						okButton.setForeground(Color.BLUE);

					}
					@Override
					public void mouseExited(MouseEvent e) {
						okButton.setBackground(Color.WHITE);
						okButton.setForeground(Color.BLUE);
					}
				});
				okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				okButton.setBackground(Color.WHITE);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String id = txtsensorid.getText();
						int floor = Integer.parseInt(txtfloorno.getText());
						String room = txtroomno.getText();

						RmiService service;

						try {
							//find the service
							service = (RmiService) Naming.lookup("rmi://localhost:1099/FireAlarmSensor");

							try {
								
								//invoke remote method and assign to a variable
								res = service.addSensor(id, floor, room);
								JOptionPane.showMessageDialog(null, "Added Successfully");
								dispose();

							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						} catch (MalformedURLException | RemoteException | NotBoundException ex) {
							ex.printStackTrace();
						}

						if (res) {

							Main main = new Main(true);
							main.main(null);
						} else {
							lblerromsg.setVisible(true);
						}

					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						cancelButton.setBackground(Color.RED);
						cancelButton.setForeground(Color.WHITE);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						cancelButton.setBackground(Color.WHITE);
						cancelButton.setForeground(Color.BLUE);
					}
				});
				cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				cancelButton.setBackground(Color.WHITE);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(EXIT_ON_CLOSE);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
