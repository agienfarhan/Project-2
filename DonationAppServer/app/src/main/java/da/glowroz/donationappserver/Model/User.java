package da.glowroz.donationappserver.Model;

/**
 * Created by GlowRoz on 21/12/2017.
 */

public class User {
    private String Nama,Pass,Id,Staff;

    public User(String nama, String pass) {
        Nama = nama;
        Pass = pass;
    }

    public User() {
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }
}
