package app;

import perpustakaan.*;

public class Main {
    public static void main(String[] args) {
        Buku nonFiksi = new NonFiksi("Atomic Habit", "James Clear", "Pengembangan Diri");
        Buku fiksi = new Fiksi("One Piece Vol. 111", "Eiichiro Oda", "Shonen");

        nonFiksi.displayInfo();
        fiksi.displayInfo();

        System.out.println();

        Anggota anggota1 = new Anggota("Jatmiko Eka Pratama", "C087");
        Anggota anggota2 = new Anggota("Damar Ghulamul Abror  ", "C060");

        anggota1.displayInfo();
        anggota2.displayInfo();

        System.out.println();

        anggota1.pinjamBuku("Atomic Habit");
        anggota2.pinjamBuku("One Piece Vol. 111", 7);

        System.out.println();

        anggota1.kembalikanBuku("Atomic Habit");
        anggota2.kembalikanBuku("One Piece Vol. 111");
    }
}
