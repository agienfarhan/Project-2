package da.glowroz.donationappclient.Model;

/**
 * Created by GlowRoz on 30/12/2017.
 */

public class User {
    private String nama;
    private  String pass;
    private String id;
    private String Staff;

    public User() {
    }

    public User(String nama, String pass) {
        this.nama = nama;
        this.pass = pass;
        Staff = "false";
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }
}

