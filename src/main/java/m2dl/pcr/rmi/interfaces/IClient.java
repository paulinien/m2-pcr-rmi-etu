package m2dl.pcr.rmi.interfaces;

import m2dl.pcr.rmi.utils.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void sendNotification(Message message) throws RemoteException;
    String getUsername() throws RemoteException;
    void setUsername(String username) throws RemoteException;
}
