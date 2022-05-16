package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewUserProfile extends AppCompatActivity {

    TextView tv_UserName, tv_UserEmail;
    Button btn_EditProfile;

    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);

        tv_UserName = findViewById(R.id.tv_UserName);
        tv_UserEmail = findViewById(R.id.tv_UserEmail);
        btn_EditProfile = findViewById(R.id.btn_EditProfile);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        getData();

    }

    private void getData() {
        DocumentReference documentReference = fstore.collection("User").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String emailProfile = (String) documentSnapshot.getData().get("email");
                String  fNameProfile= (String) documentSnapshot.getData().get("firstName");
                String lNameProfile = (String) documentSnapshot.getData().get("lastName");
                setData(emailProfile, fNameProfile, lNameProfile);

                btn_EditProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = getIntent();
                        String uid = intent.getStringExtra("user");
                        Intent intent2 = new Intent(ViewUserProfile.this, EditUserProfile.class);
                        intent2.putExtra("user", uid);
                        startActivity(intent2);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ViewUserProfile.this, "Data not sent", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setData(String email, String firstName, String lastName) {
        tv_UserName.setText(firstName + lastName);
        tv_UserEmail.setText(email);
    }

    public void openCardOptions(View view) {
        Intent intent = new Intent(this, CardOptions.class);

        startActivity(intent);
    }

    public void openWelcome(View view) {
        Intent intent = new Intent(this, WelcomePage.class);

        startActivity(intent);
    }

    public void backProductPage(View view) {
        Intent intent = new Intent(this, ProdViewCus.class);

        startActivity(intent);
    }

}