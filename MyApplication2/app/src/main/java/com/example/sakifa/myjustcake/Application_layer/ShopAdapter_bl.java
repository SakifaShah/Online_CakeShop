package com.example.sakifa.myjustcake.Application_layer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;

import java.util.List;

/**
 * Created by sakifa on 3/23/18.
 */

public class ShopAdapter_bl extends RecyclerView.Adapter<ShopAdapter_bl.ViewHolder>{
    private List<Shop_cake_list_bl> listitems;
    private Context context;
    String shopno= MyAdapter_bl.shopno;
    String imgurl= Constant.imgurl;
    public static String cakeno=null;

    public ShopAdapter_bl(List<Shop_cake_list_bl> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ShopAdapter_bl.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_cakes,parent,false);
        return new ViewHolder(v,context,listitems);
    }

    @Override
    public void onBindViewHolder(ShopAdapter_bl.ViewHolder holder, int position) {
               Shop_cake_list_bl list=listitems.get(position);
               holder.txt1.setText(list.getTxt2());
               holder.txt2.setText("Price : "+list.getTxt3());
               holder.txt3.setText("Stock : "+list.getTxt4());
               cakeno=list.getTxt1();


               String name=shopno+"_"+list.getTxt1()+".jpg";
        Mysingleton.ImageHandler.getSharedInstance(context).load(imgurl+name).into(holder.imageView);
        //Picasso.with(context).load(imgurl+name).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txt1,txt2,txt3;
        ImageView imageView;
        LinearLayout linearLayout;
        List<Shop_cake_list_bl> listitems;
        Context context;
        Button button;
        public ViewHolder(View itemView,Context context,List<Shop_cake_list_bl> listitems) {
            super(itemView);
            this.listitems=listitems;
            this.context=context;
            imageView=(ImageView)itemView.findViewById(R.id.cakeimage);
            txt1=(TextView)itemView.findViewById(R.id.cakename);
            txt2=(TextView)itemView.findViewById(R.id.price);
            txt3=(TextView)itemView.findViewById(R.id.stock);
            button=(Button)itemView.findViewById(R.id.details);
            button.setOnClickListener(this);
            linearLayout=itemView.findViewById(R.id.linearlayout);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Shop_cake_list_bl list=this.listitems.get(position);
            Intent intent=new Intent(context,Order2_bl.class);
            cakeno=list.getTxt1();
            context.startActivity(intent);


        }
    }
}
