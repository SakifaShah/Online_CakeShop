package com.example.sakifa.myjustcake.Application_layer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
 * Created by sakifa on 3/20/18.
 */

public class CakeAdaptor_bl extends RecyclerView.Adapter<CakeAdaptor_bl.ViewHolder> {
    String imgurl= Constant.imgurl;

    private List<CakeList_bl> listItems;
    String k= Tab_one_bl.shopno;
    private Context context;
    public  static String cakeno;
    //ImageView imageView;

    public CakeAdaptor_bl(List<CakeList_bl> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_item,parent,false);
        return new ViewHolder(v,context,listItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CakeList_bl listItem=listItems.get(position);

        holder.text2.setText(listItem.getText2());
        holder.text3.setText(listItem.getText3());
        holder.text4.setText(listItem.getText4());

        String name=k+"_"+listItem.getText1()+".jpg";
        Mysingleton.ImageHandler.getSharedInstance(context).load(imgurl+name).into(holder.imageView);
        //Picasso.with(context).load(imgurl+name).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView text2,text3,text4;
        public Button button1;
        ImageView imageView;
        Context context;
        List<CakeList_bl> listItems;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView,Context context,List<CakeList_bl> listItems) {
            super(itemView);
            this.context=context;
            this.listItems=listItems;
            button1=(Button)itemView.findViewById(R.id.details);
            text2=(TextView)itemView.findViewById(R.id.cakename);
            text3=(TextView)itemView.findViewById(R.id.cakeprice);
            text4=(TextView)itemView.findViewById(R.id.stock);
            imageView=(ImageView)itemView.findViewById(R.id.cakeimage);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearlayout);
            button1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            CakeList_bl cakeList=this.listItems.get(position);
            Intent intent=new Intent(context,Update2_bl.class);
            cakeno=cakeList.getText1();
            intent.putExtra("cakename",cakeList.getText2());
            intent.putExtra("cakeprice",cakeList.getText3());
            intent.putExtra("stock",cakeList.getText4());
            context.startActivity(intent);
        }
    }

}
