package com.example.helloworld.learn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private List<Item> mList;//数据源
    private LayoutInflater mInflater;//布局装载器对象
    public MyListAdapter(MainActivity mainActivity, List<Item> items) {
       mInflater=LayoutInflater.from(mainActivity);
       mList=items;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(R.layout.list_item,null);
        }
        /**
         * 找到item布局文件中对应的控件
         */
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        TextView titleTextView = (TextView) view.findViewById(R.id.textView1);
        TextView contextTextView = (TextView) view.findViewById(R.id.textView2);
        //获取相应索引的ItemBean对象
        Item item = mList.get(i);
        /**
         * 设置控件的对应属性值
         */
        imageView.setImageResource(item.getImage());
        titleTextView.setText(item.getTitle());
        contextTextView.setText(item.getContext());
        return view;
    }
}
