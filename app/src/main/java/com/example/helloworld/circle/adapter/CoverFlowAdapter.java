package com.example.helloworld.circle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

import java.util.ArrayList;

public class CoverFlowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 首先，我们新建一个NormalHolder，来保存布局中的控件所对应的变量。
     * 然后在onCreateViewHolder中返回新建的NormalHolder对象，
     * 最后通过onBindViewHolder将NormalHolder与数据绑定起来。
     */

    private Context mContext;
    private ArrayList<String> mDatas;
    private int mCreatedHolder = 0;
    int[] mPics = {R.mipmap.item4, R.mipmap.item2, R.mipmap.item3,R.mipmap.item1};

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mCreatedHolder++;
        Log.d("qijian", "onCreateViewHolder  num:"+mCreatedHolder);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new NormalHolder(inflater.inflate(R.layout.item_coverflow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("qijian", "onBindViewHolder");
        NormalHolder normalHolder = (NormalHolder) holder;
        normalHolder.mTV.setText(position+"p");
        normalHolder.mImg.setImageDrawable(ContextCompat.getDrawable(mContext, mPics[position % mPics.length]));

    }

    @Override
    public int getItemCount() {
        return mPics.length;
    }


    public CoverFlowAdapter(Context mContext/*, int[] mPics*/) {
        this.mContext = mContext;
        //this.mPics = mPics;
    }

    public class NormalHolder extends RecyclerView.ViewHolder {
        public TextView mTV;
        public ImageView mImg;

        public NormalHolder(@NonNull View itemView) {
            super(itemView);

            /**
             * mTV
             */
            mTV = (TextView) itemView.findViewById(R.id.text);
            mTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTV.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            /**
             * mImg
             */
            mImg = (ImageView) itemView.findViewById(R.id.img);
            mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTV.getText(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
