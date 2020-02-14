package com.example.helloworld.friendCircle

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import kotlin.math.ceil


class SelfPageViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    private val _dataStatusLive = MutableLiveData<Int>()
    val photoListLive: LiveData<List<PhotoItem>>
        get() = _photoListLive
    val dataStatusLive: LiveData<Int>
        get() = _dataStatusLive


    var needToScrollToTop = true
    private val perPage = 5
    var keyWords = ""
    private var currentPage = 0 //自己的api默认是从0开始 网上调用的从1开始
    private var totalPage = 1
    private var currentKey = ""
    private var isNewQuery = true
    private var isLoading = false

//    init {
//        resetQuery()
//    }

    fun resetQuery() {
        currentPage = 0 //自己的api默认是从0开始 网上调用的从1开始
        totalPage = 1
        currentKey = keyWords
        isNewQuery = true
        needToScrollToTop = true
        fetchData()
    }

    fun fetchData() {
        if (isLoading) return
        if (currentPage > totalPage) {
            _dataStatusLive.value = DATA_STATUS_NO_MORE
            return
        }
        isLoading = true
        val stringRequest = StringRequest(
                Request.Method.GET,
                getUrl(),
                Response.Listener {
                    with(Gson().fromJson(it, Pixabay::class.java)) {
                        totalPage = ceil(totalHits.toDouble() / perPage).toInt()
                        if (isNewQuery) {
                            _photoListLive.value = hits.toList()
                        } else {
                            _photoListLive.value = arrayListOf(_photoListLive.value!!, hits.toList()).flatten()
                        }
                        _dataStatusLive.value = DATA_STATUS_CAM_LOAD_MORE
                        isLoading = false
                        isNewQuery = false
                        currentPage++
                    }
                },
                Response.ErrorListener {
                    Log.d("loading", it.toString())
                    _dataStatusLive.value = DATA_STATUS_NETWORK_ERROR
                    isLoading = false
                }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    private fun getUrl(): String {
//        return "https://pixabay.com/api/?key=12472743-874dc01dadd26dc44e0801d61&q=${currentKey}&per_page=${perPage}&page=${currentPage}"
        return "http://119.3.215.150:8080/moments/followbypages/username=${currentKey}&per_page=${perPage}&page=${currentPage}"
    }

}