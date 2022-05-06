package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText et_Fname, et_Lname, et_SignUpEmail, et_Phone, et_SignUpPass, et_ConfirmPass;
    Button btn_SignUp;
    Customer cus;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_Fname = findViewById(R.id.et_Fname);
        et_Lname= findViewById(R.id.et_Lname);
        et_SignUpEmail = findViewById(R.id.et_SignUpEmail);
        et_Phone = findViewById(R.id.et_Phone);
        et_SignUpPass = findViewById(R.id.et_SignUpPass);
        et_ConfirmPass = findViewById(R.id.et_ConfirmPass);

        btn_SignUp = findViewById(R.id.btn_SignUp);

        cus = new Customer();
    }

    //Method to clear all user inputs
    public void clearControls() {
        et_Fname.setText("");
        et_Lname.setText("");
        et_SignUpEmail.setText("");
        et_Phone.setText("");
        et_SignUpPass.setText("");
        et_ConfirmPass.setText("");
    }

    public void createData(View view) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        try {
            if(TextUtils.isEmpty(et_Fname.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a First Name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_Lname.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a Last Name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_SignUpEmail.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter an Email address", Toast.LENGTH_SHORT).show();
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
                cus.setFirstName(et_Fname.getText().toString().trim());
                cus.setLastName(et_Lname.getText().toString().trim());
                cus.setEmail(et_SignUpEmail.getText().toString().trim());
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
    }
}