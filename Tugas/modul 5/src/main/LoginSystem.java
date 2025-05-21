package main;

import com.praktikum.models.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LoginSystem {

    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Item> reportedItems = new ArrayList<>();

    private Scanner scanner;

    public LoginSystem() {
        scanner = new Scanner(System.in);
        initializeDefaultUsers();
    }


    private void initializeDefaultUsers() {

        userList.add(new Admin("admin", "admin087"));


        userList.add(new Mahasiswa("Miko", "087", "087"));
        userList.add(new Mahasiswa("Rafi", "084", "084"));
    }


    public User login(String username, String password) {
        for (User user : userList) {
            if (user.login(username, password)) {
                return user;
            }
        }
        return null;
    }


    public void showMainMenu() {
        System.out.println("\n===== SISTEM LOST AND FOUND =====");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Pilih menu: ");
    }


    public void processLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loggedInUser = login(username, password);

        if (loggedInUser != null) {
            System.out.println("Login berhasil!");

            if (loggedInUser instanceof Admin) {
                showAdminMenu((Admin) loggedInUser);
            } else if (loggedInUser instanceof Mahasiswa) {
                showMahasiswaMenu((Mahasiswa) loggedInUser);
            }
        } else {
            System.out.println("Login gagal. Username atau password salah.");
        }
    }


    private void showAdminMenu(Admin admin) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Kelola Item");
            System.out.println("2. Kelola Pengguna");
            System.out.println("3. Logout");
            System.out.print("Pilih menu: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        manageItems();
                        break;
                    case 2:
                        manageUsers();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Logout berhasil.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }


    private void showMahasiswaMenu(Mahasiswa mahasiswa) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== MENU MAHASISWA =====");
            System.out.println("1. Laporkan Barang Hilang");
            System.out.println("2. Lihat Daftar Barang Hilang");
            System.out.println("3. Logout");
            System.out.print("Pilih menu: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        reportNewItem(mahasiswa);
                        break;
                    case 2:
                        viewReportedItems();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Logout berhasil.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }


    private void reportNewItem(Mahasiswa mahasiswa) {
        System.out.println("\n===== LAPORKAN BARANG HILANG =====");

        System.out.print("Nama Barang: ");
        String itemName = scanner.nextLine();

        System.out.print("Deskripsi: ");
        String description = scanner.nextLine();

        System.out.print("Lokasi Ditemukan: ");
        String location = scanner.nextLine();

        Item newItem = mahasiswa.reportItem(itemName, description, location);
        reportedItems.add(newItem);

        System.out.println("Barang berhasil dilaporkan!");
    }


    private void viewReportedItems() {
        System.out.println("\n===== DAFTAR BARANG HILANG =====");

        if (reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        boolean hasReportedItems = false;
        for (int i = 0; i < reportedItems.size(); i++) {
            Item item = reportedItems.get(i);
            if (item.getStatus().equals("Reported")) {
                System.out.println(i + ". " + item.getItemName() +
                        " - " + item.getDescription() +
                        " (Lokasi: " + item.getLocation() + ")");
                hasReportedItems = true;
            }
        }

        if (!hasReportedItems) {
            System.out.println("Belum ada laporan barang.");
        }
    }


    private void manageItems() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== KELOLA ITEM =====");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil (Claimed)");
            System.out.println("3. Kembali");
            System.out.print("Pilih menu: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewAllItems();
                        break;
                    case 2:
                        markItemAsClaimed();
                        break;
                    case 3:
                        back = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }


    private void viewAllItems() {
        System.out.println("\n===== SEMUA LAPORAN BARANG =====");

        if (reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        for (int i = 0; i < reportedItems.size(); i++) {
            Item item = reportedItems.get(i);
            System.out.println(i + ". " + item.getItemName() +
                    " - " + item.getDescription() +
                    " (Lokasi: " + item.getLocation() +
                    ", Status: " + item.getStatus() + ")");
        }
    }


    private void markItemAsClaimed() {
        System.out.println("\n===== TANDAI BARANG TELAH DIAMBIL =====");


        System.out.println("Daftar barang dengan status \"Reported\":");

        boolean hasReportedItems = false;
        for (int i = 0; i < reportedItems.size(); i++) {
            Item item = reportedItems.get(i);
            if (item.getStatus().equals("Reported")) {
                System.out.println(i + ". " + item.getItemName() +
                        " - " + item.getDescription());
                hasReportedItems = true;
            }
        }

        if (!hasReportedItems) {
            System.out.println("Tidak ada barang dengan status \"Reported\".");
            return;
        }

        System.out.print("Masukkan nomor indeks barang yang ingin ditandai: ");

        try {
            int selectedIndex = Integer.parseInt(scanner.nextLine());

            if (selectedIndex >= 0 && selectedIndex < reportedItems.size()) {
                Item selectedItem = reportedItems.get(selectedIndex);

                if (selectedItem.getStatus().equals("Reported")) {
                    selectedItem.setStatus("Claimed");
                    System.out.println("Barang berhasil ditandai sebagai \"Claimed\".");
                } else {
                    System.out.println("Barang ini sudah memiliki status \"Claimed\".");
                }
            } else {
                throw new IndexOutOfBoundsException("Indeks tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Indeks tidak valid.");
        }
    }


    private void manageUsers() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== KELOLA PENGGUNA =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Kembali");
            System.out.print("Pilih menu: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addMahasiswa();
                        break;
                    case 2:
                        deleteMahasiswa();
                        break;
                    case 3:
                        back = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }


    private void addMahasiswa() {
        System.out.println("\n===== TAMBAH MAHASISWA =====");

        System.out.print("Nama: ");
        String nama = scanner.nextLine();

        System.out.print("NIM: ");
        String nim = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Mahasiswa newMahasiswa = new Mahasiswa(nama, password, nim);
        userList.add(newMahasiswa);

        System.out.println("Mahasiswa berhasil ditambahkan!");
    }


    private void deleteMahasiswa() {
        System.out.println("\n===== HAPUS MAHASISWA =====");

        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        String nim = scanner.nextLine();

        Iterator<User> iterator = userList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user instanceof Mahasiswa) {
                Mahasiswa mahasiswa = (Mahasiswa) user;

                if (mahasiswa.getNim().equals(nim)) {
                    iterator.remove();
                    found = true;
                    break;
                }
            }
        }

        if (found) {
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus.");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }


    public Scanner getScanner() {
        return scanner;
    }


    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}