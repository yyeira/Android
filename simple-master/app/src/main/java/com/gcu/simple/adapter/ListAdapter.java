package com.gcu.simple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gcu.simple.R;
import com.gcu.simple.model.ImageInfo;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    List<ImageInfo> dataList;
    Context context;

    public ListAdapter(List<ImageInfo> dataList, Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_list, null);
            convertView.setTag(viewHolder);

            viewHolder.imageView = convertView.findViewById(R.id.imageView);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Glide.with(context).load(dataList.get(position).imageUrl).into(viewHolder.imageView);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView imageView;
    }

    public void update(List<ImageInfo> data){
        dataList = data;
        notifyDataSetChanged();
    }
}
