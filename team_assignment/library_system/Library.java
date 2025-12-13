package library_system;

import java.util.Scanner;

/**
 * Kelas library_system.Library untuk pengelolaan data buku dengan array
 * Task 3: Pengelolaan Data Buku (Bobot 30%)
 * 
 * Fitur:
 * - Array untuk menyimpan data buku dengan atribut lengkap
 * - Menampilkan semua buku yang tersedia
 * - Meminjam dan mengembalikan buku dengan update status
 */
class Library {
    private Book[] books;           // Array untuk menyimpan data buku
    private int bookCount;          // Jumlah buku dalam array
    private final int capacity;     // Kapasitas maksimal array
    private String libraryName;     // Nama perpustakaan
    
    // Konstruktor untuk inisialisasi array buku
    public Library(int capacity, String libraryName) {
        this.capacity = capacity;
        this.books = new Book[capacity];
        this.bookCount = 0;
        this.libraryName = libraryName;
        System.out.println("Perpustakaan " + libraryName + " dibuat dengan kapasitas " + capacity + " buku.");
    }
    
    // Metode untuk menambah buku ke array
    public boolean addBook(Book book) {
        if (book == null) {
            System.out.println("Error: Data buku tidak valid!");
            return false;
        }
        
        if (bookCount < capacity) {
            books[bookCount] = book;
            bookCount++;
            System.out.println("✓ Buku \"" + book.getTitle() + "\" berhasil ditambahkan ke array.");
            return true;
        } else {
            System.out.println("✗ Kapasitas array penuh! Tidak dapat menambah buku.");
            return false;
        }
    }

    public void removeBookByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {

                // geser array ke kiri
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }

                books[bookCount - 1] = null;
                bookCount--;

                System.out.println("Buku \"" + title + "\" berhasil dihapus.");
                return;
            }
        }
        System.out.println("Buku \"" + title + "\" tidak ditemukan.");
    }
    
    /**
     * FITUR UTAMA 1: Menampilkan semua buku yang tersedia
     * Iterasi array dan filter berdasarkan status ketersediaan
     */
    public void displayAvailableBooks() {
        System.out.println("\n=== BUKU-BUKU YANG TERSEDIA ===");
        System.out.println("Perpustakaan: " + libraryName);
        
        if (bookCount == 0) {
            System.out.println("Tidak ada buku dalam perpustakaan.");
            return;
        }
        
        int availableCount = 0;
        System.out.println("No. | Judul | Pengarang | Status");
        System.out.println("----+-------+-----------+--------");
        
        // Iterasi array untuk mencari buku yang tersedia
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {  // Filter buku yang tersedia
                availableCount++;
                System.out.printf("%2d. | %-20s | %-15s | TERSEDIA\n", 
                    availableCount, 
                    books[i].getTitle(), 
                    books[i].getAuthor());
            }
        }
        
        if (availableCount == 0) {
            System.out.println("Semua buku sedang dipinjam.");
        } else {
            System.out.println("\nTotal buku tersedia: " + availableCount + " dari " + bookCount + " buku");
        }
    }
    
    /**
     * FITUR UTAMA 2: Meminjam buku dengan update status dalam array
     * Mencari buku dalam array dan mengubah status ketersediaan
     */
    public boolean borrowBook(String title, String borrowerId) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("✗ Error: Judul buku tidak valid!");
            return false;
        }
        
        if (borrowerId == null || borrowerId.trim().isEmpty()) {
            System.out.println("✗ Error: ID peminjam tidak valid!");
            return false;
        }
        
        System.out.println("\n--- PROSES MEMINJAM BUKU ---");
        System.out.println("Mencari buku: \"" + title + "\" dalam array...");
        
        // Linear search dalam array untuk mencari buku
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                System.out.println("✓ Buku ditemukan di index: " + i);
                
                // Cek status ketersediaan
                if (books[i].isAvailable()) {
                    // Update status buku dalam array
                    books[i].setBorrowedBy(borrowerId);  // Set peminjam
                    books[i].setAvailable(false);       // Set status tidak tersedia
                    
                    System.out.println("✓ BERHASIL MEMINJAM!");
                    System.out.println("  Buku: " + books[i].getTitle());
                    System.out.println("  Pengarang: " + books[i].getAuthor());
                    System.out.println("  Dipinjam oleh: " + borrowerId);
                    System.out.println("  Status dalam array: " + (books[i].isAvailable() ? "Tersedia" : "Dipinjam"));
                    return true;
                } else {
                    System.out.println("✗ GAGAL MEMINJAM!");
                    System.out.println("  Buku sudah dipinjam oleh: " + books[i].getBorrowedBy());
                    return false;
                }
            }
        }
        
        System.out.println("✗ Buku \"" + title + "\" tidak ditemukan dalam array.");
        return false;
    }
    
    /**
     * FITUR UTAMA 3: Mengembalikan buku dengan update status dalam array
     * Mencari buku yang dipinjam dan mengubah status kembali ke tersedia
     */
    public boolean returnBook(String title, String returnerId) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("✗ Error: Judul buku tidak valid!");
            return false;
        }
        
        if (returnerId == null || returnerId.trim().isEmpty()) {
            System.out.println("✗ Error: ID pengembalian tidak valid!");
            return false;
        }
        
        System.out.println("\n--- PROSES MENGEMBALIKAN BUKU ---");
        System.out.println("Mencari buku: \"" + title + "\" dalam array...");
        
        // Linear search dalam array untuk mencari buku
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                System.out.println("✓ Buku ditemukan di index: " + i);
                
                // Cek status peminjaman
                if (!books[i].isAvailable()) {
                    // Validasi peminjam yang benar
                    if (books[i].getBorrowedBy().equals(returnerId)) {
                        // Update status buku dalam array
                        books[i].returnBook();  // Reset status ke tersedia
                        
                        System.out.println("✓ BERHASIL MENGEMBALIKAN!");
                        System.out.println("  Buku: " + books[i].getTitle());
                        System.out.println("  Pengarang: " + books[i].getAuthor());
                        System.out.println("  Dikembalikan oleh: " + returnerId);
                        System.out.println("  Status dalam array: " + (books[i].isAvailable() ? "Tersedia" : "Dipinjam"));
                        return true;
                    } else {
                        System.out.println("✗ GAGAL MENGEMBALIKAN!");
                        System.out.println("  Buku dipinjam oleh: " + books[i].getBorrowedBy());
                        System.out.println("  Hanya peminjam yang bisa mengembalikan buku.");
                        return false;
                    }
                } else {
                    System.out.println("✗ GAGAL MENGEMBALIKAN!");
                    System.out.println("  Buku sedang tidak dipinjam (sudah tersedia).");
                    return false;
                }
            }
        }
        
        System.out.println("✗ Buku \"" + title + "\" tidak ditemukan dalam array.");
        return false;
    }
    
    /**
     * Menampilkan semua buku (tersedia dan dipinjam) dengan detail status
     */
    public void displayAllBooks() {
        System.out.println("\n=== SEMUA BUKU DALAM ARRAY ===");
        System.out.println("Perpustakaan: " + libraryName);
        
        if (bookCount == 0) {
            System.out.println("Array buku masih kosong.");
            return;
        }
        
        System.out.println("No. | Judul | Pengarang | Status | Peminjam");
        System.out.println("----+-------+-----------+--------+----------");
        
        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            String status = book.isAvailable() ? "TERSEDIA" : "DIPINJAM";
            String borrower = book.isAvailable() ? "-" : book.getBorrowedBy();
            
            System.out.printf("%2d. | %-20s | %-15s | %-8s | %s\n", 
                (i + 1), 
                book.getTitle(), 
                book.getAuthor(), 
                status, 
                borrower);
        }
        
        System.out.println("\nTotal buku dalam array: " + bookCount + "/" + capacity);
    }
    
    /**
     * Menampilkan statistik perpustakaan dari data array
     */
    public void displayLibraryStatistics() {
        int availableBooks = 0;
        int borrowedBooks = 0;
        
        // Hitung statistik dari array
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                availableBooks++;
            } else {
                borrowedBooks++;
            }
        }
        
        System.out.println("\n=== STATISTIK PERPUSTAKAAN ===");
        System.out.println("Nama: " + libraryName);
        System.out.println("Kapasitas Array: " + capacity);
        System.out.println("Total Buku: " + bookCount);
        System.out.println("Buku Tersedia: " + availableBooks);
        System.out.println("Buku Dipinjam: " + borrowedBooks);
        System.out.println("Slot Kosong: " + (capacity - bookCount));
        
        if (bookCount > 0) {
            double percentage = (double) availableBooks / bookCount * 100;
            System.out.printf("Persentase Ketersediaan: %.1f%%\n", percentage);
        }
    }
    
    /**
     * Mencari buku berdasarkan judul dalam array
     */
    public void findBookByTitle(Scanner scanner) {
        System.out.print("Masukkan judul buku yang dicari: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Judul buku tidak boleh kosong!");
        }
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                System.out.println("✓ Buku ditemukan:");
                System.out.println("Buku      : " + books[i].getTitle());
                System.out.println("Pengarang : " + books[i].getAuthor());
                System.out.println("Status    : " +
                        (books[i].isAvailable() ?
                                "Tersedia" :
                                "Dipinjam oleh " + books[i].getBorrowedBy())
                );
                return;
            }
        }
    }
}