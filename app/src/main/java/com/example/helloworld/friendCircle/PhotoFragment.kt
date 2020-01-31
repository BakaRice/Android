package com.example.helloworld.friendCircle


import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.example.helloworld.R
import kotlinx.android.synthetic.main.fragment_photo.*

/**
 * A simple [Fragment] subclass.
 */
class PhotoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getParcelable<PhotoItem>("PHOTO")?.strURLArray = arrayOf(
                "https://pixabay.com/get/57e5d6404855a414f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
                "https://pixabay.com/get/55e2d3454853ad14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
                "https://pixabay.com/get/57e8d7414e51ab14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg")
        val strArray: Array<String?> = arrayOf(arguments?.getParcelable<PhotoItem>("PHOTO")?.fullUrl,
                "https://pixabay.com/get/55e2d3454853ad14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
                "https://pixabay.com/get/57e8d7414e51ab14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg")
        val photoAdapter = PhotoAdapter(strArray)
        recycleViewImgs.apply {
            adapter = photoAdapter
            //layoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recycleViewImgs)

        shimmerLayoutPhoto.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }

        Glide.with(requireContext())
                .load(arguments?.getParcelable<PhotoItem>("PHOTO")?.strURLArray?.get(0))
                .placeholder(R.drawable.ic_photo_gray_24dp)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false;
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false.also { shimmerLayoutPhoto?.stopShimmerAnimation() }
                    }

                })
                .into(photoView)
    }
}
