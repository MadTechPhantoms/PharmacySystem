package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import android.os.Bundle;


public class ContactUs extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        back = findViewById(R.id.btn_backcont);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUs.this,ProdViewCus.class);
                startActivity(intent);
            }
        });

        final EditText cname = findViewById(R.id.cname);
        final EditText cphone = findViewById(R.id.cphone);
        final EditText cemail = findViewById(R.id.cemail);
        final EditText cmessage = findViewById(R.id.cmessage);
        CardView msg_btn = findViewById(R.id.msg_btn);
        DAOMessage daoMessage = new DAOMessage();
        msg_btn.setOnClickListener(view ->
                {
                    if(cname.getText().toString().isEmpty() || cphone.getText().toString().isEmpty() || cemail.getText().toString().isEmpty() || cmessage.getText().toString().isEmpty())
                    {
                        Toast.makeText(this,"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        if(cphone.getText().length()<10 || cphone.getText().length()>10)
                        {
                            cphone.setError("Enter a valid mobile number");
                        }else
                        {
                            if(cemail.getText().toString().trim().matches(emailPattern)){
                                ContactUsData msg = new ContactUsData(cname.getText().toString(),cphone.getText().toString(),cemail.getText().toString(),cmessage.getText().toString());
                                daoMessage.add(msg).addOnSuccessListener(sic ->
                                        {
                                            Toast.makeText(this,"Record is inserted!",Toast.LENGTH_SHORT).show();
                                            cname.setText("");
                                            cphone.setText("");
                                            cemail.setText("");
                                            cmessage.setText("");
                                        }
                                ).addOnFailureListener(er ->
                                        {
                                            Toast.makeText(this,"Fail"+er,Toast.LENGTH_SHORT).show();

                                        }
                                );

                            }else {
                                cemail.setError("Enter valid email");
                            }
                        }

                    }


                }
        );

    }
}