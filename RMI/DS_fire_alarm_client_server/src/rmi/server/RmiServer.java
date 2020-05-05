package rmi.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.Timer;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class RmiServer extends UnicastRemoteObject implements RmiService {

	protected RmiServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException, IOException {

		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("FireAlarmSensor", new RmiServer());

		System.out.println("Server is ready...");
		
		Timer t = new Timer(0, null);

		t.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//checkStateRepeatedly();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		t.setRepeats(true);
		t.setDelay(15000); // repeat interval
		t.start();

	}
	

	/*
	 * Check Admin login credentials
	 */
	
	public boolean ValidateLogin(String username, String password) throws RemoteException{
			
		boolean found = false;
			try {
				if(username.equals("admin") && password.equals("admin"))
				{
					return found = true;
				}else {
					return found=false;
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return found;
	}
	
	/*
	 * Retrieve all the sensor details
	 */
	public String getSensorDetails() throws RemoteException {
		HttpClient client = HttpClient.newHttpClient();
		// prepare a HTTP request to send to API
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8089/sensors")).build();
		return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
				.thenApply((responseBody) -> parse(responseBody)).join();
	}

	public static String parse(String responseBody) {
		return responseBody;
	}


	/*
	 * Register a new sensor to the system
	 */
	@Override
	public boolean addSensor(String id, int floor, String room) throws RemoteException {

		boolean res = false;

		JSONObject json = new JSONObject();
		json.put("sensorId", id);
		json.put("floorNo", floor);
		json.put("roomNo", room);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			// prepare a HTTP request to send to API
			HttpPost request = new HttpPost("http://localhost:8089/addSensor");
			StringEntity params = new StringEntity(json.toString());

			request.addHeader("content-type", "application/json");

			request.setEntity(params);
			org.apache.http.HttpResponse response = httpClient.execute(request);

			System.out.println(response.getStatusLine().toString().equalsIgnoreCase("HTTP/1.1 201 Created"));

			// check the response
			res = response.getStatusLine().toString().equalsIgnoreCase("HTTP/1.1 201 Created");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	/*
	 * Edit registered sensor details
	 */
	@Override
	public boolean editSensor(String id, int floor, String room) throws RemoteException {

		boolean res = false;

		JSONObject json = new JSONObject();
		json.put("sensorId", id);
		json.put("floorNo", floor);
		json.put("roomNo", room);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			// prepare a HTTP request to send to API
			HttpPut request = new HttpPut("http://localhost:8089/editSensor");
			StringEntity params = new StringEntity(json.toString());

			request.addHeader("content-type", "application/json");

			request.setEntity(params);
			org.apache.http.HttpResponse response = httpClient.execute(request);

			System.out.println(response.getStatusLine().toString().equalsIgnoreCase("HTTP/1.1 200 OK"));

			// check the response
			res = response.getStatusLine().toString().equalsIgnoreCase("HTTP/1.1 200 OK");

		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

}
