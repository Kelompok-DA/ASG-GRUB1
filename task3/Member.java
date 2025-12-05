import java.util.Scanner;

/**
 * Kelas Member - kelas turunan dari User
 * Mendemonstrasikan inheritance dan polimorfisme
 */
class Member extends User {
    private int borrowedBooksCount;
    private int maxBorrowLimit;
    
    public Member(String userId, String name) {
        super(userId, name, "MEMBER"); // Panggil konstruktor induk
        this.borrowedBooksCount = 0;
        this.maxBorrowLimit = 3; // Batas default
    }
    
    // Konstruktor dengan batas pinjam khusus
    public Member(String userId, String name, int maxBorrowLimit) {
        super(userId, name, "MEMBER");
        this.borrowedBooksCount = 0;
        this.maxBorrowLimit = maxBorrowLimit;
    }

    // Polimorfisme - mengganti metode abstrak dari kelas induk
    @Override
    public void showMenu() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║            SISTEM PENGELOLAAN DATA BUKU           ║");
        System.out.println("║                 MENU MEMBER                       ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ User: " + String.format("%-20s", name) + " Role: " + String.format("%-12s", getUserRole()) + " ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Lihat Buku Tersedia                            ║");
        System.out.println("║ 2. Pinjam Buku                                    ║");
        System.out.println("║ 3. Kembalikan Buku                                ║");
        System.out.println("║ 4. Cari Buku                                      ║");
        System.out.println("║ 5. Keluar                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    // Metode khusus member
    public boolean borrowBook(Library library, String title) {
        if (borrowedBooksCount >= maxBorrowLimit) {
            System.out.println("Member " + name + " telah mencapai batas maksimal peminjaman!");
            return false;
        }
        
        // Task3 memerlukan borrower ID untuk tracking
        boolean success = library.borrowBook(title, this.getUserId());
        if (success) {
            borrowedBooksCount++;
            System.out.println("Member " + name + " berhasil meminjam: " + title);
            System.out.println("Total buku dipinjam: " + borrowedBooksCount + "/" + maxBorrowLimit);
        }
        return success;
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
        System.out.println("Member " + name + " melihat buku yang tersedia:");
        library.displayAvailableBooks();
    }
    
    // Metode getter untuk enkapsulasi
    public int getBorrowedBooksCount() {
        return borrowedBooksCount;
    }
    
    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }
    
    public boolean canBorrowMore() {
        return borrowedBooksCount < maxBorrowLimit;
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
     * Mengembalikan peran user sebagai MEMBER
     * @return
     */
    @Override
    public UserRole getUserRole() {
        return UserRole.MEMBER;
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
