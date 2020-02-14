package com.example.helloworld.friendCircle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.helloworld.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.fragment_self_page.*

class SelfPageFragment : Fragment() {

    companion object {
        fun newInstance() = SelfPageFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_self_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //获取传来的 name
        val name = arguments?.getString("user_name")
        //Toast.makeText(activity, name, Toast.LENGTH_SHORT).show()
        val selfPageViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(SelfPageViewModel::class.java)
        if (name != null) {
            selfPageViewModel.keyWords = "程耳"
            selfPageViewModel.resetQuery()
        }

        val selfPageAdapter = SelfPageAdapter(selfPageViewModel)
        recyclerView_self.apply {
            adapter = selfPageAdapter
            selfPageViewModel.resetQuery()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        tV_UserName_self.text = name.toString()

        

        selfPageViewModel.photoListLive.observe(viewLifecycleOwner, Observer {
            if (selfPageViewModel.needToScrollToTop) {
                recyclerView_self.scrollToPosition(0)
                selfPageViewModel.needToScrollToTop = false
            }
            selfPageAdapter.submitList(it)
            swipeLayoutSelf.isRefreshing = false
        })

        selfPageViewModel.dataStatusLive.observe(viewLifecycleOwner, Observer {
            selfPageAdapter.footerViewStatus = it
            selfPageAdapter.notifyItemChanged(selfPageAdapter.itemCount - 1)
            if (it == DATA_STATUS_NETWORK_ERROR) swipeLayoutGallery.isRefreshing = false
        })

        swipeLayoutSelf.setOnRefreshListener {
            selfPageViewModel.resetQuery()
        }

        recyclerView_self.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView_self: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView_self, dx, dy)
                if (dy < 0) return
                val layoutManager = recyclerView_self.layoutManager as StaggeredGridLayoutManager
                val intArray = IntArray(2)
                layoutManager.findLastVisibleItemPositions(intArray)
                if (intArray[0] == selfPageAdapter.itemCount - 1) {
                    selfPageViewModel.fetchData()
                }
            }
        })

    }

}
