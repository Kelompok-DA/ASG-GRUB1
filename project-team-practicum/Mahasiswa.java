public class Mahasiswa {
  String nim;
  String nama;
  double ipk;

  public Mahasiswa(String nim, String nama, double ipk) {
    this.nim = nim;
    this.nama = nama;
    this.ipk = ipk;
  }

  public void display() {
    System.out.println("NIM  : " + nim);
    System.out.println("Nama : " + nama);
    System.out.println("IPK  : " + ipk);
  }
}
