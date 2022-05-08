package com.example.caringpharmacy.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.caringpharmacy.FeedbackCreateActivity;
import com.example.caringpharmacy.FeedbackEditActivity;
import com.example.caringpharmacy.R;


public class FeedbackFragment extends Fragment {

    private CardView add_feedback;
    private ImageButton edit;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        add_feedback = view.findViewById(R.id.add_feedback);
        edit = view.findViewById(R.id.edit);


        add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FeedbackCreateActivity.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FeedbackEditActivity.class);
                startActivity(intent);
            }
        });


        return  view;
    }
}