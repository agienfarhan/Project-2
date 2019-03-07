package da.glowroz.donationappclient.Model;

/**
 * Created by GlowRoz on 31/12/2017.
 */

public class Transaksi {
    private String Id_Donasi;
    private String Nama_Donasi;
    private String Nominal_Donasi;

    public Transaksi(String id_Donasi, String nama_Donasi, String nominal_Donasi) {
        Id_Donasi = id_Donasi;
        Nama_Donasi = nama_Donasi;
        Nominal_Donasi = nominal_Donasi;
    }



    public String getId_Donasi() {
        return Id_Donasi;
    }

    public void setId_Donasi(String id_Donasi) {
        Id_Donasi = id_Donasi;
    }

    public String getNama_Donasi() {
        return Nama_Donasi;
    }

    public void setNama_Donasi(String nama_Donasi) {
        Nama_Donasi = nama_Donasi;
    }

    public String getNominal_Donasi() {
        return Nominal_Donasi;
    }

    public void setNominal_Donasi(String nominal_Donasi) {
        Nominal_Donasi = nominal_Donasi;
    }
}
