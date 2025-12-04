class Admin extends User {
    public Admin(String userId, String name) {
        super(userId, name, "ADMIN");
    }

    @Override
    public void showMenu() {
        System.out.println("=== Menu Admin ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Hapus Buku");
        System.out.println("3. Lihat Semua Buku");
        System.out.println("4. Cari Buku");
    }

    @Override
    public void interactWithSystem(Library library) {
        System.out.println(name + " (Admin) dapat menambah atau menghapus buku.");
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBook(Library library, String title) {
        library.removeBookByTitle(title);
    }

    public void viewBooks(Library library) {
        library.showAllBooks();
    }

    public void searchBook(Library library, String title) {
        Book b = library.searchBook(title);
        if (b != null) {
            System.out.println("Ditemukan: " + b.getTitle() + " | " + b.getAuthor());
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }
}
