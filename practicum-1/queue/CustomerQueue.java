public class CustomerQueue {
    private static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public CustomerQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(String name) {
        Node newNode = new Node(name);


        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }


        String served = front.name;
        front = front.next;
        size--;


        if (front == null) {
            rear = null;
        }


        return served;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }

        System.out.println("Pelanggan dalam antrean:");
        Node current = front;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current.name);
            current = current.next;
            index++;
        }
    }

    public String peek() {
        return isEmpty() ? null : front.name;
    }
}
