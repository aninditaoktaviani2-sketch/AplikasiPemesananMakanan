import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaksi {
    private Pelanggan pelanggan;
    private List<Pesanan> daftarPesanan;
    private double totalBayar;

    public Transaksi(Pelanggan pelanggan, List<Pesanan> daftarPesanan) {
        this.pelanggan = pelanggan;
        this.daftarPesanan = daftarPesanan;
    }

    public void prosesPembayaran(Scanner scanner) {
        System.out.println("\n===== RINGKASAN PESANAN =====");
        totalBayar = 0;

        for (Pesanan pesanan : daftarPesanan) {
            double subtotal = pesanan.getSubtotal();
            System.out.println("- " + pesanan.getMakanan().getNama() + " (" + pesanan.getJumlah() + "x) : Rp " + subtotal);
            totalBayar += subtotal;
        }

        System.out.println("-----------------------------");
        System.out.println("Total Pembayaran : Rp " + totalBayar);
        System.out.println("=============================");

        System.out.print("Masukkan jumlah uang yang dibayarkan: Rp ");
        double bayar = scanner.nextDouble();

        if (bayar >= totalBayar) {
            double kembalian = bayar - totalBayar;
            System.out.println("Pembayaran berhasil!");
            System.out.println("Kembalian Anda: Rp " + kembalian);

            cetakStruk(bayar, kembalian);
        } else {
            System.out.println("⚠️ Uang tidak cukup! Transaksi dibatalkan.");
        }
    }

    private void cetakStruk(double bayar, double kembalian) {
        String namaFile = "transaksi_" + pelanggan.getNama() + "_" + System.currentTimeMillis() + ".txt";

        try (FileWriter writer = new FileWriter(namaFile)) {
            writer.write("====================================\n");
            writer.write("        STRUK PEMESANAN MAKANAN     \n");
            writer.write("====================================\n");
            writer.write("Nama Pelanggan : " + pelanggan.getNama() + "\n");
            writer.write("Tanggal        : " + getTanggalSekarang() + "\n");
            writer.write("------------------------------------\n");

            for (Pesanan pesanan : daftarPesanan) {
                writer.write(pesanan.getMakanan().getNama() + " (" + pesanan.getJumlah() + "x)"
                        + " = Rp " + pesanan.getSubtotal() + "\n");
            }

            writer.write("------------------------------------\n");
            writer.write("Total Bayar : Rp " + totalBayar + "\n");
            writer.write("Uang Bayar  : Rp " + bayar + "\n");
            writer.write("Kembalian   : Rp " + kembalian + "\n");
            writer.write("====================================\n");
            writer.write("Terima kasih telah memesan!\n");
            writer.write("====================================\n");

            System.out.println("🧾 Struk transaksi berhasil disimpan ke file: " + namaFile);

        } catch (IOException e) {
            System.out.println("❌ Gagal mencetak struk: " + e.getMessage());
        }
    }

    private String getTanggalSekarang() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}