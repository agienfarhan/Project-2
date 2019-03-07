package da.glowroz.donationappserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import da.glowroz.donationappserver.Common.Common;
import da.glowroz.donationappserver.Interface.ItemClickListener;
import da.glowroz.donationappserver.Model.Donasi;
import da.glowroz.donationappserver.Model.Kategori;
import da.glowroz.donationappserver.ViewHolder.DonasiViewHolder;
import info.hoang8f.widget.FButton;

public class DonasiList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;

    //firebase
    FirebaseDatabase db;
    DatabaseReference donasiList;
    FirebaseStorage storage;
    StorageReference storageReference;

    String kategoriId = "";

    FirebaseRecyclerAdapter<Donasi, DonasiViewHolder> adapter;

    //Add new donasi
    MaterialEditText edtNama, edtDesc;
    FButton btnSelect, btnUpload;

    Donasi newDonasi;

    Uri saveUri;

    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_list);

        //firebase
        db = FirebaseDatabase.getInstance();
        donasiList = db.getReference("Donasi");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //init
        recyclerView = (RecyclerView) findViewById(R.id.recycler_donasi);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDonasiDialog();
            }
        });
        if (getIntent() != null) {
            kategoriId = getIntent().getStringExtra("KategoriId");
        }
        if (!kategoriId.isEmpty()) {
            loadListDonasi(kategoriId);
        }
    }

    private void showAddDonasiDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DonasiList.this);
        alertDialog.setTitle("Tambah Donasi");
        alertDialog.setMessage("Mohon Isi Informasi");

        LayoutInflater inflater = this.getLayoutInflater();
        View add_donasi_layout = inflater.inflate(R.layout.add_new_donasi_layout, null);

        edtNama = (MaterialEditText) add_donasi_layout.findViewById(R.id.edtNama);
        edtDesc = (MaterialEditText) add_donasi_layout.findViewById(R.id.edtDesc);
        btnSelect = (FButton) add_donasi_layout.findViewById(R.id.btnSelect);
        btnUpload = (FButton) add_donasi_layout.findViewById(R.id.btnUpload);

        //Event untuk button
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar();      //pilih gambar dari galery
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadGambar();

            }
        });

        alertDialog.setView(add_donasi_layout);
        alertDialog.setIcon(R.drawable.ic_create_new_folder_black_24dp);

        //Set Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Untuk Menambahkan Kategori
                if (newDonasi != null) {
                    donasiList.push().setValue(newDonasi);
                    Snackbar.make(rootLayout, "Donasi Baru" + newDonasi.getNama() + " telah ditambahkan", Snackbar.LENGTH_SHORT).show();

                }
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void uploadGambar() {
        if (saveUri != null) {
            final ProgressDialog mDialog = new ProgressDialog(this);
            mDialog.setMessage("Uploading...");
            mDialog.show();

            String Namagambar = UUID.randomUUID().toString();
            final StorageReference Foldergambar = storageReference.child("images/" + Namagambar);
            Foldergambar.putFile(saveUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mDialog.dismiss();
                            Toast.makeText(DonasiList.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            Foldergambar.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //set value untuk newKDpnasi ketika upload dan mendapakan download link
                                    newDonasi = new Donasi();
                                    newDonasi.setNama(edtNama.getText().toString());
                                    newDonasi.setDeskripsi(edtDesc.getText().toString());
                                    newDonasi.setKategoriId(kategoriId);
                                    newDonasi.setGambar(uri.toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mDialog.dismiss();
                            Toast.makeText(DonasiList.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //Gak usah khawatir dengan error dibawah
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mDialog.setMessage("Uploaded " + progress + "%");
                        }
                    });
        }
    }

    private void pilihGambar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), Common.PICK_IMAGE_REQUEST);
    }


    private void loadListDonasi(String kategoriId) {
        adapter = new FirebaseRecyclerAdapter<Donasi, DonasiViewHolder>(
                Donasi.class,
                R.layout.donasi_item,
                DonasiViewHolder.class,
                donasiList.orderByChild("kategoriId").equalTo(kategoriId)
        ) {
            @Override
            protected void populateViewHolder(DonasiViewHolder viewHolder, Donasi model, int position) {
                viewHolder.donasi_name.setText(model.getNama());
                Picasso.with(getBaseContext()).load(model.getGambar()).into(viewHolder.donasi_image);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            saveUri = data.getData();
            btnSelect.setText("Gambar Dipilih");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(Common.UPDATE)) {
            showUpdateDonasiDialog(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));
        } else if (item.getTitle().equals(Common.DELETE)) {
            deleteDonasi(adapter.getRef(item.getOrder()).getKey());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteDonasi(String key) {
        donasiList.child(key).removeValue();
    }


    private void showUpdateDonasiDialog(final String key, final Donasi item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DonasiList.this);
        alertDialog.setTitle("Edit Donasi");
        alertDialog.setMessage("Mohon Isi Informasi");

        LayoutInflater inflater = this.getLayoutInflater();
        View add_donasi_layout = inflater.inflate(R.layout.add_new_donasi_layout, null);

        edtNama = (MaterialEditText) add_donasi_layout.findViewById(R.id.edtNama);
        edtDesc = (MaterialEditText) add_donasi_layout.findViewById(R.id.edtDesc);
        btnSelect = (FButton) add_donasi_layout.findViewById(R.id.btnSelect);
        btnUpload = (FButton) add_donasi_layout.findViewById(R.id.btnUpload);

        //Set default name
        edtNama.setText(item.getNama());
        edtDesc.setText(item.getDeskripsi());

        //Event untuk button
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar();      //pilih gambar dari galery
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gantiGambar(item);

            }
        });

        alertDialog.setView(add_donasi_layout);
        alertDialog.setIcon(R.drawable.ic_create_new_folder_black_24dp);

        //Set Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Untuk Menambahkan Kategori
                //update information
                item.setNama(edtNama.getText().toString());
                item.setDeskripsi(edtDesc.getText().toString());


                donasiList.child(key).setValue(item);
                Snackbar.make(rootLayout, "Donasi" + item.getNama() + " telah diubah", Snackbar.LENGTH_SHORT).show();


            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void gantiGambar(final Donasi item) {
        if (saveUri != null) {
            final ProgressDialog mDialog = new ProgressDialog(this);
            mDialog.setMessage("Uploading...");
            mDialog.show();

            String Namagambar = UUID.randomUUID().toString();
            final StorageReference Foldergambar = storageReference.child("images/" + Namagambar);
            Foldergambar.putFile(saveUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mDialog.dismiss();
                            Toast.makeText(DonasiList.this, "Uploaded!!!", Toast.LENGTH_SHORT).show();

                            Foldergambar.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //set value untuk newKategori ketika upload dan mendapakan download link
                                    item.setGambar(uri.toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mDialog.dismiss();
                            Toast.makeText(DonasiList.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //Gak usah khawatir dengan error dibawah
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mDialog.setMessage("Uploaded " + progress + "%");
                        }
                    });
        }
    }
}
