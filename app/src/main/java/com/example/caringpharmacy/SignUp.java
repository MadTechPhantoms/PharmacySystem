package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText et_SignUpEmail, et_Fname, et_Lname, et_Phone, et_SignUpPass, et_ConfirmPass;
    Button btn_SignUp;
    Customer cus;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_SignUpEmail = findViewById(R.id.et_SignUpEmail);
        et_Fname = findViewById(R.id.et_Fname);
        et_Lname= findViewById(R.id.et_Lname);
        et_Phone = findViewById(R.id.et_Phone);
        et_SignUpPass = findViewById(R.id.et_SignUpPass);
        et_ConfirmPass = findViewById(R.id.et_ConfirmPass);

        btn_SignUp = findViewById(R.id.btn_SignUp);

        //Init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_cus = database.getReference("Customer");

        btn_SignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                table_cus.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Check if already customer email
                        if(snapshot.child(et_SignUpEmail.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Email already register", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDialog.dismiss();
                            Customer cus = new Customer(et_Fname.getText().toString(), et_Lname.getText().toString(), Integer.parseInt(et_Phone.getText().toString()), et_SignUpPass.getText().toString());
                            table_cus.child(et_SignUpEmail.getText().toString()).setValue(cus);
                            Toast.makeText(SignUp.this,"Sign up successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });

        //cus = new Customer();
    }
    public void backWelcome(View view) {
        Intent intent = new Intent(this, WelcomePage.class);

        startActivity(intent);
    }
/*    //Method to clear all user inputs
    public void clearControls() {
        et_SignUpEmail.setText("");
        et_Fname.setText("");
        et_Lname.setText("");
        et_Phone.setText("");
        et_SignUpPass.setText("");
        et_ConfirmPass.setText("");
    }

    public void createCustomer(View view) {
        Intent intent = new Intent(this, CustomerLogin.class);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        try {
            if(TextUtils.isEmpty(et_SignUpEmail.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter an Email address", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_Fname.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a First Name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_Lname.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a Last Name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_Phone.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a Mobile Number", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_SignUpPass.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a Password", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_ConfirmPass.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter the Password Again", Toast.LENGTH_SHORT).show();
            }
            else {
                //Take inputs from the user and assigning them to this instance (cus) of the Customer..
                cus.setEmail(et_SignUpEmail.getText().toString().trim());
                cus.setFirstName(et_Fname.getText().toString().trim());
                cus.setLastName(et_Lname.getText().toString().trim());
                cus.setPhoneNo(Integer.parseInt(et_Phone.getText().toString().trim()));
                cus.setPassword(et_SignUpPass.getText().toString().trim());

                //Insert in to the database...
                dbRef.push().setValue(cus);
                //dbRef.child("Cus1").setValue(cus); //to replace data to same record

                //Feedback to the user via a Toast...
                Toast.makeText(getApplicationContext(), "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        }
        catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }*/
}