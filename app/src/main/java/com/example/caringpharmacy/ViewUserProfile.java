package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        tv_UserName = findViewById(R.id.tv_UserName);
        tv_UserEmail = findViewById(R.id.tv_UserEmail);
    }
    public void openCardOptions(View view) {
        Intent intent = new Intent(this, CardOptions.class);

        startActivity(intent);
    }
    public void openAccSettings(View view) {
        Intent intent = new Intent(this, AccSettings.class);

        startActivity(intent);
    }
   public void openEditProfile(View view) {
        Intent intent = new Intent(this, EditUserProfile.class);

        String userName = tv_UserName.getText().toString();
        String email = tv_UserEmail.getText().toString();

        intent.putExtra("un", userName);
        intent.putExtra("em", email);
    }

    public void readCustomer(View view){

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