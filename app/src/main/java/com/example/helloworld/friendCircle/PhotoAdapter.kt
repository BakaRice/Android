package com.example.helloworld.friendCircle

import android.graphics.drawable.Drawable
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.helloworld.R
import kotlinx.android.synthetic.main.gallery_photo_cell.view.*

class PhotoAdapter(private val myDataset: Array<String?>) : RecyclerView.Adapter<PhotoAdapter.PhotoCellHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoCellHolder {
        val holder = PhotoCellHolder(LayoutInflater.from(parent.context).inflate(R.layout.gallery_photo_cell, parent, false))
        return holder;
    }

    override fun onBindViewHolder(holder: PhotoCellHolder, position: Int) {
        holder.itemView.shimmerLayoutPhotoCell.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }

        Glide.with(holder.itemView)
                .load(myDataset[position])
                .placeholder(R.drawable.ic_photo_gray_24dp)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false.also { holder.itemView.shimmerLayoutPhotoCell?.stopShimmerAnimation() }
                    }

                }).into(holder.itemView.photo_cell)

    }

    class PhotoCellHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return myDataset.size
    }
}




