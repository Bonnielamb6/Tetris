package org.example;

import java.util.Scanner;

public class ScannerWrapper {
    private final Scanner scanner;
    public ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
    public int getIntInput() {
        return scanner.nextInt();
    }
}
