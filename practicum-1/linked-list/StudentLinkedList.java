import java.util.LinkedList;
import java.util.Scanner;

public class StudentLinkedList {
    LinkedList<StudentNode> students = new LinkedList<>();

    public void addStudent(Scanner sc) {
        System.out.print("Masukkan NIM: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) return;

        // Check NIM duplication
        for (StudentNode node : students) {
            if (node.getId().equalsIgnoreCase(id)) return;
        }

        System.out.print("Masukkan nama: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) return;

        System.out.print("Masukkan nilai (0-100): ");
        int score = InputUtil.readInt(sc);

        // Add node to the LinkedList
        students.addLast(new StudentNode(id, name, score));
        System.out.println("Data ditambahkan.");
    }

    public void removeStudent(Scanner sc) {
        if (students.isEmpty()) {
            System.out.println("Daftar kosong.");
            return;
        }

        System.out.print("Masukkan NIM yang akan dihapus: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) return;

        // Iterate the node one-by-one
        var it = students.iterator();
        while (it.hasNext()) {
            StudentNode node = it.next();
            if (node.getId().equalsIgnoreCase(id)) {
                it.remove();
                System.out.println("Data dihapus.");
                return;
            }
        }

        System.out.println("Data tidak ditemukan.");
    }

    public void updateScore(Scanner sc) {
        if (students.isEmpty()) {
            System.out.println("Daftar kosong.");
            return;
        }

        System.out.print("Masukkan NIM yang akan diupdate: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) return;

        // Find the node then update
        for (StudentNode node : students) {
            if (node.getId().equalsIgnoreCase(id)) {
                System.out.print("Masukkan nilai baru (0-100): ");
                int newScore = InputUtil.readInt(sc);
                node.setScore(newScore);
                System.out.println("\nMengupdate nilai mahasiswa (" + node.getName() + " -> " + newScore + ")");
                displayWithHeader("Daftar Mahasiswa setelah update:");
                return;
            }
        }

        System.out.println("Data tidak ditemukan.");
    }

    public void display() {
        displayWithHeader("\nDaftar Mahasiswa:");
    }

    private void displayWithHeader(String header) {
        if (students.isEmpty()) {
            System.out.println("Daftar kosong.");
            return;
        }

        System.out.println(header);
        int index = 1;
        // Print the LinkedList elements
        for (StudentNode node : students) {
            System.out.println(index + ". NIM: " + node.getId()
                    + ", Nama: " + node.getName()
                    + ", Nilai: " + node.getScore());
            index++;
        }
    }
}
