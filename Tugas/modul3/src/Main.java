import java.util.Scanner;

class User {
    private final String nama;
    private final String nim;

    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public boolean login(String inputNama, String inputNim) {
        return nama.equals(inputNama) && nim.equals(inputNim);
    }

}

class Admin extends User {
    private final String password;

    public Admin(String nama, String nim, String password) {
        super(nama, nim);
        this.password = password;
    }

    @Override
    public boolean login(String inputNama, String inputPassword) {
        return getNama().equals(inputNama) && this.password.equals(inputPassword);
    }

    public void displayInfo() {
        System.out.println("Login Admin sukses! Selamat datang, " + getNama());
    }
}

class Mahasiswa extends User {
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim) {
        return super.login(inputNama, inputNim);
    }

    public void displayInfo() {
        System.out.println("Login Mahasiswa sukses! Selamat datang, " + getNama());
    }
}

class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Admin  \n2. Mahasiswa");
        System.out.print("Pilih jenis login :  ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        if (pilihan == 1) {
            System.out.print("Masukkan username Admin :  ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan password Admin :  ");
            String password = scanner.nextLine();

            Admin admin = new Admin("admin", "087", "admin087");
            if (admin.login(nama, password)) {
                admin.displayInfo();
            } else {
                System.out.println("Login gagal! Username atau password salah.");
            }
        } else if (pilihan == 2) {
            System.out.print("Masukkan nama Mahasiswa :  ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan NIM Mahasiswa :  ");
            String nim = scanner.nextLine();

            Mahasiswa mahasiswa = new Mahasiswa("okim", "087");
            if (mahasiswa.login(nama, nim)) {
                mahasiswa.displayInfo();
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }
        } else {
            System.out.println("Pilihan tidak valid!");
        }

        scanner.close();
    }
}