package da.glowroz.donationappserver.Common;

import da.glowroz.donationappserver.Model.User;
/**
 * Created by GlowRoz on 21/12/2017.
 */

public class Common {
    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static  String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Menunggu Konfirmasi Transaksi";
        else if(code.equals("1"))
            return "Sedang Melakukan Konfirmasi Transaksi";
        else
            return "Transaksi Berhasil";
    }


}
