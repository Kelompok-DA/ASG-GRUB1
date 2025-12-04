/**
 * Kelas Member - kelas turunan dari User
 * Mendemonstrasikan inheritance dan polimorfisme
 */
class Member extends User {
    private int borrowedBooksCount;
    private int maxBorrowLimit;
    
    public Member(String userId, String name) {
        super(userId, name, "MEMBER"); // Panggil konstruktor induk
        this.borrowedBooksCount = 0;
        this.maxBorrowLimit = 3; // Batas default
    }
    
    // Konstruktor dengan batas pinjam khusus
    public Member(String userId, String name, int maxBorrowLimit) {
        super(userId, name, "MEMBER");
        this.borrowedBooksCount = 0;
        this.maxBorrowLimit = maxBorrowLimit;
    }

    // Polimorfisme - mengganti metode abstrak dari kelas induk
    @Override
    public void showMenu() {
        System.out.println("=== Menu Member ===");
        System.out.println("1. Lihat Buku Tersedia");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Lihat Status Peminjaman");
        System.out.println("Buku dipinjam: " + borrowedBooksCount + "/" + maxBorrowLimit);
    }

    // Polimorfisme - mengganti metode abstrak dengan implementasi spesifik
    @Override
    public void interactWithSystem(Library library) {
        System.out.println(name + " (Member) memiliki akses untuk meminjam dan mengembalikan buku.");
        System.out.println("Batas maksimal peminjaman: " + maxBorrowLimit + " buku");
        
        // Mendemonstrasikan kemampuan member
        System.out.println("Demonstrasi kemampuan member:");
        viewAvailableBooks(library);
    }

    // Metode khusus member
    public boolean borrowBook(Library library, String title) {
        if (borrowedBooksCount >= maxBorrowLimit) {
            System.out.println("Member " + name + " telah mencapai batas maksimal peminjaman!");
            return false;
        }
        
        boolean success = library.borrowBook(title);
        if (success) {
            borrowedBooksCount++;
            System.out.println("Member " + name + " berhasil meminjam: " + title);
            System.out.println("Total buku dipinjam: " + borrowedBooksCount + "/" + maxBorrowLimit);
        }
        return success;
    }

    public boolean returnBook(Library library, String title) {
        boolean success = library.returnBook(title);
        if (success && borrowedBooksCount > 0) {
            borrowedBooksCount--;
            System.out.println("Member " + name + " berhasil mengembalikan: " + title);
            System.out.println("Total buku dipinjam: " + borrowedBooksCount + "/" + maxBorrowLimit);
        }
        return success;
    }
    
    public void viewAvailableBooks(Library library) {
        System.out.println("Member " + name + " melihat buku yang tersedia:");
        library.displayAvailableBooks();
    }
    
    // Metode getter untuk enkapsulasi
    public int getBorrowedBooksCount() {
        return borrowedBooksCount;
    }
    
    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }
    
    public boolean canBorrowMore() {
        return borrowedBooksCount < maxBorrowLimit;
    }
}
