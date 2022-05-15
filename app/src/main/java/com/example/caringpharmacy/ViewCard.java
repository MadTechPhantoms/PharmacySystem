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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCard extends AppCompatActivity {

    String cardTypeD, cardTypeC;
    RadioButton radbtn_ViewDebit, radbtn_ViewCredit;
    EditText et_CardNo, et_Holder, et_ExpDate, et_CVV;
    Button btn_UpdateCard;
    Card crd;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

        radbtn_ViewDebit = findViewById(R.id.radbtn_ViewDebit);
        radbtn_ViewCredit = findViewById(R.id.radbtn_ViewCredit);
        et_CardNo = findViewById(R.id.et_CardNo);
        et_Holder = findViewById(R.id.et_Holder);
        et_ExpDate = findViewById(R.id.et_ExpDate);
        et_CVV = findViewById(R.id.et_CVV);

        cardTypeD = radbtn_ViewDebit.getText().toString().trim();
        cardTypeC = radbtn_ViewCredit.getText().toString().trim();

        btn_UpdateCard = findViewById(R.id.btn_UpdateCard);

        crd = new Card();

        showData();
    }
    //Method to clear all user inputs
    public void clearControls() {
        et_CardNo.setText("");
        et_Holder.setText("");
        et_ExpDate.setText("");
        et_CVV.setText("");
    }

    public void backCardOptions(View view) {
        Intent intent = new Intent(this, CardOptions.class);

        startActivity(intent);
    }

    public void showData(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Card").child("Card1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    et_CardNo.setText(snapshot.child("cardNo").getValue().toString());
                    et_Holder.setText(snapshot.child("holderName").getValue().toString());
                    et_ExpDate.setText(snapshot.child("expDate").getValue().toString());
                    et_CVV.setText(snapshot.child("cvv").getValue().toString());
                }
                else {
                    //Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ViewCard.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateCard(View view) {
        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Card");
        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("Card1")) {
                    try{
                        crd.setCardNo(et_CardNo.getText().toString().trim());
                        crd.setHolderName(et_Holder.getText().toString().trim());
                        crd.setExpDate(et_ExpDate.getText().toString().trim());
                        crd.setCvv(Integer.parseInt(et_CVV.getText().toString().trim()));

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Card").child("Card1");
                        dbRef.setValue(crd);
                        /*clearControls();*/
                        //Feedback to the user via Toast...
                        Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Invalid Card Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteCard(View view) {
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Card");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("Card1")) {
                    dbRef = FirebaseDatabase.getInstance().getReference().child("Card").child("Card1");
                    dbRef.removeValue();
                    clearControls();
                    Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}