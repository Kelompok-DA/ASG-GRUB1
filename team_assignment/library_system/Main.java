package library_system;

import java.util.Scanner;

/**
 * Sistem Pengelolaan Data Buku dengan Array - Interactive System
 * Task 3: Pengelolaan Data Buku
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
    private static User currentUser = null;
    
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("     SISTEM PENGELOLAAN DATA BUKU - INTERACTIVE MODE");
        System.out.println("=======================================================");
        System.out.println("Task 3: Pengelolaan Data Buku dengan Array\n");
        
        // Inisialisasi sistem
        initializeSystem();
        
        // Login user
        loginUser();
        
        // library_system.Main menu loop - sistem berjalan terus menerus
        runMainMenuLoop();
    }
    
    /**
     * Inisialisasi sistem perpustakaan
     */
    private static void initializeSystem() {
        scanner = new Scanner(System.in);
        perpustakaan = new Library(15, "Perpustakaan Digital BINUS");
        
        System.out.println("=== INISIALISASI SISTEM ===");
        initializeBooksArray();
        System.out.println("✓ Sistem berhasil diinisialisasi!\n");
    }

    /**
     * Login user ke dalam sistem
     */
    private static void loginUser() {
        System.out.println("=== LOGIN PENGGUNA ===");

        // pilih peran user
        UserRole selectedRole = selectRole();

        // get ID dan nama user
        System.out.print("Masukkan ID Pengguna: ");
        currentUserId = scanner.nextLine().trim();
        if (currentUserId.isEmpty()) {
            currentUserId = "USER001";
        }

        System.out.print("Masukkan Nama Anda: ");
        currentUserName = scanner.nextLine().trim();
        if (currentUserName.isEmpty()) {
            currentUserName = "Pengguna";
        }

        // membuat object user sesuai peran setelah data diinput
        createUserByRole(selectedRole);

        System.out.println("✓ Login berhasil!");
        System.out.println("Selamat datang, " + currentUserName + " (ID: " + currentUserId + ")\n");

        currentUser.showMenu();
    }

    /**
     * Memilih peran user: library_system.Admin atau library_system.Member
     */
    private static UserRole selectRole() {
        System.out.println("Pilih peran Anda:");
        System.out.println("1. library_system.Admin");
        System.out.println("2. library_system.Member");
        System.out.print("Masukkan pilihan (1-2): ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                System.out.println("Anda login sebagai library_system.Admin...\n");
                return UserRole.ADMIN;
            case "2":
                System.out.println("Anda login sebagai library_system.Member...\n");
                return UserRole.MEMBER;
            default:
                System.out.println("Pilihan tidak valid. Menggunakan peran library_system.Member sebagai default.\n");
                return UserRole.MEMBER;
        }
    }

    /**
     * Membuat objek user berdasarkan peran yang dipilih
     * @param role
     */
    private static void createUserByRole(UserRole role) {
        if (role == UserRole.ADMIN) {
            currentUser = new Admin(currentUserId, currentUserName);
        } else {
            currentUser = new Member(currentUserId, currentUserName);
        }
    }

    /**
     * Loop menu utama sistem - berjalan terus menerus
     */
    private static void runMainMenuLoop() {
        boolean running = true;
        
        while (running) {
            //displayMainMenu();
            int totalOptions = currentUser.getTotalMenuOptions() + 1; // +1 untuk opsi keluar
            System.out.print("Pilih menu (1-" + totalOptions + "): ");

            String choice = scanner.nextLine().trim();
            
            System.out.println(); // Line break
            
            switch (choice) {
                case "1":
                    currentUser.handleMenuOption1(perpustakaan, scanner);
                    break;
                case "2":
                    currentUser.handleMenuOption2(perpustakaan, scanner);
                    break;
                case "3":
                    currentUser.handleMenuOption3(perpustakaan, scanner);
                    break;
                case "4":
                    perpustakaan.findBookByTitle(scanner);
                    break;
                case "5":
                    if (currentUser.getUserType().equals(UserRole.MEMBER.name())) {
                        running = handleExit();
                        break;
                    } else {
                        handleViewStatistics();
                    }
                    break;
                case "6":
                    if (currentUser.getUserType().equals(UserRole.ADMIN.name())) {
                        running = handleExit();
                        break;
                    }
                    System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-" + totalOptions + ".");
                    break;
                default:
                    System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-" + totalOptions + ".");
            }
            
            if (running) {
                System.out.println("\nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
                clearScreen();
            }
        }
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
            System.out.println("library_system.User: " + currentUserName + " (ID: " + currentUserId + ")");
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