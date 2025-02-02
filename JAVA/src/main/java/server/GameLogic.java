package server;

import java.util.Random;

public class GameLogic {
    // private static final int SECRET_NUMBER = 42;
    private final Random random = new Random();

    public int validateGuess(String input) throws IllegalArgumentException {
        try {
            int guess = Integer.parseInt(input);
            if (guess < 1 || guess > 100) {
                throw new IllegalArgumentException("Number out of range, please guess between 1 and 100.");
            }
            return guess;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input, please enter a number.");
        }
    }

    public boolean checkGuessCorrectness(int guess, int bound) {
        int secretNumber = generateRandomNumber(bound);
        secretNumber = adjustNumber(secretNumber);
        secretNumber = transformNumber(secretNumber);

        return guess == secretNumber;
    }

    @Override
    public boolean checkGuessCorrectness(int guess, int testNumber) {
        testNumber = adjustNumber(testNumber);
        testNumber = transformNumber(testNumber);

        return guess == testNumber;
    }


    private int adjustNumber(int number) {
        if (number % 2 == 1) {
            int primNumber = generatePrimeRandomNumber();
            number += primNumber;
        } else {
            number = reverseDigits(number);
        }
        return number;
    }

    private int transformNumber(int number) {
        if (number >= 100) {
            return number / 2;
        }
        else if(number < 50) {
            return number * 2;
        }
        return number;
    }

    private int reverseDigits(int number) {
        int reverse = 0;
        while (number > 0) {
            reverse = reverse * 10;
            reverse += number % 10;
            number = number / 10;
        }
        return reverse;
    }

    private int generatePrimeRandomNumber() {
        int[] primesList = {2, 3, 5, 7, 11, 13};
        int primeIndex = random.nextInt(6);
        return primesList[primeIndex];
    }

    private int generateRandomNumber(int bound) {
        int number = random.nextInt(bound) + 1;
        return number;
    }

    public void generatePrefix(int guess) {
        int formatChoice = random.nextInt(3);
        String prefix;

        switch (formatChoice) {
            case 0:
                prefix = (guess % 2 == 0)
                        ? "The number you selected is " + guess + " and it is even!"
                        : "The number you selected is " + guess + " and it is odd!";
                break;
            case 1:
                prefix = (guess > 100)
                        ? "You selected " + guess + ", a number greater than 100! Great choice!"
                        : "You selected " + guess + ", which is a small number!";
                break;
            case 2:
                int randomFact = random.nextInt(100);
                prefix = "The number " + guess + " has a special fact: " + randomFact + " is a random number generated.";
                break;
            default:
                prefix = "You selected " + guess + ".";
        }

        if (guess >= 0 && guess <= 50) {
            prefix += " Your guess is within the safe zone!";
        } else if (guess > 50 && guess <= 150) {
            prefix += " Be careful! Your guess is in the uncertain range.";
        } else {
            prefix += " Your guess is in the high-risk zone!";
        }

        System.out.println(prefix);  // Prints the prefix instead of returning it
    }
}

