import java.util.Scanner;

/**
 * Kelas induk abstrak yang merepresentasikan User dalam sistem perpustakaan
 * Mendemonstrasikan inheritance - berfungsi sebagai base class untuk Admin dan Member
 */
abstract class User {
    protected String userId;
    protected String name;
    protected String userType;

    // Konstruktor dengan validasi
    public User(String userId, String name, String userType) {
        if (userId == null || name == null || userType == null) {
            throw new IllegalArgumentException("User data cannot be null");
        }
        this.userId = userId;
        this.name = name;
        this.userType = userType;
    }

    // Metode getter (enkapsulasi)
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUserType() {
        return userType;
    }

    // Metode umum untuk semua pengguna
    public void displayUserInfo() {
        System.out.println("ID: " + userId + ", Name: " + name + ", Type: " + userType);
    }

    // Metode abstrak - harus diimplementasikan oleh kelas turunan (polimorfisme)
    public abstract void showMenu();
    public abstract void handleMenuOption1(Library library, Scanner scanner);
    public abstract void handleMenuOption2(Library library, Scanner scanner);
    public abstract void handleMenuOption3(Library library, Scanner scanner);
    public abstract int getTotalMenuOptions();
    public abstract UserRole getUserRole();
}
