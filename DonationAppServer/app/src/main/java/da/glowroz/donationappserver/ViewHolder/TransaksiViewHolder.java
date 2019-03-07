package da.glowroz.donationappserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import da.glowroz.donationappserver.Interface.ItemClickListener;
import da.glowroz.donationappserver.R;

/**
 * Created by GlowRoz on 05/01/2018.
 */

public class TransaksiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    public TextView txtTransaksiIdStatus, txtTransaksiStatus, txtTransaksiId, txtTransaksiRekening;

    private ItemClickListener itemClickListener;

    public TransaksiViewHolder(View itemView) {
        super(itemView);

        txtTransaksiIdStatus = (TextView)itemView.findViewById(R.id.transaksi_idstatus);
        txtTransaksiStatus = (TextView)itemView.findViewById(R.id.transaksi_status);
        txtTransaksiId = (TextView)itemView.findViewById(R.id.transaksi_id);
        txtTransaksiRekening = (TextView)itemView.findViewById(R.id.transaksi_rekening);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select The Action");

        menu.add(0,0,getAdapterPosition(),"Update");
        menu.add(0,1,getAdapterPosition(),"Delete");
    }
}