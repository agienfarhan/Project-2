package da.glowroz.donationappserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import da.glowroz.donationappserver.Common.Common;
import da.glowroz.donationappserver.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class SignIn extends AppCompatActivity {
    EditText edtId, edtPass;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtId = (MaterialEditText) findViewById(R.id.edtId);
        edtPass = (MaterialEditText) findViewById(R.id.edtPass);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        //Init Firebase
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtId.getText().toString(), edtPass.getText().toString());
            }
        });
    }
    private void signInUser(final String id, String pass) {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Tunggu Sebentar...");
        mDialog.show();

        final String localId = id;
        final String localPass = pass;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localId).exists())    {

                    mDialog.dismiss();
                    User user = dataSnapshot.child(localId).getValue(User.class);
                    user.setId(localId);

                    if (Boolean.parseBoolean(user.getStaff()))    {

                        if (user.getPass().equals(localPass))   {
                            Intent loginIntent = new Intent(SignIn.this,Home.class);
                            Common.currentUser = user;
                            startActivity(loginIntent);
                            finish();
                        }
                        else    {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Password Salah !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else    {
                        mDialog.dismiss();
                        Toast.makeText(SignIn.this, "Silahkan Login dengan Akun Staff!", Toast.LENGTH_SHORT).show();
                    }
                }
                else    {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "User tidak ada di Database !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
