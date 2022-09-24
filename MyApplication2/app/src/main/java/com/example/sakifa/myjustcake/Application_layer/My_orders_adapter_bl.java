package com.example.sakifa.myjustcake.Application_layer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;


import java.util.List;

/**
 * Created by sakifa on 3/28/18.
 */

public class My_orders_adapter_bl extends RecyclerView.Adapter<My_orders_adapter_bl.ViewHolder>{
    public static String shopno= Tab_one_bl.shopno;
    private List<My_orders_list_bl> listItems;
    private Context context;
    String imgurl=Constant.imgurl;
    String name;

    public My_orders_adapter_bl(List<My_orders_list_bl> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override

    public My_orders_adapter_bl.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders,parent,false);
        return new My_orders_adapter_bl.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull My_orders_adapter_bl.ViewHolder holder, int position) {
        My_orders_list_bl listItem=listItems.get(position);
        holder.text1.setText(listItem.getText2());
        String cakeno=listItem.getText1();
        name=shopno+"_"+cakeno+".jpg";
        Mysingleton.ImageHandler.getSharedInstance(context).load(imgurl+name).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;
        public TextView text2;
        public LinearLayout linearLayout;
        Context context;
        List<My_orders_list_bl> listItems;
        Button button1;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            //this.context=context;
            //this.listItems=listItems;
            text1=(TextView)itemView.findViewById(R.id.text1);

            imageView=(ImageView)itemView.findViewById(R.id.cake1);
            //button1=(Button)itemView.findViewById(R.id.button1);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearlayout);
            //button1.setOnClickListener(this);
        }

        /*@Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            My_orders_list_bl listItem=this.listItems.get(position);
            Intent intent=new Intent(this.context,Shop_cakes_pl.class);
            //intent.putExtra("shopno",listItem.getText2());
            shopno=listItem.getText2();
            context.startActivity(intent);

        }*/
    }
}
