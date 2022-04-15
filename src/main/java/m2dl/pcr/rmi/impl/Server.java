package m2dl.pcr.rmi.impl;

import m2dl.pcr.rmi.services.InboxService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String args[]) {
        try {
            //Expose InboxService
            InboxService service = new InboxService();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Inbox", service);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
