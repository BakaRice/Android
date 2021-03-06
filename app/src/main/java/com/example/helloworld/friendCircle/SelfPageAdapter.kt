package com.example.helloworld.friendCircle

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.helloworld.R
import kotlinx.android.synthetic.main.fragment_self_page_info_cell.view.*
import kotlinx.android.synthetic.main.gallery_cell.view.*
import kotlinx.android.synthetic.main.gallery_footer.view.*

class SelfPageAdapter(val selfPageViewModel: SelfPageViewModel, val name: String?) : ListAdapter<PhotoItem, SelfPagerViewHolder>(DIFFCALLBACK) {
    companion object {
        const val NORMAL_VIEW_TYPE = 0
        const val FOOTER_VIEW_TYPE = 1
        const val START_VIEW_TYPE = 2
    }

    var footerViewStatus = DATA_STATUS_CAM_LOAD_MORE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelfPagerViewHolder {
        val holder: RecyclerView.ViewHolder
        if (viewType == NORMAL_VIEW_TYPE) {
            holder = SelfPagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gallery_cell, parent, false))
            holder.itemView.setOnClickListener {
                Bundle().apply {
                    putParcelable("PHOTO", getItem(holder.adapterPosition))
                    holder.itemView.findNavController().navigate(R.id.action_selfPageFragment_to_photoFragment, this)
                }
            }
        } else if (viewType == START_VIEW_TYPE) {
            holder = SelfPagerViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.fragment_self_page_info_cell,
                            parent,
                            false).also {
                        (it.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
                    }
            )
        } else {
            holder = SelfPagerViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.gallery_footer,
                            parent,
                            false).also {
                        (it.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
                        it.setOnClickListener { item ->
                            item.progressBar.visibility = View.VISIBLE
                            item.textView4.text = "正在加载..."
                            selfPageViewModel.fetchData()
                        }
                    })

        }
        return holder
    }

    override fun onBindViewHolder(holder: SelfPagerViewHolder, position: Int) {
        if (position == itemCount - 1) {
            with(holder.itemView) {
                when (footerViewStatus) {
                    DATA_STATUS_CAM_LOAD_MORE -> {
                        progressBar.visibility = View.VISIBLE
                        textView4.text = "正在加载..."
                        isClickable = false;
                    }
                    DATA_STATUS_NO_MORE -> {
                        progressBar.visibility = View.GONE
                        textView4.text = "全部加载完毕"
                        isClickable = false
                    }
                    DATA_STATUS_NETWORK_ERROR -> {
                        progressBar.visibility = View.GONE
                        textView4.text = "网络故障，单击重试"
                        isClickable = true
                    }
                }
            }
            return
        }
        if (position == 0) {
            with(holder.itemView) {
                tV_UserName_self.text = name.toString()
                val mRequestOptions = RequestOptions.circleCropTransform()
                        //.diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                Glide.with(holder.itemView)
                        .load("https://pixabay.com/get/51e5dc45425ab108f5d084609629327e1c3ddee3524c704c7d2e7dd29248cc5c_640.jpg")
                        .apply(mRequestOptions)
                        .placeholder(R.drawable.ic_photo_gray_24dp)
                        .into(img_head_self)
            }
            return
        }
        val photoItem = getItem(position)
        with(holder.itemView) {
            shimmerLayoutCell.apply {
                setShimmerColor(0x55FFFFFF)
                setShimmerAngle(0)
                startShimmerAnimation()
            }
            textViewTitle.text = photoItem.title
            textViewUsername.text = getItem(position).userName
            textViewLikeNum.text = getItem(position).likeNum
        }
        Glide.with(holder.itemView)
                .load(getItem(position).strURLArray[0] ?: "http://119.3.215.150/")
                .placeholder(R.drawable.photoplace_holder)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false.also { holder.itemView.shimmerLayoutCell?.stopShimmerAnimation() }
                    }
                })
                .into(holder.itemView.imageView)

    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) FOOTER_VIEW_TYPE
        else if (position == 0) START_VIEW_TYPE
        else NORMAL_VIEW_TYPE
    }

    object DIFFCALLBACK : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }

}

class SelfPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)