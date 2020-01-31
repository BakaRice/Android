package com.example.helloworld.friendCircle


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.SnapHelper

import com.example.helloworld.R
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val galleryAdapter = GalleryAdapter()
        recycleView.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        val galleryViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(GalleryViewModel::class.java)
        galleryViewModel.photoListLive.observe(this, Observer {
            galleryAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing = false
        })

        galleryViewModel.photoListLive.value ?: galleryViewModel.fetchData()

        swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.fetchData()
        }
    }

}
