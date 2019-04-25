package com.example.austcanteen;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List <ListItems> listItems;
    private Context context;
    private String available;

    public MyAdapter(List<ListItems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItems obj = listItems.get(position);

        holder.nameTv.setText(obj.getName());
        holder.priceTv.setText(obj.getPrice());
        holder.ratingBar.setRating(obj.getRating());
        available = obj.getAvailibility();
        if(available.equals("YES")){
            holder.availableTV.setText("Available");
            holder.availableTV.setTextColor(ContextCompat.getColor(context, R.color.available));
        } else{
            holder.availableTV.setText("Not Available");
            holder.availableTV.setTextColor(ContextCompat.getColor(context, R.color.notAvailable));
        }

        holder.imageView.setImageBitmap(obj.getBmp());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView priceTv;
        ImageView imageView;
        RatingBar ratingBar;
        TextView availableTV;
        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView)itemView.findViewById(R.id.nameTV);
            priceTv = (TextView)itemView.findViewById(R.id.priceTV);
            imageView = (ImageView) itemView.findViewById(R.id.imageIV);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingRB);
            availableTV = (TextView) itemView.findViewById(R.id.availableTV);
        }
    }
}
