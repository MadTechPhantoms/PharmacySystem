package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminFeedbackView extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVAdapterAdmin adapter;
    ImageButton back;


    DAOFeedback dao;
    boolean isLoading=false;
    String key =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback_view);

        back = findViewById(R.id.backadmin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminFeedbackView.this,ProdMainActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout = findViewById(R.id.swip1);
        recyclerView = findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVAdapterAdmin(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOFeedback();
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (totalItem < lastVisible + 3) {
                    if (!isLoading) {
                        isLoading = true;
                        loadData();
                    }
                }
            }
        });


    }
    private void loadData()
    {

        swipeRefreshLayout.setRefreshing(true);
        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<Feedback> emps = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    Feedback emp = data.getValue(Feedback.class);
                    emp.setKey(data.getKey());
                    emps.add(emp);
                    key = data.getKey();
                }
                adapter.setItems(emps);
                adapter.notifyDataSetChanged();
                isLoading =false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}