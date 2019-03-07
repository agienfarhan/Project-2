package da.glowroz.donationappclient.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import da.glowroz.donationappclient.DonasiDetail;
import da.glowroz.donationappclient.Interface.ItemClickListener;
import da.glowroz.donationappclient.R;

/**
 * Created by GlowRoz on 03/01/2018.
 */

class DonasiCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_nama_donasi,txt_nominal;
    public ImageView gmbr_donasi_count;

    private ItemClickListener itemClickListener;

    public void setTxt_nama_donasi(TextView txt_nama_donasi) {
        this.txt_nama_donasi = txt_nama_donasi;
    }

    public DonasiCartViewHolder(View itemView) {
        super(itemView);
        txt_nama_donasi = (TextView)itemView.findViewById(R.id.donasi_item_name);
        txt_nominal = (TextView) DonasiDetail
    }

    @Override
    public void onClick(View v) {

    }
}

public class DonasiAdapter {
}
