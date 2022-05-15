package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText et_SignUpEmail, et_Fname, et_Lname, et_Phone, et_SignUpPass, et_ConfirmPass;
    Button btn_SignUp;
    private String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String uid;

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

        mAuth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateFields();

            }
        });
    }

    private void LogUser() {
        Intent intent = new Intent(this, CustomerLogin.class);
        startActivity(intent);

    }

    private void validateFields() {

        String email, firstName, lastName, mobile, sPass, cPass;

        email=et_SignUpEmail.getText().toString();
        firstName=et_Fname.getText().toString();
        lastName=et_Lname.getText().toString();
        mobile=et_Phone.getText().toString();
        sPass=et_SignUpPass.getText().toString();
        cPass=et_ConfirmPass.getText().toString();


        if(!(email.isEmpty())){

            if(!(firstName.isEmpty())){
                if(!(lastName.isEmpty())){
                    if (!(mobile.isEmpty())){
                        if(!(sPass.isEmpty())){
                            if(!(cPass.isEmpty())){

                                if(email.matches(EmailPattern)){

                                    String passwordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";
                                    if(sPass.matches(passwordPattern)){
                                        if (cPass.matches(passwordPattern)){
                                            if(sPass.equals(cPass)) {

                                                RegisterCustomer(email, firstName, lastName, mobile, sPass);
                                                Toast.makeText(SignUp.this, "Hello User " + firstName + " " + lastName, Toast.LENGTH_SHORT).show();

                                            }else{
                                                et_ConfirmPass.setError("password should be same");
                                            }
                                        }else{
                                            et_ConfirmPass.setError("password length should 8-24");
                                        }
                                    }else{
                                        et_SignUpPass.setError("password length should 8-24");
                                    }
                                }else{
                                    et_SignUpEmail.setError("Invalid Email");
                                }
                            }else{
                                et_ConfirmPass.setError("Please fill this");
                            }
                        }else{
                            et_SignUpPass.setError("Please fill this");
                        }

                    }else{
                        et_Phone.setError("Please fill this");
                    }

                }else{
                    et_Lname.setError("Please fill this");
                }

            }else{
                et_Fname.setError("Please fill this");
            }

        }else{
            et_SignUpEmail.setError("Please fill this");
        }

    }

        private void RegisterCustomer(String email, String firstName, String lastName, String mobile, String sPass) {

        mAuth.createUserWithEmailAndPassword(email,sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //success
                if(task.isSuccessful()){

                    //save user in the firebase

                    uid = mAuth.getCurrentUser().getUid();
                    Map<String,Object> user = new HashMap<>();
                    user.put("email",email);
                    user.put("firstName",firstName);
                    user.put("lastName",lastName);
                    user.put("phoneNo",mobile);
                    user.put("password",sPass);

                    DocumentReference documentReference =fstore.collection("User").document(uid);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUp.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            clearControls();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUp.this, "Data Not Saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    LogUser();

                }else{
                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //not success
            }
        });
    }

    public void backWelcome(View view) {
        Intent intent = new Intent(this, WelcomePage.class);

        startActivity(intent);
    }

    //Method to clear all user inputs
    public void clearControls() {
        et_SignUpEmail.setText("");
        et_Fname.setText("");
        et_Lname.setText("");
        et_Phone.setText("");
        et_SignUpPass.setText("");
        et_ConfirmPass.setText("");
    }

}