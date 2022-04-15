package m2dl.pcr.rmi.services;

import m2dl.pcr.rmi.interfaces.IClient;
import m2dl.pcr.rmi.utils.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class ClientService extends UnicastRemoteObject implements IClient {

    private String username = "";

    public ClientService() throws RemoteException {
        super();
    }

    @Override
    public void sendNotification(Message message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public String getUsername() throws RemoteException {
        return username;
    }

    @Override
    public void setUsername(String username) throws RemoteException {
        this.username = username;
    }

}
