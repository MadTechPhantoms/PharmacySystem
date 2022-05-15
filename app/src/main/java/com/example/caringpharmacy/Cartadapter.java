package com.example.caringpharmacy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.caringpharmacy.modelprod;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cartadapter extends FirebaseRecyclerAdapter<modelprod, com.example.caringpharmacy.Cartadapter.prodviewholder> {
    DatabaseReference dbRef;
    modelprod prodobj;
    int overTotalPrice=0;



    public Cartadapter(@NonNull FirebaseRecyclerOptions<modelprod> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull com.example.caringpharmacy.Cartadapter.prodviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull modelprod model) {
        holder.prdname.setText(model.getPrname());
        holder.prdprice.setText(("LKR ") + String.valueOf(model.getPrprice().shortValue()) + ".00");
        holder.prquantity.setText(String.valueOf(model.getPrquantity().shortValue()));
//        Glide.with(holder.img.getContext()).load(model.getPrimgurl()).into(holder.img);
                 Glide.with(holder.img.getContext())
                .load(model.getPrimgurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

//        int oneTypeProductTPrice=((Integer.valueOf(model.getPrprice()))) * Integer.valueOf(model.getPrquantity());
//        overTotalPrice=overTotalPrice+oneTypeProductTPrice;



        holder.dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.prquantity.getContext());
                builder.setTitle("Delete Product From Cart");
                builder.setMessage("Are you sure you want to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Cart").child(getRef(position).getKey()).removeValue();
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

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.prquantity.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cartupdate))
                        .setExpanded(true, 320)
                        .create();

                View myview = dialogPlus.getHolderView();
                EditText upQuantity = myview.findViewById(R.id.upQuantity);
                Button update = myview.findViewById(R.id.cartup);


          upQuantity.setText(String.valueOf(model.getPrquantity().shortValue()));



                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();

                        map.put("prquantity", Integer.parseInt(upQuantity.getText().toString()));


                        FirebaseDatabase.getInstance().getReference().child("Cart")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }





    @NonNull
    @Override
    public Cartadapter.prodviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartsingleview,parent,false);
        return new Cartadapter.prodviewholder(view);
    }

    class prodviewholder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView prdname, prdprice,prquantity;
        ImageButton editbtn,dltbtn;

        public prodviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img1);
            prdname = (TextView) itemView.findViewById(R.id.Cprod_name);
            prdprice = (TextView) itemView.findViewById(R.id.Cprod_price);
            prquantity = (TextView) itemView.findViewById(R.id.Cquantity);
            editbtn=(ImageButton)itemView.findViewById(R.id.Ceditbtn);
            dltbtn=(ImageButton)itemView.findViewById(R.id.Cdltbtn);

        }
    }
    }