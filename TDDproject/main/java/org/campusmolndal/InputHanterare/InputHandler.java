package org.campusmolndal.InputHanterare;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int getIntInput() {
        int input = 0;
        boolean isValidNumber = true;
        while (isValidNumber) {
            try {
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

    public String getStringInput() {
        return scanner.nextLine();
    }

    public boolean getTaskStatusInput() {
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

    public void closeScanner() {
        scanner.close();
    }
}
