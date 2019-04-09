package com.welson.gankio2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.welson.gankio2.R;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.util.GlideUtil;

import java.util.ArrayList;

public class CategoryEntityRecyclerAdapter extends RecyclerView.Adapter<CategoryEntityRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GankEntity> entities;

    public CategoryEntityRecyclerAdapter(Context context, ArrayList<GankEntity> entities){
        this.context = context;
        this.entities = entities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_normal_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(entities.get(position).getDesc());
        holder.user.setText(entities.get(position).getWho());
        holder.time.setText(entities.get(position).getPublishedAt().substring(0, 10));
        if (entities.get(position).getImages() != null) {
            holder.image.setVisibility(View.VISIBLE);
            GlideUtil.loadImage(context, entities.get(position).getImages()[0], holder.image);
        } else {
            holder.image.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView user;
        TextView time;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_normal_item_title);
            user = itemView.findViewById(R.id.news_normal_item_user);
            time = itemView.findViewById(R.id.news_normal_item_time);
            image = itemView.findViewById(R.id.news_normal_item_image);
        }
    }
}
