import java.util.Scanner;

/**
 * Kelas Admin - kelas turunan dari User
 * Mendemonstrasikan inheritance dan polimorfisme
 */
class Admin extends User {
    private int booksAdded;
    
    public Admin(String userId, String name) {
        super(userId, name, "ADMIN"); // Panggil konstruktor induk
        this.booksAdded = 0;
    }

    // Polimorfisme - mengganti metode abstrak dari kelas induk
    @Override
    public void showMenu() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║            SISTEM PENGELOLAAN DATA BUKU           ║");
        System.out.println("║                  MENU ADMIN                       ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ User: " + String.format("%-20s", getName()) + " Role: " + String.format("%-12s", getUserRole()) + " ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Lihat Semua Buku                               ║");
        System.out.println("║ 2. Tambah Buku                                    ║");
        System.out.println("║ 3. Hapus Buku                                     ║");
        System.out.println("║ 4. Cari Buku                                      ║");
        System.out.println("║ 5. Lihat Laporan Perpustakaan                     ║");
        System.out.println("║ 6. Keluar                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }


    // Metode khusus admin
    public void addBook(Library library, Book book) {
        library.addBook(book);
        booksAdded++;
        System.out.println("Total buku yang ditambahkan admin ini: " + booksAdded);
    }

    
    public void viewAllBooks(Library library) {
        System.out.println("Admin " + name + " melihat semua buku di perpustakaan:");
        library.displayAllBooks();
    }
    
    // Metode yang menunjukkan hak istimewa admin
    public void generateReport(Library library) {
        System.out.println("=== Laporan Perpustakaan oleh Admin " + name + " ===");
        library.displayLibraryStatistics();
    }

    /**
     * Mengembalikan total opsi menu untuk admin
     * @return
     */
    @Override
    public int getTotalMenuOptions() {
        return 5;
    }

    /**
     * Mengembalikan peran user sebagai ADMIN
     * @return
     */
    @Override
    public UserRole getUserRole() {
        return UserRole.ADMIN;
    }

    /**
     * Polimorfisme - implementasi metode abstrak untuk menangani opsi menu admin
     * Melihat semua buku dan statusnya
     * @param library
     * @param scanner
     */
    @Override
    public void handleMenuOption1(Library library, Scanner scanner) {
        System.out.println("=== MENU 1: LIHAT SEMUA BUKU (ADMIN) ===");
        this.viewAllBooks(library);
    }

    /**
     * Polimorfisme - implementasi metode abstrak untuk menangani opsi menu admin
     * Menambahkan buku baru
     * @param library
     * @param scanner
     */
    @Override
    public void handleMenuOption2(Library library, Scanner scanner) {
        System.out.println("=== MENU 2: TAMBAH BUKU (ADMIN) ===");
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Judul buku tidak boleh kosong!");
            return;
        }
        System.out.print("Masukkan nama pengarang: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("❌ Nama pengarang tidak boleh kosong!");
            return;
        }
        Book newBook = new Book(title, author);
        this.addBook(library, newBook);
    }

    /**
     * Polimorfisme - implementasi metode abstrak untuk menangani opsi menu admin
     * Menghapus buku berdasarkan judul
     * @param library
     * @param scanner
     */
    @Override
    public void handleMenuOption3(Library library, Scanner scanner) {
        System.out.println("=== MENU 3: HAPUS BUKU (ADMIN) ===");
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Judul buku tidak boleh kosong!");
            return;
        }

        library.removeBookByTitle(title);
    }
}
