package da.glowroz.donationappserver.Model;

/**
 * Created by GlowRoz on 21/12/2017.
 */

public class Kategori {
    private String Nama;
    private String Gambar;

    public Kategori() {
    }

    public Kategori(String nama, String gambar) {
        Nama = nama;
        Gambar = gambar;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }
}
