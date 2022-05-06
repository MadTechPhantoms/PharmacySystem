package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewUserProfile extends AppCompatActivity {

    TextView tv_UserName, tv_UserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);

        tv_UserName = findViewById(R.id.et_Fname);
        tv_UserEmail = findViewById(R.id.et_SignUpEmail);
    }

    public void readData(View view){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("Cus1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    tv_UserName.setText(snapshot.child("firstName").getValue().toString());
                    tv_UserName.setText(snapshot.child("lastName").getValue().toString());
                    tv_UserEmail.setText(snapshot.child("email").getValue().toString());
                }
                else {
                    //Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ViewUserProfile.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}