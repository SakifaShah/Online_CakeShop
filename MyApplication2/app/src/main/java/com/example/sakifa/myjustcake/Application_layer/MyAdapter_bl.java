package com.example.sakifa.myjustcake.Application_layer;

/**
 * Created by sakifa on 3/9/18.
 */
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

        import com.example.sakifa.myjustcake.presentation_layer.Shop_cakes_pl;
        import com.example.sakifa.myjustcake.R;

        import java.util.List;

/**
 * Created by sakifa on 3/2/18.
 */

public class MyAdapter_bl extends RecyclerView.Adapter<MyAdapter_bl.ViewHolder> {
    public static String shopno=null;
    private List<ListItem_bl> listItems;
    private Context context;

    public MyAdapter_bl(List<ListItem_bl> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v,context,listItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem_bl listItem=listItems.get(position);
        holder.text1.setText(listItem.getText1());
        holder.text2.setText(listItem.getText2());


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView text1;
        public TextView text2;
        public LinearLayout linearLayout;
        Context context;
        List<ListItem_bl> listItems;
        Button button1;
        public ImageView imageView;
        public ViewHolder(View itemView,Context context,List<ListItem_bl> listItems) {
            super(itemView);
            this.context=context;
            this.listItems=listItems;
            text1=(TextView)itemView.findViewById(R.id.text1);
            text2=(TextView)itemView.findViewById(R.id.text2);
            button1=(Button)itemView.findViewById(R.id.button1);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearlayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            ListItem_bl listItem=this.listItems.get(position);
            Intent intent=new Intent(this.context,Shop_cakes_pl.class);
            //intent.putExtra("shopno",listItem.getText2());
            shopno=listItem.getText2();
            context.startActivity(intent);

        }
    }

}
