import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerQueue queue = new CustomerQueue();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistem Antrean Customer Service ===");
            System.out.println("1. Tambah pelanggan (enqueue)");
            System.out.println("2. Layani pelanggan (dequeue)");
            System.out.println("3. Tampilkan antrean");
            System.out.println("4. Lihat pelanggan terdepan (peek)");
            System.out.println("5. Exit");
            System.out.print("Pilih menu (1-5): ");

            int choice = readInt(sc);
            switch (choice) {
                case 1 -> {
                    System.out.print("Masukkan nama pelanggan: ");
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Nama tidak boleh kosong.");
                    } else {
                        queue.enqueue(name);
                        System.out.println("Berhasil menambahkan: " + name);
                    }
                }
                case 2 -> {
                    String served = queue.dequeue();
                    if (served == null) {
                        System.out.println("Antrean kosong. Tidak ada pelanggan yang bisa dilayani.");
                    } else {
                        System.out.println("Melayani pelanggan: " + served);
                    }
                }
                case 3 -> queue.display();
                case 4 -> {
                    String front = queue.peek();
                    if (front == null) {
                        System.out.println("Antrean kosong.");
                    } else {
                        System.out.println("Pelanggan terdepan: " + front);
                    }
                }
                case 5 -> {
                    System.out.println("Terima kasih! Program selesai.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid. Masukkan 1-5.");
            }
        }
    }

    // Helper agar input menu aman (tidak crash kalau user ketik huruf)
    private static int readInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Input harus angka. Coba lagi: ");
            }
        }
    }
}
