import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("============================");
        System.out.println("   Aplikasi Pemesanan Makanan");
        System.out.println("============================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama pelanggan: ");
        String nama = scanner.nextLine();

        Pelanggan pelanggan = new Pelanggan(nama);
        pelanggan.buatPesanan();

        scanner.close();
    }
}