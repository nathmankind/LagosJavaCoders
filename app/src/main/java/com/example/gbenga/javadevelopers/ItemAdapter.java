package com.example.gbenga.javadevelopers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbenga.javadevelopers.controller.DetailActivity;
import com.example.gbenga.javadevelopers.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by GBENGA on 9/15/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList){
        this.context = applicationContext;
        this.items = itemArrayList;
    }
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int i){
        viewHolder.title.setText(items.get(i).getLogin());
        viewHolder.gitHubUrl.setText(items.get(i).getHtmlUrl());

        Picasso.with(context)
                .load(items.get(i).getAvatarUrl())
                .placeholder(R.drawable.images)
                .into(viewHolder.imageView);
    }
    @Override
    public int getItemCount(){
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView gitHubUrl;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.user_name);
            gitHubUrl = (TextView) view.findViewById(R.id.gitHubUrl);
            imageView = (ImageView) view.findViewById(R.id.user_image);

            // on item click
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Item clickedDataItem = items.get(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", items.get(position).getLogin());
                        intent.putExtra("html_url", items.get(position).getHtmlUrl());
                        intent.putExtra("avatar_url", items.get(position).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(context, "You clicked" + clickedDataItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
