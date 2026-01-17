import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		StudentLinkedList studentLinkedList = new StudentLinkedList();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\n=== Manajemen Data Mahasiswa ===");
			System.out.println("1. Tambah mahasiswa");
			System.out.println("2. Hapus mahasiswa");
			System.out.println("3. Update nilai mahasiswa");
			System.out.println("4. Tampilkan daftar mahasiswa");
			System.out.println("5. Exit");
			System.out.print("Pilih menu (1-5): ");

			int choice = InputUtil.readInt(sc);
			switch (choice) {
				case 1 -> studentLinkedList.addStudent(sc);
				case 2 -> studentLinkedList.removeStudent(sc);
				case 3 -> studentLinkedList.updateScore(sc);
				case 4 -> studentLinkedList.display();
				case 5 -> {
					System.out.println("Selesai.");
					sc.close();
					return;
				}
				default -> System.out.println("Pilihan tidak valid. Masukkan 1-5.");
			}
		}
	}
}
