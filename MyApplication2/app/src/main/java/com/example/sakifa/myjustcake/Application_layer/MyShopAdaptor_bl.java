package com.example.sakifa.myjustcake.Application_layer;

/**
 * Created by sakifa on 3/16/18.
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.presentation_layer.Shop_info_pl;

import java.util.List;

/**
 * Created by sakifa on 3/2/18.
 */

public class MyShopAdaptor_bl extends RecyclerView.Adapter<MyShopAdaptor_bl.ViewHolder> {

    private List<MyShopItem_bl> listItems;

    private Context context;
    public static String t=null;

    //public ArrayList arrayList=MyShop_pl.arrayList;
    public MyShopAdaptor_bl(List<MyShopItem_bl> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v,context,listItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyShopItem_bl listItem = listItems.get(position);
        holder.text1.setText(listItem.getText1());
        holder.text2.setText(listItem.getText2());
        //t = listItem.getText2();
        /*String s= Integer.toString(position);
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, t, Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent(context, Shop_info_pl.class);
                context.startActivity(intent);

            }
        });*/

        //picasso
     //   Picasso.with(context).load("http://192.168.10.2/uploads/3_9.jpeg").into(cakeimage);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView text1;
        public TextView text2;
        public LinearLayout linearLayout;
        Button button1;
        Context context;
        List<MyShopItem_bl> listitems;
        public ViewHolder(View itemView,Context context,List<MyShopItem_bl> listitems) {
            super(itemView);
            this.context=context;
            this.listitems=listitems;
            text1 = (TextView) itemView.findViewById(R.id.text1);
            button1=(Button)itemView.findViewById(R.id.button1);
            text2 = (TextView) itemView.findViewById(R.id.text2);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            MyShopItem_bl myShopItem=this.listitems.get(position);
            Intent intent=new Intent(this.context,Shop_info_pl.class);
            t=myShopItem.getText2();
            context.startActivity(intent);
        }
    }

}
