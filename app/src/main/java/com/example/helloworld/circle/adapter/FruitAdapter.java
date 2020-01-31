package com.example.helloworld.circle.adapter;


//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;
import com.example.helloworld.circle.entity.Fruit;
import com.example.helloworld.circle.layoutManager.HorizonCustomLayoutManager;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;
    //内层适配器
    CoverFlowAdapter mcoverFlowAdapter;
    private Context mContext;

    public FruitAdapter(List<Fruit> fruitList, CoverFlowAdapter coverFlowAdapter, Context context) {
        mFruitList = fruitList;
        mcoverFlowAdapter = coverFlowAdapter;
        mContext = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        RecyclerView fruitImages;//正文所包含的图片
        TextView fruitName;//用户名字
        CircleImageView fruitImagec;//用户头像
        TextView fruitcontent;//朋友圈正文
        //TextView fruitdiscuss;//朋友圈评论
        ImageView fruitlike;//朋友圈点赞
        TextView fruitTime;//发送时间


        public ViewHolder(View view, List<Fruit> mFruitList) {//构造方法
            super(view);
            fruitView = view;
            fruitImages = view.findViewById(R.id.recycle_view_img);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
            fruitcontent = (TextView) view.findViewById(R.id.fruit_content);
            // fruitdiscuss = (TextView) view.findViewById(R.id.textView);
            fruitImagec = (CircleImageView) view.findViewById(R.id.profile_image);
            fruitlike = (ImageView) view.findViewById(R.id.like);
            fruitTime = (TextView) view.findViewById(R.id.fruit_time);

            fruitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Fruit fruit = mFruitList.get(position);
                    Toast.makeText(v.getContext(),
                            "you clicked view" + fruit.getName(),
                            Toast.LENGTH_SHORT).show();
                }
            });
//            fruitImages.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    Fruit fruit = mFruitList.get(position);
//                    Toast.makeText(v.getContext(),
//                            "you clicked image" + fruit.getName(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
            fruitcontent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Fruit fruit = mFruitList.get(position);
                    Toast.makeText(v.getContext(),
                            "you clicked Content" + fruit.getContent(),
                            Toast.LENGTH_SHORT).show();
                }
            });
            fruitlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positon = getAdapterPosition();
                    Fruit fruit = mFruitList.get(positon);

                    if (fruit.getLiked() > 0) {
                        Log.d("sas", "哭了");
                        fruitlike.setImageResource(R.drawable.liked);
                        fruit.setLiked(-1);
                    } else {
                        Log.d("sas", "笑了");
                        fruitlike.setImageResource(R.drawable.like);
                        fruit.setLiked(1);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.circle_item, viewGroup, false);
        /**the other way to realize the createViewHolder,
         * you should new a ViewHolder to do youself work
         */
        //LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        //return new yourHolder(layoutInflater.inflate(R.layout.circle_item, viewGroup, false));
        //public class yourHolder extends RecyclerView.ViewHolder{  /* YOUR CODING */}
        final ViewHolder holder = new ViewHolder(view, mFruitList);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Fruit fruit = mFruitList.get(position);
        //RV-start

        mcoverFlowAdapter.mPics = new int[]{R.mipmap.ic_launcher_round + position, R.mipmap.ic_launcher_round + position + 1};
        viewHolder.fruitImages.setAdapter(mcoverFlowAdapter);
        //设计一个构造函数 直接传入img的高度来限制layoutManager的高度
//        HorizonCustomLayoutManager horizonCustomLayoutManager = new HorizonCustomLayoutManager(700);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);

//        viewHolder.fruitImages.setLayoutManager(horizonCustomLayoutManager);
        viewHolder.fruitImages.setLayoutManager(layoutManager);
        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(viewHolder.fruitImages);
        //RV-end
        viewHolder.fruitName.setText(fruit.getName());
        viewHolder.fruitImagec.setImageResource(fruit.getImageId());
        viewHolder.fruitcontent.setText(fruit.getContent());
        //viewHolder.fruitdiscuss.setText(fruit.getComment());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        viewHolder.fruitTime.setText(sdf.format(fruit.getSendTime()));
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}

