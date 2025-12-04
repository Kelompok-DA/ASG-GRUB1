/**
 * Kelas Library untuk mengelola buku dan operasi perpustakaan
 * Mendemonstrasikan enkapsulasi dan menyediakan layanan untuk subclass User
 */
class Library {
    private Book[] books;
    private int bookCount;
    private final int capacity;
    private String libraryName;

    public Library(int capacity) {
        this.capacity = capacity;
        this.books = new Book[capacity];
        this.bookCount = 0;
        this.libraryName = "Perpustakaan Digital";
    }
    
    public Library(int capacity, String libraryName) {
        this.capacity = capacity;
        this.books = new Book[capacity];
        this.bookCount = 0;
        this.libraryName = libraryName;
    }

    // Metode tambah buku dengan validasi
    public void addBook(Book book) {
        if (book == null) {
            System.out.println("Error: Buku tidak valid!");
            return;
        }
        
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
            System.out.println("Buku \"" + book.getTitle() + "\" berhasil ditambahkan ke " + libraryName + ".");
        } else {
            System.out.println("Kapasitas perpustakaan penuh! (Maksimal: " + capacity + " buku)");
        }
    }

    // Hapus buku berdasarkan judul dengan implementasi lengkap
    public boolean removeBookByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Judul buku tidak valid!");
            return false;
        }
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                // Geser buku-buku yang tersisa untuk mengisi celah
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null; // Bersihkan slot terakhir
                bookCount--;
                System.out.println("Buku \"" + title + "\" berhasil dihapus dari " + libraryName + ".");
                return true;
            }
        }
        System.out.println("Buku \"" + title + "\" tidak ditemukan di " + libraryName + ".");
        return false;
    }

    // Pinjam buku dengan implementasi lengkap
    public boolean borrowBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Judul buku tidak valid!");
            return false;
        }
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                if (books[i].isAvailable()) {
                    books[i].setAvailable(false);
                    System.out.println("Buku \"" + title + "\" berhasil dipinjam.");
                    return true;
                } else {
                    System.out.println("Buku \"" + title + "\" sedang dipinjam oleh orang lain.");
                    return false;
                }
            }
        }
        System.out.println("Buku \"" + title + "\" tidak ditemukan di " + libraryName + ".");
        return false;
    }

    // Kembalikan buku dengan implementasi lengkap
    public boolean returnBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Judul buku tidak valid!");
            return false;
        }
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                if (!books[i].isAvailable()) {
                    books[i].returnBook();
                    System.out.println("Buku \"" + title + "\" berhasil dikembalikan.");
                    return true;
                } else {
                    System.out.println("Buku \"" + title + "\" tidak sedang dipinjam.");
                    return false;
                }
            }
        }
        System.out.println("Buku \"" + title + "\" tidak ditemukan di " + libraryName + ".");
        return false;
    }
    
    // Tampilkan semua buku
    public void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("Tidak ada buku di " + libraryName + ".");
            return;
        }
        
        System.out.println("\n=== Semua Buku di " + libraryName + " ===");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". " + books[i].getBookInfo());
        }
        System.out.println("Total buku: " + bookCount + "/" + capacity);
    }
    
    // Tampilkan hanya buku yang tersedia
    public void displayAvailableBooks() {
        System.out.println("\n=== Buku Tersedia di " + libraryName + " ===");
        int availableCount = 0;
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                System.out.println((availableCount + 1) + ". " + books[i].getBookInfo());
                availableCount++;
            }
        }
        
        if (availableCount == 0) {
            System.out.println("Tidak ada buku yang tersedia saat ini.");
        } else {
            System.out.println("Total buku tersedia: " + availableCount);
        }
    }
    
    // Cari buku berdasarkan judul atau pengarang
    public void searchBooks(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            System.out.println("Error: Kata kunci pencarian tidak valid!");
            return;
        }
        
        System.out.println("\n=== Hasil Pencarian: \"" + searchTerm + "\" ===");
        int foundCount = 0;
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].matches(searchTerm)) {
                System.out.println((foundCount + 1) + ". " + books[i].getBookInfo());
                foundCount++;
            }
        }
        
        if (foundCount == 0) {
            System.out.println("Tidak ada buku yang ditemukan dengan kata kunci: \"" + searchTerm + "\"");
        } else {
            System.out.println("Ditemukan " + foundCount + " buku yang sesuai.");
        }
    }
    
    // Buat laporan perpustakaan
    public void generateLibraryReport() {
        int availableBooks = 0;
        int borrowedBooks = 0;
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                availableBooks++;
            } else {
                borrowedBooks++;
            }
        }
        
        System.out.println("\n=== Laporan " + libraryName + " ===");
        System.out.println("Total buku: " + bookCount + "/" + capacity);
        System.out.println("Buku tersedia: " + availableBooks);
        System.out.println("Buku dipinjam: " + borrowedBooks);
        System.out.println("Kapasitas tersisa: " + (capacity - bookCount));
    }
    
    // Metode getter
    public int getBookCount() {
        return bookCount;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public String getLibraryName() {
        return libraryName;
    }
}
