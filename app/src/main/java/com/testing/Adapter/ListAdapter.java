package com.testing.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testing.Bean.ListModel;
import com.testing.Interface.RecyclerInterface;
import com.testing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ListModel> list;
    Context context;
    int last;
    public ArrayList<ListModel> arraylist;
    RecyclerInterface recyclerInterface;

    public ListAdapter() {
    }

    public ListAdapter(List<ListModel> list, Context context, RecyclerInterface recyclerInterface) {
        this.list = list;
        this.context = context;
        this.arraylist = new ArrayList<ListModel>();
        this.arraylist.clear();
        this.arraylist.addAll(list);
        this.recyclerInterface = recyclerInterface;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_list, parent, false);
            return new RecycleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        final RecycleViewHolder holder = (RecycleViewHolder) viewHolder;


        holder.title_tv.setText(list.get(position).getTitle());
        holder.description_tv.setText(list.get(position).getDescription());

        if (!list.get(position).getImage().equalsIgnoreCase(""))
        {
            Picasso.with(context)
                    .load(""+list.get(position).getImage().replaceAll(" ", "%20"))
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(800,800)
                    .centerCrop()
                    .into(holder.news_img);
        } else {}

        holder.root_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerInterface.itemClick(position,"itemClick");
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }



    class RecycleViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout root_layout;
        TextView title_tv,description_tv;
        ImageView news_img;

        public RecycleViewHolder (View itemView) {
            super (itemView);
            this.root_layout = itemView.findViewById(R.id.root_layout);
            this.title_tv = itemView.findViewById (R.id.title_tv);
            this.description_tv = itemView.findViewById (R.id.description_tv);
            this.news_img = itemView.findViewById (R.id.news_img);
        }
    }

}
