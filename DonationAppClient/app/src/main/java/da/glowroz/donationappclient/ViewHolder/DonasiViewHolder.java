package da.glowroz.donationappclient.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import da.glowroz.donationappclient.Interface.ItemClickListener;
import da.glowroz.donationappclient.R;

/**
 * Created by GlowRoz on 30/12/2017.
 */

public class DonasiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView donasi_name;
    public ImageView donasi_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DonasiViewHolder(View itemView) {
        super(itemView);

        donasi_name = (TextView)itemView.findViewById(R.id.donasi_name);
        donasi_image = (ImageView)itemView.findViewById(R.id.donasi_image);


        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
