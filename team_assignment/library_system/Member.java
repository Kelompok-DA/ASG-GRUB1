package library_system;

import java.util.Scanner;

/**
 * Kelas library_system.Member - kelas turunan dari library_system.User
 * Mendemonstrasikan inheritance dan polimorfisme
 */
class Member extends User {
    public Member(String userId, String name) {
        super(userId, name, UserRole.MEMBER.name()); // Panggil konstruktor induk
    }

    // Polimorfisme - mengganti metode abstrak dari kelas induk
    @Override
    public void showMenu() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║            SISTEM PENGELOLAAN DATA BUKU            ║");
        System.out.println("║                 MENU MEMBER                        ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ library_system.User: " + String.format("%-20s", name) + " Role: " + String.format("%-12s", getUserType()) + " ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Lihat Buku Tersedia                             ║");
        System.out.println("║ 2. Pinjam Buku                                     ║");
        System.out.println("║ 3. Kembalikan Buku                                 ║");
        System.out.println("║ 4. Cari Buku                                       ║");
        System.out.println("║ 5. Keluar                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    /**
     * Handle menu 3: Pinjam Buku
     */
    private static void handleBorrowBook(Library perpustakaan, Scanner scanner, String currentUserId) {
        System.out.println("=== MENU 3: PINJAM BUKU ===");

        // Tampilkan buku yang tersedia terlebih dahulu
        perpustakaan.displayAvailableBooks();

        System.out.print("\nMasukkan judul buku yang ingin dipinjam: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ Judul buku tidak boleh kosong!");
            return;
        }

        boolean success = perpustakaan.borrowBook(title, currentUserId);
        if (success) {
            System.out.println("  Selamat! Anda berhasil meminjam buku.");
        } else {
            System.out.println("  Maaf, peminjaman buku gagal.");
        }
    }

    /**
     * Handle menu 4: Kembalikan Buku
     */
    private static void handleReturnBook(Library perpustakaan, Scanner scanner, String currentUserId) {
        System.out.println("=== MENU 4: KEMBALIKAN BUKU ===");

        // Tampilkan semua buku untuk referensi
        perpustakaan.displayAllBooks();

        System.out.print("\nMasukkan judul buku yang ingin dikembalikan: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ Judul buku tidak boleh kosong!");
            return;
        }

        boolean success = perpustakaan.returnBook(title, currentUserId);
        if (success) {
            System.out.println("  Terima kasih! Buku berhasil dikembalikan.");
        } else {
            System.out.println("  Maaf, pengembalian buku gagal.");
        }
    }
    
    public void viewAvailableBooks(Library library) {
        System.out.println("library_system.Member " + name + " melihat buku yang tersedia:");
        library.displayAvailableBooks();
    }

    /**
     * Mengembalikan total opsi menu untuk member
     * @return
     */
    @Override
    public int getTotalMenuOptions() {
        return 4;
    }

    /**
     * Polimorfisme - implementasi metode abstrak untuk menangani opsi menu member
     * Melihat buku yang tersedia
     * @param library
     * @param scanner
     */
    @Override
    public void handleMenuOption1(Library library, Scanner scanner) {
        System.out.println("=== MENU 1: LIHAT BUKU TERSEDIA (MEMBER) ===");
        this.viewAvailableBooks(library);
    }

    /**
     * Polimorfisme - implementasi metode abstrak untuk menangani opsi menu member
     * Meminjam buku
     * @param library
     * @param scanner
     */
    @Override
    public void handleMenuOption2(Library library, Scanner scanner) {
        handleBorrowBook(library, scanner, this.getUserId());
    }

    /**
     * Polimorfisme - implementasi metode abstrak untuk menangani opsi menu member
     * Mengembalikan buku yang dipinjam
     * @param library
     * @param scanner
     */
    @Override
    public void handleMenuOption3(Library library, Scanner scanner) {
        handleReturnBook(library, scanner, this.getUserId());
    }
}
