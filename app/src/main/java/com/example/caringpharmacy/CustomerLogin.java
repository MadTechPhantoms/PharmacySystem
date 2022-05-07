package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class CustomerLogin extends AppCompatActivity {

    EditText et_Email, et_Password;
    Button btn_SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        et_Email = findViewById(R.id.et_Email);
        et_Password = findViewById(R.id.et_Password);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_cus = database.getReference("Customer");

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog mDialog = new ProgressDialog(CustomerLogin.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                table_cus.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Check customer not exist in database
                        if(snapshot.child(et_Email.getText().toString()).exists()) {
                            //Get customer information
                            mDialog.dismiss();
                            Customer cus = snapshot.child(et_Email.getText().toString()).getValue(Customer.class);
                            if(cus.getPassword().equals(et_Password.getText().toString())) {
                                Toast.makeText(CustomerLogin.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(CustomerLogin.this, "Wrong Password !!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(CustomerLogin.this, "Customer not exist in database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    public void backWelcome(View view) {
        Intent intent = new Intent(this, WelcomePage.class);

        startActivity(intent);
    }

}