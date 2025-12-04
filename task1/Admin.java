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
        System.out.println("=== Menu Admin ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Hapus Buku");
        System.out.println("3. Lihat Semua Buku");
        System.out.println("4. Lihat Statistik Admin");
        System.out.println("Books added by this admin: " + booksAdded);
    }

    // Polimorfisme - mengganti metode abstrak dengan implementasi spesifik
    @Override
    public void interactWithSystem(Library library) {
        System.out.println(name + " (Admin) memiliki akses penuh untuk mengelola perpustakaan.");
        System.out.println("Admin dapat menambah, menghapus, dan melihat semua buku.");
        
        // Mendemonstrasikan kemampuan admin
        System.out.println("Demonstrasi kemampuan admin:");
        addBook(library, new Book("Java Programming", "Oracle"));
        viewAllBooks(library);
    }

    // Metode khusus admin
    public void addBook(Library library, Book book) {
        library.addBook(book);
        booksAdded++;
        System.out.println("Total buku yang ditambahkan admin ini: " + booksAdded);
    }

    public void removeBook(Library library, String title) {
        library.removeBookByTitle(title);
        System.out.println("Admin " + name + " menghapus buku: " + title);
    }
    
    public void viewAllBooks(Library library) {
        System.out.println("Admin " + name + " melihat semua buku di perpustakaan:");
        library.displayAllBooks();
    }
    
    // Metode yang menunjukkan hak istimewa admin
    public void generateReport(Library library) {
        System.out.println("=== Laporan Perpustakaan oleh Admin " + name + " ===");
        library.generateLibraryReport();
    }
}
