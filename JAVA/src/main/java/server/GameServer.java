package server;

import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GameServer {
     private static final Set<ClientHandler> clients = Collections.newSetFromMap(new ConcurrentHashMap<>()); // Thread-safe set for storing clients
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public static void checkAndSendMessage() {
        boolean someCondition = true; 
        if (someCondition) {
            broadcastMessage("This is a message sent to all clients!");
        }
    }
}
