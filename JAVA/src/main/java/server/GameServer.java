package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;


public class GameServer {
    private static final int PORT = 8080;
    private static final Set<ClientHandler> clients = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static final GameLogic gameLogic = new GameLogic();
    private static final List<String> guesses = new ArrayList<>(); // List to store guesses
    private static String winner = "None";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected.");

                // Generate a random player name or allow the client to choose
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                // Add client to the set
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if the guess is correct using GameLogic
    public synchronized boolean checkGuess(int guess, String playerName) {
        // Validate guess
        boolean isCorrect = gameLogic.checkGuessCorrectness(guess);
        String guessMessage = playerName + ": " + guess;

        // Add the guess to the list
        guesses.add(guessMessage);

        // If correct, set the winner
        if (isCorrect) {
            winner = playerName;
        }
        return isCorrect;
    }

    // Update game state and notify all clients
    public synchronized void updateGameState() {
        String gameStateMessage = buildGameStateMessage();
        sendToAllClients(gameStateMessage);
    }

    // Construct the game state message with guesses and current winner
    private String buildGameStateMessage() {
        StringBuilder message = new StringBuilder("Current guesses:\n");

        // Add all guesses
        for (String guess : guesses) {
            message.append(guess).append("\n");
        }

        message.append("\nCurrent winner: ").append(winner).append("\n");
        return message.toString();
    }

    // Broadcast message to all connected clients
    private void sendToAllClients(String message) {
        for (ClientHandler client : clients) {
            try {
                PrintWriter out = new PrintWriter(client.getClientSocket().getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<ClientHandler> getClients() {
        return clients;
    }
}
