import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    StudentHashTable data = new StudentHashTable();
    StudentGraph graph = new StudentGraph();

    while (true) {
      System.out.println("\n=== Sistem Data Mahasiswa ===");
      System.out.println("1. Tambah Mahasiswa");
      System.out.println("2. Cari Mahasiswa");
      System.out.println("3. Hapus Mahasiswa");
      System.out.println("4. Tambah Relasi Mahasiswa");
      System.out.println("5. Hapus Relasi Mahasiswa");
      System.out.println("6. Tampilkan Graph");
      System.out.println("7. Keluar");
      System.out.print("Pilih menu: ");

      int pilih = input.nextInt();
      input.nextLine();

      switch (pilih) {
        case 1:
          System.out.print("Masukkan NIM: ");
          String nim = input.nextLine();

          System.out.print("Masukkan Nama: ");
          String nama = input.nextLine();

          System.out.print("Masukkan IPK: ");
          double ipk = input.nextDouble();

          if (data.insert(nim, nama, ipk)) {
            // Keep the graph aligned with the student master data.
            graph.addStudent(nim);
            System.out.println("Mahasiswa berhasil ditambahkan!");
          } else {
            System.out.println("NIM sudah terdaftar.");
          }
          break;

        case 2:
          System.out.print("Masukkan NIM yang dicari: ");
          String cariNim = input.nextLine();
          data.search(cariNim);
          // Show connections only when the vertex exists to keep output clean.
          if (graph.searchStudent(cariNim)) {
            graph.displayConnections(cariNim);
          } else {
            System.out.println("Mahasiswa tidak ditemukan di graph.");
          }
          break;

        case 3:
          System.out.print("Masukkan NIM yang dihapus: ");
          String hapusNim = input.nextLine();
          if (data.delete(hapusNim)) {
            graph.deleteStudent(hapusNim);
            System.out.println("Mahasiswa berhasil dihapus.");
          } else {
            System.out.println("Mahasiswa tidak ditemukan.");
          }
          break;

        case 4:
          System.out.print("Masukkan NIM pertama: ");
          String nim1 = input.nextLine();
          System.out.print("Masukkan NIM kedua: ");
          String nim2 = input.nextLine();

          if (graph.addRelation(nim1, nim2)) {
            System.out.println("Relasi berhasil ditambahkan.");
          } else {
            System.out.println("Relasi gagal ditambahkan. Pastikan NIM valid.");
          }
          break;

        case 5:
          System.out.print("Masukkan NIM pertama: ");
          String delNim1 = input.nextLine();
          System.out.print("Masukkan NIM kedua: ");
          String delNim2 = input.nextLine();

          if (graph.removeRelation(delNim1, delNim2)) {
            System.out.println("Relasi berhasil dihapus.");
          } else {
            System.out.println("Relasi tidak ditemukan.");
          }
          break;

        case 6:
          graph.displayAll();
          break;

        case 7:
          System.out.println("Program selesai.");
          return;

        default:
          System.out.println("Menu tidak valid!");
      }
    }
  }
}
