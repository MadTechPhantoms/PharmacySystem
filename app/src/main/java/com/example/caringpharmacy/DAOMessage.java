package com.example.caringpharmacy;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOMessage {
    private DatabaseReference databaseReference;

    public  DAOMessage()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ContactUsData.class.getSimpleName());
    }

    public Task<Void> add(ContactUsData msg)
    {

        return databaseReference.push().setValue(msg);
    }
}
