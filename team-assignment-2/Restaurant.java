public class Restaurant {
  private Queue<Customer> queue = new Queue<Customer>();

  /**
   * Menambahkan customer ke antrian
   */
  public void queueCustomer(Customer customer) {
    queue.enqueue(customer);
  }

  /**
   * Melayani pelanggan selanjutnya
   */
  public Customer serveNextCustomer() {
    return queue.dequeue();
  }

  /**
   * Mengembalikan daftar customer dalam antrian
   */
  public Customer[] listCustomer() {
    return queue.toArray(Customer.class);
  }
}