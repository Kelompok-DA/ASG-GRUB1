package restaurant_system;

public class SingleLinkedList<T> {
  public T val;
  public SingleLinkedList<T> next;

  public SingleLinkedList(T val, SingleLinkedList<T> next) {
    this.val = val;
    this.next = next;
  }
}