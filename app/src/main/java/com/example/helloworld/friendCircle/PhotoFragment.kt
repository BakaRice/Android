package com.example.helloworld.friendCircle


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.helloworld.R
import kotlinx.android.synthetic.main.fragment_photo.*


/**
 * A simple [Fragment] subclass.
 */
const val REQUEST_WRITE_EXTERNAL_STORAGE = 1;

class PhotoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val strArray: Array<String?> = arrayOf(arguments?.getParcelable<PhotoItem>("PHOTO")?.fullUrl,
                "https://pixabay.com/get/55e2d3454853ad14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
                "https://pixabay.com/get/57e8d7414e51ab14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg")
        val photoAdapter = PhotoAdapter(strArray)
        recycleViewImgs.apply {
            adapter = photoAdapter
            //layoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        //图片recycleView pager 滑动
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recycleViewImgs)

        shimmerLayoutPhoto.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }

        val item = arguments?.getParcelable<PhotoItem>("PHOTO")
        //假数据 content
        item?.content = "kotlin先阐述两个概念：\n" +
                "\"?\"加在变量名后，系统在任何情况不会报它的空指针异常。\n" +
                "\"!!\"加在变量名后，如果对象为null，那么系统一定会报异常！"

        tV_UserName.text = item?.userName

        val mRequestOptions = RequestOptions.circleCropTransform()
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
        Glide.with(requireContext())
                .load(item?.previewUrl)
                .apply(mRequestOptions)
                .placeholder(R.drawable.ic_photo_gray_24dp)
                .into(img_head)
        tV_Title.text = item?.title
        tV_content.text = item?.content
        if (item?.isLike == false) imgBut_Like.setImageResource(R.drawable.unattention)
        else imgBut_Like.setImageResource(R.drawable.attention)
        imgBut_Like.setOnClickListener {
            if (item?.isLike == false) {
                imgBut_Like.setImageResource(R.drawable.attention)
            } else {
                imgBut_Like.setImageResource(R.drawable.unattention)
            }
        }
    }


}

