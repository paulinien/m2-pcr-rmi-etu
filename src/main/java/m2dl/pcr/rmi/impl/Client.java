package m2dl.pcr.rmi.impl;

import m2dl.pcr.rmi.interfaces.IClient;
import m2dl.pcr.rmi.interfaces.IInbox;
import m2dl.pcr.rmi.services.ClientService;
import m2dl.pcr.rmi.utils.Message;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private Client() {}

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        Scanner sc = new Scanner(System.in);
        String username = "";
        String channelName = "";
        boolean run = true;
        try {
            IClient clientService = new ClientService();
            //Get server
            Registry registry = LocateRegistry.getRegistry(host);
            IInbox server = (IInbox) registry.lookup("Inbox");

            System.out.println("Entrez le nom du channel :");
            channelName = sc.nextLine();

            boolean serverResponse;

            //Get username & message
            do {
                System.out.println("Entrez votre nom d'utilisateur :");
                username = sc.nextLine();
                clientService.setUsername(username);
                serverResponse = server.login(username, clientService, channelName);
                if(!serverResponse) System.out.println("Ce nom d'utilisateur n'est pas disponible");
            } while (!serverResponse);
            System.out.println("Début de la conversation :");
            for (Message m : server.getAllMessageFromChannel(channelName)) {
                System.out.println(m);
            }
            while(run) {
                String message = sc.nextLine();
                //If message equals clean then we clean the server messages
                if("clean".equalsIgnoreCase(message)) server.cleanMessages(channelName);
                else if("exit".equalsIgnoreCase(message)) run = false;
                else server.sendMessage(new Message(username, message), channelName);
            }
            server.logout(clientService.getUsername(), clientService, channelName);
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
