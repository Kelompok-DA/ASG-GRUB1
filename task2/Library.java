class Library {
    private Book[] books;
    private int bookCount;

    public Library(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
    }

    public void addBook(Book book) { //menambahkan buku
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
            System.out.println("Buku \"" + book.getTitle() + "\" berhasil ditambahkan.");
        } else {
            System.out.println("Kapasitas perpustakaan penuh.");
        }
    }

    public void removeBookByTitle(String title) { //menghapus buku
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
    
    public Book searchBook(String title) { //pencarian buku berdasarkan judul
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    public void showAllBooks() { //menampilkan semua buku
        if (bookCount == 0) {
            System.out.println("Belum ada buku dalam perpustakaan.");
            return;
        }

        System.out.println("=== Daftar Buku ===");
        for (int i = 0; i < bookCount; i++) {
            Book b = books[i];
            System.out.println((i + 1) + ". " +
                    b.getTitle() + " | " + 
                    b.getAuthor() + " | " + 
                    (b.isAvailable() ? "Tersedia" : "Dipinjam"));
        }
    }

  public void borrowBook(String title) {
        System.out.println("Meminjam buku dengan judul: " + title + " (logika lengkap di nomor 3).");
    }

    public void returnBook(String title) {
        System.out.println("Mengembalikan buku dengan judul: " + title + " (logika lengkap di nomor 3).");
    }
}
