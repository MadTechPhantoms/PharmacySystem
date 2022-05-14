package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

public class MsgDeleteProfile extends AppCompatActivity {

    Button btn_DeleteCardYes;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_delete_profile);

        btn_DeleteCardYes = findViewById(R.id.btn_DeleteCardYes);
        Intent intent = getIntent();
        uid = intent.getStringExtra("user");

        btn_DeleteCardYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("User")
                        .document(uid)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MsgDeleteProfile.this,"Successfully Deleted", Toast.LENGTH_SHORT).show();
                                backWelcomePage();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.e("task","Error!!",e.getCause());

                    }
                });

            }
        });

    }

    public void backWelcomePage() {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
    }

    public void backEditProfile(View view) {
        Intent intent = new Intent(this, EditUserProfile.class);

        startActivity(intent);
    }
}