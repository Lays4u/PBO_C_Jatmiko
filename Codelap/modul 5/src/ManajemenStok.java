import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class ManajemenStok {

    public static void kurangiStokBarang(ArrayList<Barang> daftarBarang, int indeks, int jumlah) throws StokTidakCukupException, IndexOutOfBoundsException {
        Barang b = daftarBarang.get(indeks);
        if (jumlah > b.getStok()) {
            throw new StokTidakCukupException("Stok untuk '" + b.getNama() + "' hanya tersisa " + b.getStok());
        }
        b.setStok(b.getStok() - jumlah);
        System.out.println("Stok barang '" + b.getNama() + "' berhasil dikurangi. Sisa stok: " + b.getStok());
    }

    public static void main(String[] args) {
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        daftarBarang.add(new Barang("pulpen", 5));
        //daftarBarang.remove(0);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===== Menu Manajemen Stok =====");
            System.out.println("1. Tambah Barang Baru");
            System.out.println("2. Tampilkan Semua Barang");
            System.out.println("3. Kurangi Stok Barang");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");

            int opsi = -1;
            try {
                opsi = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (opsi) {
                case 1:

                    try {
                        System.out.print("Masukkan nama barang: ");
                        String nama = scanner.nextLine();

                        System.out.print("Masukkan stok awal: ");
                        int stok = scanner.nextInt();
                        scanner.nextLine();

                        Barang barang = new Barang(nama, stok);
                        daftarBarang.add(barang);
                        System.out.println("Barang '" + nama + "' berhasil ditambahkan.");
                    } catch (InputMismatchException e) {
                        System.out.println("Input stok harus berupa angka!");
                        scanner.nextLine();
                    }
                    break;

                case 2:

                    System.out.println("--- Daftar Barang ---");
                    if (daftarBarang.isEmpty()) {
                        System.out.println("Stok barang kosong.");
                    } else {
                        Iterator<Barang> iterator = daftarBarang.iterator();
                        int index = 0;
                        while (iterator.hasNext()) {
                            Barang b = iterator.next();
                            System.out.println(index+1 + ". Nama: " + b.getNama() + ", Stok: " + b.getStok());
                            index++;
                        }
                    }
                    break;

                case 3:

                    if (daftarBarang.isEmpty()) {
                        System.out.println("Belum ada barang.");
                        break;
                    }

                    System.out.println("--- Daftar Barang (Pilih untuk Kurangi Stok) ---");
                    for (int i = 0; i < daftarBarang.size(); i++) {
                        System.out.println(i + ". " + daftarBarang.get(i).getNama() + " (Stok: " + daftarBarang.get(i).getStok() + ")");
                    }

                    try {
                        System.out.print("Masukkan nomor indeks barang: ");
                        int indeks = scanner.nextInt();

                        System.out.print("Masukkan jumlah stok yang akan diambil: ");
                        int jumlahDiambil = scanner.nextInt();
                        scanner.nextLine();


                        kurangiStokBarang(daftarBarang, indeks, jumlahDiambil);

                    } catch (InputMismatchException e) {
                        System.out.println("Input indeks atau jumlah bukan angka.");
                        scanner.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Indeks tidak valid.");
                    } catch (StokTidakCukupException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Terima kasih! Program berakhir.");
                    break;

                default:
                    System.out.println("Opsi tidak valid!");
            }
        }

        scanner.close();
    }
}
