package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class EditUserProfile extends AppCompatActivity {

    EditText et_UserEmail, et_UserFname, et_UserLname,  et_UserPhone;
    Button btn_SaveUserProf;
    Customer cus;
    DatabaseReference dbRef;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        et_UserEmail = findViewById(R.id.et_UserEmail);
        et_UserFname = findViewById(R.id.et_UserFname);
        et_UserLname = findViewById(R.id.et_UserLname);
        et_UserPhone = findViewById(R.id.et_UserPhone);

        btn_SaveUserProf = findViewById(R.id.btn_SaveUserProf);

        cus = new Customer();

        Intent intent = getIntent();
        email= intent.getStringExtra("em");


        et_UserEmail.setText(email);

    }
    public void backUserProfile(View view) {
        Intent intent = new Intent(this, ViewUserProfile.class);

        startActivity(intent);
    }
    public void openDeleteProfile(View view) {
        Intent intent = new Intent(this, MsgDeleteProfile.class);

        startActivity(intent);
    }
    //Method to clear all user inputs
    public void clearControls() {
        et_UserEmail.setText("");
        et_UserFname.setText("");
        et_UserLname.setText("");
        et_UserPhone.setText("");
    }
    public void showData(View view){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("key");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    et_UserEmail.setText(snapshot.child("email").getValue().toString());
                    et_UserFname.setText(snapshot.child("firstname").getValue().toString());
                    et_UserLname.setText(snapshot.child("lastname").getValue().toString());
                    et_UserPhone.setText(snapshot.child("phoneNo").getValue().toString());
                }
                else {
                    //Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    Toast.makeText(EditUserProfile.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateData(View view) {
        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("key")) {
                    try{
                        cus.setEmail(et_UserEmail.getText().toString().trim());
                        cus.setFirstName(et_UserFname.getText().toString().trim());
                        cus.setLastName(et_UserLname.getText().toString().trim());
                        cus.setPhoneNo(Integer.parseInt(et_UserPhone.getText().toString().trim()));

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("key");
                        dbRef.setValue(cus);
                        clearControls();

                        //Feedback to the user via Toast...
                        Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
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

    public void deleteData(View view) {
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("key")) {
                    dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("key");
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