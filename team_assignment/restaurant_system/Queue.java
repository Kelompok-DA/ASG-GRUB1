package restaurant_system;

public class Queue<T> {
  private SingleLinkedList<T> head = null;
  private SingleLinkedList<T> tail = null;

  /**
   * Mengambil item pertama
   */
  public T dequeue() {
    if (head == null) {
      return null;
    }

    T val = head.val;
    head = head.next;
    return val;
  }

  /**
   * Menambahkan item ke urutan terakhir
   */
  public void enqueue(T val) {
    SingleLinkedList<T> node = new SingleLinkedList<T>(val, null);
    if (head == null) {
      head = node;
      tail = head;
    } else {
      tail.next = node;
      tail = tail.next;
    }
  }

  /**
   * Menadapatkan jumlah item pada antrian
   */
  public int length() {
    SingleLinkedList<T> next = head;
    int size = 0;
    while (next != null) {
      next = next.next;
      size++;
    }

    return size;
  }

  /**
   * Membuat antrian dalam array;
   */
  public T[] toArray(Class<T> clazz) {
    int size = length();

    @SuppressWarnings("unchecked")
    T[] items = (T[]) java.lang.reflect.Array.newInstance(clazz, size);

    SingleLinkedList<T> next = head;
    int index = 0;
    while (next != null) {
      items[index] = next.val;
      next = next.next;
      index++;
    }

    return items;
  }
}