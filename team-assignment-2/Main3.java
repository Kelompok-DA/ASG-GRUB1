import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AntrianRestoran antrian = new AntrianRestoran();
        int pilihan;

        do {
            System.out.println("\n=== SISTEM ANTRIAN RESTORAN ===");
            System.out.println("1. Tambah pelanggan (push)");
            System.out.println("2. Layani pelanggan (pop)");
            System.out.println("3. Tampilkan antrian");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan = input.nextInt();

            // membersihkan buffer newline
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String nama = input.nextLine();
                    antrian.push(nama);
                    antrian.printAntrian();
                    break;

                case 2:
                    antrian.pop();
                    antrian.printAntrian();
                    break;

                case 3:
                    antrian.printAntrian();
                    break;

                case 0:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);

        input.close();
    }
}
