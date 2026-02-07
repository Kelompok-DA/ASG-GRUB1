import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class StudentGraph {
  private HashMap<String, LinkedList<String>> adjList = new HashMap<>();

  public boolean addStudent(String nim) {
    // Keep graph aligned with student data by ensuring each student exists as a vertex.
    if (adjList.containsKey(nim)) {
      return false;
    }

    adjList.put(nim, new LinkedList<>());
    return true;
  }

  public boolean searchStudent(String nim) {
    // BFS checks reachability by exploring neighbors level by level.
    // It's a simple fit for unweighted reachability without extra weight handling.
    if (adjList.isEmpty()) {
      return false;
    }

    HashMap<String, Boolean> visited = new HashMap<>();
    Queue<String> queue = new LinkedList<>();

    String start = adjList.keySet().iterator().next();
    queue.add(start);
    visited.put(start, true);

    while (!queue.isEmpty()) {
      String current = queue.poll();
      if (current.equals(nim)) {
        return true;
      }

      for (String neighbor : adjList.get(current)) {
        if (!visited.containsKey(neighbor)) {
          visited.put(neighbor, true);
          queue.add(neighbor);
        }
      }
    }

    return false;
  }

  public boolean addRelation(String nim1, String nim2) {
    // Avoid self-relations since they do not add meaningful collaboration info.
    if (nim1.equals(nim2)) {
      return false;
    }

    if (!adjList.containsKey(nim1) || !adjList.containsKey(nim2)) {
      return false;
    }

    LinkedList<String> list1 = adjList.get(nim1);
    LinkedList<String> list2 = adjList.get(nim2);

    if (!list1.contains(nim2)) {
      list1.add(nim2);
    }

    if (!list2.contains(nim1)) {
      list2.add(nim1);
    }

    return true;
  }

  public boolean removeRelation(String nim1, String nim2) {
    if (!adjList.containsKey(nim1) || !adjList.containsKey(nim2)) {
      return false;
    }

    LinkedList<String> list1 = adjList.get(nim1);
    LinkedList<String> list2 = adjList.get(nim2);

    boolean removed1 = list1.remove(nim2);
    boolean removed2 = list2.remove(nim1);

    return removed1 || removed2;
  }

  public boolean deleteStudent(String nim) {
    if (!adjList.containsKey(nim)) {
      return false;
    }

    // Clean up edges so other vertices stay consistent after deletion.
    Set<String> keys = adjList.keySet();
    for (String key : keys) {
      adjList.get(key).remove(nim);
    }

    adjList.remove(nim);
    return true;
  }

  public void displayConnections(String nim) {
    if (!adjList.containsKey(nim)) {
      System.out.println("Mahasiswa tidak ditemukan di graph.");
      return;
    }

    LinkedList<String> connections = adjList.get(nim);
    if (connections.isEmpty()) {
      System.out.println("Tidak ada relasi untuk NIM " + nim + ".");
      return;
    }

    System.out.println("Relasi mahasiswa untuk NIM " + nim + ":");
    for (String connectedNim : connections) {
      System.out.println("- " + connectedNim);
    }
  }

  public void displayAll() {
    if (adjList.isEmpty()) {
      System.out.println("Graph masih kosong.");
      return;
    }

    for (String nim : adjList.keySet()) {
      System.out.println("NIM " + nim + " terhubung dengan: " + adjList.get(nim));
    }
  }
}
