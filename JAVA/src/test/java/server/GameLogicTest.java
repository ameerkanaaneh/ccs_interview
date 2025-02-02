package server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    private final GameLogic gameLogic = new GameLogic();

    @Test
    void testValidateGuess() {
        // Directly passing the value to test
        String guess = "10";
        assertEquals(10, gameLogic.validateGuess(guess));
    }

    @Test
    void testValidateGuessCorrectness() {
        // Directly passing values to test
        int guess = 15;
        assertEquals(false, gameLogic.checkGuessCorrectness(guess, 100));
    }

}