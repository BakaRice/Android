package com.example.helloworld.learn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.List;

public class MyListAdapter2 extends BaseAdapter {
    private List<Cou2> mList;//数据源
    private LayoutInflater mInflater;//布局装载器对象
    public MyListAdapter2(Main2Activity mainActivity, List<Cou2> items) {
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
            view = mInflater.inflate(R.layout.model,null);
        }
        /**
         * 找到item布局文件中对应的控件
         */
        TextView titleTextView = (TextView) view.findViewById(R.id.textView3);
//        ImageButton imageButton=(ImageButton) view.findViewById(R.id.imageButton);
        //获取相应索引的ItemBean对象
        Cou2 item = mList.get(i);
        /**
         * 设置控件的对应属性值
         */
        titleTextView.setText(i+1 +" "+item.getParttitle());
        return view;
    }
}
