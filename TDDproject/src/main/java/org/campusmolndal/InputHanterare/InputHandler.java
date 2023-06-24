package org.campusmolndal.InputHanterare;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int getIntInput() {// denna metod hämtar input från användaren och returnerar ett heltal
        int input = 0;
        boolean isValidNumber = true;
        while (isValidNumber) { // while loop som körs så länge isValidNumber är true
            try { // try catch block som fångar upp felaktig input från användaren
                input = scanner.nextInt();
                isValidNumber = false;
                scanner.nextLine();
            } catch (RuntimeException e) {
                System.out.println("Ogiltigt värde, försök igen.");
                scanner.nextLine();
            }
        }
        return input;
    }

    public String getStringInput() { // denna metod hämtar input från användaren och returnerar en sträng värde
        return scanner.nextLine();
    }

    public boolean getTaskStatusInput() { // denna metod hämtar input från användaren och returnerar en boolean värde
        boolean isValidStatus = true;
        boolean status = false;
        while (isValidStatus) {
            try {
                status = scanner.nextBoolean();
                isValidStatus = false;
                scanner.nextLine();
            } catch (RuntimeException e) {
                System.out.println("Ogiltig status, ange true eller false.");
                scanner.nextLine();
            }
        }
        return status;
    }

    public void closeScanner() { // denna metod stänger scanner objektet
        scanner.close();
    }
}
