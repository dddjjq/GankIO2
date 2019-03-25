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
import com.welson.gankio2.util.DateUtil;

import java.util.ArrayList;

public class CalendarRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<String> dates;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_ICON = 1;

    public CalendarRecyclerAdapter(Context context, ArrayList<String> dates){
        this.context = context;
        this.dates = dates;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL){
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_item_layout,parent,false);
            return new NormalViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_item_icon,parent,false);
            return new IconViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_NORMAL:
                NormalViewHolder normalViewHolder = (NormalViewHolder)holder;
                String day = DateUtil.getDay(dates.get(position));
                String week = DateUtil.getWeek(dates.get(position));
                String month = DateUtil.getMonth(dates.get(position));
                normalViewHolder.day.setText(day);
                normalViewHolder.week.setText(week);
                normalViewHolder.month.setText(month);
                break;
            case TYPE_ICON:
                IconViewHolder iconViewHolder = (IconViewHolder)holder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dates.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dates.size() + 1){
            return TYPE_ICON;
        }else {
            return TYPE_NORMAL;
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder{
        TextView day,week,month;
        NormalViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.calendar_date);
            week = itemView.findViewById(R.id.calendar_week);
            month = itemView.findViewById(R.id.calendar_month);
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        IconViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.calendar_icon);
        }
    }
}
