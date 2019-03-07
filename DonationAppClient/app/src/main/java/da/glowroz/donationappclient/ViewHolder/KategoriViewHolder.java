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

public class KategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtKategoriName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public KategoriViewHolder(View itemView) {
        super(itemView);

        txtKategoriName = (TextView)itemView.findViewById(R.id.kategori_name);
        imageView = (ImageView)itemView.findViewById(R.id.kategori_image);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
