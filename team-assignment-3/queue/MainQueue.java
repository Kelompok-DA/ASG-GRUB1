import java.util.Scanner;

// class untuk merepresentasikan node dalam linked list
class Node<T> {
    public final T data; // data yang disimpan dalam node
    public Node<T> next; // referensi ke node berikutnya

    public Node(T data) {
        this.data = data;
    }
}

// class untuk implementasi queue menggunakan linked list
class Queue<T> {
    private Node<T> head; // pointer ke elemen paling depan
    private Node<T> tail; // pointer ke elemen paling belakang
    private int size; // jumlah elemen dalam queue

    // menambahkan elemen baru ke akhir queue
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (tail == null) {
            // jika queue kosong, head dan tail menunjuk ke node baru
            head = newNode;
            tail = newNode;
        } else {
            // tambahkan node baru di akhir dan update tail
            tail.next = newNode;
            tail = newNode;
        }
        size++; // increment ukuran queue
    }

    // mengeluarkan dan mengembalikan elemen paling depan
    public T dequeue() {
        if (head == null) {
            // kembalikan null jika queue kosong
            return null;
        }
        T data = head.data; // simpan data yang akan dikembalikan
        head = head.next; // geser head ke node berikutnya
        if (head == null) {
            // jika queue menjadi kosong, set tail ke null juga
            tail = null;
        }
        size--; // decrement ukuran queue
        return data;
    }

    // melihat elemen paling depan tanpa mengeluarkannya
    public T peek() {
        return head == null ? null : head.data;
    }

    // mengecek apakah queue kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // mengembalikan jumlah elemen dalam queue
    public int count() {
        return size;
    }

    // mengonversi queue menjadi string untuk ditampilkan
    @Override
    public String toString() {
        if (head == null) {
            return "[]"; // queue kosong
        }
        StringBuilder builder = new StringBuilder("[");
        Node<T> current = head;
        // iterasi melalui semua node
        while (current != null) {
            builder.append(current.data);
            current = current.next;
            if (current != null) {
                builder.append(", "); // tambahkan koma jika bukan elemen terakhir
            }
        }
        builder.append("]");
        return builder.toString();
    }
}

public class MainQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> queue = new Queue<>(); // inisialisasi queue untuk integer

        boolean running = true;
        while (running) {
            printMenu(); // tampilkan menu
            System.out.print("Pilih menu: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    // menu enqueue: tambahkan elemen ke queue
                    System.out.print("Masukkan angka untuk dimasukkan ke antrian: ");
                    String valueInput = scanner.nextLine().trim();
                    try {
                        int value = Integer.parseInt(valueInput);
                        queue.enqueue(value);
                        System.out.println("Berhasil memasukkan: " + value);
                    } catch (NumberFormatException e) {
                        // tangani input yang bukan angka
                        System.out.println("Angka tidak valid.");
                    }
                    break;
                case "2":
                    // menu dequeue: keluarkan elemen dari queue
                    if (queue.isEmpty()) {
                        System.out.println("Antrian kosong.");
                    } else {
                        int removed = queue.dequeue();
                        System.out.println("Berhasil mengeluarkan: " + removed);
                    }
                    break;
                case "3":
                    // menu peek: lihat elemen paling depan
                    if (queue.isEmpty()) {
                        System.out.println("Antrian kosong.");
                    } else {
                        System.out.println("Item paling depan: " + queue.peek());
                    }
                    break;
                case "4":
                    // menu count: tampilkan jumlah elemen
                    System.out.println("Total item dalam antrian: " + queue.count());
                    break;
                case "5":
                    // menu display: tampilkan isi queue
                    System.out.println("Isi antrian: " + queue);
                    break;
                case "0":
                    // menu exit: keluar dari program
                    running = false;
                    System.out.println("Keluar.");
                    break;
                default:
                    // tangani pilihan menu yang tidak valid
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }

            System.out.println(); // baris kosong untuk pemisah
        }

        scanner.close(); // tutup scanner
    }

    // method untuk menampilkan menu pilihan
    private static void printMenu() {
        System.out.println("=== Menu Antrian ===");
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Peek");
        System.out.println("4. Count items");
        System.out.println("5. Display queue");
        System.out.println("0. Exit");
    }
}