package com.example.helloworld.friendCircle.upload;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.helloworld.R;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.MimeType;
import com.ypx.imagepicker.bean.PickerError;
import com.ypx.imagepicker.data.OnImagePickCompleteListener2;
import com.ypx.imagepicker.presenter.IPickerPresenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UploadActivity_java extends AppCompatActivity {
    private ArrayList<ImageItem> picList = new ArrayList<>();
    private GridLayout mGridLayout;

    private int maxCount = 9;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload);
        initView();
        picList.clear();
        refreshGridLayout();
    }

    private void initView() {
        mGridLayout = findViewById(R.id.gridLayout);
    }

    private void preview(int pos) {
        IPickerPresenter presenter = new RedBookPresenter();
//        IPickerPresenter presenter = mRbWeChat.isChecked() ? new WeChatPresenter() : new CustomImgPickerPresenter();
//        if (mCbPreviewCanEdit.isChecked()) {
//            //开启编辑预览
//            ImagePicker.preview(this, presenter, picList, pos, new OnImagePickCompleteListener() {
//                @Override
//                public void onImagePickComplete(ArrayList<ImageItem> items) {
//                    //图片编辑回调，主线程
//                    picList.clear();
//                    picList.addAll(items);
//                    refreshGridLayout();
//                }
//            });
//        } else {
        //开启普通预览
        ImagePicker.preview(this, presenter, picList, pos, null);
        //}
    }

    private void startPick() {
//        ImagePicker.provideMediaSets(this, MimeType.ofAll(), new MediaSetsDataSource.MediaSetProvider() {
//            @Override
//            public void providerMediaSets(ArrayList<ImageSet> imageSets) {
//                Log.e("startPick", "providerMediaSets: " + imageSets.size());
//            }
//        });

//        ImagePicker.provideAllMediaItems(this, getMimeTypes(), new MediaItemsDataSource.MediaItemProvider() {
//            @Override
//            public void providerMediaItems(ArrayList<ImageItem> imageItems, ImageSet allVideoSet) {
//                Log.e("startPick", "providerMediaSets: " + imageItems.size());
//            }
//        });
            redBookPick(maxCount - picList.size());
    }

    private void redBookPick(int count) {
        ImagePicker.withCrop(new RedBookPresenter())//设置presenter
                .setMaxCount(count)//设置选择数量
                .showCamera(true)//设置显示拍照
                .setColumnCount(4)//设置列数
                .mimeTypes(getMimeTypes())//设置需要加载的文件类型
                // .filterMimeType(MimeType.GIF)//设置需要过滤掉的文件类型
                .assignGapState(false)
                .setFirstImageItem(picList.size() > 0 ? picList.get(0) : null)//设置上一次选中的图片
                .setVideoSinglePick(true)//设置视频单选
                .setSinglePickWithAutoComplete(false)
                .setMaxVideoDuration(120000L)//设置可选区的最大视频时长
                .setMinVideoDuration(5000L)
                .pick(this, new OnImagePickCompleteListener2() {
                    @Override
                    public void onPickFailed(PickerError error) {
                        Toast.makeText(UploadActivity_java.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {
                        //图片剪裁回调，主线 程
                        //注意：剪裁回调里的ImageItem中getCropUrl()才是剪裁过后的图片地址
                        for (ImageItem item : items) {
                            if (item.getCropUrl() != null && item.getCropUrl().length() > 0) {
                                item.path = item.getCropUrl();
                            }
                        }
                        picList.addAll(items);
                        refreshGridLayout();
                    }
                });
    }

    private void refreshGridLayout() {
        mGridLayout.setVisibility(View.VISIBLE);
        mGridLayout.removeAllViews();
        int num = picList.size();
        final int picSize = (getScreenWidth() - dp(20)) / 4;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(picSize, picSize);
        if (num >= maxCount) {
            mGridLayout.setVisibility(View.VISIBLE);
            for (int i = 0; i < num; i++) {
                RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.a_layout_pic_select, null);
                view.setLayoutParams(params);
                view.setPadding(dp(5), dp(5), dp(5), dp(5));
                setPicItemClick(view, i);
                mGridLayout.addView(view);
            }
        } else {
            mGridLayout.setVisibility(View.VISIBLE);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.add_pic));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(dp(5), dp(5), dp(5), dp(5));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startPick();
                }
            });
            for (int i = 0; i < num; i++) {
                RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.a_layout_pic_select, null);
                view.setLayoutParams(params);
                view.setPadding(dp(5), dp(5), dp(5), dp(5));
                setPicItemClick(view, i);
                mGridLayout.addView(view);
            }
            mGridLayout.addView(imageView);
        }
    }

    public void setPicItemClick(RelativeLayout layout, final int pos) {
        ImageView iv_pic = (ImageView) layout.getChildAt(0);
        ImageView iv_close = (ImageView) layout.getChildAt(1);
        Glide.with(this).load(picList.get(pos).path).into(iv_pic);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picList.remove(pos);
                refreshGridLayout();
            }
        });
        iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview(pos);
            }
        });
    }

    public int dp(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, this.getResources().getDisplayMetrics());
    }

    /**
     * 获得屏幕宽度
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    private Set<MimeType> getMimeTypes() {
        Set<MimeType> mimeTypes = new HashSet<>();
        mimeTypes.add(MimeType.JPEG);
        mimeTypes.add(MimeType.PNG);
        mimeTypes.add(MimeType.WEBP);
        mimeTypes.add(MimeType.MPEG);
        return mimeTypes;
    }
}
