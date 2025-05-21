package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();
        Scanner scanner = loginSystem.getScanner();
        boolean exit = false;

        System.out.println("====================================");
        System.out.println("SELAMAT DATANG DI SISTEM LOST & FOUND");
        System.out.println("====================================");

        while (!exit) {
            loginSystem.showMainMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        loginSystem.processLogin();
                        break;
                    case 2:
                        exit = true;
                        System.out.println("Terima kasih telah menggunakan sistem kami.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }

        loginSystem.closeScanner();
    }
}
