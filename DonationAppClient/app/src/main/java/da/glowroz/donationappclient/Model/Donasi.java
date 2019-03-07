package da.glowroz.donationappclient.Model;

/**
 * Created by GlowRoz on 30/12/2017.
 */

public class Donasi {
    private String nama;
    private String deskripsi;
    private String gambar;
    private String kategoriId;

    public Donasi() {
    }

    public Donasi(String nama, String deskripsi, String gambar, String kategoriId) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.kategoriId = kategoriId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(String kategoriId) {
        this.kategoriId = kategoriId;
    }
}
