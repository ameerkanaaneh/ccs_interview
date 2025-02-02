package server;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final String playerName;

    public ClientHandler(Socket socket, String playerName) {
        this.clientSocket = socket;
        this.playerName = playerName;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Welcome to the Guessing Game, " + playerName + "!");
            out.println("Guess a number between 1 and 100.");
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                try {
                    int guess = Integer.parseInt(inputLine);
                    boolean isCorrect = GameServer.checkGuess(guess, playerName);

                    if (isCorrect) {
                        out.println("Congratulations! You guessed the correct number!");
                    } else {
                        out.println("Try again!");
                    }

                    // Update and broadcast game state to all clients
                    GameServer.updateGameState();
                    
                } catch (NumberFormatException e) {
                    out.println("Invalid input. Please enter a valid number.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
