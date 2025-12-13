package library_system;

/**
 * Kelas library_system.Book merepresentasikan buku dalam perpustakaan
 * Mendemonstrasikan enkapsulasi dengan field private dan metode public
 */
class Book {
    private String title;
    private String author;
    private boolean available;
    private String bookId;
    private String borrowedBy;

    public Book(String title, String author) {
        if (title == null || author == null) {
            throw new IllegalArgumentException("Title and author cannot be null");
        }
        this.title = title;
        this.author = author;
        this.available = true;
        this.bookId = generateBookId();
        this.borrowedBy = null;
    }
    
    // Konstruktor dengan ID buku
    public Book(String bookId, String title, String author) {
        if (bookId == null || title == null || author == null) {
            throw new IllegalArgumentException("library_system.Book data cannot be null");
        }
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
        this.borrowedBy = null;
    }

    // Metode getter (enkapsulasi)
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }
    
    public String getBookId() {
        return bookId;
    }
    
    public String getBorrowedBy() {
        return borrowedBy;
    }

    // Metode setter dengan validasi
    public void setAvailable(boolean available) {
        this.available = available;
        if (available) {
            this.borrowedBy = null;
        }
    }
    
    public void setBorrowedBy(String userId) {
        this.borrowedBy = userId;
        this.available = false;
    }
    
    public void returnBook() {
        this.available = true;
        this.borrowedBy = null;
    }

    // Metode utilitas
    private String generateBookId() {
        return "B" + System.currentTimeMillis() % 10000;
    }
    
    public String getBookInfo() {
        String status = available ? "Tersedia" : "Dipinjam oleh: " + borrowedBy;
        return String.format("ID: %s | %s by %s [%s]", bookId, title, author, status);
    }
    
    @Override
    public String toString() {
        return getBookInfo();
    }
    
    // Metode untuk mengecek apakah buku sesuai kriteria pencarian
    public boolean matches(String searchTerm) {
        if (searchTerm == null) return false;
        String search = searchTerm.toLowerCase();
        return title.toLowerCase().contains(search) || 
               author.toLowerCase().contains(search) ||
               bookId.toLowerCase().contains(search);
    }
}
