package da.glowroz.donationappserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import da.glowroz.donationappserver.Common.Common;
import da.glowroz.donationappserver.Interface.ItemClickListener;
import da.glowroz.donationappserver.R;

/**
 * Created by GlowRoz on 28/12/2017.
 */

public class DonasiViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener
{

    public TextView donasi_name;
    public ImageView donasi_image;

    private ItemClickListener itemClickListener;

    public DonasiViewHolder(View itemView){
        super(itemView);

        donasi_name = (TextView)itemView.findViewById(R.id.donasi_name);
        donasi_image = (ImageView)itemView.findViewById(R.id.donasi_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view)
    {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the action");

        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(), Common.DELETE);
    }
}