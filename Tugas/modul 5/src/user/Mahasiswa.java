package user;

public class Mahasiswa extends User {
    private String nim;

    public Mahasiswa(String username, String password, String nim) {
        super(username, password);
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public boolean login(String usernameInput, String passwordInput) {
        return this.username.equals(usernameInput) && this.password.equals(passwordInput);
    }


    public Item reportItem(String itemName, String description, String location) {
        return new Item(itemName, description, location);
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "username='" + username + '\'' +
                ", nim='" + nim + '\'' +
                '}';
    }
}