package com.mayco.desafiospaceflight.ui.dialog

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayco.desafiospaceflight.network.response.NewsResponse
import com.mayco.desafiospaceflight.repository.NewsRepository

class DialogDetailsViewModel(private val repository: NewsRepository) : ViewModel() {


    var listener: DialogListener? = null

    private val _new = MutableLiveData<NewsResponse>()
    val news: LiveData<NewsResponse>
        get() = _new

    fun getClick() {
        _new.postValue(repository.getClick())
    }

    fun onClose(view: View) {
        listener!!.onClose()
    }
}