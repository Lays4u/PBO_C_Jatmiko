package com.praktikum.main;

import com.praktikum.users.User;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user;
        if (username.equals("admin") && password.equals("admin123")) {
            user = new Admin(username, password);
        } else {
            user = new Mahasiswa(username, password);
        }

        if (user.login()) {
            user.displayAppMenu();
        } else {
            System.out.println("Login gagal. Periksa kembali username dan password Anda.");
        }
    }
}
