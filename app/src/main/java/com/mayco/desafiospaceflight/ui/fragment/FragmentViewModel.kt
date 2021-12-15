package com.mayco.desafiospaceflight.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayco.desafiospaceflight.network.response.NewsResponse
import com.mayco.desafiospaceflight.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FragmentViewModel(private val repository: NewsRepository) : ViewModel(), CoroutineScope {

    var listener: FragmentListener? = null


    private val _newsList = MutableLiveData<List<NewsResponse>>()
    val newsList: LiveData<List<NewsResponse>>
        get() = _newsList


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initViewModel() {
        _newsList.postValue(repository.getNews())

        //listener!!.onSeacrh()


    }

    fun saveClick(newsResponse: NewsResponse) {

        repository.savaClick(newsResponse)

    }

    fun getNewsPage(num: Int) {
        launch {
            try {
                val response = repository.getNews(15, num)
                if (response.isSuccessful) {
                    repository.saveNews(response.body()!!)

                    val listApi = response.body()!!
                    val listCache = repository.getNews()
                    val listActual = arrayListOf<NewsResponse>()

                    listActual.addAll(listCache)
                    listActual.addAll(listApi)

                    _newsList.postValue(listActual)

                }

            } catch (e: Exception) {

                _newsList.postValue(arrayListOf())
            }
        }
    }


}