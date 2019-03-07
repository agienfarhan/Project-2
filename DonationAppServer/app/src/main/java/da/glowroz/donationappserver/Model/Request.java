package da.glowroz.donationappserver.Model;

import java.util.List;

/**
 * Created by GlowRoz on 05/01/2018.
 */

public class Request {
    private String id;
    private String name;
    private String no_rek;
    private String status;
    private String total;

    private List<Transaksi> donasi; //List of Transaksi Donasi

    public Request() {
    }

    public Request(String id, String name, String no_rek, String total, List<Transaksi> donasi) {
        this.id = id;
        this.name = name;
        this.no_rek = no_rek;
        this.total = total;
        this.donasi = donasi;
        this.status = "0"; //Default = 0, 1 = Sedang Melakukan Konfirmasi
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo_rek() {
        return no_rek;
    }

    public void setNo_rek(String no_rek) {
        this.no_rek = no_rek;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Transaksi> getDonasi() {
        return donasi;
    }

    public void setDonasi(List<Transaksi> donasi) {
        this.donasi = donasi;
    }
}
