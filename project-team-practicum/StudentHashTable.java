import java.util.HashMap;

public class StudentHashTable {
    HashMap<String, Mahasiswa> table = new HashMap<>();

    public boolean insert(String nim, String nama, double ipk) {
      // Reject duplicates to keep NIM as a reliable unique key.
      if (table.containsKey(nim)) {
        return false;
      }

      Mahasiswa mhs = new Mahasiswa(nim, nama, ipk);
      table.put(nim, mhs);
      return true;
    }

    public void search(String nim) {
      Mahasiswa mhs = table.get(nim);

      if (mhs != null) {
        System.out.println("Mahasiswa ditemukan:");
        mhs.display();
      } else {
        System.out.println("Mahasiswa tidak ditemukan.");
      }
    }

    public Mahasiswa get(String nim) {
      return table.get(nim);
    }

    public boolean delete(String nim) {
      // Guard early so callers can react with a clear message.
      if (!table.containsKey(nim)) {
        return false;
      }

      table.remove(nim);
      return true;
    }
}
