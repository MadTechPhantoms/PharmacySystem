package com.example.caringpharmacy;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.text.BreakIterator;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cusprodadapter extends FirebaseRecyclerAdapter<modelprod,Cusprodadapter.prodviewholder> {
    DatabaseReference dbRef;
    modelprod prodobj;


    public Cusprodadapter(@NonNull FirebaseRecyclerOptions<modelprod> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull prodviewholder holder, int position, @NonNull modelprod model) {
        holder.prdname.setText(model.getPrname());
        holder.prdprice.setText(("LKR ") + String.valueOf(model.getPrprice().shortValue()) + ".00");
//        Glide.with(holder.img.getContext()).load(model.getPrimgurl()).into(holder.img);
        Glide.with(holder.img.getContext())
                .load(model.getPrimgurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);



        holder.rlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_prod_detail))
                        .setExpanded(true, 1600)
                        .create();

                View myview = dialogPlus.getHolderView();
                TextView prname = myview.findViewById(R.id.txtv_pname);
                ImageView img =myview.findViewById(R.id.imgprod);
                TextView prprice = myview.findViewById(R.id.txtv_pprice);
                EditText prquantity = myview.findViewById(R.id.txtv_quantity);
                Button buttonCart= myview.findViewById(R.id.buttonCart);
                TextView imageurl=myview.findViewById(R.id.imgurl);

                Glide.with(holder.img.getContext())
                        .load(model.getPrimgurl())
                        .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                        .circleCrop()
                        .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(img);



                prname.setText(model.getPrname());
                prprice.setText(String.valueOf(model.getPrprice().shortValue()));
                imageurl.setText(model.getPrimgurl());

                dialogPlus.show();


                buttonCart.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("Cart");
                        prodobj=new modelprod();

                        try{

                            prodobj.setPrname(prname.getText().toString().trim());
                            prodobj.setPrprice(Integer.parseInt(prprice.getText().toString().trim()));
                            prodobj.setPrquantity(Integer.parseInt(prquantity.getText().toString().trim()));
                            prodobj.setPrimgurl(imageurl.getText().toString().trim());

                            dbRef.push().setValue(prodobj);
                            dialogPlus.dismiss();


                        }catch(Exception e){
//                            Toast.makeText(getApplicationContext(),"Invalid price",Toast.LENGTH_SHORT).show();
                            System.out.println(e);
                        }
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public Cusprodadapter.prodviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cusprodsinglerow,parent,false);
        return new Cusprodadapter.prodviewholder(view);
    }

    class prodviewholder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView prdname, prdprice;
        RelativeLayout rlay;


        public prodviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgcus);
            prdname = (TextView) itemView.findViewById(R.id.prod_name2);
            prdprice = (TextView) itemView.findViewById(R.id.prod_price2);
            rlay=(RelativeLayout)itemView.findViewById(R.id.rlay);
        }
    }


}
