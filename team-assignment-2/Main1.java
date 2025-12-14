public class Main1 {
    public static void main(String[] args) {
        AntrianRestoran antrian = new AntrianRestoran();
        antrian.printAntrian();

        antrian.push("John");
        antrian.printAntrian();

        antrian.push("Doe");
        antrian.printAntrian();

        String pelangganSelanjutnya = antrian.pop();
        System.out.println("Nama pelanggan selanjutnya: " + pelangganSelanjutnya);
        antrian.printAntrian();

        pelangganSelanjutnya = antrian.pop();
        System.out.println("Nama pelanggan selanjutnya: " + pelangganSelanjutnya);
        antrian.printAntrian();
    }
}
