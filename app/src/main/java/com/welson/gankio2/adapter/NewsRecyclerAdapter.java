package com.welson.gankio2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.welson.gankio2.R;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.util.GlideUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class NewsRecyclerAdapter extends RecyclerView.Adapter {

    private LinkedHashMap<String, ArrayList<GankEntity>> gankEntities;
    private Context context;
    private static final int TYPE_FULI = 0;
    private static final int TYPE_HEAD = 1;
    private static final int TYPE_NORMAL = 2;
    private ArrayList<String> categorys;
    private ArrayList<GankEntity> allData;
    private String fuliUrl = "";
    private int currentCount = 0;

    public NewsRecyclerAdapter(Context context,LinkedHashMap<String, ArrayList<GankEntity>> gankEntities){
        this.gankEntities = gankEntities;
        this.context = context;
        init();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType){
            case TYPE_FULI:
                view =inflater.inflate(R.layout.news_fuli_item_layout, parent, false);
                return new FuliViewHolder(view);
            case TYPE_HEAD:
                view =inflater.inflate(R.layout.news_head_item_layout, parent, false);
                return new HeadViewHolder(view);
            case TYPE_NORMAL:
                view = inflater.inflate(R.layout.news_normal_item_layout, parent, false);
                return new NormalViewHolder(view);
        }
        view = inflater.inflate(R.layout.news_normal_item_layout, parent, false);
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_FULI:
                FuliViewHolder viewHolder = (FuliViewHolder)holder;
                GlideUtil.loadImage(context,fuliUrl,viewHolder.fuliImage);
                break;
            case TYPE_HEAD:
                HeadViewHolder headViewHolder = (HeadViewHolder)holder;
                if(currentCount < categorys.size()){
                    headViewHolder.headText.setText(categorys.get(currentCount));
                    currentCount++;
                }
                break;
            case TYPE_NORMAL:
                int realPosition = getRealPosition(position);
                NormalViewHolder normalViewHolder = (NormalViewHolder)holder;
                normalViewHolder.title.setText(allData.get(realPosition).getDesc());
                normalViewHolder.user.setText(allData.get(realPosition).getWho());
                normalViewHolder.time.setText(allData.get(realPosition).getPublishedAt().substring(0,10));
                if (allData.get(realPosition).getImages() != null){
                    normalViewHolder.image.setVisibility(View.VISIBLE);
                    Log.d("dingyl","url : " + allData.get(realPosition).getImages()[0]);
                    GlideUtil.loadImage(context,allData.get(realPosition).getImages()[0],normalViewHolder.image);
                }else {
                    normalViewHolder.image.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        int normalSize = 0;
        for (int i=0;i<categorys.size();i++){
            normalSize += gankEntities.get(categorys.get(i)).size();
        }
        return 1 + categorys.size() + normalSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_FULI;
        }else if (allData.get(position-1) == null){
            return TYPE_HEAD;
        }else {
            return TYPE_NORMAL;
        }
    }

    private int getRealPosition(int position){
        return position - 1;
    }

    private void init(){
        categorys = new ArrayList<>();
        allData = new ArrayList<>();
        for (Map.Entry<String,ArrayList<GankEntity>> entry : gankEntities.entrySet()){
            if (!entry.getKey().equals("福利")){
                categorys.add(entry.getKey());
                allData.add(null); // add for head
                allData.addAll(entry.getValue());
            }else {
                fuliUrl = entry.getValue().get(0).getUrl();
            }
        }
    }

    class FuliViewHolder extends RecyclerView.ViewHolder{

        ImageView fuliImage;

        FuliViewHolder(View itemView) {
            super(itemView);
            fuliImage = itemView.findViewById(R.id.news_fuli_image);
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{

        TextView headText;

        HeadViewHolder(View itemView) {
            super(itemView);
            headText = itemView.findViewById(R.id.news_head_item_text);
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView user;
        TextView time;
        ImageView image;

        NormalViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_normal_item_title);
            user = itemView.findViewById(R.id.news_normal_item_user);
            time = itemView.findViewById(R.id.news_normal_item_time);
            image = itemView.findViewById(R.id.news_normal_item_image);
        }
    }
}
