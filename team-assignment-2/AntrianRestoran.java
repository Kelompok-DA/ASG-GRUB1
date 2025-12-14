// LinkedList untuk antrian pelanggan
class AntrianRestoran {
    // pelanggan paling depan (yang akan dilayani)
    private Node head;
    // pelanggan paling belakang
    private Node tail;
    
    public AntrianRestoran() {
        this.head = null;
        this.tail = null;
    }

    // PUSH (menambahkan pelanggan ke antrian)
    public void push(String nama) {
        Node newNode = new Node(nama);

        // Jika antrian masih kosong
        if (tail == null) {
            head = newNode;
            tail = newNode;
            System.out.println(nama + " masuk ke antrian.");
            return;
        }

        // Tambah antrian
        tail.next = newNode;
        tail = newNode;
        System.out.println(nama + " masuk ke antrian.");
    }

    // POP (melayani pelanggan terdepan)
    public String pop() {
        if (head == null) {
            System.out.println("Tidak ada pelanggan dalam antrian.");
            return null;
        }

        String namaDilayani = head.namaPelanggan;
        head = head.next;

        // Kondisi antrian kosong, tail harus null
        if (head == null) {
            tail = null;
        }

        System.out.println("Melayani pelanggan: " + namaDilayani);
        return namaDilayani;
    }

     
    // Menampilkan daftar antrian
    public void printAntrian() {			    
        if (head == null) {
            System.out.println("Antrian kosong.");
            return;
        }

        System.out.print("Isi antrian: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.namaPelanggan + " -> ");
            current = current.next;
        }
        System.out.println("END");
    }
}
