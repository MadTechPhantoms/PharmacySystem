package com.example.caringpharmacy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class Prodadapter extends FirebaseRecyclerAdapter<modelprod, Prodadapter.prodviewholder> {

    public Prodadapter(@NonNull FirebaseRecyclerOptions<modelprod> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull prodviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull modelprod model) {
        holder.prdname.setText(model.getPrname());
        holder.prdprice.setText(("LKR ") + String.valueOf(model.getPrprice().shortValue()) + ".00");
        Glide.with(holder.img.getContext()).load(model.getPrimgurl()).into(holder.img);

        holder.dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Product");
                builder.setMessage("Are you sure you want to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Product").child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public prodviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prodsinglerow,parent,false);
        return new prodviewholder(view);
    }

    class prodviewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView prdname, prdprice;
        ImageButton editbtn,dltbtn;

        public prodviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            prdname = (TextView) itemView.findViewById(R.id.prod_name);
            prdprice = (TextView) itemView.findViewById(R.id.prod_price);

            editbtn=(ImageButton)itemView.findViewById(R.id.editbtn);
            dltbtn=(ImageButton)itemView.findViewById(R.id.dltbtn);
        }
    }
}
