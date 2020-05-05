package rmi.formgui;

import java.awt.Color;
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

import rmi.server.RmiService;
import javax.swing.SwingConstants;


public class SensorDetails extends JPanel {

	/**
	 * Create the panel.
	 */
	private String sensorId;
	private int floorNo;
	private String roomNo;
	private boolean status;
	private int co2Level;
	private int smokeLevel;
	private JLabel lblsensorid;
	private boolean isAdminn;
	private JFrame frame;

	public SensorDetails(String sensorId, int floorNo, String roomNo, boolean status, int co2level,
			int smokelevel, boolean isAdminn, JFrame frame) {
		setLayout(null);
		this.sensorId = sensorId;
		this.floorNo = floorNo;
		this.roomNo = roomNo;
		this.status = status;
		this.co2Level = co2level;
		this.smokeLevel = smokelevel;
		this.isAdminn = isAdminn;
		this.frame = frame;

		JPanel panel = new JPanel();
		panel.setBackground(this.status ? Color.GREEN : Color.RED); //If co2 level goes above 5 display in red
		panel.setBounds(0, 0, 175, 154);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Floor");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(57, 59, 73, 22);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Room");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(57, 122, 81, 31);
		panel.add(lblNewLabel_5);

		JLabel lblfloornumber = new JLabel("" + this.floorNo);
		lblfloornumber.setForeground(new Color(255, 255, 255));
		lblfloornumber.setFont(new Font("Segoe UI", Font.BOLD, 34));
		lblfloornumber.setBounds(63, 18, 38, 29);
		panel.add(lblfloornumber);

		JLabel lblroomnumber = new JLabel(this.roomNo);
		lblroomnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblroomnumber.setForeground(new Color(255, 255, 255));
		lblroomnumber.setFont(new Font("Segoe UI", Font.BOLD, 34));
		lblroomnumber.setBounds(23, 95, 126, 28);
		panel.add(lblroomnumber);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(176, 0, 481, 154);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblsensorstatus = new JLabel(this.status ? "NORMAL" : "DANGER");
		lblsensorstatus.setForeground(this.status ? Color.black : Color.red);
		lblsensorstatus.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		lblsensorstatus.setBounds(10, 10, 163, 25);
		panel_1.add(lblsensorstatus);

		JLabel lblNewLabel_2 = new JLabel("Smoke Level");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 29));
		lblNewLabel_2.setBounds(107, 105, 163, 35);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CO2 Level");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 29));
		lblNewLabel_3.setBounds(318, 105, 153, 35);
		panel_1.add(lblNewLabel_3);

		JLabel lblco2level = new JLabel("" + this.smokeLevel);
		lblco2level.setForeground(new Color(0, 102, 204));
		lblco2level.setFont(new Font("Segoe UI", Font.PLAIN, 53));
		lblco2level.setBounds(145, 20, 102, 62);
		panel_1.add(lblco2level);

		JLabel lblsmokelevele = new JLabel("" + this.co2Level);
		lblsmokelevele.setForeground(new Color(0, 102, 204));
		lblsmokelevele.setFont(new Font("Segoe UI", Font.PLAIN, 53));
		lblsmokelevele.setBounds(339, 20, 116, 62);
		panel_1.add(lblsmokelevele);

		JButton btnNewButton = new JButton("");
		btnNewButton.setVisible(isAdminn);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				EditSensor editSensor = new EditSensor(lblfloornumber.getText(), lblroomnumber.getText(),
						lblsensorid.getText(), frame);
				editSensor.setVisible(true);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(SensorDetails.class.getResource("/img/edit.png")));
		btnNewButton.setBounds(446, 10, 25, 25);
		btnNewButton.setBorderPainted(false);
		panel_1.add(btnNewButton);

		lblsensorid = new JLabel(this.sensorId);
		lblsensorid.setBounds(156, 144, 45, 13);
		lblsensorid.setVisible(false);
		add(lblsensorid);
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCo2level() {
		return co2Level;
	}

	public void setCo2level(int co2level) {
		this.co2Level = co2level;
	}

	public int getSmokelevel() {
		return smokeLevel;
	}

	public void setSmokelevel(int smokelevel) {
		this.smokeLevel = smokelevel;
	}
}
