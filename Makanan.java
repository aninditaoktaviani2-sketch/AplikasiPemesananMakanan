import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Makanan {
    private String nama;
    private double harga;

    // Daftar menu makanan
    private static Makanan[] daftarMenu = {
        new Makanan("Nasi Goreng", 15000),
        new Makanan("Mie Ayam", 12000),
        new Makanan("Ayam Geprek", 18000),
        new Makanan("Sate Ayam", 20000),
        new Makanan("Bakso", 14000),
        new Makanan("Nasi Uduk", 13000),
        new Makanan("Es Teh", 5000),
        new Makanan("Es Jeruk", 7000)
    };

    // Konstruktor
    public Makanan(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    // Format harga jadi ribuan
    private static String formatRupiah(double harga) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols();
        simbol.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,###", simbol);
        return format.format(harga);
    }

    // Menampilkan daftar menu
    public static void tampilkanMenu() {
        System.out.println("-----------------------------");
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i].getNama()
                + " - Rp " + formatRupiah(daftarMenu[i].getHarga()));
        }
        System.out.println("-----------------------------");
    }

    // Ambil makanan berdasarkan nomor
    public static Makanan getMakananByNomor(int nomor) {
        if (nomor > 0 && nomor <= daftarMenu.length) {
            return daftarMenu[nomor - 1];
        }
        return null;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }
}