import java.util.*;
import java.io.*;
public class AplikasiPemesanan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<Makanan, Integer> pesanan = new LinkedHashMap<>();

        System.out.println("============================");
        System.out.println("   Aplikasi Pemesanan Makanan");
        System.out.println("============================");

        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = input.nextLine();

        System.out.println("\nHalo " + namaPelanggan + ", selamat datang di Aplikasi Pemesanan Makanan!\n");

        boolean lanjut = true;

        while (lanjut) {
            System.out.println("\nBerikut daftar menu yang tersedia:");
            Makanan.tampilkanMenu();

            System.out.print("\nMasukkan nomor makanan yang ingin dipesan: ");
            int nomor = input.nextInt();
            Makanan makananDipilih = Makanan.getMakananByNomor(nomor);

            if (makananDipilih != null) {
                System.out.print("Masukkan jumlah pesanan untuk " + makananDipilih.getNama() + ": ");
                int jumlah = input.nextInt();
                pesanan.put(makananDipilih, pesanan.getOrDefault(makananDipilih, 0) + jumlah);
                System.out.println("? " + makananDipilih.getNama() + " (" + jumlah + " porsi) telah ditambahkan ke pesanan.");
            } else {
                System.out.println("Nomor makanan tidak valid!");
            }

            // Pilihan lanjut
            System.out.print("\nApakah ingin menambah, menghapus, atau selesai? (tambah/hapus/selesai): ");
            String pilihan = input.next().toLowerCase();

            switch (pilihan) {
                case "tambah":
                    break;
                case "hapus":
                    if (pesanan.isEmpty()) {
                        System.out.println("Belum ada pesanan untuk dihapus!");
                        break;
                    }
                    int index = 1;
                    System.out.println("\nDaftar pesanan saat ini:");
                    for (Makanan m : pesanan.keySet()) {
                        System.out.println(index + ". " + m.getNama() + " (" + pesanan.get(m) + "x)");
                        index++;
                    }
                    System.out.print("Masukkan nomor pesanan yang ingin dihapus: ");
                    int hapusNomor = input.nextInt();

                    if (hapusNomor > 0 && hapusNomor <= pesanan.size()) {
                        Makanan makananDihapus = new ArrayList<>(pesanan.keySet()).get(hapusNomor - 1);
                        pesanan.remove(makananDihapus);
                        System.out.println("? " + makananDihapus.getNama() + " telah dihapus dari pesanan.");
                    } else {
                        System.out.println("Nomor pesanan tidak valid!");
                    }
                    break;
                case "selesai":
                    lanjut = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }

        // RINGKASAN PESANAN
        System.out.println("\n===== RINGKASAN PESANAN =====");
        double total = 0;
        for (Makanan m : pesanan.keySet()) {
            double subtotal = m.getHarga() * pesanan.get(m);
            System.out.println("- " + m.getNama() + " (" + pesanan.get(m) + "x) : Rp " + subtotal);
            total += subtotal;
        }
        System.out.println("-----------------------------");
        System.out.println("Total Pembayaran : Rp " + total);
        System.out.println("=============================");

        System.out.print("Masukkan jumlah uang yang dibayarkan: Rp ");
        double bayar = input.nextDouble();

        if (bayar >= total) {
            double kembalian = bayar - total;
            System.out.println("Pembayaran berhasil!");
            System.out.println("Kembalian Anda: Rp " + kembalian);

            // Simpan ke file
            try {
                String fileName = "transaksi_" + namaPelanggan + "_" + System.currentTimeMillis() + ".txt";
                FileWriter writer = new FileWriter(fileName);
                writer.write("===== STRUK PEMBAYARAN =====\n");
                writer.write("Nama Pelanggan : " + namaPelanggan + "\n\n");
                for (Makanan m : pesanan.keySet()) {
                    writer.write("- " + m.getNama() + " (" + pesanan.get(m) + "x)\n");
                }
                writer.write("-----------------------------\n");
                writer.write("Total : Rp " + total + "\n");
                writer.write("Bayar : Rp " + bayar + "\n");
                writer.write("Kembalian : Rp " + kembalian + "\n");
                writer.close();
                System.out.println("? Struk transaksi berhasil disimpan ke file: " + fileName);
            } catch (IOException e) {
                System.out.println("Gagal menyimpan struk transaksi!");
            }
        } else {
            System.out.println("Uang tidak cukup! Transaksi dibatalkan.");
        }

        input.close();
    }
}
