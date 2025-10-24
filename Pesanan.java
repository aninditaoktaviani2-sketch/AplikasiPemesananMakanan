public class Pesanan {
    private Pelanggan pelanggan;
    private Makanan makanan;
    private int jumlah;

    public Pesanan(Pelanggan pelanggan, Makanan makanan, int jumlah) {
        this.pelanggan = pelanggan;
        this.makanan = makanan;
        this.jumlah = jumlah;
    }

    public Makanan getMakanan() {
        return makanan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getSubtotal() {
        return makanan.getHarga() * jumlah;
    }
}