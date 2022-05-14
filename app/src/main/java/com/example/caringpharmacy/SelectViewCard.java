package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class SelectViewCard extends AppCompatActivity {

    RadioButton radbtn_Card1, radbtn_Card2, radbtn_Card3;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view_card);

        radbtn_Card1 = findViewById(R.id.radbtn_Card1);
        radbtn_Card2 = findViewById(R.id.radbtn_Card2);
        radbtn_Card3 = findViewById(R.id.radbtn_Card3);

        getData();
    }

    private void getData() {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Card");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    radbtn_Card1.setText(snapshot.child("cardNo").getValue().toString());

                    radbtn_Card1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(SelectViewCard.this, ViewCard.class);
                            startActivity(intent);
                        }
                    });
                }
                else {
                    //Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    Toast.makeText(SelectViewCard.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}