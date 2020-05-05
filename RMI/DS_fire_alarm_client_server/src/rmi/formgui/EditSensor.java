package rmi.formgui;

import java.awt.BorderLayout;
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
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditSensor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean res;
	private JTextField txtFloorNo;
	private JTextField txtRoomNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditSensor dialog = new EditSensor(null, null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditSensor(String lblfloornumber, String lblroomnumber, String lblsensorid, JFrame frame) {
		setBounds(350, 300, 452, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLUE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Floor No");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 23));
			lblNewLabel.setBounds(41, 101, 111, 45);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Room No");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
			lblNewLabel_1.setBounds(41, 176, 111, 51);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtFloorNo = new JTextField();
			txtFloorNo.setHorizontalAlignment(SwingConstants.CENTER);
			txtFloorNo.setBackground(Color.LIGHT_GRAY);
			txtFloorNo.setForeground(Color.BLACK);
			txtFloorNo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			txtFloorNo.setBounds(201, 107, 184, 45);
			contentPanel.add(txtFloorNo);
			txtFloorNo.setColumns(10);
		}
		{
			txtRoomNo = new JTextField();
			txtRoomNo.setHorizontalAlignment(SwingConstants.CENTER);
			txtRoomNo.setBackground(Color.LIGHT_GRAY);
			txtRoomNo.setForeground(Color.BLACK);
			txtRoomNo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			txtRoomNo.setBounds(201, 185, 184, 45);
			contentPanel.add(txtRoomNo);
			txtRoomNo.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLUE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("EDIT");
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
				okButton.setForeground(Color.BLUE);
				okButton.setBackground(Color.WHITE);
				okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				txtFloorNo.setText(lblfloornumber);
				txtRoomNo.setText(lblroomnumber);
				{
					JLabel lblNewLabel_2 = new JLabel("Edit Sensor");
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setForeground(Color.WHITE);
					lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 27));
					lblNewLabel_2.setBounds(109, 23, 213, 45);
					contentPanel.add(lblNewLabel_2);
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						RmiService service;

						try {
							//find the service
							service = (RmiService) Naming.lookup("rmi://localhost:1099/FireAlarmSensor");

							try {
								//invoke remote method and assign to a variable
								res = service.editSensor(lblsensorid, Integer.parseInt(txtFloorNo.getText()),
										txtRoomNo.getText());
								JOptionPane.showMessageDialog(null, "Edited Successfully");
								dispose();

							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						} catch (MalformedURLException | RemoteException | NotBoundException ex) {
							ex.printStackTrace();
						}

						if (res) {
							frame.dispose();
							Main main = new Main(true);
							main.main(null);

						} else {
							System.out.println("Error");
						}
					}
				});
				okButton.setActionCommand("OK");
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
				cancelButton.setForeground(Color.BLUE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
