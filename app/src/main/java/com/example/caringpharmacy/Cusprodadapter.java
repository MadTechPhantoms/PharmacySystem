package com.example.caringpharmacy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cusprodadapter extends FirebaseRecyclerAdapter<modelprod,Cusprodadapter.prodviewholder> {

    public Cusprodadapter(@NonNull FirebaseRecyclerOptions<modelprod> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull prodviewholder holder, int position, @NonNull modelprod model) {
        holder.prdname.setText(model.getPrname());
        holder.prdprice.setText(String.valueOf(model.getPrprice().shortValue()));
        Glide.with(holder.img.getContext()).load(model.getPrimgurl()).into(holder.img);
    }

    @NonNull
    @Override
    public Cusprodadapter.prodviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cusprodsinglerow,parent,false);
        return new Cusprodadapter.prodviewholder(view);
    }

    class prodviewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView prdname, prdprice;
        ImageButton viewbtn;

        public prodviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            prdname = (TextView) itemView.findViewById(R.id.prod_name);
            prdprice = (TextView) itemView.findViewById(R.id.prod_price);

            viewbtn = (ImageButton)itemView.findViewById(R.id.viewbtn);
        }
    }

}
