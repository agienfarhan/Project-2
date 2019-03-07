package da.glowroz.donationappserver.Model;

/**
 * Created by GlowRoz on 05/01/2018.
 */

public class Transaksi {
    public String donasiId;
    public String donasiName;
    public String quantity;
    public String minimal;

    public Transaksi() {
    }

    public Transaksi(String donasiId, String donasiName, String quantity, String minimal) {
        this.donasiId = donasiId;
        this.donasiName = donasiName;
        this.quantity = quantity;
        this.minimal = minimal;
    }

    public String getDonasiId() {
        return donasiId;
    }

    public void setDonasiId(String donasiId) {
        this.donasiId = donasiId;
    }

    public String getDonasiName() {
        return donasiName;
    }

    public void setDonasiName(String donasiName) {
        this.donasiName = donasiName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMinimal() {
        return minimal;
    }

    public void setMinimal(String minimal) {
        this.minimal = minimal;
    }
}
