class Member extends User {
    public Member(String userId, String name) {
        super(userId, name, "MEMBER");
    }

    @Override
    public void showMenu() {
        System.out.println("=== Menu Member ===");
        System.out.println("1. Lihat Buku Tersedia");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Cari Buku");
    }

    @Override
    public void interactWithSystem(Library library) {
        System.out.println(name + " (Member) dapat meminjam dan mengembalikan buku.");
    }

    public void borrowBook(Library library, String title) {
        library.borrowBook(title);
    }

    public void returnBook(Library library, String title) {
        library.returnBook(title);
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
