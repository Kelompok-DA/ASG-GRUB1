import java.util.HashMap;

public class StudentHashTable {
    HashMap<String, Mahasiswa> table = new HashMap<>();

    public void insert(String nim, String nama, double ipk) {
      Mahasiswa mhs = new Mahasiswa(nim, nama, ipk);
      table.put(nim, mhs);
      System.out.println("Mahasiswa berhasil ditambahkan!");
    }
}
