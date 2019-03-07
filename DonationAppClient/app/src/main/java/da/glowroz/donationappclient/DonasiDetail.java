package da.glowroz.donationappclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import da.glowroz.donationappclient.Common.Common;
import da.glowroz.donationappclient.Database.Database;
import da.glowroz.donationappclient.Model.Donasi;
import da.glowroz.donationappclient.Model.Transaksi;
import info.hoang8f.widget.FButton;

public class DonasiDetail extends AppCompatActivity {

    TextView nama_donasi,deskripsi_donasi;
    ImageView gambar_donasi;

    CollapsingToolbarLayout collapsingToolbarLayout;
    FButton btnDonasi;

    String donasiId="";

    FirebaseDatabase database;
    DatabaseReference donasi;

    Donasi currentDonasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        donasi = database.getReference("Donasi");

        //Initialize view
        btnDonasi = (FButton)findViewById(R.id.btnDonasi);
        btnDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Nominal = new Intent(DonasiDetail.this,Nominal_Donasi.class);
                startActivity(Nominal);

            }
        });


        deskripsi_donasi = (TextView)findViewById(R.id.deskripsi_donasi);
        nama_donasi = (TextView)findViewById(R.id.nama_donasi);
        gambar_donasi = (ImageView)findViewById(R.id.gmbr_donasi);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);



        //Get Donasi Id from Intent
        if (getIntent() != null)
            donasiId = getIntent().getStringExtra("DonasiId");
        if (!donasiId.isEmpty())
        {
            getDonasiDetail(donasiId);
        }

    }


    private void getDonasiDetail(String donasiId) {
        donasi.child(donasiId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentDonasi = dataSnapshot.getValue(Donasi.class);

                //set Image
                Picasso.with(getBaseContext()).load(currentDonasi.getGambar())
                        .into(gambar_donasi);

                collapsingToolbarLayout.setTitle(currentDonasi.getNama());

                nama_donasi.setText(currentDonasi.getNama());

                deskripsi_donasi.setText(currentDonasi.getDeskripsi());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
