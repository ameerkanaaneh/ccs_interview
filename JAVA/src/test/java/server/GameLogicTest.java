package server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

class GameLogicTest {
    private final GameLogic gameLogic = new GameLogic();
    Scanner myObj = new Scanner(System.in);
    int guess;

    @Test
    void testValidateGuess() {
        System.out.println("Guess a number: ");
        String guess = myObj.nextLine();
        assertEquals(10, gameLogic.validateGuess("10"));
    }

    @Test
    void testValidateGuessCorrectness() {
        System.out.println("Guess a number: ");
        String guess = myObj.nextLine();
        assertgameLogic.checkGuessCorrectness(15, 100);
    }
}
