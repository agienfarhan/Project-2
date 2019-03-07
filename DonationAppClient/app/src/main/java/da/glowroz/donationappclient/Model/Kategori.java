package da.glowroz.donationappclient.Model;

/**
 * Created by GlowRoz on 30/12/2017.
 */

public class Kategori {
    private String nama;
    private String gambar;

    public Kategori() {
    }

    public Kategori(String nama, String gambar) {
        this.nama = nama;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
