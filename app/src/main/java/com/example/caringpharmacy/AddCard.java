package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCard extends AppCompatActivity {

    RadioButton radbtn_AddDebit, radbtn_AddCredit;
    EditText et_AddCardNo, et_AddHolder, et_AddExpDate, et_AddCVV;
    Button btn_AddCard;
    Card crd;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        radbtn_AddDebit = findViewById(R.id.radbtn_ViewDebit);
        radbtn_AddCredit = findViewById(R.id.radbtn_ViewCredit);
        et_AddCardNo = findViewById(R.id.et_CardNo);
        et_AddHolder = findViewById(R.id.et_Holder);
        et_AddExpDate = findViewById(R.id.et_ExpDate);
        et_AddCVV = findViewById(R.id.et_CVV);

        btn_AddCard = findViewById(R.id.btn_UpdateCard);

        crd = new Card();
    }
    //Method to clear all user inputs
    public void clearControls() {
        et_AddCardNo.setText("");
        et_AddHolder.setText("");
        et_AddExpDate.setText("");
        et_AddCVV.setText("");
    }

    public void createCard(View view) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Card");
     try {
            if(TextUtils.isEmpty(et_AddCardNo.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a Card Number", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_AddHolder.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a Holder Name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_AddExpDate.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter an Exp. Date", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_AddCVV.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a CVV", Toast.LENGTH_SHORT).show();
            } else {
                //Take inputs from the user and assigning them to this instance (crd) of the Card..

                crd.setCardNo(et_AddCardNo.getText().toString().trim());
                crd.setHolderName(et_AddHolder.getText().toString().trim());
                crd.setExpDate(et_AddExpDate.getText().toString().trim());
                crd.setCvv(Integer.parseInt(et_AddCVV.getText().toString().trim()));

                //Insert in to the database...
                /*dbRef.push().setValue(crd);*/
                dbRef.child("Card1").setValue(crd); //to replace data to same record
                //Feedback to the user via a Toast...
                Toast.makeText(getApplicationContext(), "Card added Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        } catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Card Number", Toast.LENGTH_SHORT).show();
        }
    }

    public void backCardOptions(View view) {
        Intent intent = new Intent(this, CardOptions.class);

        startActivity(intent);
    }

}