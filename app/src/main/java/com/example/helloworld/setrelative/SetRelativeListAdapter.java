package com.example.helloworld.setrelative;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.setrelative.entity.Relative;

import java.util.List;

public class SetRelativeListAdapter extends BaseAdapter {

    private List<Relative> mList;//数据源
    private LayoutInflater mInflater;//布局装载器对象
    public SetRelativeListAdapter(SetRelative setRelative, List<Relative> items) {
        mInflater=LayoutInflater.from(setRelative);
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
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item,null);
        }
        /**
         * 找到item布局文件中对应的控件
         */
        TextView name = convertView.findViewById(R.id.textView1);
        TextView number = convertView.findViewById(R.id.textView2);
//        ImageButton imageButton=(ImageButton) view.findViewById(R.id.imageButton);
        //获取相应索引的ItemBean对象
        Relative item = mList.get(i);
        /**
         * 设置控件的对应属性值
         */
        name.setText(item.getName());
        number.setText(item.getNumber());
        return convertView;
    }
}
