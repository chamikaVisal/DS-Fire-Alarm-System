package rmi.formgui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import rmi.server.RmiService;

public class Main extends JFrame {

	private static JPanel contentPane;
	private static String responseBody;
	private static Main frame;
	private static boolean isAdmin = true;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Main(isAdmin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				RmiService service;

				try {

					service = (RmiService) Naming.lookup("rmi://localhost:1099/FireAlarmSensor");
					Timer t = new Timer(0, null);

					t.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								responseBody = service.getSensorDetails();
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
							showSensorDetails(responseBody);
						}
					});

					t.setRepeats(true);
					t.setDelay(5000); // repeat interval is t.setDelay(30000); 
					t.start();

				} catch (MalformedURLException | RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main(boolean isAdmin) {
		Main.isAdmin = isAdmin;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(210, 0, 689, 780);


		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setIcon(new ImageIcon(Main.class.getResource("/img/plus.jpg")));
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);

		JMenuItem menuItem1 = new JMenuItem("Add new Sensor");
		menuItem1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuItem1.setForeground(Color.WHITE);
		menuItem1.setBackground(Color.BLUE);

		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSensor addSensor = new AddSensor();
				addSensor.setVisible(true);
			}
		});
		mnNewMenu.add(menuItem1);
		menuItem1.setVisible(isAdmin);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem menuItem2 = new JMenuItem("Exit");
		menuItem2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuItem2.setForeground(Color.WHITE);
		menuItem2.setBackground(Color.RED);

		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		mnNewMenu.add(menuItem2);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(1);
		gridLayout.setRows(0);
		contentPane.setLayout(gridLayout);

		JScrollPane scrollPane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setContentPane(scrollPane);

	}

	/**
	 * used to display sensor details
	 */
	public static void showSensorDetails(String responseBody) {

		contentPane.removeAll();

		JSONArray resArray = new JSONArray(responseBody);

		JPanel jPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		//display sensor details in the user interface
		for (int i = 0; i < resArray.length(); i++) { //loop through the responseBody

			JSONObject obj = resArray.getJSONObject(i);

//			JSONObject lastReading = obj.getJSONObject("lastReading");

			int co2Level = obj.getInt("co2Lvl");
			int smokeLevel = obj.getInt("smokeLvl");
//			String time = lastReading.getString("time");

//			boolean activated = obj.getBoolean("activated");
			String id = String.valueOf(obj.getInt("sensorId"));
			int floor = obj.getInt("floorNo");
			String room = String.valueOf(obj.getInt("roomNo"));

			SensorDetails sensorDetails = new SensorDetails(id, floor, room, co2Level < 5 && smokeLevel <5,
					co2Level, smokeLevel, isAdmin, frame);
			sensorDetails.setVisible(true);
			contentPane.add(sensorDetails);
//			jPanel.add(sensorDetails);
		}

//		scrollPane.setViewportView(jPanel);
//		contentPane.add(scrollPane);
		contentPane.validate();
		contentPane.repaint();
	}

}
