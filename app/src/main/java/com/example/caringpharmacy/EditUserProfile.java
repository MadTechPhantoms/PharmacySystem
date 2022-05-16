package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditUserProfile extends AppCompatActivity {

    EditText et_UserEmail, et_UserFname, et_UserLname,  et_UserPhone;
    Button btn_SaveUserProf;

    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String uid;
    String email, firstName, lastName, phoneNo;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        et_UserEmail = findViewById(R.id.et_UserEmail);
        et_UserFname = findViewById(R.id.et_UserFname);
        et_UserLname = findViewById(R.id.et_UserLname);
        et_UserPhone = findViewById(R.id.et_UserPhone);

        btn_SaveUserProf = findViewById(R.id.btn_SaveUserProf);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        user =mAuth.getCurrentUser();

        btn_SaveUserProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditUserProfile.this);
                builder.setTitle("Re Authentication");
                builder.setMessage("Enter Your Password");
                final EditText input = new EditText(EditUserProfile.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mPassword = input.getText().toString();
                        ReAuthentication(mPassword);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });
                builder.show();
            }
        });
        getData();

    }

    private void ReAuthentication(String mPassword) {
        AuthCredential credential = EmailAuthProvider.getCredential(email,mPassword);
        user.reauthenticate(credential).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                updateDetails();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void updateDetails() {
        String user_email = et_UserEmail.getText().toString();
        String user_fName = et_UserFname.getText().toString();
        String user_lName = et_UserLname.getText().toString();
        String user_phone = et_UserPhone.getText().toString();

        user.updateEmail(user_email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                fstore.collection("User").document(uid).update( "email",user_email);
                fstore.collection("User").document(uid).update( "firstName",user_fName);
                fstore.collection("User").document(uid).update( "lastName",user_lName);
                fstore.collection("User").document(uid).update( "phoneNo",user_phone);

                Toast.makeText(EditUserProfile.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(EditUserProfile.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {

        DocumentReference documentReference = fstore.collection("User").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                email = (String) documentSnapshot.getData().get("email");
                firstName= (String) documentSnapshot.getData().get("firstName");
                lastName= (String) documentSnapshot.getData().get("lastName");
                phoneNo = (String) documentSnapshot.getData().get("phoneNo");

                setData(email,firstName,lastName,phoneNo);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    private void setData(String email,String firstName,String lastName, String phoneNo) {
        et_UserEmail.setText(email);
        et_UserFname.setText(firstName);
        et_UserLname.setText(lastName);
        et_UserPhone.setText(phoneNo);
    }

    public void backUserProfile(View view) {
        Intent intent = new Intent(this, ViewUserProfile.class);

        startActivity(intent);
    }

    public void openDeleteProfile(View view) {
        Intent intent = getIntent();
        String uid = intent.getStringExtra("user");
        Intent intent2 = new Intent(this, MsgDeleteProfile.class);
        intent2.putExtra("user", uid);
        startActivity(intent2);
    }

}