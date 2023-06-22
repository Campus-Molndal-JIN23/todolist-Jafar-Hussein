package org.campusmolndal.InputHanterare;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public  InputHandler() {
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
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return input;
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public boolean getTaskStatusInput() {
        return scanner.nextBoolean();
    }

    public void closeScanner() {
        scanner.close();
    }
}
