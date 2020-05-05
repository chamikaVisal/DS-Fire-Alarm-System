package rmi.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author admin
 */
public interface RmiService extends Remote {
    public String getSensorDetails() throws RemoteException;

    public boolean addSensor(String id, int floor, String room) throws RemoteException;

    public boolean ValidateLogin(String username, String password) throws RemoteException;

    public boolean editSensor(String id, int floor, String room) throws RemoteException;


}
