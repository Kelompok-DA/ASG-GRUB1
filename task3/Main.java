import java.util.Scanner;

/**
 * Sistem Pengelolaan Data Buku dengan Array - Interactive System
 * Task 3: Pengelolaan Data Buku (Bobot 30%)
 * 
 * Sistem yang berjalan terus menerus dengan menu interaktif:
 * 1. Array untuk menyimpan data buku dengan atribut: judul, pengarang, status ketersediaan
 * 2. Menampilkan semua buku yang tersedia
 * 3. Meminjam dan mengembalikan buku dengan update status dalam array
 */
public class Main {
    
    private static Library perpustakaan;
    private static Scanner scanner;
    private static String currentUserId = "";
    private static String currentUserName = "";
    
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("     SISTEM PENGELOLAAN DATA BUKU - INTERACTIVE MODE");
        System.out.println("=======================================================");
        System.out.println("Task 3: Pengelolaan Data Buku dengan Array (Bobot 30%)\n");
        
        // Inisialisasi sistem
        initializeSystem();
        
        // Login user
        loginUser();
        
        // Main menu loop - sistem berjalan terus menerus
        runMainMenuLoop();
    }
    
    /**
     * Inisialisasi sistem perpustakaan
     */
    private static void initializeSystem() {
        scanner = new Scanner(System.in);
        perpustakaan = new Library(15, "Perpustakaan Digital ITB");
        
        System.out.println("=== INISIALISASI SISTEM ===");
        initializeBooksArray();
        System.out.println("✓ Sistem berhasil diinisialisasi!\n");
    }
    
    /**
     * Login user ke dalam sistem
     */
    private static void loginUser() {
        System.out.println("=== LOGIN PENGGUNA ===");
        System.out.print("Masukkan ID Pengguna: ");
        currentUserId = scanner.nextLine().trim();
        
        if (currentUserId.isEmpty()) {
            currentUserId = "USER001"; // Default user
        }
        
        System.out.print("Masukkan Nama Anda: ");
        currentUserName = scanner.nextLine().trim();
        
        if (currentUserName.isEmpty()) {
            currentUserName = "Pengguna"; // Default name
        }
        
        System.out.println("✓ Login berhasil!");
        System.out.println("Selamat datang, " + currentUserName + " (ID: " + currentUserId + ")\n");
    }
    
    /**
     * Loop menu utama sistem - berjalan terus menerus
     */
    private static void runMainMenuLoop() {
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            
            System.out.print("Pilih menu (1-6): ");
            String choice = scanner.nextLine().trim();
            
            System.out.println(); // Line break
            
            switch (choice) {
                case "1":
                    handleDisplayAvailableBooks();
                    break;
                case "2":
                    handleDisplayAllBooks();
                    break;
                case "3":
                    handleBorrowBook();
                    break;
                case "4":
                    handleReturnBook();
                    break;
                case "5":
                    handleViewStatistics();
                    break;
                case "6":
                    running = handleExit();
                    break;
                default:
                    System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-6.");
            }
            
            if (running) {
                System.out.println("\nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
                clearScreen();
            }
        }
    }
    
    /**
     * Menampilkan menu utama sistem
     */
    private static void displayMainMenu() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║            SISTEM PENGELOLAAN DATA BUKU           ║");
        System.out.println("║                    MENU UTAMA                     ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ User: " + String.format("%-20s", currentUserName) + " ID: " + String.format("%-10s", currentUserId) + " ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Lihat Buku Tersedia                            ║");
        System.out.println("║ 2. Lihat Semua Buku                               ║");
        System.out.println("║ 3. Pinjam Buku                                    ║");
        System.out.println("║ 4. Kembalikan Buku                                ║");
        System.out.println("║ 5. Lihat Statistik Perpustakaan                   ║");
        System.out.println("║ 6. Keluar                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
    
    /**
     * Clear screen untuk tampilan yang bersih
     */
    private static void clearScreen() {
        // Print multiple newlines to simulate clear screen
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
    
    /**
     * Handle menu 1: Lihat Buku Tersedia
     */
    private static void handleDisplayAvailableBooks() {
        System.out.println("=== MENU 1: LIHAT BUKU TERSEDIA ===");
        perpustakaan.displayAvailableBooks();
    }
    
    /**
     * Handle menu 2: Lihat Semua Buku
     */
    private static void handleDisplayAllBooks() {
        System.out.println("=== MENU 2: LIHAT SEMUA BUKU ===");
        perpustakaan.displayAllBooks();
    }
    
    /**
     * Handle menu 3: Pinjam Buku
     */
    private static void handleBorrowBook() {
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
            System.out.println("🎉 Selamat! Anda berhasil meminjam buku.");
        } else {
            System.out.println("😞 Maaf, peminjaman buku gagal.");
        }
    }
    
    /**
     * Handle menu 4: Kembalikan Buku
     */
    private static void handleReturnBook() {
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
            System.out.println("🎉 Terima kasih! Buku berhasil dikembalikan.");
        } else {
            System.out.println("😞 Maaf, pengembalian buku gagal.");
        }
    }
    
    /**
     * Handle menu 5: Lihat Statistik
     */
    private static void handleViewStatistics() {
        System.out.println("=== MENU 5: STATISTIK PERPUSTAKAAN ===");
        perpustakaan.displayLibraryStatistics();
    }
    
    /**
     * Handle menu 6: Keluar dari sistem
     */
    private static boolean handleExit() {
        System.out.println("=== KELUAR DARI SISTEM ===");
        System.out.print("Apakah Anda yakin ingin keluar? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("y") || confirm.equals("yes")) {
            System.out.println("\n" + "=".repeat(55));
            System.out.println("Terima kasih telah menggunakan Sistem Pengelolaan Buku!");
            System.out.println("User: " + currentUserName + " (ID: " + currentUserId + ")");
            System.out.println("Sampai jumpa lagi! 👋");
            System.out.println("=".repeat(55));
            
            scanner.close();
            return false; // Exit the loop
        } else {
            System.out.println("✓ Kembali ke menu utama...");
            return true; // Continue the loop
        }
    }
    
    /**
     * Inisialisasi array buku dengan data lengkap (judul, pengarang, status)
     */
    private static void initializeBooksArray() {
        System.out.println("Mengisi array dengan data buku...");
        
        // Buat objek buku dengan atribut lengkap
        Book[] initialBooks = {
            new Book("Algoritma dan Pemrograman", "Rinaldi Munir"),
            new Book("Struktur Data dengan Java", "Robert Sedgewick"),
            new Book("Basis Data Fundamental", "Ramez Elmasri"),
            new Book("Jaringan Komputer Modern", "Andrew Tanenbaum"),
            new Book("Sistem Operasi Konsep", "Abraham Silberschatz"),
            new Book("Pemrograman Berorientasi Objek", "James Gosling"),
            new Book("Kecerdasan Buatan", "Stuart Russell"),
            new Book("Rekayasa Perangkat Lunak", "Ian Sommerville"),
            new Book("Machine Learning", "Tom Mitchell"),
            new Book("Computer Networks", "Kurose & Ross")
        };
        
        // Tambahkan setiap buku ke array
        for (Book book : initialBooks) {
            perpustakaan.addBook(book);
        }
        
        System.out.println("✓ Array buku berhasil diinisialisasi dengan " + initialBooks.length + " buku!");
    }
}