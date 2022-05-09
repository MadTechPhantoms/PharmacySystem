package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class FeedbackMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_main);
        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_position = findViewById(R.id.edit_position);
        Button btn = findViewById(R.id.btn_submit);
        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v->
        {
            Intent intent =new Intent(FeedbackMainActivity.this, RVActivity.class);
            startActivity(intent);
        });
        DAOFeedback dao =new DAOFeedback();
        Feedback emp_edit = (Feedback)getIntent().getSerializableExtra("EDIT");
        if(emp_edit !=null)
        {
            btn.setText("UPDATE");
            edit_name.setText(emp_edit.getEmail());
            edit_position.setText(emp_edit.getFeedbackdt());
            btn_open.setVisibility(View.GONE);
        }
        else
        {
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }
        //add a feedback to database
        btn.setOnClickListener(v->
        {
            Feedback emp = new Feedback(edit_name.getText().toString(), edit_position.getText().toString());
            if(emp_edit==null)
            {
                dao.add(emp).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else
            {
                //update a feedback to database
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("email", edit_name.getText().toString());
                hashMap.put("feedbackdt", edit_position.getText().toString());
                dao.update(emp_edit.getKey(), hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });


    }
}

