import java.util.Scanner;

class RekeningBank {
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;

    // Konstruktor
    public RekeningBank(String nomorRekening, String namaPemilik, double saldo) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldo;
    }

    // Metode untuk menampilkan informasi rekening
    public void tampilkanInfo() {
        System.out.println("Nomor Rekening: " + nomorRekening);
        System.out.println("Nama Pemilik: " + namaPemilik);
        System.out.println("Saldo: Rp" + saldo);
        System.out.println();
    }

    // Metode untuk setor uang
    public void setorUang(double jumlah) {
        saldo += jumlah;
        System.out.println(namaPemilik + " menyetor Rp" + jumlah + ". Saldo sekarang: Rp" + saldo);
    }

    // Metode untuk tarik uang
    public void tarikUang(double jumlah) {
        if (jumlah > saldo) {
            System.out.println(namaPemilik + " menarik Rp" + jumlah + ". (Gagal, Saldo tidak mencukupi) Saldo saat ini: Rp" + saldo);
        } else {
            saldo -= jumlah;
            System.out.println(namaPemilik + " menarik Rp" + jumlah + ". (Berhasil) Saldo sekarang: Rp" + saldo);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        RekeningBank rekening1 = new RekeningBank("202410370110087", "Jatmiko Eka Pratama", 7500000.0);
        RekeningBank rekening2 = new RekeningBank("202410370110084", "Rafi Aditya", 2500000.0);

        while (true) {
            System.out.println("\nPilih aksi:");
            System.out.println("1. Lihat Info Rekening");
            System.out.println("2. Setor Uang");
            System.out.println("3. Tarik Uang");
            System.out.println("4. Keluar");
            System.out.print("Masukkan pilihan: ");
            int pilihan = scanner.nextInt();

            if (pilihan == 4) {
                System.out.println("Terima kasih!");
                break;
            }

            System.out.print("Pilih rekening (1 atau 2): ");
            int rek = scanner.nextInt();
            RekeningBank rekening = (rek == 1) ? rekening1 : rekening2;

            switch (pilihan) {
                case 1:
                    rekening.tampilkanInfo();
                    break;
                case 2:
                    System.out.print("Masukkan jumlah setor: Rp");
                    double setor = scanner.nextDouble();
                    rekening.setorUang(setor);
                    break;
                case 3:
                    System.out.print("Masukkan jumlah tarik: Rp");
                    double tarik = scanner.nextDouble();
                    rekening.tarikUang(tarik);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}
