package restaurant_system;

import java.util.Scanner;

public class MainTaskRestaurant {
  private static Scanner scanner;
  private static Restaurant restaurant;

  public static void main(String[] args) {
    System.out.println("=======================================================");
    System.out.println("     SISTEM PENGELOLAAN RESTORAN - INTERACTIVE MODE");

    initializeSystem();
    runMainMenuLoop();
  }

  /**
   * Inisialisasi sistem perpustakaan
   */
  private static void initializeSystem() {
    scanner = new Scanner(System.in);
    restaurant = new Restaurant();
  }

   /**
   * Loop menu utama sistem - berjalan terus menerus
   */
  private static void runMainMenuLoop() {
      boolean running = true;
      
      while (running) {
        System.out.println("=======================================================");
        System.out.println("Pilih menu: ");
        System.out.println("1. Tambah pelanggan ke antrian.");
        System.out.println("2. Daftar pelanggan dalam antrian.");
        System.out.println("3. Layani pelanggan selanjutnya.");
        System.out.println("4. Keluar.");
        System.out.println("=======================================================");

        String choice = scanner.nextLine().trim();
        
        System.out.println(); // Line break
        
        switch (choice) {
            case "1":
                System.out.print("Nama pelanggan: ");

                String customerName = scanner.nextLine().trim();
                if (customerName.isEmpty()) {
                  System.out.println("❌ Nama pelanggan tidak boleh kosong!\n");
                  break;
                }

                Customer newCustomer = new Customer(customerName);
                restaurant.queueCustomer(newCustomer);
                break;
            case "2":
              Customer[] customers = restaurant.listCustomer();
              if (customers.length == 0) {
                System.out.println("❌ Daftar pelanggan kosong.");
                break;
              }

              for (int i = 0; i < customers.length; i++) {
                System.out.println(i + 1 + ". Nama pelanggan: " + customers[i].getName());
              }

              break;
            case "3":
                Customer nextCustomer = restaurant.serveNextCustomer();
                if (nextCustomer == null) {
                  System.out.println("❌ Tidak ada pelanggan di dalam antrian.");
                  break;
                }
                System.out.print("Nama pelanggan selanjutnya: " + nextCustomer.getName() + "\n");
                break;
            case "4":
              running = handleExit();
              break;
            default:
              System.out.println("❌ Pilihan tidak valid!");
        }
        
        if (running) {
            System.out.println("\nTekan Enter untuk melanjutkan...");
            scanner.nextLine();
            clearScreen();
        }
      }
  }

    /**
     * Handle menu 6: Keluar dari sistem
     */
    private static boolean handleExit() {
        System.out.println("=== KELUAR DARI SISTEM ===");
        System.out.print("Apakah Anda yakin ingin keluar? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("y") || confirm.equals("yes")) {
            System.out.println("=======================================================");
            System.out.println("Terima kasih telah menggunakan Sistem Pengelolaan Restoran!");
            System.out.println("Sampai jumpa lagi! 👋");
            System.out.println("=======================================================");
            
            scanner.close();
            return false; // Exit the loop
        } else {
            System.out.println("✓ Kembali ke menu utama...");
            return true; // Continue the loop
        }
    }

  /**
   * Clear screen untuk tampilan yang bersih
   */
  private static void clearScreen() {
      // Print multiple newlines to simulate clear screen
      for (int i = 0; i < 3; i++) {
          System.out.println();
      }
  }
}