class KarakterGame {
    private String nama;
    private int HP;

    public KarakterGame(String nama, int HP) {
        this.nama = nama;
        this.HP = HP;
    }

    public String getNama() {
        return nama;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int kesehatan) {
        this.HP = kesehatan;
    }

    public void serang(KarakterGame target) {

    }
}

class Pahlawan extends KarakterGame {
    public Pahlawan(String nama, int HP) {
        super(nama, HP);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menembak " + target.getNama() + " menggunakan vandal!");
        target.setHP(target.getHP() - 150);
        System.out.println(target.getNama() + " sekarang memiliki HP " + target.getHP());
    }
}

class Musuh extends KarakterGame {
    public Musuh(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan BladeStrom!");
        target.setHP(target.getHP() - 120);
        System.out.println(target.getNama() + " sekarang memiliki HP " + target.getHP());
    }
}

public class Main {
    public static void main(String[] args) {
        KarakterGame umum = new KarakterGame("Karakter Umum", 100);
        Pahlawan Yoru = new Pahlawan("Yoru", 150);
        Musuh Jett = new Musuh("Jett", 150);

        System.out.println("Status awal:");
        System.out.println(Yoru.getNama() + " memiliki HP: " + Yoru.getHP());
        System.out.println(Jett.getNama() + " memiliki HP: " + Jett.getHP());


        Yoru.serang(Jett);
        Jett.serang(Yoru);
    }
}