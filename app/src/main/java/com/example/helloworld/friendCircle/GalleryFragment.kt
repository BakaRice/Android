package com.example.helloworld.friendCircle


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.helloworld.R
import com.example.helloworld.friendCircle.upload.UploadActivity_java
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }


    @SuppressLint("ShowToast", "FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val galleryViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(GalleryViewModel::class.java)
        val galleryAdapter = GalleryAdapter(galleryViewModel)
        recycleView.apply {
            adapter = galleryAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        galleryViewModel.photoListLive.observe(this, Observer {
            if (galleryViewModel.needToScrollToTop) {
                recycleView.scrollToPosition(0)
                galleryViewModel.needToScrollToTop = false
            }
            galleryAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing = false
        })
        galleryViewModel.dataStatusLive.observe(this, Observer {
            galleryAdapter.footerViewStatus = it
            galleryAdapter.notifyItemChanged(galleryAdapter.itemCount - 1)
            if (it == DATA_STATUS_NETWORK_ERROR) swipeLayoutGallery.isRefreshing = false

        })

        swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.resetQuery()
        }
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) return
                val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                val intArray = IntArray(2)
                layoutManager.findLastVisibleItemPositions(intArray)
                if (intArray[0] == galleryAdapter.itemCount - 1) {
                    galleryViewModel.fetchData()
                }
            }
        })

        tV_FriendCircle_upload.setOnClickListener {
            val intent = Intent(activity, UploadActivity_java::class.java)
            startActivity(intent)
        }
    }

}
