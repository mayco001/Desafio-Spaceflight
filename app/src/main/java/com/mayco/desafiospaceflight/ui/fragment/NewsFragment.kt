package com.mayco.desafiospaceflight.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayco.desafiospaceflight.databinding.FragmentNewsBinding
import com.mayco.desafiospaceflight.network.response.NewsResponse
import com.mayco.desafiospaceflight.ui.adapter.NewsRecyclerAdapter
import com.mayco.desafiospaceflight.ui.dialog.DialogDetails
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private val viewModel: FragmentViewModel by viewModel()
    lateinit var binding: FragmentNewsBinding

    private var adapterNews = NewsRecyclerAdapter(this::getNews)

    private var numPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {

        binding.newsList.layoutManager = LinearLayoutManager(context)
        binding.newsList.adapter = adapterNews

        binding.newsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!canScrollVertically(
                        recyclerView,
                        1
                    ) && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    numPage += 15
                    viewModel.getNewsPage(numPage)
                }
            }
        })


    }

    private fun initViewModel() {
        binding.viewmodel = viewModel
        viewModel.initViewModel()

        viewModel.newsList.observe(requireActivity(), Observer<List<NewsResponse>> {
            adapterNews.items = it
            adapterNews.newList = it
        })

    }

    private fun getNews(obj: NewsResponse) {
        viewModel.saveClick(obj)
        DialogDetails().show(requireActivity().supportFragmentManager, "Details")
    }

//    override fun onSeacrh() {
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                adapterNews.filter.filter(query)
//                return true
//    }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapterNews.filter.filter(newText)
//                return true
//            }
//
//        })
//    }


}