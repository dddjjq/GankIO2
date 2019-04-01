package com.welson.gankio2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.welson.gankio2.R;
import com.welson.gankio2.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalendarRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<String> dates;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_ICON = 1;
    private boolean isFocusInit = false;
    private HashMap<Integer,NormalViewHolder> viewHolderHashMap;
    private int normalTextColor = Color.parseColor("#000000");
    private int normalSmallTextColor = Color.parseColor("#A9A9A9");
    private int selectTextColor = Color.parseColor("#00ACC1");
    private OnDateSelectedListener onDateSelectedListener;

    public CalendarRecyclerAdapter(Context context, ArrayList<String> dates){
        this.context = context;
        this.dates = dates;
        viewHolderHashMap = new HashMap<>();
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
                final int currentPosition = position;
                NormalViewHolder normalViewHolder = (NormalViewHolder)holder;
                viewHolderHashMap.put(currentPosition,normalViewHolder);
                if (position == 0 && !isFocusInit){
                    setItemSelected(0);
                    isFocusInit = true;
                }
                String day = DateUtil.getDay(dates.get(position));
                String week = DateUtil.getWeek(dates.get(position));
                String month = DateUtil.getMonth(dates.get(position));
                normalViewHolder.day.setText(day);
                normalViewHolder.week.setText(week);
                normalViewHolder.month.setText(month);
                normalViewHolder.calendarLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setItemSelected(currentPosition);
                        if (onDateSelectedListener != null){
                            onDateSelectedListener.onDateSelect(DateUtil.getRequestDate(dates.get(currentPosition)));
                        }
                    }
                });
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
        if (position == dates.size()){
            return TYPE_ICON;
        }else {
            return TYPE_NORMAL;
        }
    }

    private void setItemSelected(int position){
        for (Map.Entry<Integer,NormalViewHolder> entry : viewHolderHashMap.entrySet()){
            if (entry.getKey() == position){
                entry.getValue().day.setTextColor(selectTextColor);
                entry.getValue().week.setTextColor(selectTextColor);
                entry.getValue().month.setTextColor(selectTextColor);
            }else {
                entry.getValue().day.setTextColor(normalTextColor);
                entry.getValue().week.setTextColor(normalSmallTextColor);
                entry.getValue().month.setTextColor(normalSmallTextColor);
            }
        }

    }

    class NormalViewHolder extends RecyclerView.ViewHolder{
        TextView day,week,month;
        RelativeLayout calendarLayout;
        NormalViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.calendar_date);
            week = itemView.findViewById(R.id.calendar_week);
            month = itemView.findViewById(R.id.calendar_month);
            calendarLayout = itemView.findViewById(R.id.calendar_layout);
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        IconViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.calendar_icon);
        }
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener){
        this.onDateSelectedListener = onDateSelectedListener;
    }

    public interface OnDateSelectedListener{
        void onDateSelect(String date);
    }
}
