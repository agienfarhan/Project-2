package da.glowroz.donationappclient;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import da.glowroz.donationappclient.Database.Database;
import da.glowroz.donationappclient.Model.*;
import da.glowroz.donationappclient.Model.Donasi;
import info.hoang8f.widget.FButton;

public class Nominal_Donasi extends AppCompatActivity {

    TextView nama_donasi;
    MaterialEditText nominal_donasi;

    CollapsingToolbarLayout collapsingToolbarLayout;
    FButton btnNominal;

    String donasiId="";

    FirebaseDatabase database;
    DatabaseReference donasi;

    Donasi currentDonasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominal__donasi);



        //Firebase
        database = FirebaseDatabase.getInstance();
        donasi = database.getReference("Donasi");

        btnNominal = (FButton) findViewById(R.id.btnNominal);

        btnNominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToDonasi(new Transaksi(
                        donasiId,
                        currentDonasi.getNama(),
                        nominal_donasi.getText().toString()

                ));
                Toast.makeText(Nominal_Donasi.this, "Ditambahkan ke Donasi", Toast.LENGTH_SHORT).show();
            }
        });

        nominal_donasi = (MaterialEditText) findViewById(R.id.edtNominal);
        nama_donasi = (TextView)findViewById(R.id.nama_donasi);

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

                collapsingToolbarLayout.setTitle(currentDonasi.getNama());

                nama_donasi.setText(currentDonasi.getNama());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

