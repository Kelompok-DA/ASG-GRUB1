/**
 * Program demonstrasi operasi array untuk manajemen perpustakaan
 * Task 2: Operasi pada Array menggunakan Bahasa pemrograman Java (Bobot 30%)
 * 
 * Fitur yang diimplementasikan:
 * 1. Menambahkan buku baru ke dalam perpustakaan menggunakan array
 * 2. Menghapus buku dari perpustakaan dengan operasi geser array
 * 3. Mencari buku berdasarkan judul menggunakan linear search
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEM PERPUSTAKAAN - OPERASI ARRAY ===");
        System.out.println("Demonstrasi operasi array: tambah, hapus, dan cari buku\n");
        
        // Inisialisasi perpustakaan dengan kapasitas array
        Library perpustakaan = new Library(10);
        
        // Demonstrasi 1: MENAMBAH BUKU (Array Operations)
        System.out.println("=== 1. OPERASI MENAMBAH BUKU KE ARRAY ===");
        demonstrasiTambahBuku(perpustakaan);
        
        // Demonstrasi 2: MENCARI BUKU (Linear Search)
        System.out.println("\n=== 2. OPERASI PENCARIAN BUKU DALAM ARRAY ===");
        demonstrasiCariBuku(perpustakaan);
        
        // Demonstrasi 3: MENGHAPUS BUKU (Array Shifting)
        System.out.println("\n=== 3. OPERASI MENGHAPUS BUKU DARI ARRAY ===");
        demonstrasiHapusBuku(perpustakaan);
        
        // Status akhir array
        System.out.println("\n=== STATUS AKHIR ARRAY PERPUSTAKAAN ===");
        perpustakaan.showAllBooks();
        
        // Demonstrasi operasi array tambahan
        System.out.println("\n=== DEMONSTRASI OPERASI ARRAY LANJUTAN ===");
        demonstrasiOperasiLanjutan(perpustakaan);
    }
    
    /**
     * Demonstrasi operasi menambah buku ke dalam array
     * Menunjukkan bagaimana elemen baru ditambahkan ke akhir array
     */
    private static void demonstrasiTambahBuku(Library perpustakaan) {
        System.out.println("Menambahkan buku-buku ke dalam array perpustakaan:");
        
        // Tambah beberapa buku untuk demonstrasi array operations
        Book buku1 = new Book("Algoritma dan Struktur Data", "Thomas Cormen");
        Book buku2 = new Book("Pemrograman Java", "James Gosling");  
        Book buku3 = new Book("Basis Data", "Ramez Elmasri");
        Book buku4 = new Book("Jaringan Komputer", "Andrew Tanenbaum");
        Book buku5 = new Book("Sistem Operasi", "Abraham Silberschatz");
        
        System.out.println("\n--- Proses penambahan ke array ---");
        perpustakaan.addBook(buku1);
        perpustakaan.addBook(buku2);
        perpustakaan.addBook(buku3);
        perpustakaan.addBook(buku4);
        perpustakaan.addBook(buku5);
        
        System.out.println("\n--- Status array setelah penambahan ---");
        perpustakaan.showAllBooks();
    }
    
    /**
     * Demonstrasi operasi pencarian buku dalam array
     * Menggunakan linear search untuk mencari berdasarkan judul
     */
    private static void demonstrasiCariBuku(Library perpustakaan) {
        System.out.println("Mencari buku dalam array menggunakan linear search:");
        
        // Test pencarian buku yang ada
        System.out.println("\n--- Pencarian buku yang ADA dalam array ---");
        String judulCari1 = "Pemrograman Java";
        Book hasil1 = perpustakaan.searchBook(judulCari1);
        
        if (hasil1 != null) {
            System.out.println("✓ DITEMUKAN!");
            System.out.println("  Judul: " + hasil1.getTitle());
            System.out.println("  Pengarang: " + hasil1.getAuthor());
            System.out.println("  Status: " + (hasil1.isAvailable() ? "Tersedia" : "Dipinjam"));
        } else {
            System.out.println("✗ Buku tidak ditemukan");
        }
        
        // Test pencarian buku yang tidak ada
        System.out.println("\n--- Pencarian buku yang TIDAK ADA dalam array ---");
        String judulCari2 = "Machine Learning";
        Book hasil2 = perpustakaan.searchBook(judulCari2);
        
        if (hasil2 != null) {
            System.out.println("✓ DITEMUKAN!");
            System.out.println("  Judul: " + hasil2.getTitle());
            System.out.println("  Pengarang: " + hasil2.getAuthor());
        } else {
            System.out.println("✗ Buku '" + judulCari2 + "' tidak ditemukan dalam array");
        }
        
        // Test pencarian case-insensitive
        System.out.println("\n--- Test pencarian case-insensitive ---");
        String judulCari3 = "basis data"; // lowercase
        Book hasil3 = perpustakaan.searchBook(judulCari3);
        
        if (hasil3 != null) {
            System.out.println("✓ DITEMUKAN! (case-insensitive search berhasil)");
            System.out.println("  Judul asli: " + hasil3.getTitle());
            System.out.println("  Kata kunci: " + judulCari3);
        } else {
            System.out.println("✗ Buku tidak ditemukan");
        }
    }
    
    /**
     * Demonstrasi operasi menghapus buku dari array
     * Menunjukkan proses array shifting untuk menghilangkan gap
     */
    private static void demonstrasiHapusBuku(Library perpustakaan) {
        System.out.println("Menghapus buku dari array dengan array shifting:");
        
        System.out.println("\n--- Status array SEBELUM penghapusan ---");
        perpustakaan.showAllBooks();
        
        // Hapus buku dari tengah array
        System.out.println("\n--- Menghapus buku dari TENGAH array ---");
        String bukuHapus1 = "Basis Data";
        System.out.println("Menghapus: " + bukuHapus1);
        perpustakaan.removeBookByTitle(bukuHapus1);
        
        System.out.println("Status array setelah penghapusan:");
        perpustakaan.showAllBooks();
        
        // Hapus buku dari awal array  
        System.out.println("\n--- Menghapus buku dari AWAL array ---");
        String bukuHapus2 = "Algoritma dan Struktur Data";
        System.out.println("Menghapus: " + bukuHapus2);
        perpustakaan.removeBookByTitle(bukuHapus2);
        
        System.out.println("Status array setelah penghapusan:");
        perpustakaan.showAllBooks();
        
        // Test hapus buku yang tidak ada
        System.out.println("\n--- Test menghapus buku yang TIDAK ADA ---");
        String bukuHapus3 = "Buku Tidak Ada";
        System.out.println("Mencoba menghapus: " + bukuHapus3);
        perpustakaan.removeBookByTitle(bukuHapus3);
    }
    
    /**
     * Demonstrasi operasi array lanjutan dan edge cases
     */
    private static void demonstrasiOperasiLanjutan(Library perpustakaan) {
        System.out.println("Demonstrasi operasi array lanjutan:");
        
        // Test penambahan hingga kapasitas penuh
        System.out.println("\n--- Test kapasitas maksimal array ---");
        System.out.println("Menambah buku hingga mendekati kapasitas maksimal...");
        
        // Tambah lebih banyak buku
        perpustakaan.addBook(new Book("Artificial Intelligence", "Stuart Russell"));
        perpustakaan.addBook(new Book("Data Mining", "Jiawei Han"));
        perpustakaan.addBook(new Book("Computer Graphics", "Donald Hearn"));
        perpustakaan.addBook(new Book("Software Engineering", "Ian Sommerville"));
        perpustakaan.addBook(new Book("Computer Architecture", "John Hennessy"));
        perpustakaan.addBook(new Book("Database Systems", "Raghu Ramakrishnan"));
        perpustakaan.addBook(new Book("Web Programming", "Deitel"));
        
        // Coba tambah buku ketika array penuh
        perpustakaan.addBook(new Book("Extra Book", "Extra Author"));
        
        System.out.println("\n--- Status final array ---");
        perpustakaan.showAllBooks();
        
        // Demonstrasi pencarian multiple
        System.out.println("\n--- Pencarian beberapa buku sekaligus ---");
        String[] judulCari = {
            "Artificial Intelligence",
            "Buku Tidak Ada", 
            "Software Engineering",
            "pemrograman java" // test case sensitivity
        };
        
        for (String judul : judulCari) {
            System.out.print("Mencari '" + judul + "': ");
            Book hasil = perpustakaan.searchBook(judul);
            if (hasil != null) {
                System.out.println("✓ DITEMUKAN - " + hasil.getTitle());
            } else {
                System.out.println("✗ TIDAK DITEMUKAN");
            }
        }
        
        // Summary operasi array
        System.out.println("\n=== RINGKASAN OPERASI ARRAY ===");
        System.out.println("✓ Penambahan elemen ke array (addBook)");
        System.out.println("✓ Pencarian linear dalam array (searchBook)");
        System.out.println("✓ Penghapusan dengan array shifting (removeBookByTitle)");
        System.out.println("✓ Penanganan array penuh (capacity management)");
        System.out.println("✓ Pencarian case-insensitive");
        System.out.println("✓ Penanganan elemen tidak ditemukan");
    }
}