package com.gcu.simple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gcu.simple.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<String> contents;
    Context context;
    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int position) {
        return contents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            // 初始化viewHolder
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_list, null);
            convertView.setTag(viewHolder);

        }else{
            // 复用旧对象
            viewHolder = (ViewHolder)convertView.getTag();
        }
        // 设置textview内容
        viewHolder.textView.setText(contents.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
