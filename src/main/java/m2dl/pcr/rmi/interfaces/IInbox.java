package m2dl.pcr.rmi.interfaces;

import m2dl.pcr.rmi.utils.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IInbox extends Remote {
    void sendMessage(Message message, String channelName) throws RemoteException;
    List<Message> getAllMessageFromChannel(String channelName) throws RemoteException;
    void cleanMessages(String channelName) throws RemoteException;
    boolean login(String username, IClient client, String channelName) throws RemoteException;
    boolean logout(String username, IClient client, String channelName) throws RemoteException;
}
