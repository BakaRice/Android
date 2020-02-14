package com.example.helloworld.friendCircle


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
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
        val strArray: Array<String>? = arguments?.getParcelable<PhotoItem>("PHOTO")?.strURLArray
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

        img_head.setOnClickListener{
            Bundle().apply {
                putString("user_name",item?.userName.toString())
                img_head.findNavController().navigate(R.id.action_photoFragment_to_selfPageFragment,this)
            }
           // img_head.findNavController().navigate(R.id.action_photoFragment_to_selfPageFragment)
        }
        tV_UserName.setOnClickListener{
            Bundle().apply {
                putString("user_name",item?.userName.toString())
                img_head.findNavController().navigate(R.id.action_photoFragment_to_selfPageFragment,this)
            }
        }
    }


}

