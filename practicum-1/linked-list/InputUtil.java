import java.util.Scanner;

public class InputUtil {
    // Helper agar input angka aman
    public static int readInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Input harus angka. Coba lagi: ");
            }
        }
    }
}
