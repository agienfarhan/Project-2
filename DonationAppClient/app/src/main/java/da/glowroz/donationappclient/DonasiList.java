package da.glowroz.donationappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import da.glowroz.donationappclient.Interface.ItemClickListener;
import da.glowroz.donationappclient.Model.Donasi;
import da.glowroz.donationappclient.ViewHolder.DonasiViewHolder;

public class DonasiList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference donasiList;

    String kategoriId = "";

    FirebaseRecyclerAdapter<Donasi,DonasiViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        donasiList = database.getReference("Donasi");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_donasi);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent here
        if(getIntent() !=null)
            kategoriId = getIntent().getStringExtra("kategoriId");
        if(!kategoriId.isEmpty() && kategoriId !=null)
        {
            loadListDonasi(kategoriId);
        }
    }

    private void loadListDonasi(String kategoriId) {
        adapter = new FirebaseRecyclerAdapter<Donasi, DonasiViewHolder>(Donasi.class,
                R.layout.donasi_item,
                DonasiViewHolder.class,
                donasiList.orderByChild("kategoriId").equalTo(kategoriId) // like Select * form Donasi where kategoriId =
                ) {
            @Override
            protected void populateViewHolder(DonasiViewHolder viewHolder, Donasi model, int position) {
                viewHolder.donasi_name.setText(model.getNama());
                Picasso.with(getBaseContext()).load(model.getGambar())
                        .into(viewHolder.donasi_image);

                final Donasi local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new activity
                        Intent donasiDetail = new Intent(DonasiList.this , DonasiDetail.class);
                        donasiDetail.putExtra("DonasiId",adapter.getRef(position).getKey()); //Send DonasiId to new activity
                        startActivity(donasiDetail);
                    }
                });
            }
        };
        //Set Adapter
        recyclerView.setAdapter(adapter);
    }
}
