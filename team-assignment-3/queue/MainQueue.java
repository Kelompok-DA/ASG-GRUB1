import java.util.Scanner;

class Node<T> {
  public final T data;
  public Node<T> next;

  public Node(T data) {
    this.data = data;
  }
}

class Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void enqueue(T item) {
      Node<T> newNode = new Node<>(item);
      if (tail == null) {
          head = newNode;
          tail = newNode;
      } else {
          tail.next = newNode;
          tail = newNode;
      }
      size++;
    }

    public T dequeue() {
      if (head == null) {
          return null;
      }
      T data = head.data;
      head = head.next;
      if (head == null) {
          tail = null;
      }
      size--;
      return data;
    }

    public T peek() {
      return head == null ? null : head.data;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public int count() {
      return size;
    }

    @Override
    public String toString() {
      if (head == null) {
          return "[]";
      }
      StringBuilder builder = new StringBuilder("[");
      Node<T> current = head;
      while (current != null) {
          builder.append(current.data);
          current = current.next;
          if (current != null) {
              builder.append(", ");
          }
      }
      builder.append("]");
      return builder.toString();
    }
}

public class MainQueue {
  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Queue<Integer> queue = new Queue<>();

      boolean running = true;
      while (running) {
        printMenu();
        System.out.print("Pilih menu: ");

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1":
                System.out.print("Masukkan angka untuk dimasukkan ke antrian: ");
                String valueInput = scanner.nextLine().trim();
                try {
                    int value = Integer.parseInt(valueInput);
                    queue.enqueue(value);
                    System.out.println("Berhasil memasukkan: " + value);
                } catch (NumberFormatException e) {
                    System.out.println("Angka tidak valid.");
                }
                break;
            case "2":
                if (queue.isEmpty()) {
                    System.out.println("Antrian kosong.");
                } else {
                    int removed = queue.dequeue();
                    System.out.println("Berhasil mengeluarkan: " + removed);
                }
                break;
            case "3":
                if (queue.isEmpty()) {
                    System.out.println("Antrian kosong.");
                } else {
                    System.out.println("Item paling depan: " + queue.peek());
                }
                break;
            case "4":
                System.out.println("Total item dalam antrian: " + queue.count());
                break;
            case "5":
                System.out.println("Isi antrian: " + queue);
                break;
            case "0":
                running = false;
                System.out.println("Keluar.");
                break;
            default:
                System.out.println("Pilihan menu tidak valid.");
                break;
        }

        System.out.println();
      }

      scanner.close();
  }

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