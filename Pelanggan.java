import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pelanggan {
    private String nama;
    private List<Pesanan> daftarPesanan = new ArrayList<>();

    public Pelanggan(String nama) {
        this.nama = nama;
    }

    public void buatPesanan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHalo " + nama + ", selamat datang di Aplikasi Pemesanan Makanan!");
        boolean lanjut = true;

        while (lanjut) {
            System.out.println("\nBerikut daftar menu yang tersedia:");
            Makanan.tampilkanMenu();

            System.out.print("\nMasukkan nomor makanan yang ingin dipesan: ");
            int pilihan = scanner.nextInt();

            Makanan makananDipilih = Makanan.getMakananByNomor(pilihan);

            if (makananDipilih != null) {
                System.out.print("Masukkan jumlah pesanan untuk " + makananDipilih.getNama() + ": ");
                int jumlah = scanner.nextInt();

                Pesanan pesanan = new Pesanan(this, makananDipilih, jumlah);
                daftarPesanan.add(pesanan);
                System.out.println("✅ " + makananDipilih.getNama() + " (" + jumlah + " porsi) telah ditambahkan ke pesanan.");
            } else {
                System.out.println("❌ Nomor makanan tidak valid.");
            }

            System.out.print("Apakah ingin menambah pesanan lagi? (y/n): ");
            String jawaban = scanner.next();
            lanjut = jawaban.equalsIgnoreCase("y");
        }

        // Lanjut ke transaksi pembayaran
        Transaksi transaksi = new Transaksi(this, daftarPesanan);
        transaksi.prosesPembayaran(scanner);
    }

    public String getNama() {
        return nama;
    }
}