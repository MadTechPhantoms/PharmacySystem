package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLogin extends AppCompatActivity {

    EditText et_Email, et_Password;
    Button btn_SignIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        et_Email = findViewById(R.id.et_Email);
        et_Password = findViewById(R.id.et_Password);
        btn_SignIn=findViewById(R.id.btn_SignIn);

        mAuth=FirebaseAuth.getInstance();

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();

            }
        });

    }

    private void logUser() {
        Intent intent = new Intent(CustomerLogin.this,ViewUserProfile.class);
        String uid = mAuth.getCurrentUser().getUid();
        intent.putExtra("user", uid);
        startActivity(intent);
    }

    private void validateFields() {
        String username,password;
        username=et_Email.getText().toString();
        password=et_Password.getText().toString();

        //validation
        logUser(username,password);
    }

    private void logUser(String username, String password) {
        mAuth.signInWithEmailAndPassword(username,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                logUser();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(CustomerLogin.this, "Error!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void backWelcome(View view) {
        Intent intent = new Intent(this, WelcomePage.class);

        startActivity(intent);
    }

}