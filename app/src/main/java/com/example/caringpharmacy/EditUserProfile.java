package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditUserProfile extends AppCompatActivity {

    EditText et_UserFname, et_UserLname, et_UserEmail, et_UserPhone;
    Button btn_SaveUserProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        
    }
}