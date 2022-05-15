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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

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
//        Glide.with(holder.img.getContext()).load(model.getPrimgurl()).into(holder.img);
        Glide.with(holder.img.getContext())
                .load(model.getPrimgurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

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

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_upd_prod))
                        .setExpanded(true, 1600)
                        .create();

                View myview = dialogPlus.getHolderView();
                EditText updpname = myview.findViewById(R.id.upd_pname);
                EditText updpprice = myview.findViewById(R.id.upd_pprice);
                EditText updpurl = myview.findViewById(R.id.upd_purl);
                Button update = myview.findViewById(R.id.btn_updsub);

                updpname.setText(model.getPrname());
                updpprice.setText(String.valueOf(model.getPrprice().shortValue()));
                updpurl.setText(model.getPrimgurl());

                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("prname", updpname.getText().toString());
                        map.put("prprice", Integer.parseInt(updpprice.getText().toString()));
                        map.put("primgurl", updpurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Product")
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
    public prodviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prodsinglerow,parent,false);
        return new prodviewholder(view);
    }

    class prodviewholder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView prdname, prdprice;
        ImageButton editbtn,dltbtn;

        public prodviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img1);
            prdname = (TextView) itemView.findViewById(R.id.prod_name);
            prdprice = (TextView) itemView.findViewById(R.id.prod_price);

            editbtn=(ImageButton)itemView.findViewById(R.id.editbtn);
            dltbtn=(ImageButton)itemView.findViewById(R.id.dltbtn);
        }
    }
}
