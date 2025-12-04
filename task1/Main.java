/**
 * Kelas Main untuk mendemonstrasikan konsep Object-Oriented Programming:
 * 1. Inheritance: User -> Admin, Member
 * 2. Polimorfisme: Implementasi berbeda dari metode abstrak
 * 3. Enkapsulasi: Field private dengan metode public
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstrasi Konsep OOP: Inheritance & Polymorphism\n");
        
        // Buat instance perpustakaan
        Library library = new Library(100, "Perpustakaan Universitas");
        
        // Tambahkan beberapa buku awal
        initializeLibrary(library);
        
        // Buat pengguna menggunakan polimorfisme - referensi User ke subclass yang berbeda
        User admin1 = new Admin("A001", "Budi Santoso");
        User admin2 = new Admin("A002", "Sari Wijaya"); 
        User member1 = new Member("M001", "Ahmad Rizki");
        User member2 = new Member("M002", "Diana Putri", 5); // Batas pinjam khusus
        User member3 = new Member("M003", "Reza Fahmi");
        
        // Simpan semua pengguna dalam array - mendemonstrasikan polimorfisme
        User[] users = { admin1, admin2, member1, member2, member3 };
        
        System.out.println("=== DEMONSTRASI POLYMORPHISM ===");
        System.out.println("Semua objek dalam array 'users' diperlakukan sama,");
        System.out.println("tetapi setiap objek menjalankan method sesuai kelasnya:\n");
        
        // Demonstrasikan polimorfisme - panggilan metode yang sama, perilaku yang berbeda
        for (User user : users) {
            // Setiap tipe pengguna akan menjalankan versi performUserAction mereka sendiri
            user.performUserAction(library);
        }
        
        System.out.println("=== DEMONSTRASI SPECIFIC FUNCTIONALITY ===");
        
        // Demonstrasikan fungsi khusus admin
        demonstrateAdminFeatures(library, (Admin) admin1);
        
        // Demonstrasikan fungsi khusus member
        demonstrateMemberFeatures(library, (Member) member1, (Member) member2);
        
        // Status akhir perpustakaan
        System.out.println("=== STATUS AKHIR PERPUSTAKAAN ===");
        library.generateLibraryReport();
        library.displayAllBooks();
    }
    
    /**
     * Inisialisasi perpustakaan dengan beberapa buku
     */
    private static void initializeLibrary(Library library) {
        System.out.println("Menginisialisasi perpustakaan dengan buku-buku...\n");
        
        library.addBook(new Book("Pemrograman Java", "James Gosling"));
        library.addBook(new Book("Struktur Data", "Robert Sedgewick"));
        library.addBook(new Book("Basis Data", "Ramez Elmasri"));
        library.addBook(new Book("Jaringan Komputer", "Andrew Tanenbaum"));
        library.addBook(new Book("Algoritma", "Thomas Cormen"));
    }
    
    /**
     * Demonstrasikan fitur khusus admin
     */
    private static void demonstrateAdminFeatures(Library library, Admin admin) {
        System.out.println("=== DEMONSTRASI FITUR ADMIN ===");
        
        // Tambah buku baru
        admin.addBook(library, new Book("Machine Learning", "Tom Mitchell"));
        admin.addBook(library, new Book("Artificial Intelligence", "Stuart Russell"));
        
        // Buat laporan
        admin.generateReport(library);
        
        // Hapus sebuah buku
        admin.removeBook(library, "Algoritma");
        
        System.out.println();
    }
    
    /**
     * Demonstrasikan fitur khusus member
     */
    private static void demonstrateMemberFeatures(Library library, Member member1, Member member2) {
        System.out.println("=== DEMONSTRASI FITUR MEMBER ===");
        
        // Member 1 meminjam buku
        System.out.println("Member 1 meminjam buku:");
        member1.borrowBook(library, "Pemrograman Java");
        member1.borrowBook(library, "Struktur Data");
        
        // Member 2 mencoba meminjam buku yang sama
        System.out.println("\nMember 2 mencoba meminjam buku yang sama:");
        member2.borrowBook(library, "Pemrograman Java"); // Seharusnya gagal - sudah dipinjam
        member2.borrowBook(library, "Basis Data"); // Seharusnya berhasil
        
        // Tampilkan status peminjaman
        System.out.println("\nStatus peminjaman:");
        System.out.println("Member 1 - Buku dipinjam: " + member1.getBorrowedBooksCount() + "/" + member1.getMaxBorrowLimit());
        System.out.println("Member 2 - Buku dipinjam: " + member2.getBorrowedBooksCount() + "/" + member2.getMaxBorrowLimit());
        
        // Kembalikan sebuah buku
        System.out.println("\nMember 1 mengembalikan buku:");
        member1.returnBook(library, "Struktur Data");
        
        // Sekarang member 2 bisa meminjamnya
        System.out.println("\nSekarang Member 2 bisa meminjam buku yang dikembalikan:");
        member2.borrowBook(library, "Struktur Data");
        
        System.out.println();
    }
}
