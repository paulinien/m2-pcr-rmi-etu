package m2dl.pcr.rmi.services;

import m2dl.pcr.rmi.interfaces.IClient;
import m2dl.pcr.rmi.interfaces.IInbox;
import m2dl.pcr.rmi.utils.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InboxService extends UnicastRemoteObject implements IInbox {

    private Map<String, IClient> clientsMap = new HashMap<>();
    //private List<Message> messages = new ArrayList<>();
    private Map<String, List<Message>> messages = new HashMap<>();
    private Map<String, Map<String, IClient>> channels = new HashMap<>();
    public InboxService() throws RemoteException {
        super();
    }

    @Override
    public void sendMessage(Message message, String channelName) throws RemoteException {
        messages.computeIfAbsent(channelName, k -> new ArrayList<>());
        messages.get(channelName).add(message);
        for(IClient c : channels.get(channelName).values()) {
            try {
                c.sendNotification(message);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public List<Message> getAllMessageFromChannel(String channelName) throws RemoteException {
        messages.computeIfAbsent(channelName, k -> new ArrayList<>());
        return messages.get(channelName);
    }

    @Override
    public void cleanMessages(String channelName) throws RemoteException {
        messages.remove(channelName);
    }

    @Override
    public boolean login(String username, IClient client, String channelName) throws RemoteException {
        if(channels.get(channelName) == null) {
            channels.put(channelName, new HashMap<>());
        } else if(channels.get(channelName).containsKey(username)) return false;
        channels.get(channelName).put(username, client);
        return true;
    }

    @Override
    public boolean logout(String username, IClient client, String channelName) throws RemoteException {
        return channels.get(channelName).remove(username, client);
    }
}
