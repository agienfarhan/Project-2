package da.glowroz.donationappserver.Model;

/**
 * Created by GlowRoz on 28/12/2017.
 */

public class Donasi {
    private String Nama, Gambar, Deskripsi,Minimal, KategoriId;

    public Donasi() {
    }

    public Donasi(String nama, String gambar, String deskripsi, String minimal, String kategoriId) {
        Nama = nama;
        Gambar = gambar;
        Deskripsi = deskripsi;
        Minimal = minimal;
        KategoriId = kategoriId;
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

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getMinimal() {
        return Minimal;
    }

    public void setMinimal(String minimal) {
        Minimal = minimal;
    }

    public String getKategoriId() {
        return KategoriId;
    }

    public void setKategoriId(String kategoriId) {
        KategoriId = kategoriId;
    }
}
