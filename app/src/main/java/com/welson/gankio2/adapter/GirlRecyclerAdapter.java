package com.welson.gankio2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.welson.gankio2.R;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.util.GlideUtil;

import java.util.ArrayList;

public class GirlRecyclerAdapter extends RecyclerView.Adapter<GirlRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GankEntity> entities;

    public GirlRecyclerAdapter(Context context, ArrayList<GankEntity> entities){
        this.context = context;
        this.entities = entities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.girls_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideUtil.loadImage(context,entities.get(position).getUrl(),holder.girlImage);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView girlImage;

        public ViewHolder(View itemView) {
            super(itemView);
            girlImage = itemView.findViewById(R.id.girl_image);
        }
    }
}
