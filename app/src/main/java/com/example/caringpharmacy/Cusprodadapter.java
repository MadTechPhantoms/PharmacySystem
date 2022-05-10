package com.example.caringpharmacy;

import android.annotation.SuppressLint;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cusprodadapter extends FirebaseRecyclerAdapter<modelprod,Cusprodadapter.prodviewholder> {

    public Cusprodadapter(@NonNull FirebaseRecyclerOptions<modelprod> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull prodviewholder holder, int position, @NonNull modelprod model) {
        holder.prdname.setText(model.getPrname());
        holder.prdprice.setText(("LKR ") + String.valueOf(model.getPrprice().shortValue()) + ".00");
        Glide.with(holder.img.getContext()).load(model.getPrimgurl()).into(holder.img);

        holder.viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_prod_detail))
                        .setExpanded(true, 1600)
                        .create();

                View myview = dialogPlus.getHolderView();
                TextView prname = myview.findViewById(R.id.txtv_pname);
                TextView prprice = myview.findViewById(R.id.txtv_pprice);

                prname.setText(model.getPrname());
                prprice.setText(("LKR ") + String.valueOf(model.getPrprice().shortValue()) + ".00");

                dialogPlus.show();
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

        CircleImageView img;
        TextView prdname, prdprice;
        ImageButton viewbtn;

        public prodviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.imgcus);
            prdname = (TextView) itemView.findViewById(R.id.prod_name2);
            prdprice = (TextView) itemView.findViewById(R.id.prod_price2);

            viewbtn = (ImageButton)itemView.findViewById(R.id.viewbtn);
        }
    }

}
